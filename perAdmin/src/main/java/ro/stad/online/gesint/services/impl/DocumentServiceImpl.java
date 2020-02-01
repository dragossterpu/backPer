package ro.stad.online.gesint.services.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.SortOrder;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.exceptions.GesintException;
import ro.stad.online.gesint.persistence.entities.Alerta;
import ro.stad.online.gesint.persistence.entities.DocumentBlob;
import ro.stad.online.gesint.persistence.entities.Documentul;
import ro.stad.online.gesint.persistence.entities.TipDocument;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.persistence.repositories.DocumentRepository;
import ro.stad.online.gesint.persistence.repositories.TipDocumentRepository;
import ro.stad.online.gesint.services.DocumentService;
import ro.stad.online.gesint.web.beans.gd.FiltruDocument;

/**
 *
 * Implementarea serviciului de documente.
 *
 * @author STAD
 *
 */

@Service("documentService")
@Transactional
public class DocumentServiceImpl implements DocumentService {

        /**
         * SessionFactory
         */
        @Autowired
        private SessionFactory sessionFactory;

        /**
         * Repository de documente.
         */
        @Autowired
        private DocumentRepository documentoRepository;

        /**
         * Repositorio de tipo de documento.
         */
        @Autowired
        private TipDocumentRepository tipoDocumentoRepository;

        /**
         * Devuelve los documentos que corresponden a un tipo de documento.
         * @param tipoDocumento Nombre del tipo de documento
         * @return Listado de documentos
         */
        @Override
        public List<Documentul> cautaNumeTipDocument(final String tipoDocumento) {
                return documentoRepository.cautaNumeTipDocument(tipoDocumento);
        }

        /**
         * Consulta en base de datos en base a los parámetros recibidos. La consulta se hace paginada.
         *
         * @param first Primer elemento a devolver de la búsqueda
         * @param pageSize Número máximo de registros a mostrar
         * @param sortField Campo por el cual se ordena la búsqueda
         * @param sortOrder Sentido de la ordenación
         * @param busquedaDocumento Objeto que contiene los criterios de búsqueda
         * @return Lista de los documentos que corresponden a los criterios recibidos
         *
         */
        @Override
        public List<Documentul> cautareDocumentCriteria(final int first, final int pageSize, final String sortField,
                        final SortOrder sortOrder, final FiltruDocument busquedaDocumento) {
                final Session session = sessionFactory.openSession();
                final Criteria criteriaDocumento = session.createCriteria(Documentul.class, "documento");
                creaCriteria(busquedaDocumento, criteriaDocumento);
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (busquedaDocumento.getUsuario() != null) {
                        criteriaDocumento.createAlias("inspeccion", "inspecciones");
                        criteriaDocumento.add(Restrictions.eq("inspecciones.id",
                                        busquedaDocumento.getUsuario().getUsername()));
                }
                pregatestePaginacreOrdenCriteria(criteriaDocumento, first, pageSize, sortField, sortOrder, "id");
                @SuppressWarnings("unchecked")
                final List<Documentul> listado = criteriaDocumento.list();
                session.close();
                return listado;
        }

        /**
         * Recupera un tipo de documento a partir de su nombre.
         * @param nombre nombre del tipo
         * @return tipo de documento
         */
        @Override
        public TipDocument cautareTipDocumentNume(final String nombre) {
                return tipoDocumentoRepository.findByNume(nombre);
        }

        /**
         * Primeste un fișier UploadedFile din care se recupereaza datele pentru a genera un Document care va fi stocat
         * în baza de date. Returnează documentul inregistrat.
         * @param file fisier care se va stoca in BBDD
         * @param tip tipul de document
         * @return Document document stocat in bbdd
         * @throws GesintException Excepție posibilă
         *
         */
        @Override
        public Documentul cargaDocumento(final UploadedFile file, final TipDocument tip, final Utilizator utilizator)
                        throws GesintException {
                try {
                        final Documentul documentul = documentoRepository.save(creareDocument(file, tip, utilizator));
                        documentul.getNume();
                        utilizator.getUsername();
                        return documentul;
                }
                catch (DataAccessException | IOException ex) {
                        throw new GesintException(ex);
                }
        }

        /**
         * Recibe un archivo UploadedFile y los datos necesarios para general un Documentul pero no lo almacena en base
         * de datos. Sólo deja el objeto preparado para guardarlo.
         *
         * @param file fichero a cargar en BDD
         * @param tipo tipo de documentp
         * @param inspeccion inspección asociada al documento
         * @return documento cargado en base de datos
         * @throws ProgesinException excepción lanzada
         */
        @Override
        public Documentul incarcareDocumentFaraSalvare(final UploadedFile file, final TipDocument tip,
                        final Utilizator utilizator) throws GesintException {
                try {
                        return creareDocument(file, tip, utilizator);
                }
                catch (final IOException ex) {
                        throw new GesintException(ex);
                }
        }

        /**
         * Añade al criteria los parámetros de búsqueda.
         * @param busquedaDocumento Objeto que contiene los parámetros de búsqueda
         * @param criteria Criteria al que se añadirán los parámetros.
         */
        private void creaCriteria(final FiltruDocument filtruDocument, final Criteria criteria) {

                if (filtruDocument.getFechaDesde() != null) {
                        criteria.add(Restrictions.ge(Constante.DATECREATE, filtruDocument.getFechaDesde()));
                }
                if (filtruDocument.getFechaHasta() != null) {
                        final Date fechaHasta = new Date(
                                        filtruDocument.getFechaHasta().getTime() + TimeUnit.DAYS.toMillis(1));
                        criteria.add(Restrictions.le(Constante.DATECREATE, fechaHasta));
                }
                if (filtruDocument.getNombre() != null) {
                        criteria.add(Restrictions.ilike("nume", filtruDocument.getNombre(), MatchMode.ANYWHERE));
                }
                if (filtruDocument.getTipDocument() != null) {
                        criteria.add(Restrictions.eq("tipDocument", filtruDocument.getTipDocument()));
                }
                if (filtruDocument.isEliminat()) {
                        criteria.add(Restrictions.isNotNull(Constante.DATEDELETED));
                }
                else {
                        criteria.add(Restrictions.isNull(Constante.DATEDELETED));
                }
                if (filtruDocument.getDescripcion() != null) {
                        criteria.add(Restrictions.ilike("descriere", filtruDocument.getDescripcion(),
                                        MatchMode.ANYWHERE));
                }
                criteriaMateriaIndexada(criteria, filtruDocument.getMateriaIndexada());
        }

        /**
         * Crea el documento.
         * @param file Fichero subido por el usuario.
         * @param tipo Tipo de documento.
         * @param inspeccion Inspección a la que se asocia.
         * @return Documentul generado
         * @throws DataAccessException Excepción SQL
         * @throws IOException Excepción entrada/salida
         */
        private Documentul creareDocument(final UploadedFile file, final TipDocument tip, final Utilizator utilizator)
                        throws IOException {
                final Documentul docu = new Documentul();
                docu.setNume(file.getFileName());
                docu.setTipDocument(tip);
                final byte[] fileBlob = StreamUtils.copyToByteArray(file.getInputstream());
                final DocumentBlob doc = new DocumentBlob();
                doc.setFichero(fileBlob);
                docu.setFisier(doc);
                docu.setTipContinut(file.getContentType());
                return docu;
        }

        /**
         * Añade al criteria el filtro de la materia indexada introducida en el formulario.
         * @param criteria Criteria al que se añadirán los parámetros.
         * @param materiaIndexada materia indexada introducida en el filtro (separada por comas)
         */
        private void criteriaMateriaIndexada(final Criteria criteria, final String materiaIndexada) {
                if (materiaIndexada != null) {
                        final String[] cheie = materiaIndexada.split(",");
                        final Criterion[] cheieOr = new Criterion[cheie.length];
                        for (int i = 0; i < cheie.length; i++) {
                                cheieOr[i] = Restrictions.ilike("materiaIndexada", cheie[i].trim(), MatchMode.ANYWHERE);
                        }
                        criteria.add(Restrictions.or(cheieOr));
                }
        }

        /**
         * Ștergeți o serie de documente din baza de date. Documentul care trebuie șters este trecut ca parametru.
         * @param entity Documentul care trebuie șters
         *
         */
        @Override
        public void delete(final Documentul entity) {
                documentoRepository.delete(entity);
        }

        /**
         * Recibe un documento como parámetro y devuelve un stream para realizar la descarga. Primiți un document ca
         * parametru și returnați un stream(flux) pentru a efectua descărcarea.
         * @param entity Document pentru descărcare.
         * @return DefaultStreamedContent Descărcați fluxul
         * @throws GesintException Excepție posibilă
         */
        @Override
        public DefaultStreamedContent descarcareDocument(final Documentul entity) throws GesintException {
                final Documentul docu = documentoRepository.findOne(entity.getId());
                DefaultStreamedContent streamDocument;
                if (docu != null) {
                        final InputStream stream = new ByteArrayInputStream(docu.getFisier().getFichero());
                        streamDocument = new DefaultStreamedContent(stream, entity.getTipContinut(), docu.getNume());
                }
                else {
                        throw new GesintException(new Exception("A apărut o eroare la descărcarea documentului"));
                }
                return streamDocument;
        }

        /**
         * Primește id-ul unui document ca parametru și returnează un flux pentru efectuarea descărcării.
         * @param id Document pentru descărcare.
         * @return DefaultStreamedContent Descărcați fluxul
         * @throws GesintException Excepție posibilă
         */
        @Override
        public DefaultStreamedContent descarcareDocument(final Long id) throws GesintException {
                final Documentul entity = documentoRepository.findById(id);
                DefaultStreamedContent streamDocument;
                if (entity != null) {
                        final InputStream stream = new ByteArrayInputStream(entity.getFisier().getFichero());
                        streamDocument = new DefaultStreamedContent(stream, entity.getTipContinut(), entity.getNume());
                }
                else {
                        throw new GesintException(new Exception("A apărut o eroare la descărcarea documentului"));
                }
                return streamDocument;

        }

        /**
         * Căutați toate documentele care au fost eliminate logic.
         * @return Lista documentelor selectate
         */
        @Override
        public List<Documentul> findByFechaBajaIsNotNull() {
                return documentoRepository.findByDateDeletedIsNotNull();
        }

        /**
         * Căutați toate documentele care nu au eliminare logică.
         * @return Lista documentelor selectate
         */
        @Override
        public List<Documentul> findByFechaBajaIsNull() {
                return documentoRepository.findByDateDeletedIsNull();
        }

        /**
         * Returnează un document localizat după id-ul său.
         * @param id Identificatorul documentului
         * @return Documentul
         */
        @Override
        public Documentul findOne(final Long id) {
                return documentoRepository.findOne(id);
        }

        /**
         * Consulta el número de registros en base de datos que corresponden a los criterios de búsqueda.
         * @param busqueda Objeto que contiene los criterios de búsqueda
         * @return número de registros correspondientes a la búsqueda
         */
        @Override
        public int getCounCriteria(final FiltruDocument cautare) {
                final Session session = sessionFactory.openSession();
                final Criteria criteria = session.createCriteria(Documentul.class, "document");
                creaCriteria(cautare, criteria);
                criteria.setProjection(Projections.rowCount());
                final Long cnt = (Long) criteria.uniqueResult();
                session.close();
                return Math.toIntExact(cnt);
        }

        /**
         * Devuelve la lista de tipos de documentos.
         * @return lista de tipos de documentos
         */
        @Override
        public List<TipDocument> listaTipuriDocumente() {
                return (List<TipDocument>) tipoDocumentoRepository.findAll();
        }

        /**
         * Devuelve el nombre del fichero contenido en el objeto Documentul.
         * @param documento del cual quiere extraerse el nombre del fichero contenido
         * @return nombre del fichero
         */
        @Override
        public String obtieneNumeFisier(final Documentul document) {
                final Documentul docu = documentoRepository.findById(document.getId());
                return docu.getNume();
        }

        /**
         * Prepara el criteria pasado como parámetro para la paginación de Primefaces.
         * @param criteria criteria a configurar
         * @param first primer elemento
         * @param pageSize tamaño de cada página de resultados
         * @param sortField campo por el que se ordenan los resultados
         * @param sortOrder sentido de la ordenacion (ascendente/descendente)
         * @param defaultField campo de ordenación por defecto
         */
        @Override
        public void pregatestePaginacreOrdenCriteria(final Criteria criteria, final int first, final int pageSize,
                        final String sortField, final SortOrder sortOrder, final String defaultField) {
                criteria.setFirstResult(first);
                criteria.setMaxResults(pageSize);

                if ((sortField != null) && sortOrder.equals(SortOrder.ASCENDING)) {
                        criteria.addOrder(Order.asc(sortField));
                }
                else if ((sortField != null) && sortOrder.equals(SortOrder.DESCENDING)) {
                        criteria.addOrder(Order.desc(sortField));
                }
                else if (sortField == null) {
                        criteria.addOrder(Order.asc(defaultField));
                }
        }

        /**
         * Recupera un documento de la papelera.
         * @param documento Es el documento a recuperar de la papelera
         */
        @Override
        public void recupereazaDocument(final Documentul document) {
                document.setDateDeleted(null);
                document.setUserDeleted(null);
                save(document);
        }

        /**
         * Salvați un document în baza de date. Ca parametru, primește documentul care trebuie salvat și returnează
         * documentul salvat.
         * @param entity Documentul Document pentru salvare
         * @return documento documentul salvat
         */
        @Override
        @Transactional(readOnly = false)
        public Documentul save(final Documentul entity) {
                return documentoRepository.save(entity);
        }

        /**
         * Salvați o serie de documente în baza de date. Ca parametru, primește documentele care trebuie salvate și
         * returnează documentele salvate.
         * @param entities Documente de salvat
         * @return lista documentelor salvate
         *
         */
        @Override
        public Iterable<Documentul> save(final Iterable<Documentul> entities) {
                return documentoRepository.save(entities);
        }

        /**
         * Elimina todos los documentos almacenados en la papelera.
         */
        @Override
        public List<Documentul> golesteCosulGunoi() {
                final List<Documentul> listaEliminar = documentoRepository.findByDateDeletedIsNotNull();
                documentoRepository.delete(listaEliminar);
                return listaEliminar;
        }

        /**
         * Returnează documentele care corespund alertei.
         * @param alerta Alerta
         * @return Lista documentelor
         */
        @Override
        public List<Documentul> findByAlerta(final Alerta alerta) {
                final List<Documentul> listaEliminar = documentoRepository.findByAlerta(alerta);
                documentoRepository.delete(listaEliminar);
                return listaEliminar;
        }
}