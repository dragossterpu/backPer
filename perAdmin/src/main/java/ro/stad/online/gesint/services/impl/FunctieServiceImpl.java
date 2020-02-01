package ro.stad.online.gesint.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.NoArgsConstructor;
import ro.stad.online.gesint.persistence.entities.ParamEchipa;
import ro.stad.online.gesint.persistence.repositories.FunctieRepository;
import ro.stad.online.gesint.services.FunctieService;

/**
 * Implementarea metodelor definite în interfața FunctieService.
 * @author STAD
 *
 */
@NoArgsConstructor
@Service
@Transactional
public class FunctieServiceImpl implements FunctieService, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Repository de pteam.
	 */
	@Autowired
	private transient FunctieRepository functieRepository;

	/**
	 * Eliminarea unei functii
	 * @param functie
	 */
	@Override
	@Transactional(readOnly = false)
	public void delete(final ParamEchipa functie) {
		this.functieRepository.delete(functie);
	}

	/**
	 * Cauta toate functiile inregistrate
	 */
	@Override
	public List<ParamEchipa> fiindAll() {
		return (List<ParamEchipa>) functieRepository.findAll();
	}

	/**
	 * Salveaza o functie
	 * @param functie ParamEchipa
	 * @return functie
	 */
	@Override
	@Transactional(readOnly = false)
	public ParamEchipa save(final ParamEchipa functie) {
		return functieRepository.save(functie);
	}

}
