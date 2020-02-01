package ro.stad.online.gesint.lazydata;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.persistence.entities.Documente;
import ro.stad.online.gesint.services.DocumentService;
import ro.stad.online.gesint.web.beans.gd.FiltruDocument;

/**
 * 
 * Modelo para paginación desde servidor extendiendo el modelo LazyDataModel de Primefaces.
 * 
 * @author STAD
 *
 */
@Component
@Setter
@Getter
public class LazyDataDocumente extends LazyDataModel<Documente> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Objeto que contiene los parámetros de búsqueda.
	 */
	private FiltruDocument busqueda;

	/**
	 * Servicio.
	 */
	private transient DocumentService servicio;

	/**
	 * Lista que sirve al modelo como fuente de datos.
	 */
	private List<Documente> datasource;

	/**
	 * Constructor.
	 * 
	 * @param service Servicio a utilizar
	 */
	public LazyDataDocumente(DocumentService service) {
		this.servicio = service;
		this.busqueda = new FiltruDocument();
	}

	/**
	 * Sobreescritura del método getRowData para adaptarlo a nuestro modelo.
	 * 
	 * @param rowKey Fila que se ha seleccionado en la vista
	 * @return Documente que corresponde a la fila seleccionada
	 */
	@Override
	public Documente getRowData(String rowKey) {
		Documente doc = null;
		for (Documente docu : datasource) {
			if (docu.getId().toString().equals(rowKey))
				doc = docu;
		}
		return doc;
	}

	/**
	 * Sobreestritura del método getRowKey para adaptarlo a nuestro modelo.
	 * 
	 * @param solicitud Documente del que se desea recuperar la clave
	 * @return Clave del documento
	 */
	@Override
	public Object getRowKey(Documente solicitud) {
		return solicitud.getId();
	}

	/**
	 * Sobreescritura del método load para realizar la carga paginada.
	 * 
	 * @param first primer elemento de la paginación
	 * @param pageSize número máximo de registros recuperados
	 * @param sortField campo por el que se ordena
	 * @param sortOrder sentido de la orientación
	 * @param filters que se aplicarán a la búsqueda.
	 * @return listado de registros que corresponden a la búsqueda
	 */
	@Override
	public List<Documente> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Documente> listado = null;
		if (busqueda != null) {
			this.setRowCount(servicio.getCounCriteria(busqueda));
			listado = servicio.cautareDocumentCriteria(first, pageSize, sortField, sortOrder, busqueda);
			this.setDatasource(listado);
		}
		else {
			this.setRowCount(0);
		}
		return listado;

	}

}
