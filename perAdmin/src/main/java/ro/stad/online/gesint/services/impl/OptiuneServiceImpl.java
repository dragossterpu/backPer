package ro.stad.online.gesint.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.NoArgsConstructor;
import ro.stad.online.gesint.persistence.entities.Optiune;
import ro.stad.online.gesint.persistence.entities.Provincia;
import ro.stad.online.gesint.persistence.repositories.OptiuneRepository;
import ro.stad.online.gesint.services.OptiuneService;

/**
 * Implementarea metodelor definite în interfața OptiuneService.
 * @author STAD
 *
 */
@NoArgsConstructor
@Service
@Transactional
public class OptiuneServiceImpl implements OptiuneService, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Repository de Optiune.
	 */
	@Autowired
	private transient OptiuneRepository optiuneRepository;

	/**
	 * Eliminarea unei optiuni
	 * @param optiune
	 */
	@Override
	@Transactional(readOnly = false)
	public void delete(final Optiune optiune) {
		this.optiuneRepository.delete(optiune);
	}

	/**
	 * Cauta toate optiunile inregistrate
	 * @return List<Optiune>)
	 */
	@Override
	public List<Optiune> fiindAll() {
		return (List<Optiune>) optiuneRepository.findAll();
	}

	/**
	 * Cauta toate optiunile inregistrate ale unui judet
	 * @return List<Optiune>)
	 */
	@Override
	public List<Optiune> findByCodeProvince(final Provincia code) {
		return (List<Optiune>) optiuneRepository.findByProvince(code);
	}

	/**
	 * Salveaza o optiune
	 * @param optiune ParamEchipa
	 * @return optiune
	 */
	@Override
	@Transactional(readOnly = false)
	public Optiune save(final Optiune optiune) {
		return optiuneRepository.save(optiune);
	}
}
