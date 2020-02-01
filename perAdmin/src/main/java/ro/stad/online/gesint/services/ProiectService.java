package ro.stad.online.gesint.services;

import java.util.List;

import org.hibernate.Criteria;
import org.primefaces.model.SortOrder;

import ro.stad.online.gesint.model.filters.FiltruProiect;
import ro.stad.online.gesint.persistence.entities.Proiect;

/**
 * Interfaz del servicio para la gestión de proyectos.
 *
 * @author STAD
 *
 */
public interface ProiectService {

        /**
         * Busca proiecte con los parametros de búsqueda.
         * @param filtruProiect FiltruProiect
         * @param sortOrder SortOrder
         * @param sortField String
         * @param pageSize int
         * @param first int
         *
         * @return List<Proiect>
         *
         *
         */
        List<Proiect> cautareProiecteCriteria(int first, int pageSize, String sortField, SortOrder sortOrder,
                        FiltruProiect filtruProiect);

        /**
         * Elimina un proiect
         *
         * @param proiect Proiect
         */
        void delete(Proiect proiect);

        /**
         * Returneaza o lista cu toate proiectele.
         * @return List<Proiect>
         */
        List<Proiect> findAll();

        /**
         * Obtiene el conteo de criteria.
         * @param filtruProiect FiltruProiect
         * @return int
         */
        int getCounCriteria(FiltruProiect filtruProiect);

        /**
         * Salvați sau actualizați un proiect.
         *
         * @param proiect
         * @return Proiect actualizat
         */
        Proiect save(Proiect proiect);

        /**
         * Obtinem nivelul cel mai mare
         *
         * @param team
         * @return Echipa actualizat
         */
        List<Proiect> findAllByOrderByRankDesc();

        /**
         * @param criteria
         * @param first
         * @param pageSize
         * @param sortField
         * @param sortOrder
         * @param defaultField
         *
         */
        void pregatirePaginareOrdenareCriteria(Criteria criteria, int first, int pageSize, String sortField,
                        SortOrder sortOrder, String defaultField);
}
