package ro.stad.online.gesint.services.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.stad.online.gesint.persistence.entities.Provincia;
import ro.stad.online.gesint.persistence.repositories.ProvincieRepository;
import ro.stad.online.gesint.services.ProvinciaService;

/**
 * Implementación del servicio de judete.
 *
 * @author STAD
 *
 */
@Service
public class ProvinciaServiceImpl implements ProvinciaService {

	/**
	 * Repositoriu de Provincia.
	 */
	@Autowired
	private ProvincieRepository provincieRepository;

	/**
	 * Recibe un archivo UploadedFile y los datos necesarios para general un Documentul pero no lo almacena en base de
	 * datos. Sólo deja el objeto preparado para guardarlo.
	 *
	 * @param file fichero a cargar en BDD
	 * @param tipo tipo de documentp
	 * @param inspeccion inspección asociada al documento
	 * @return documento cargado en base de datos
	 * @throws IOException
	 * @throws ProgesinException excepción lanzada
	 */
	@Override
	public Provincia incarcareImaginaFaraStocare(final byte[] file, final Provincia judet) throws IOException {
		return creareImagine(file, judet);
	}

	/**
	 * Incarcam fotografia judetului
	 * @param provincia
	 * @return Provincia judet
	 */
	private void incarcareDatePersonaleUser(final byte[] fileBlob, final Provincia judet) {
		judet.setPhoto(fileBlob);
	}

	/**
	 * Incarcam imaginea provinciei.
	 *
	 * @param file Fichero subido por el usuario.
	 * @param judetul caruia se asociaza imaginea.
	 * @return judet
	 * @throws DataAccessException Excepción SQL
	 * @throws IOException Excepción entrada/salida
	 */
	private Provincia creareImagine(final byte[] file, final Provincia judet) throws IOException {
		incarcareDatePersonaleUser(file, judet);
		provincieRepository.save(judet);
		return judet;
	}

	/**
	 * Devuelve toate judetele inregistrate in baza de date.
	 * @return lista de judete
	 */
	@Override
	public List<Provincia> fiindAll() {
		return provincieRepository.findAllByOrderByNameAsc();
	}

	/**
	 * Cauta un judet
	 * @param province Judetul
	 * @return Provincia province
	 */
	@Override
	public Provincia fiindOne(final Provincia province) {
		provincieRepository.findOne(province.getIndicator());
		return province;
	}

	/**
	 * Cauta un judet dupa id
	 * @param province Judetul
	 * @return Provincia province
	 */
	@Override
	public Provincia findById(final String province) {
		return provincieRepository.findOne(province);
	}

	/**
	 * @param descripción
	 * @see ro.stad.online.gesint.services.ProvinciaService#findByName(java.lang.String)
	 * @retur provincia
	 */
	@Override
	public Provincia findByName(final String descripcion) {
		return provincieRepository.findByName(descripcion);
	}

	/**
	 * Salvați un judet
	 * @param judet Provincia
	 * @return judetul actualizat
	 */
	@Override
	@Transactional(readOnly = false)
	public Provincia save(final Provincia judet) {
		return provincieRepository.save(judet);
	}
}
