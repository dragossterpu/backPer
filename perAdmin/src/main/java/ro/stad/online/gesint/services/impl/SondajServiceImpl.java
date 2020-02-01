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
import ro.stad.online.gesint.model.filters.FiltruSondaj;
import ro.stad.online.gesint.persistence.entities.Sondaj;
import ro.stad.online.gesint.persistence.repositories.SondajRepository;
import ro.stad.online.gesint.services.SondajService;
import ro.stad.online.gesint.util.FacesUtilities;
import ro.stad.online.gesint.util.UtilitatiCriteria;

/**
 * Implementarea metodelor definite în interfața SondajService.
 * @author STAD
 *
 */
@NoArgsConstructor
@Service
@Transactional
public class SondajServiceImpl implements SondajService, Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        /**
         * Session de criteria.
         */
        @Autowired
        private transient SessionFactory sessionFactory;

        /**
         * Repository de sondaje.
         */
        @Autowired
        private transient SondajRepository sondajRepository;

        /**
         * Session.
         */
        private Session session;

        /**
         * @param sessionFact Factoría de sesiones
         */
        public SondajServiceImpl(final SessionFactory sessionFact) {
                sessionFactory = sessionFact;
        }

        /**
         * Căutați sondaje cu parametrii de căutare.
         * @param filtruSondaj FiltruSondaj
         * @param sortOrder SortOrder
         * @param sortField String
         * @param pageSize int
         * @param first int
         * @return List<Sondaj>
         */
        @Override
        public List<Sondaj> cautareSondajeCriteria(final int first, final int pageSize, final String sortField,
                        final SortOrder sortOrder, final FiltruSondaj filtruSondaj) {
                try {

                        session = sessionFactory.openSession();
                        final Criteria criteria = session.createCriteria(Sondaj.class, "sondaj");
                        creaCriteria(filtruSondaj, criteria);
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
                        @SuppressWarnings(Constante.UNCHECKED)
                        final List<Sondaj> sondajeList = criteria.list();

                        return sondajeList;
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
         * Verificați criteriile pentru căutarea sondajelor.
         *
         * @param criteria consulta criteria
         * @param filtruSondaj Obiect care conține parametrii de căutare
         */
        private void creaCriteria(final FiltruSondaj filtruSondaj, final Criteria criteria) {
                UtilitatiCriteria.setCondicionCriteriaCadenaLike(filtruSondaj.getDescriere(), criteria, "descriere");
                UtilitatiCriteria.setCondicionCriteriaCadenaLike(filtruSondaj.getIntrebare(), criteria, "intrebare");
                UtilitatiCriteria.setCondicionCriteriaIgualdadLong(filtruSondaj.getId(), criteria, Constante.ID);
                UtilitatiCriteria.setCondicionCriteriaFechaMayor(filtruSondaj.getDataDinSondaj(), criteria,
                                "dataIncepere");
                UtilitatiCriteria.setCondicionCriteriaFechaMenorIgual(filtruSondaj.getDataPanaSondaj(), criteria,
                                "dataIncepere");
                UtilitatiCriteria.setCondicionCriteriaIgualdadBoolean(filtruSondaj.getActiv(), criteria, "activ");
                UtilitatiCriteria.setCondicionCriteriaFechaMayor(filtruSondaj.getDateFromCreated(), criteria,
                                Constante.DATECREATE);
                UtilitatiCriteria.setCondicionCriteriaFechaMenorIgual(filtruSondaj.getDateUntilCreated(), criteria,
                                Constante.DATECREATE);
                UtilitatiCriteria.setCondicionCriteriaIgualdadEnum(filtruSondaj.getTipSondaj(), criteria, "tipSondaj");
                // daca este null nu a fst selectrata situatia sondajului
                if (filtruSondaj.getValidate() != null) {
                        if (filtruSondaj.getValidate()) {
                                criteria.add(Restrictions.isNull("dateDeleted"));
                        }
                        else {
                                criteria.add(Restrictions.isNotNull("dateDeleted"));
                        }
                }
        }

        /**
         * Cauta un sondaj
         * @param sondaj Sondaj
         * @return sondaj Sondaj
         */
        @Override
        public Sondaj fiindOne(final Sondaj sondaj) {
                return sondajRepository.findOne(sondaj.getId());
        }

        /**
         * Obțineți numărul
         * @param cautare FiltruSondaj
         * @return int
         */
        @Override

        public int getCounCriteria(final FiltruSondaj cautare) {
                try {
                        session = sessionFactory.openSession();
                        final Criteria crit = session.createCriteria(Sondaj.class);
                        creaCriteria(cautare, crit);
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
         * Salvați un sondaj
         * @param sondaj Sondaj
         * @return sondaj
         */
        @Override
        @Transactional(readOnly = false)
        public Sondaj save(final Sondaj sondaj) {
                return sondajRepository.save(sondaj);
        }

}
