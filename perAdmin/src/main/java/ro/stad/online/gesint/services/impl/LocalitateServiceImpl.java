package ro.stad.online.gesint.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.model.filters.FiltruLocalitate;
import ro.stad.online.gesint.persistence.entities.Localitate;
import ro.stad.online.gesint.persistence.entities.Provincia;
import ro.stad.online.gesint.persistence.entities.enums.TipLocalitateEnum;
import ro.stad.online.gesint.persistence.repositories.LocalitateRepository;
import ro.stad.online.gesint.services.CriteriaService;
import ro.stad.online.gesint.services.LocalitateService;
import ro.stad.online.gesint.services.ProvinciaService;
import ro.stad.online.gesint.util.UtilitatiCriteria;

/**
 * Implementarea serviciului de localitati.
 *
 * @author STAD
 *
 */
@Service
public class LocalitateServiceImpl implements LocalitateService {

        /**
         * Repository de Localitate.
         */
        @Autowired
        private LocalitateRepository localitateRepository;

        /**
         * Session.
         */
        private Session session;

        /**
         * Corecții, stil de verificare și curățare a cursurilor fără a utiliza link-ul de servicii Session.
         */
        @Autowired
        private transient SessionFactory sessionFactory;

        /**
         * Serviciu pentru a utiliza metodele utilizate împreună cu criteria.
         */
        @Autowired
        private transient CriteriaService criteriaService;

        /**
         * Variabila utilizata pentru injectarea serviciului de judete
         */
        @Autowired
        private transient ProvinciaService provinciaService;

        /**
         * Căutare orașe aparținând unei provincii.
         * @param idProvincia Provincia pe care vrem să o consultăm.
         * @return lista Lista de localitati in provincie.
         */
        @Override
        public List<Localitate> cautareDupaProvincie(final Long idProvincia) {
                return localitateRepository.findByProvinceOrderByName(idProvincia);
        }

        /**
         * Salveaza o localitate nouă
         * @param nume localitatii
         * @param provincia din care face parte localitatea
         * @return localitate creata
         */
        @Override
        @Transactional(readOnly = false)
        public Localitate crearLocalidad(final String nume, final Provincia provincia,
                        final TipLocalitateEnum tipLoclalitate) {
                final Localitate nouaLocalitate = new Localitate();
                nouaLocalitate.setName(nume);
                nouaLocalitate.setProvince(provincia);
                nouaLocalitate.setResidence(false);
                nouaLocalitate.setSector(null);
                nouaLocalitate.setTypelocality(tipLoclalitate);
                localitateRepository.save(nouaLocalitate);
                return nouaLocalitate;

        }

        /**
         * Verifica dacă există o localitate cu același nume..
         *
         * @param name numele localitatii
         * @param provincia din care face parte localitatea
         * @return valor booleano
         */
        @Override
        public boolean existeByNameIgnoreCaseAndProvincia(final String name, final Provincia provincia) {
                return localitateRepository.existsByNameIgnoreCaseAndProvince(name, provincia);
        }

        /**
         * Verifica dacă există o localitate cu același nume.
         *
         * @param name numele localitatii
         * @param provincia din care face parte localitatea
         * @return localitatea Localitate
         */
        @Override
        public Localitate localidadByNameIgnoreCaseAndProvincia(final String name, final Provincia provincia) {
                return localitateRepository.findByNameIgnoreCaseAndProvince(name, provincia);
        }

        /**
         * Devuelve toate judetele inregistrate in baza de date.
         * @return lista de judete
         */
        @Override
        public List<Localitate> fiindAll() {
                return (ArrayList<Localitate>) localitateRepository.findAll();
        }

        /**
         * Cauta o localitate dupa id acestuia
         * @param descripcion
         * @return Localitate
         *
         */
        @Override
        public Localitate findById(final Long localidadId) {
                return localitateRepository.findOne(localidadId);
        }

        /**
         * Cauta o localitate dupa numele acestuia
         * @param descripcion
         * @return Localitate
         *
         */
        @Override
        public Localitate findByName(final String local) {
                return localitateRepository.findByName(local);
        }

        /**
         * Cauta toate localitatile unei provincii
         * @param Long id identificator de judet
         * @return lista de localitati.
         * @see ro.mira.per.controller.AdminController.users(HttpServletRequest, Integer) (potential match)
         */
        @Override
        public List<Localitate> findByProvince(final Provincia province) {
                return localitateRepository.findAllByProvince(province);
        }

        /**
         * Cauta toate localitatile unei provincii
         * @param Long id identificator de judet
         * @return lista de localitati.
         * @see ro.mira.per.controller.AdminController.users(HttpServletRequest, Integer) (potential match)
         */
        @Override
        public List<Localitate> findByProvinceAndNivel(final Provincia province, final Long nivel) {
                return localitateRepository.findAllByProvinceAndNivel(province, nivel);
        }

        /**
         * Salvați o localitate
         * @param localitate Localitate
         * @return localitate actualizata
         */
        @Override
        @Transactional(readOnly = false)
        public Localitate save(final Localitate localitate) {
                return localitateRepository.save(localitate);
        }

        /**
         * Metoda care primește un fișier UploadedFile și datele necesare pentru a genera un document, dar nu îl
         * stochează într-o bază de date. Lăsați obiectul gata să îl salveze.
         * @param file fisier pentru stocare in BBDD
         * @param localitate localitate
         * @return document stocat in BBDD
         * @throws IOException
         */
        @Override
        public Localitate incarcareImaginaFaraStocare(final byte[] file, final Localitate localitate)
                        throws IOException {
                return creareImagine(file, localitate);
        }

        /**
         * Incarcam imaginea provinciei.
         *
         * @param file Fisier incarcat de utilizator
         * @param judetul caruia se asociaza imaginea.
         * @return judet
         * @throws DataAccessException Excepción SQL
         * @throws IOException Excepción entrada/salida
         */
        private Localitate creareImagine(final byte[] file, final Localitate localitate) throws IOException {
                incarcareDatePersonaleUser(file, localitate);
                localitateRepository.save(localitate);
                return localitate;
        }

        /**
         * Incarcam fotografia judetului
         * @param provincia
         * @return Provincia judet
         */
        private void incarcareDatePersonaleUser(final byte[] fileBlob, final Localitate localitate) {
                localitate.setPhoto(fileBlob);
        }

        /**
         * Metoda care returnează numărul de localitati într-o consultare bazată pe criterii.
         *
         * @param cautare obiect cu parametri de căutare
         * @return returnează numărul de înregistrări dintr-o consultare criteria.
         */
        @Override
        public int getCounCriteria(final FiltruLocalitate cautare) {
                this.session = this.sessionFactory.openSession();
                final Criteria criteria = this.session.createCriteria(Localitate.class, "localitate");
                cautareCriteria(cautare, criteria);
                criteria.setProjection(Projections.rowCount());
                final Long cnt = (Long) criteria.uniqueResult();
                this.session.close();
                return Math.toIntExact(cnt);
        }

        /**
         * Căutare de toate locațiile care îndeplinesc condițiile inserate în clasa FiltruLocalitate.
         * @param cautare FiltruLocalitate
         * @param criteria Criteria
         */
        private void cautareCriteria(final FiltruLocalitate cautare, final Criteria criteria) {
                UtilitatiCriteria.setCondicionCriteriaCadenaLike(cautare.getNume(), criteria, "name");
                UtilitatiCriteria.setCondicionCriteriaIgualdadEnum(cautare.getTip(), criteria, "typelocality");
                if (cautare.getIdProvincia() != null && !cautare.getIdProvincia().equals("")) {
                        criteria.add(Restrictions.eq("province", provinciaService.findById(cautare.getIdProvincia())));
                }
        }

        /**
         * Căutare de toate locațiile care îndeplinesc condițiile inserate în clasa FiltruLocalitate.
         *
         * @param cautare obiect cu parametri de căutare
         * @param first primul element
         * @param pageSize dimensiunea fiecărei pagini de rezultate
         * @param sortField câmpul în care sunt sortate rezultatele
         * @param sortOrder ordine de sortare (crescator / descrescator)
         * @return lista de localitati.
         */
        @Override
        public List<Localitate> cautareLocalitateCriteria(final int first, final int pageSize, final String sortField,
                        final SortOrder sortOrder, final FiltruLocalitate cautare) {
                this.session = this.sessionFactory.openSession();
                final Criteria criteria = this.session.createCriteria(Localitate.class, "localitate");
                cautareCriteria(cautare, criteria);

                this.criteriaService.pregatirePaginareOrdenareCriteria(criteria, first, pageSize, sortField, sortOrder,
                                Constante.ID);

                @SuppressWarnings(Constante.UNCHECKED)
                final List<Localitate> lista = criteria.list();
                this.session.close();

                return lista;
        }

        /**
         * Elimina o localitate
         * 
         * @param id identicatorul localitatii
         */
        @Override
        @Transactional(readOnly = false)
        public void delete(Localitate localitate) {
                localitateRepository.delete(localitate.getId());
        }
}
