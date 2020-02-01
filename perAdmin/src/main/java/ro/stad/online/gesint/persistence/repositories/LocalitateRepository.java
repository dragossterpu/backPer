package ro.stad.online.gesint.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ro.stad.online.gesint.persistence.entities.Localitate;
import ro.stad.online.gesint.persistence.entities.Provincia;

/**
 * Repositoriu pentru entitatea Localitate.
 * @author STAD
 */
public interface LocalitateRepository extends CrudRepository<Localitate, Long> {
	/**
	 * Comprueba si existe un municipio sabiendo su nombre y la provincia a la que pertenece.
	 *
	 * @param name nombre del municipio.
	 * @param provincia del municipio
	 * @return existe?
	 */
	boolean existsByNameIgnoreCaseAndProvince(String name, Provincia provincia);

	/**
	 * Cauta toate localitatile unei provincii
	 * @param Long id identificator de judet
	 * @return lista de localitati.
	 */
	List<Localitate> findAllByProvince(Provincia province);

	/**
	 * Busca todos las localitati de una provincia.
	 *
	 * @param idProvincia id de la provincia seleccionada
	 * @return List<Localidad> Lista de localitati por provincia.
	 */
	List<Localitate> findByProvinceOrderByName(Long idProvincia);

	/**
	 * @param name
	 * @param provincia
	 * @return
	 *
	 */
	Localitate findByNameIgnoreCaseAndProvince(String name, Provincia provincia);

	/**
	 * Cauta o localitate dupa numele acestuia
	 * @param descripcion
	 * @return Localitate
	 *
	 */
	Localitate findByName(String descripcion);

	/**
	 * Cauta o localitate dupa numele acestuia si importanta
	 * @param descripcion
	 * @paramnivel
	 * @return List<Localitate>
	 *
	 */
	List<Localitate> findAllByProvinceAndNivel(Provincia province, Long nivel);
}
