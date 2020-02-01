package ro.stad.online.gesint.services;

import java.io.IOException;
import java.util.List;

import org.primefaces.model.SortOrder;

import ro.stad.online.gesint.model.filters.FiltruLocalitate;
import ro.stad.online.gesint.persistence.entities.Localitate;
import ro.stad.online.gesint.persistence.entities.Provincia;
import ro.stad.online.gesint.persistence.entities.enums.TipLocalitateEnum;

/**
 * Interfață pentru serviciul de Localitate.
 *
 * @author STAD
 *
 */
public interface LocalitateService {

        /**
         * Cauta toate localitatile unei provincii
         * @param Long id identificator de judet
         * @return lista de localitati.
         * @see ro.mira.per.controller.AdminController.users(HttpServletRequest, Integer) (potential match)
         */
        List<Localitate> cautareDupaProvincie(Long idProvincia);

        /**
         * Guarda un nuevo municipio.
         * @param nombre del municipio
         * @param provincia a la que pertenece el municipio
         * @return municipio creado (true si es salvat correctamente)
         */
        Localitate crearLocalidad(String nombre, Provincia provincia, TipLocalitateEnum tipLoclalitate);

        /**
         * Comprueba si existe un municipio conociendo su nombre.
         *
         * @param name nombre del municipio
         * @param provincia a la que pertenece el municipio
         * @return valor booleano
         */
        boolean existeByNameIgnoreCaseAndProvincia(String name, Provincia provincia);

        /**
         * Cauta un municipio conociendo su nombre.
         *
         * @param name nombre del municipio
         * @param judetul
         * @return Localitate localidad
         */
        Localitate localidadByNameIgnoreCaseAndProvincia(String name, Provincia provincia);

        /**
         * Cauta toate localitatile
         * @return lista de localitati.
         * @see ro.mira.per.controller.AdminController.users(HttpServletRequest, Integer) (potential match)
         */
        List<Localitate> fiindAll();

        /**
         * Cauta o localitate
         * @param Long localitiId
         * @return Localitate localitatea
         */
        Localitate findById(Long localitiId);

        /**
         * Cauta o localitate
         * @param Long localitiId
         * @return Localitate localitatea
         */
        Localitate findByName(String local);

        /**
         * Cauta toate localitatile unei provincii
         * @param Long id identificator de judet
         * @return lista de localitati.
         * @see ro.mira.per.controller.AdminController.users(HttpServletRequest, Integer) (potential match)
         */
        List<Localitate> findByProvince(Provincia province);

        /**
         * Cauta toate localitatile unei provincii
         * @param Long id identificator de judet
         * @return lista de localitati.
         * @see ro.mira.per.controller.AdminController.users(HttpServletRequest, Integer) (potential match)
         */
        List<Localitate> findByProvinceAndNivel(Provincia province, Long nivel);

        /**
         * Inregistreaza o localitate.
         * @param localitate Localitate
         * @return localitate actualizata
         */
        Localitate save(Localitate localitate);

        /**
         * Incarcam fotografia unei localitati.
         * @param Localitate localitate
         * @throws IOException
         */

        Localitate incarcareImaginaFaraStocare(byte[] bs, Localitate localitate) throws IOException;

        /**
         * Método que devuelve el número de links en una consulta basada en criteria.
         *
         * @param linkBusqueda LinksBusqueda objeto con parámetros de búsqueda
         * @return devuelve el número de registros de una consulta criteria.
         */
        int getCounCriteria(FiltruLocalitate filtruLocalitate);

        /**
         * Método que devuelve la lista de links en una consulta basada en criteria.
         *
         * @param linkBusqueda objeto con los criterios de búsqueda
         * @param first primer elemento
         * @param pageSize tamaño de cada página de resultados
         * @param sortField campo por el que se ordenan los resultados
         * @param sortOrder sentido de la ordenacion (ascendente/descendente)
         * @return la lista de links.
         */
        List<Localitate> cautareLocalitateCriteria(int first, int pageSize, String sortField, SortOrder sortOrder,
                        FiltruLocalitate filtruLocalitate);

        /**
         * Elimina o localitate
         * @param Localitate localitate
         */
        void delete(Localitate localitate);
}
