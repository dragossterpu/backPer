package ro.stad.online.gesint.services;

import org.primefaces.event.FileUploadEvent;

/**
 * Implementarea serviciului de operațiuni masive.
 * @author STAD
 */
public interface OperatieMasivaFisierService {

	/**
	 * Procesează operarea masivă a unui fișier și efectuează acțiunea relevantă în BBDD.
	 * @param event eveniment în care dosarul va fi obținut
	 * @param tipoRegistro tipul de operațiune
	 * @param mensajeExcepcion mensaj în cazul apariției unei erori
	 */
	void procesareOperatieMasivaFisier(final FileUploadEvent event, String tipoRegistro, String mensajeExcepcion);

}
