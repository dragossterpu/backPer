package ro.stad.online.gesint.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.primefaces.model.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitchellbosecke.pebble.error.PebbleException;

import lombok.NoArgsConstructor;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.model.filters.FiltruAlerta;
import ro.stad.online.gesint.persistence.entities.Alerta;
import ro.stad.online.gesint.persistence.entities.Documentul;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.persistence.repositories.AlertaRepository;
import ro.stad.online.gesint.services.AlertaService;
import ro.stad.online.gesint.util.EMail;
import ro.stad.online.gesint.util.FacesUtilities;
import ro.stad.online.gesint.util.UtilitatiCriteria;

/**
 * Implementarea metodelor definite în interfața AlertaService.
 * @author STAD
 *
 */
@NoArgsConstructor
@Service
@Transactional
public class AlertaServiceImpl implements AlertaService, Serializable {

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
         * Repository de alertas.
         */
        @Autowired
        private transient AlertaRepository alertaRepository;

        /**
         * Clase para el envío de mails.
         */
        @Autowired
        private transient EMail mailAlertaSender;

        /**
         * Session.
         */
        private Session session;

        /**
         * Constructor usado para el test.
         *
         * @param sessionFact Factoría de sesiones
         */
        public AlertaServiceImpl(final SessionFactory sessionFact) {
                sessionFactory = sessionFact;
        }

        /**
         * Căutați alerte cu parametrii de căutare.
         * @param filtruAlerta FiltruAlerta
         * @param sortOrder SortOrder
         * @param sortField String
         * @param pageSize int
         * @param first int
         * @return List<Alerta>
         */
        @Override
        public List<Alerta> cautareAlertaCriteria(final int first, final int pageSize, final String sortField,
                        final SortOrder sortOrder, final FiltruAlerta filtruAlerta) {
                try {
                        session = sessionFactory.openSession();
                        final Criteria criteria = session.createCriteria(Alerta.class, Constante.ALERTA);
                        creaCriteria(filtruAlerta, criteria);
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
                        final List<Alerta> alertasList = criteria.list();

                        return alertasList;
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
         * Verificați criteriile pentru căutarea alertelor.
         *
         * @param criteria consulta criteria
         * @param filtruAlerta Obiect care conține parametrii de căutare
         */
        private void creaCriteria(final FiltruAlerta filtruAlerta, final Criteria criteria) {
                UtilitatiCriteria.setCondicionCriteriaCadenaLike(filtruAlerta.getDestinatar(), criteria, "destinatari");
                UtilitatiCriteria.setCondicionCriteriaCadenaLike(filtruAlerta.getTitlu(), criteria, Constante.TITLU);
                UtilitatiCriteria.setCondicionCriteriaIgualdadLong(filtruAlerta.getId(), criteria, Constante.ID);
                UtilitatiCriteria.setCondicionCriteriaFechaMayor(filtruAlerta.getDateFromSend(), criteria,
                                Constante.SENDDATE);
                UtilitatiCriteria.setCondicionCriteriaFechaMenorIgual(filtruAlerta.getDateUntilSend(), criteria,
                                Constante.SENDDATE);
                UtilitatiCriteria.setCondicionCriteriaFechaMayor(filtruAlerta.getDateFromCreated(), criteria,
                                Constante.DATECREATE);
                UtilitatiCriteria.setCondicionCriteriaFechaMenorIgual(filtruAlerta.getDateUntilCreated(), criteria,
                                Constante.DATECREATE);
                UtilitatiCriteria.setCondicionCriteriaIgualdadEnum(filtruAlerta.getTipAlerta(), criteria, "tipAlerta");

        }

        /**
         * Trimiteți alertă sau comunicare în mod individual.
         * @param alerta Alerta
         * @param usuario User
         * @throws PebbleException
         */
        @Override
        public void sendAlert(Alerta alerta, List<Utilizator> utilizatoriSelectionati, List<Documentul> documenteIncarcate,
                        String plantilla, Map<String, String> paramPlantilla) throws PebbleException {
                Alerta alertaLocal;
                alertaLocal = new Alerta();
                BeanUtils.copyProperties(alerta, alertaLocal);
                mailAlertaSender.send(alertaLocal, utilizatoriSelectionati, documenteIncarcate, plantilla,
                                paramPlantilla);
        }

        /**
         * Obțineți numărul
         * @param busqueda FiltruRegistru
         * @return int
         */
        @Override

        public int getCounCriteria(final FiltruAlerta busqueda) {
                try {
                        session = sessionFactory.openSession();
                        final Criteria crit = session.createCriteria(Alerta.class);
                        creaCriteria(busqueda, crit);
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
         * Salvați o alertă sau o comunicare
         * @param alerta Alerta
         * @return Alerta
         */
        @Override
        @Transactional(readOnly = false)
        public Alerta save(final Alerta alerta) {
                return alertaRepository.save(alerta);
        }

        /**
         * Eliminarea unei alerte
         * @param alerta
         */
        @Override
        @Transactional(readOnly = false)
        public void delete(final Long id) {
                this.alertaRepository.delete(id);
        }

        /**
         * Cauta o alerta
         * @param alerta Alerta
         * @return alerta Alerta
         */
        @Override
        public Alerta fiindOne(final Alerta alerta) {
                alertaRepository.findOne(alerta.getId());
                return alerta;
        }

}
