package ro.per.online.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.per.online.persistence.entities.PLocality;
import ro.per.online.persistence.entities.PProvince;
import ro.per.online.persistence.entities.enums.TypeLocalityEnum;
import ro.per.online.persistence.repositories.LocalityRepository;
import ro.per.online.services.LocalityService;

/**
 * Implementación del servicio de localitati.
 *
 * @author STAD
 *
 */
@Service
public class LocalityServiceImpl implements LocalityService {

	/**
	 * Repositoriu de PLocality.
	 */
	@Autowired
	private LocalityRepository localityRepository;

	/**
	 * Devuelve toate judetele inregistrate in baza de date.
	 * @return lista de judete
	 */
	@Override
	public List<PLocality> fiindAll() {
		return (ArrayList<PLocality>) localityRepository.findAll();
	}

	/**
	 * Cauta toate localitatile unei provincii
	 * @param Long id identificator de judet
	 * @return lista de localitati.
	 * @see ro.mira.per.controller.AdminController.users(HttpServletRequest, Integer) (potential match)
	 */
	@Override
	public List<PLocality> findByProvince(final PProvince province) {
		return this.localityRepository.findAllByProvince(province);
	}

	/**
	 * Busca las localidades pertenecientes a una provincia.
	 * @param idProvincia Provincia que queremos consultar.
	 * @return lista Lista de localidades por provincia.
	 */
	@Override
	public List<PLocality> buscaByProvincia(final Long idProvincia) {
		return localityRepository.findByProvinceOrderByName(idProvincia);
	}

	/**
	 * Comprueba si existe un municipio conociendo su nombre.
	 * 
	 * @param name nombre del municipio
	 * @param provincia a la que pertenece el municipio
	 * @return valor booleano
	 */
	@Override
	public boolean existeByNameIgnoreCaseAndProvincia(String name, PProvince provincia) {
		return localityRepository.existsByNameIgnoreCaseAndProvince(name, provincia);
	}

	/**
	 * Guarda un nuevo municipio.
	 * @param nombre del municipio
	 * @param provincia a la que pertenece el municipio
	 * @return municipio creado (true si es guardado correctamente)
	 */
	@Override
	@Transactional(readOnly = false)
	public PLocality crearLocalidad(final String nombre, final PProvince provincia,
			final TypeLocalityEnum tipLoclalitate) {
		final PLocality nuevaLocalidad = new PLocality();
		nuevaLocalidad.setName(nombre);
		nuevaLocalidad.setProvince(provincia);
		nuevaLocalidad.setResidence(false);
		nuevaLocalidad.setSector(null);
		nuevaLocalidad.setTypelocality(tipLoclalitate);
		localityRepository.save(nuevaLocalidad);
		return nuevaLocalidad;

	}

	@Override
	public PLocality findById(Long localidadId) {
		return localityRepository.findOne(localidadId);
	}
}
