package ro.stad.online.gesint.services.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.google.common.base.Throwables;

import lombok.NoArgsConstructor;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.model.filters.FiltruRegistru;
import ro.stad.online.gesint.persistence.entities.RegistruActivitate;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.persistence.entities.enums.RegistruEnum;
import ro.stad.online.gesint.persistence.entities.enums.SectiuniEnum;
import ro.stad.online.gesint.persistence.repositories.RegistruActivitateRepository;
import ro.stad.online.gesint.services.RegistruActivitateService;
import ro.stad.online.gesint.util.FacesUtilities;
import ro.stad.online.gesint.util.Utilitati;
import ro.stad.online.gesint.util.UtilitatiCriteria;

/**
 *
 * Implementación del servicio de Registro de Actividad.
 *
 * @author EZENTIS
 *
 */

@NoArgsConstructor
@Service("registruActivitateService")
public class RegistruActivitateServiceImpl implements RegistruActivitateService, Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        /**
         * Session.
         */
        private Session session;

        /**
         * Repositorio de registro de actividad.
         */
        @Autowired
        private transient RegistruActivitateRepository regActividadRepository;

        /**
         * Factoría de sesiones.
         */
        @Autowired
        private transient SessionFactory sessionFactory;

        /**
         * Constructor usado para el test.
         *
         * @param sessionFact Factoría de sesiones
         */
        public RegistruActivitateServiceImpl(final SessionFactory sessionFact) {
                this.sessionFactory = sessionFact;
        }

        /**
         * Metodo que da de alta un registro de actividad.
         * @param seccion String
         * @param tipoReg String
         * @param descripcion String
         * @param usuario User
         */
        private void inregistrareRegistruActivitate(final String descripcion, final String tipoReg,
                        final String seccion, final Utilizator usuario) {
                try {
                        final RegistruActivitate registruActivitate = new RegistruActivitate();
                        registruActivitate.setUsername(usuario.getUsername());
                        registruActivitate.setTipRegActivitate(RegistruEnum.ALTA.toString());
                        registruActivitate.setFechaAlta(new Date());
                        registruActivitate.setNumeSectiune(SectiuniEnum.valueOf(SectiuniEnum.class, seccion));
                        registruActivitate.setDescripcion(descripcion);
                        this.regActividadRepository.save(registruActivitate);
                }
                catch (final DataAccessException e) {
                        salveazaRegistruEroare(seccion, "RegistruActivitateService", e);
                }

        }

        /**
         * Busca registros por usuario.
         * @param infoUsuario String
         * @return List<String>
         */
        @Override
        public List<String> cautareUtilizatorDupaRegistru(final String infoUsuario) {
                return this.regActividadRepository.cautareUtilizatorDupaRegistru("%" + infoUsuario + "%");
        }

        /**
         * Busca registro de actividad por criteria.
         * @param first int
         * @param pageSize int
         * @param sortField String
         * @param sortOrder SortOrder
         * @param regBusqueda RegistroBusqueda
         * @return List<RegistruActivitate>
         */
        @Override
        public List<RegistruActivitate> cautareRegistruActivitateCriteria(final int first, final int pageSize,
                        final String sortField, final SortOrder sortOrder, final FiltruRegistru regBusqueda) {
                try {
                        this.session = this.sessionFactory.openSession();
                        final Criteria criteria = this.session.createCriteria(RegistruActivitate.class);
                        creaCriteria(regBusqueda, criteria);

                        criteria.setFirstResult(first);
                        criteria.setMaxResults(pageSize);

                        if (sortField != null && sortOrder.equals(SortOrder.ASCENDING)) {
                                criteria.addOrder(Order.asc(sortField));
                        }
                        else if (sortField != null && sortOrder.equals(SortOrder.DESCENDING)) {
                                criteria.addOrder(Order.desc(sortField));
                        }
                        else if (sortField == null) {
                                criteria.addOrder(Order.asc(Constante.DATECREATE));
                        }

                        @SuppressWarnings(Constante.UNCHECKED)
                        final List<RegistruActivitate> listaRegistros = criteria.list();

                        return listaRegistros;
                }
                finally {
                        if (this.session != null) {
                                try {
                                        this.session.close();
                                }
                                catch (final DataAccessException e) {
                                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                        Constante.EROAREMESAJ, Constante.DESCEROAREMESAJ);
                                }
                        }
                }

        }

        /**
         * Consulta criteria para la búsqueda del registro de actividad.
         *
         * @param criteria consulta criteria
         * @param registroBusqueda Objeto que contiene los parámetros de búsqueda
         */
        private void creaCriteria(final FiltruRegistru filtruRegistru, final Criteria criteria) {

                UtilitatiCriteria.setCondicionCriteriaFechaMayor(filtruRegistru.getFechaDesde(), criteria,
                                Constante.DATECREATE);

                UtilitatiCriteria.setCondicionCriteriaFechaMenorIgual(filtruRegistru.getFechaHasta(), criteria,
                                Constante.DATECREATE);

                UtilitatiCriteria.setCondicionCriteriaIgualdadEnum(filtruRegistru.getNumeSectiune(), criteria,
                                Constante.NUMESECTIUNE);

                UtilitatiCriteria.setCondicionCriteriaIgualdadEnum(filtruRegistru.getTipRegActivitate(), criteria,
                                Constante.TIPREGACTIVITATE);

                UtilitatiCriteria.setCondicionCriteriaCadenaLike(filtruRegistru.getUsernameRegActividad(), criteria,
                                Constante.USERCREATE);

                UtilitatiCriteria.setCondicionCriteriaIgualdadLong(filtruRegistru.getIdUtilizator(), criteria,
                                Constante.IDUTILIZATOR);
        }

        /**
         * Obtiene el conteo de criteria.
         * @param busqueda RegistroBusqueda
         * @return int
         */
        @Override
        public int getCounCriteria(final FiltruRegistru filtru) {
                try {
                        this.session = this.sessionFactory.openSession();
                        final Criteria criteria = this.session.createCriteria(RegistruActivitate.class);
                        creaCriteria(filtru, criteria);
                        criteria.setProjection(Projections.rowCount());
                        final Long cnt = (Long) criteria.uniqueResult();

                        return Math.toIntExact(cnt);
                }
                finally {
                        if (this.session != null) {
                                try {
                                        this.session.close();
                                }
                                catch (final DataAccessException e) {
                                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                        Constante.EROAREMESAJ, Constante.DESCEROAREMESAJ);
                                }
                        }
                }

        }

        /**
         * Guarda un registro de actividad de tipo alta o modificación.
         * @param idObjeto Long
         * @param descripcion String
         * @param ambito String
         * @param objeto String
         * @param borrado boolean
         */
        private void inregistrareRegistru(final Long idObiect, final String descriere, final String ambito,
                        final String obiect, final boolean borrado) {
                final RegistruEnum operacion = Utilitati.procesarOperacion(idObiect, borrado);

                final StringBuilder descripcionReg = new StringBuilder(operacion.toString());
                descripcionReg.append(obiect);
                if (!operacion.equals(RegistruEnum.ALTA)) {
                        descripcionReg.append(idObiect);
                }
                descripcionReg.append(" (" + descriere + ") ha terminado con éxito.");
                inregistrareRegistruActivitate(descripcionReg.toString(), operacion.name(), ambito, null);
        }

        /**
         * Guarda un registro de borrado del objeto.
         * @param idObjeto Long
         * @param descripcion String
         * @param ambito String
         * @param objeto String
         */
        @Override
        public void salveazaRegistruInregistrareModificare(final Long idObjeto, final String descripcion,
                        final String ambito, final String objeto) {
                inregistrareRegistru(idObjeto, descripcion, ambito, objeto, false);
        }

        /**
         * Guarda un registro de borrado del objeto.
         * @param idObjeto Long
         * @param descripcion String
         * @param ambito String
         * @param objeto String
         */
        @Override
        public void salveazaRegistruEliminare(final Long idObjeto, final String descripcion, final String ambito,
                        final String objeto) {
                inregistrareRegistru(idObjeto, descripcion, ambito, objeto, true);
        }

        /**
         * Guarda un registro de actividad de tipo error.
         * @param ambito String
         * @param objeto String
         * @param e Exception
         */
        @Override
        public void salveazaRegistruEroare(final String ambito, final String objeto, final Exception e) {
                inregistrareRegistruActivitate(Throwables.getStackTraceAsString(e) + "/" + objeto,
                                RegistruEnum.ERROR.name(), ambito, null);
        }

        /**
         * Guarda en base de datos un registro de login del usuario indicado.
         * @param usuario User
         */
        @Override
        public void salveazaRegistruLoginOK(final Utilizator usuario) {
                inregistrareRegistruActivitate("Login del usuario: " + usuario.getUsername(), RegistruEnum.ALTA.name(),
                                SectiuniEnum.LOGIN.name(), usuario);
        }

        /**
         * Guarda en base de datos un registro de login erroneo del usuario indicado.
         * @param usuario String
         */
        @Override
        public void salveazaRegistruLoginKO(final String usuario) {
                inregistrareRegistruActivitate("Login del usuario erroneo: " + usuario, RegistruEnum.ERROR.name(),
                                "LOGIN", null);
        }

        @Override
        public void inregistrareActivitate(final SectiuniEnum seccion, final String tipo, final String descripcion) {
                final RegistruActivitate reg = new RegistruActivitate();
                final Utilizator usuario = (Utilizator) SecurityContextHolder.getContext().getAuthentication()
                                .getPrincipal();
                reg.setFechaAlta(new Date());
                reg.setNumeSectiune(seccion);
                reg.setTipRegActivitate(RegistruEnum.ALTA.name());
                reg.setUsernameRegActividad(usuario.getUsername());
                this.regActividadRepository.save(reg);

        }

        @Override
        public void inregistrareEroare(final SectiuniEnum seccion, final Exception exception) {
                final RegistruActivitate reg = new RegistruActivitate();
                final Utilizator usuario = (Utilizator) SecurityContextHolder.getContext().getAuthentication()
                                .getPrincipal();
                reg.setFechaAlta(new Date());
                reg.setNumeSectiune(seccion);
                reg.setTipRegActivitate(RegistruEnum.ERROR.name());
                reg.setUsernameRegActividad(usuario.getUsername());
                this.regActividadRepository.save(reg);

        }

        /**
         * Guarda un registro de actividad.
         * @param entity RegistruActivitate
         */
        @Override
        public void save(final RegistruActivitate entity) {
                this.regActividadRepository.save(entity);
        }

}
