package ro.stad.online.gesint.services;

import java.util.List;

import org.primefaces.model.SortOrder;

import ro.stad.online.gesint.model.filters.FiltruRegistru;
import ro.stad.online.gesint.persistence.entities.RegistruActivitate;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.persistence.entities.enums.SectiuniEnum;

/**
 *
 * Interfaz para el servicio de Registro de Actividad.
 *
 * @author STAD
 *
 **/
public interface RegistruActivitateService {

        /**
         * Busca los registros realizados por un usuario.
         *
         * @param infoUsuario Usuario para el que se hace la consulta
         * @return Listado de los registros resultantes
         */
        List<String> cautareUtilizatorDupaRegistru(String infoUsuario);

        /**
         * Busca registros de actividad según unos parámetros de forma paginada.
         *
         * @param first Primer elemento a mostrar
         * @param pageSize Número máximo de registros recuperados
         * @param sortField Campo de ordenación
         * @param sortOrder Sentido de la ordenación
         * @param regBusqueda Objeto que contiene los parámetros de búsqueda
         * @return Listado de los registros que responden a la búsqueda
         */

        List<RegistruActivitate> cautareRegistruActivitateCriteria(int first, int pageSize, String sortField,
                        SortOrder sortOrder, FiltruRegistru regBusqueda);

        /**
         * Devuelve el número total de registros de una búsqueda.
         *
         * @param busqueda Objeto que contiene los criterios de búsqueda
         * @return Número de registros que responden a la búsqueda
         */
        int getCounCriteria(FiltruRegistru busqueda);

        /**
         * Guarda un registro de alta/modificación del objeto.
         * @param idObjeto Long
         * @param descripcion String
         * @param ambito String
         * @param objeto String
         */
        void salveazaRegistruInregistrareModificare(Long idObjeto, String descripcion, String ambito, String objeto);

        /**
         * Guarda un registro de borrado del objeto.
         * @param idObjeto Long
         * @param descripcion String
         * @param ambito String
         * @param objeto String
         */
        void salveazaRegistruEliminare(Long idObjeto, String descripcion, String ambito, String objeto);

        /**
         * Guarda un registro de actividad de tipo error.
         * @param ambito String
         * @param objeto String
         * @param e Exception
         */
        void salveazaRegistruEroare(String ambito, String objeto, Exception e);

        /**
         * Guarda en base de datos un registro de login del usuario indicado.
         * @param usuario User
         */
        void salveazaRegistruLoginOK(Utilizator usuario);

        /**
         * Guarda en base de datos un registro de login erroneo del usuario indicado.
         * @param usuario String
         */
        void salveazaRegistruLoginKO(String usuario);

        /**
         * Guarda en base de datos un registro de actividad.
         * @param seccion Sección en la que se produce la actividad
         * @param tipo Tipo de actividad a registrar
         * @param descripcion Descripción de la actividad
         */
        void inregistrareActivitate(SectiuniEnum seccion, String tipo, String descripcion);

        /**
         * Guarda en el registro de actividad el error que se ha producido.
         * @param seccion Dónde se produce el error
         * @param exception Excepción generada
         */
        void inregistrareEroare(SectiuniEnum seccion, Exception exception);

        /**
         * Guarda en base de datos un registro de actividad.
         *
         * @param entity Registro a guardar en base de datos
         */
        void save(RegistruActivitate entity);

}
