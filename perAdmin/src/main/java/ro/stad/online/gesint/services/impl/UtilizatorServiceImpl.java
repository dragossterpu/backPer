package ro.stad.online.gesint.services.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.model.filters.FiltruEchipa;
import ro.stad.online.gesint.model.filters.FiltruUtilizator;
import ro.stad.online.gesint.persistence.entities.Localitate;
import ro.stad.online.gesint.persistence.entities.ParamEchipa;
import ro.stad.online.gesint.persistence.entities.Provincia;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.persistence.entities.pojo.AnNumarPojo;
import ro.stad.online.gesint.persistence.repositories.UtilizatorRepository;
import ro.stad.online.gesint.services.LocalitateService;
import ro.stad.online.gesint.services.ParamEchipaService;
import ro.stad.online.gesint.services.ProvinciaService;
import ro.stad.online.gesint.services.UtilizatorService;
import ro.stad.online.gesint.util.FacesUtilities;
import ro.stad.online.gesint.util.Utilitati;
import ro.stad.online.gesint.util.UtilitatiCriteria;

/**
 * Implementarea serviciului de utilizatori.
 *
 * @author STAD
 *
 */

@Transactional
@Service
public class UtilizatorServiceImpl implements UtilizatorService {

        /**
         * Repository de Utilizator.
         */
        @Autowired
        private UtilizatorRepository utilizatorRepository;

        /**
         * SessionFactory.
         */
        @Autowired
        private transient SessionFactory sessionFactory;

        /**
         * Session.
         */

        private Session session;

        /**
         * Variabila utilizata pentru a injecta serviciul provinciei.
         *
         */
        @Autowired
        private ProvinciaService provinciaService;

        /**
         * Variabila utilizata pentru a injecta serviciul functilor.
         *
         */
        @Autowired
        private ParamEchipaService paramEchipaService;

        /**
         * Variabila utilizata pentru a injecta serviciul localitatilor.
         *
         */
        @Autowired
        private LocalitateService localitateService;

        /**
         * Utilidades.
         */
        @Autowired
        private Utilitati utilitati;

        /**
         * Stabilirea unei liste de utilizatori pentru eliminarea logica.
         * @param listaUtilizatori Lista de usuarios a modificar
         * @return lista de usuarios modificada
         */
        @Override
        public List<Utilizator> bajaLogica(final List<String> listaUtilizatori) {
                final Date fecha = new Date();
                final List<Utilizator> listaSalvare = utilizatorRepository.findByUsernameIn(listaUtilizatori);
                for (final Utilizator utilizator : listaSalvare) {
                        utilizator.setDateDeleted(fecha);
                        utilizator.setUserDeleted(utilitati.getUsuarioLogado().getUsername());
                }
                return (List<Utilizator>) utilizatorRepository.save(listaSalvare);
        }

        /**
         * Devuelve una lista con nombres de los usuarios que estén presentes en la lista y en BBDD.
         * @param listaNombres lista de nombres que se buscarán en bbdd
         * @return Lista de nombres de usuarios presentes en la BBDD
         */
        @Override
        public List<String> cautareListaNumeUtilizator(final List<String> listaNombres) {
                return utilizatorRepository.findUsernamesByUsername(listaNombres);
        }

        /**
         * Busca el usuario por criteria sin paginar.
         * @param filtruUtilizator AnNumarPojo
         * @return lista de usuarios
         */
        @Override
        public List<Utilizator> cautareUtilizator(final FiltruUtilizator filtruUtilizator) {
                try {
                        session = sessionFactory.openSession();
                        final Criteria criteria = session.createCriteria(Utilizator.class);
                        creaCriteria(filtruUtilizator, criteria);
                        return criteria.list();
                }
                finally {
                        session.close();
                }
        }

        /**
         * Busca usuarios con los parametros de búsqueda.
         * @param filtruUtilizator AnNumarPojo
         * @param sortOrder SortOrder
         * @param sortField String
         * @param pageSize int
         * @param first int
         *
         * @return List<User>
         *
         *
         */
        @SuppressWarnings("unchecked")
        @Override
        public List<Utilizator> cautareUtilizatorCriteria(final int first, final int pageSize, final String sortField,
                        final SortOrder sortOrder, final FiltruUtilizator filtruUtilizator) {
                try {
                        this.session = this.sessionFactory.openSession();
                        final Criteria criteria = this.session.createCriteria(Utilizator.class, "user");
                        criteria.setFirstResult(first);
                        criteria.setMaxResults(pageSize);
                        if (sortField != null) {
                                if (sortOrder.equals(SortOrder.ASCENDING)) {
                                        criteria.addOrder(Order.asc(sortField));
                                }
                                else if (sortOrder.equals(SortOrder.DESCENDING)) {
                                        criteria.addOrder(Order.desc(sortField));
                                }
                        }
                        else {
                                criteria.addOrder(Order.desc(Constante.DATECREATE));
                        }
                        List<Utilizator> usuariosList;
                        creaCriteria(filtruUtilizator, criteria);

                        usuariosList = criteria.list();
                        this.session.close();
                        return usuariosList;
                }
                finally {
                        closeSession();
                }
        }

        /**
         * Busca usuarios utilizando criteria.
         *
         * @param filtruUtilizator AnNumarPojo
         * @return List<User>
         */
        @Override
        public List<Utilizator> cautareUtilizatorCriteria(final FiltruUtilizator filtruUtilizator) {
                try {
                        this.session = this.sessionFactory.openSession();
                        final Criteria criteria = this.session.createCriteria(Utilizator.class);
                        final List<Utilizator> usuariosList = gestionarCriteriaUsuarios(filtruUtilizator, criteria);
                        return usuariosList;
                }
                finally {
                        closeSession();
                }
        }

        /**
         * Busca usuarios utilizando criteria.
         *
         * @param usuarioBusqueda AnNumarPojo
         * @return List<User>
         */
        @Override
        public List<Utilizator> cautareUtilizatorCriteriaLocal(final FiltruEchipa filtruEchipa) {
                try {
                        this.session = this.sessionFactory.openSession();
                        final Criteria criteria = this.session.createCriteria(Utilizator.class);
                        criteria.addOrder(Order.asc("rank"));
                        final List<Utilizator> usuariosList = gestionarCriteriaUsuariosLocal(filtruEchipa, criteria);
                        return usuariosList;
                }
                finally {
                        closeSession();
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
         * @throws IOException
         * @throws ProgesinException excepción lanzada
         */
        @Override
        public Utilizator incarcareImaginaFaraStocare(final byte[] file, final Utilizator user) throws IOException {
                return creareImagine(file, user);
        }

        /**
         * Incarcam datele personale ale utilizatorului
         * @param provincia
         * @param nuevaLocalidad
         * @param usuario
         * @return usuario
         */
        private void incarcareDatePersonaleUser(final byte[] fileBlob, final Utilizator usuario) {
                usuario.setPhoto(fileBlob);
        }

        /**
         * Manejo y cierre de la sesión.
         */
        private void closeSession() {
                if (this.session != null && this.session.isOpen()) {
                        try {
                                this.session.close();
                        }
                        catch (final DataAccessException e) {
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                Constante.EROAREMESAJ, Constante.DESCEROAREMESAJ);
                        }
                }
        }

        /**
         * @param filtruUtilizator
         * @param criteria
         */
        private void creaCriteria(final FiltruUtilizator filtruUtilizator, final Criteria criteria) {
                UtilitatiCriteria.setCondicionCriteriaFechaMayor(filtruUtilizator.getDateFrom(), criteria,
                                Constante.DATECREATE);
                UtilitatiCriteria.setCondicionCriteriaFechaMenorIgual(filtruUtilizator.getDateUntil(), criteria,
                                Constante.DATECREATE);
                UtilitatiCriteria.setCondicionCriteriaCadenaLike(filtruUtilizator.getName(), criteria, "name");
                UtilitatiCriteria.setCondicionCriteriaCadenaLike(filtruUtilizator.getLastName(), criteria, "lastName");
                UtilitatiCriteria.setCondicionCriteriaIgualdadBoolean(filtruUtilizator.getValidated(), criteria,
                                Constante.VALIDAT);
                UtilitatiCriteria.setCondicionCriteriaIgualdadBoolean(false, criteria, "destinatarExtern");
                UtilitatiCriteria.setCondicionCriteriaCadenaLike(filtruUtilizator.getIdCard(), criteria, "idCard");
                UtilitatiCriteria.setCondicionCriteriaCadenaLike(filtruUtilizator.getEmail(), criteria, "email");
                UtilitatiCriteria.setCondicionCriteriaIgualdadEnum(filtruUtilizator.getRole(), criteria, "role");
                UtilitatiCriteria.setCondicionCriteriaIgualdadEnum(filtruUtilizator.getSex(), criteria, "sex");
                UtilitatiCriteria.setCondicionCriteriaIgualdadEnum(filtruUtilizator.getCivilStatus(), criteria,
                                "civilStatus");
                if (filtruUtilizator.getTypeLocality() != null) {
                        criteria.createAlias("locality", "locality");
                        UtilitatiCriteria.setCondicionCriteriaIgualdadEnum(filtruUtilizator.getTypeLocality(), criteria,
                                        "locality.typelocality");
                }
                UtilitatiCriteria.setCondicionCriteriaIgualdadEnum(filtruUtilizator.getEducation(), criteria,
                                "education");
                if (filtruUtilizator.getIdProvincia() != null && !filtruUtilizator.getIdProvincia().equals("")) {
                        criteria.add(Restrictions.eq("province",
                                        provinciaService.findById(filtruUtilizator.getIdProvincia())));
                }
                if (filtruUtilizator.getIdFunctia() != null) {
                        criteria.add(Restrictions.eq("team",
                                        paramEchipaService.findById(filtruUtilizator.getIdFunctia())));
                }
                if (filtruUtilizator.getIdLocalidad() != null) {
                        criteria.add(Restrictions.eq("locality",
                                        localitateService.findById(filtruUtilizator.getIdLocalidad())));
                }
                final ParamEchipa functia = paramEchipaService.findById(30L);
                criteria.add(Restrictions.eq("team", functia));
                UtilitatiCriteria.setCondicionCriteriaIgualdadEnum(filtruUtilizator.getEducation(), criteria,
                                "education");
                // criteria.add(Restrictions.isNull(Constante.FECHABAJA));
                extractUserEliminado(filtruUtilizator, criteria);
        }

        /**
         * @param usuarioBusqueda
         * @param criteria
         */
        private void creaCriteriaLocal(final FiltruEchipa filtruEchipa, final Criteria criteria) {
                UtilitatiCriteria.setCondicionCriteriaFechaMayor(filtruEchipa.getDateFrom(), criteria,
                                Constante.DATECREATE);
                UtilitatiCriteria.setCondicionCriteriaFechaMenorIgual(filtruEchipa.getDateUntil(), criteria,
                                Constante.DATECREATE);
                UtilitatiCriteria.setCondicionCriteriaCadenaLike(filtruEchipa.getName(), criteria, "name");
                UtilitatiCriteria.setCondicionCriteriaCadenaLike(filtruEchipa.getLastName(), criteria, "lastName");
                UtilitatiCriteria.setCondicionCriteriaCadenaLike(filtruEchipa.getEmail(), criteria, "email");
                UtilitatiCriteria.setCondicionCriteriaIgualdadEnum(filtruEchipa.getRole(), criteria, "role");

                if (filtruEchipa.getIdProvincia() != null && !filtruEchipa.getIdProvincia().equals(Constante.SPATIU)) {
                        criteria.add(Restrictions.eq("province",
                                        provinciaService.findById(filtruEchipa.getIdProvincia())));
                }
                if (filtruEchipa.getIdFunctia() != null) {
                        criteria.add(Restrictions.eq("team", paramEchipaService.findById(filtruEchipa.getIdFunctia())));
                }
                // Daca nu sa ales o functie le cautam pe toate din conducerea locala
                if (!filtruEchipa.getListTeam().isEmpty()) {
                        criteria.add(Restrictions.in("team", filtruEchipa.getListTeam()));
                }

        }

        /**
         * Crea el documento.
         *
         * @param file Fichero subido por el usuario.
         * @param user a la que se asocia.
         * @return user generado
         * @throws DataAccessException Excepción SQL
         * @throws IOException Excepción entrada/salida
         */
        private Utilizator creareImagine(final byte[] file, final Utilizator user) throws IOException {
                incarcareDatePersonaleUser(file, user);
                utilizatorRepository.save(user);
                return user;
        }

        /**
         * Borrado de usuario por username.
         * @param username Utilizator
         */
        @Override
        @Transactional(readOnly = false)
        public void delete(final Utilizator username) {
                this.utilizatorRepository.delete(username);
        }

        /**
         * Establece una lista de usuarios como inactivos.
         * @param listaUtilizatori Lista de usuarios a modificar
         * @return lista de usuarios modificada
         */
        @Override
        public List<Utilizator> desactivar(final List<String> listaUtilizatori) {
                final List<Utilizator> listaSalvare = utilizatorRepository.findByUsernameIn(listaUtilizatori);
                for (final Utilizator user : listaSalvare) {
                        user.setValidated(false);
                        user.setUserUpdated(utilitati.getUsuarioLogado().getUsername());
                }
                return (List<Utilizator>) utilizatorRepository.save(listaSalvare);
        }

        /**
         * @param filtruUtilizator
         * @param criteria
         *
         */
        private void extractUserEliminado(final FiltruUtilizator filtruUtilizator, final Criteria criteria) {
                if (!filtruUtilizator.getEliminado().equals(Constante.SPATIU)) {
                        if (filtruUtilizator.getEliminado().equals(Constante.NO)) {
                                criteria.add(Restrictions.isNull(Constante.DATEDELETED));
                        }
                        else {
                                criteria.add(Restrictions.isNotNull(Constante.DATEDELETED));
                        }
                }
        }

        /**
         * Devuelve toti utilizatorii inregistrati in baza de date.
         * @return lista de unidades
         */
        @Override
        public List<Utilizator> fiindAll() {
                final Iterable<Utilizator> users = utilizatorRepository.findAll();
                return (List<Utilizator>) users;
        }

        /**
         * Devuelve utilizatorul inregistrat in baza de date.
         * @return Utilizator user
         * @see
         */
        @Override
        public Utilizator fiindOne(final String id) {
                final Utilizator user = utilizatorRepository.findOne(id);
                return user;
        }

        /**
         * Busca una lista de usuarios por su correo o su documento de identidad, ignorando mayúsculas..
         *
         * @param correo correo del usuario a buscar
         * @param nif documento del usuario a buscar
         * @return resultado de la búsqueda
         *
         */
        @Override
        public List<Utilizator> findByCorreoIgnoreCaseOrDocIdentidadIgnoreCase(final String correo, final String cnp) {
                return utilizatorRepository.findByEmailIgnoreCaseOrIdCardIgnoreCase(correo, cnp);
        }

        /**
         * Căutați un utilizator cu CNP.
         * @param cnp String - cnp-ul utilizatorului
         * @return User
         */
        @Override
        public Utilizator findByIdCard(final String cnp) {
                return this.utilizatorRepository.findByIdCard(cnp);
        }

        /**
         *
         */

        @Override
        public List<Utilizator> findByLocality(final Localitate loca) {
                return utilizatorRepository.findByLocality(loca);
        }

        /**
         *
         */
        @Override
        public List<Utilizator> findByName() {
                final String nume = Constante.DESTINATAR;
                return utilizatorRepository.findByName(nume);
        }

        /**
         * (non-Javadoc)
         *
         * @see ro.stad.online.gesint.services.UtilizatorService#findByProvinceAndTeam(ro.stad.online.gesint.persistence.entities.Provincia,
         * java.util.List)
         */
        @Override
        public List<Utilizator> findByProvinceAndTeam(final Provincia prov, final List<ParamEchipa> listaTeam) {
                return utilizatorRepository.findByProvinceAndTeamIn(prov, listaTeam);
        }

        /**
         * Cauta un utilizator cu functia.
         * @param rol RolEnum
         * @param prov Provincia
         * @return User
         */
        @Override
        public Utilizator findByTeam(final ParamEchipa team) {
                return utilizatorRepository.findByTeam(team);
        }

        /**
         * Cauta un utilizator cu rolul si judetul.
         * @param rol RolEnum
         * @param prov Provincia
         * @return User
         */
        @Override
        public Utilizator findByTeamAndProvince(final ParamEchipa team, final Provincia prov) {
                return utilizatorRepository.findByTeamAndProvince(team, prov);
        }

        /**
         *
         */

        @Override
        public Long findCount() {
                return utilizatorRepository.count();
        }

        /**
         *
         */
        @Override
        public int findUsersBySex(final AnNumarPojo membru) {
                try {
                        this.session = this.sessionFactory.openSession();
                        final Criteria critteria = this.session.createCriteria(Utilizator.class, "user");
                        critteria.add(Restrictions.ge("dateCreate", membru.getDesde()));
                        critteria.add(Restrictions.le("dateCreate", membru.getHasta()));
                        UtilitatiCriteria.setCondicionCriteriaIgualdadEnum(membru.getSex(), critteria, "sex");
                        critteria.setProjection(Projections.rowCount());
                        final Long cnt = (Long) critteria.uniqueResult();
                        return Math.toIntExact(cnt);
                }
                finally {
                        closeSession();
                }

        }

        /**
         * Obitne el listado de usuario en base a las condiciones de Criteria.
         * @param filtruUtilizator AnNumarPojo
         * @param criteria Criteria
         * @return List<User>
         */
        private List<Utilizator> gestionarCriteriaUsuarios(final FiltruUtilizator filtruUtilizator,
                        final Criteria criteria) {
                creaCriteria(filtruUtilizator, criteria);
                @SuppressWarnings(Constante.UNCHECKED)
                final List<Utilizator> usuariosList = criteria.list();
                this.session.close();
                return usuariosList;
        }

        /**
         * Obitne el listado de usuario en base a las condiciones de Criteria.
         * @param usuarioBusqueda AnNumarPojo
         * @param criteria Criteria
         * @return List<User>
         */
        private List<Utilizator> gestionarCriteriaUsuariosLocal(final FiltruEchipa filtruEchipa,
                        final Criteria criteria) {
                creaCriteriaLocal(filtruEchipa, criteria);
                @SuppressWarnings(Constante.UNCHECKED)
                final List<Utilizator> usuariosList = criteria.list();
                this.session.close();
                return usuariosList;
        }

        /**
         * Obtiene el conteo de criteria.
         * @param busqueda AnNumarPojo
         * @return int
         */
        @Override
        public int getCounCriteria(final FiltruUtilizator busqueda) {
                try {
                        this.session = this.sessionFactory.openSession();
                        final Criteria teria = this.session.createCriteria(Utilizator.class, "user");
                        creaCriteria(busqueda, teria);
                        teria.setProjection(Projections.rowCount());
                        final Long cnt = (Long) teria.uniqueResult();

                        return Math.toIntExact(cnt);
                }
                finally {
                        closeSession();
                }

        }

        /**
         * Guarda una lista de usuarios.
         * @param usuarios lista
         * @return lista de usuarios
         */
        @Override
        public List<Utilizator> salvat(final List<Utilizator> usuarios) {
                return (List<Utilizator>) utilizatorRepository.save(usuarios);
        }

        /**
         * Guarda en BBDD un listado de usuarios provisionales.
         *
         * @param listadoUsuariosProvisionales listardo de usuarios provisionales
         * @return lista de usuarios grabados
         *
         */
        @Override
        public List<Utilizator> save(final List<Utilizator> listUsers) {
                return (List<Utilizator>) utilizatorRepository.save(listUsers);
        }

        /**
         * Metoda care genereaza automat 100 de utilizatori
         *
         * @see ro.stad.online.gesint.services.UtilizatorService#save(ro.stad.online.gesint.persistence.entities.Utilizator)
         */
        @Override
        @Transactional(readOnly = false)
        public Utilizator save(final Utilizator entity) {
                return utilizatorRepository.save(entity);
        }
}
