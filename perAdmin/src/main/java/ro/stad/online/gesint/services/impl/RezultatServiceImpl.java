package ro.stad.online.gesint.services.impl;

import java.io.Serializable;
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

import lombok.NoArgsConstructor;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.model.filters.FiltruRezultat;
import ro.stad.online.gesint.persistence.entities.PartidRezultateJudete;
import ro.stad.online.gesint.persistence.entities.PartidRezultateLocalitate;
import ro.stad.online.gesint.persistence.repositories.RezultatRepository;
import ro.stad.online.gesint.services.PartidService;
import ro.stad.online.gesint.services.ProvinciaService;
import ro.stad.online.gesint.services.RezultatService;
import ro.stad.online.gesint.util.FacesUtilities;
import ro.stad.online.gesint.util.UtilitatiCriteria;

/**
 * Implementarea metodelor definite în interfața RezultatService.
 * @author STAD
 *
 */
@NoArgsConstructor
@Service
@Transactional
public class RezultatServiceImpl implements RezultatService, Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        /**
         * Session criteria.
         */
        @Autowired
        private transient SessionFactory sessionFactory;

        /**
         * Repository de rezultate.
         */
        @Autowired
        private transient RezultatRepository rezultatRepository;

        /**
         * Variabila utilizata pentru a injecta serviciul provinciei.
         *
         */
        @Autowired
        private ProvinciaService provinciaService;

        /**
         * Variabila utilizata pentru a injecta serviciul partid.
         *
         */
        @Autowired
        private PartidService partidService;

        /**
         * Session.
         */
        private Session session;

        /**
         * Constructor usado para el test.
         *
         * @param sessionFact Factoría de sesiones
         */
        public RezultatServiceImpl(final SessionFactory sessionFact) {
                sessionFactory = sessionFact;
        }

        /**
         * Căutați rezultate cu parametrii de căutare.
         * @param filtruRezultat FiltruRezultat
         * @param sortOrder SortOrder
         * @param sortField String
         * @param pageSize int
         * @param first int
         * @return List<PartidRezultateJudete>
         */
        @Override
        public List<PartidRezultateJudete> cautareRezultatCriteria(final int first, final int pageSize,
                        final String sortField, final SortOrder sortOrder, final FiltruRezultat filtruRezultat) {
                try {
                        session = sessionFactory.openSession();
                        final Criteria criteria = session.createCriteria(PartidRezultateJudete.class,
                                        "partid_rezultat_judet");
                        creaCriteria(filtruRezultat, criteria);
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
                                criteria.addOrder(Order.asc("judetul"));
                                criteria.addOrder(Order.desc("procentajTotalVoturi"));
                        }
                        @SuppressWarnings(Constante.UNCHECKED)
                        final List<PartidRezultateJudete> rezultateList = criteria.list();

                        return rezultateList;
                }
                finally {
                        if ((session != null) && session.isOpen()) {
                                try {
                                        session.close();
                                }
                                catch (final DataAccessException e) {
                                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                        Constante.EROAREMESAJ, Constante.DESCEROAREMESAJ);
                                }
                        }
                }

        }

        /**
         * Căutați rezultate cu parametrii de căutare.
         * @param filtruRezultat FiltruRezultat
         * @param sortOrder SortOrder
         * @param sortField String
         * @param pageSize int
         * @param first int
         * @return List<PartidRezultateLocalitate>
         */
        @Override
        public List<PartidRezultateLocalitate> cautareRezultatCriteriaLoc(final int first, final int pageSize,
                        final String sortField, final SortOrder sortOrder, final FiltruRezultat filtruRezultat) {
                try {
                        session = sessionFactory.openSession();
                        final Criteria criteria = session.createCriteria(PartidRezultateLocalitate.class,
                                        "partid_rezultat_localitate");
                        creaCriteria(filtruRezultat, criteria);
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
                                criteria.addOrder(Order.asc("judetul"));
                                criteria.addOrder(Order.desc("procentajTotalVoturi"));
                        }
                        @SuppressWarnings(Constante.UNCHECKED)
                        final List<PartidRezultateLocalitate> rezultateList = criteria.list();

                        return rezultateList;
                }
                finally {
                        if ((session != null) && session.isOpen()) {
                                try {
                                        session.close();
                                }
                                catch (final DataAccessException e) {
                                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                        Constante.EROAREMESAJ, Constante.DESCEROAREMESAJ);
                                }
                        }
                }

        }

        /**
         * Cauta anii alegerilor
         * @return List<Integer>
         */
        @Override
        public List<Integer> cautaAni() {
                return rezultatRepository.cautaAni();

        }

        /**
         * Verificați criteriile pentru căutarea rezultatelor.
         *
         * @param criteria consulta criteria
         * @param filtruRezultat Obiect care conține parametrii de căutare
         */
        private void creaCriteria(final FiltruRezultat filtruRezultat, final Criteria criteria) {
                if (filtruRezultat.getIdPartid() != null && !filtruRezultat.getIdPartid().equals("")) {
                        criteria.add(Restrictions.eq("partid", partidService.fiindOne(filtruRezultat.getIdPartid())));
                }
                if (filtruRezultat.getIdProvincia() != null && !filtruRezultat.getIdProvincia().equals("")) {
                        criteria.add(Restrictions.eq("judetul",
                                        provinciaService.findById(filtruRezultat.getIdProvincia())));
                }

                UtilitatiCriteria.setCondicionCriteriaIgualdadInt(filtruRezultat.getAnAlegeri(), criteria, "anAlegeri");
                UtilitatiCriteria.setCondicionCriteriaIgualdadEnum(filtruRezultat.getTipAlegeri(), criteria,
                                "tipAlegeri");

        }

        /**
         * Obțineți numărul
         * @param busqueda FiltruRegistru
         * @return int
         */
        @Override

        public int getCounCriteria(final FiltruRezultat filtruRezultat) {
                try {
                        session = sessionFactory.openSession();
                        final Criteria crit = session.createCriteria(PartidRezultateJudete.class);
                        creaCriteria(filtruRezultat, crit);
                        crit.setProjection(Projections.rowCount());
                        final Long cnt = (Long) crit.uniqueResult();

                        return Math.toIntExact(cnt);
                }
                finally {
                        if ((session != null) && session.isOpen()) {
                                try {
                                        session.close();
                                }
                                catch (final DataAccessException e) {
                                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                        Constante.EROAREMESAJ, Constante.DESCEROAREMESAJ);
                                }
                        }
                }

        }

        /**
         * Salvați un rezultat.
         * @param rezultat PartidRezultateJudete
         * @return PartidRezultateJudete
         */
        @Override
        @Transactional(readOnly = false)
        public PartidRezultateJudete save(final PartidRezultateJudete rezultat) {
                return rezultatRepository.save(rezultat);
        }

        /**
         * Cauta un partid
         * @param idPartid Long
         * @return partid Partid
         */
        @Override
        public PartidRezultateJudete fiindOne(final Long idPartid) {
                return rezultatRepository.findOne(idPartid);
        }
}
