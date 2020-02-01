package ro.stad.online.gesint.services;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import com.mitchellbosecke.pebble.error.PebbleException;

import ro.stad.online.gesint.model.filters.FiltruAlerta;
import ro.stad.online.gesint.persistence.entities.Alerta;
import ro.stad.online.gesint.persistence.entities.Documente;
import ro.stad.online.gesint.persistence.entities.Utilizator;

/**
 * Declarația metodelor care vor fi utilizate pentru persistența alertelor..
 * @author STAD
 *
 */
public interface AlertaService {

        /**
         * Busca alertas con los parametros de búsqueda.
         * @param filtruAlerta FiltruAlerta
         * @param sortOrder SortOrder
         * @param sortField String
         * @param pageSize int
         * @param first int
         *
         * @return List<Alerta>
         *
         *
         */
        List<Alerta> cautareAlertaCriteria(int first, int pageSize, String sortField, SortOrder sortOrder,
                        FiltruAlerta filtruAlerta);

        /**
         * Eliminación de un usuario.
         * @param usuario a eliminar
         */
        void delete(Long id);

        /**
         * Obtiene el conteo de criteria.
         * @param busqueda FiltruAlerta
         * @return int
         */
        int getCounCriteria(FiltruAlerta busqueda);

        /**
         * Guarda una alerta.
         * @param alerta Alerta
         * @return Alerta actualizado
         */
        Alerta save(Alerta alerta);

        /**
         * Envía una alerta pasando una lista de destinatari.
         * @param alerta Alerta
         * @param utilizatoriSelectionati List<User>
         */
        void sendAlert(Alerta alerta, List<Utilizator> utilizatoriSelectionati, List<Documente> documenteIncarcate,
                        String plantilla, Map<String, String> paramPlantilla) throws PebbleException;

        /**
         * Cauta o alerta
         * @param alerta Alerta
         * @return alerta Alerta
         */
        Alerta fiindOne(Alerta alerta);

}
