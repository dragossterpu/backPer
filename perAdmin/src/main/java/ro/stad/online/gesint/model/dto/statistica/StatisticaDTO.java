package ro.stad.online.gesint.model.dto.statistica;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase DTO que contiene el resultado de una fila perteneciente a estadisticas de denuncias por provincia.
 *
 * @author EZENTIS
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StatisticaDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5468840743886693413L;

	/**
	 * numarTotal.
	 *
	 */
	int numarTotal;

	/**
	 * totalcuLiceu.
	 *
	 */
	int totalcuLiceu;

	/**
	 * total cu Liceu.
	 *
	 */
	int totalStudiiSup;

	/**
	 * total cu studii bazice.
	 *
	 */
	int totalBazice;

	/**
	 * total locuitori.
	 *
	 */
	int locuitoriTotal;

	/**
	 * total locuitori barbati.
	 *
	 */
	int totalBarbati;

	/**
	 * total locuitori femei.
	 *
	 */
	int totalFemei;

	/**
	 * total locuitori cu drept de vot.
	 *
	 */
	int totalVot;

	/**
	 * total inregistrati in ultima luna.
	 *
	 */
	int totalUltimaLuna;

	/**
	 * total inregistrati in ultimele doua luni.
	 *
	 */
	int totalUltimDouaLuni;

	/**
	 * total inregistrati in ultimele trei luni.
	 *
	 */
	int totalUltimTreiLuni;

	/**
	 * total inregistrati in ultimele patru luni.
	 *
	 */
	int totalUltimPatruLuni;

	/**
	 * total inregistrati in ultimele cinci luni.
	 *
	 */
	int totalUltimCinciLuni;

	/**
	 * total inregistrati in ultimele sase luni.
	 *
	 */
	int totalUltimSaseLuni;

	/**
	 * total inregistrati in ultimele sapte luni.
	 *
	 */
	int totalUltimSapteLuni;

	/**
	 * total inregistrati in ultimele opt luni.
	 *
	 */
	int totalUltimOptLuni;

	/**
	 * total inregistrati in ultimele noua luni.
	 *
	 */
	int totalUltimNouaLuni;

	/**
	 * total inregistrati in ultimele zece luni.
	 *
	 */
	int totalUltimZeceLuni;

	/**
	 * total inregistrati in ultimele unsprezece luni.
	 *
	 */
	int totalUltimUnspeLuni;

	/**
	 * total inregistrati in ultimul an.
	 *
	 */
	int totalUltimAn;

	/**
	 * total inregistrati in ultimul an in urma.
	 *
	 */
	int totalUltimaLunaAnAtras;

	/**
	 * total inregistrati in ultimele doua luni ale anului trecut
	 *
	 */
	int totalUltimDouaLuniAnAtras;

	/**
	 * total inregistrati in ultimele trei luni ale anului trecut
	 *
	 */
	int totalUltimTreiLuniAnAtras;

	/**
	 * total inregistrati in ultimele patru luni ale anului trecut
	 *
	 */
	int totalUltimPatruLuniAnAtras;

	/**
	 * total inregistrati in ultimele cinci luni ale anului trecut
	 *
	 */
	int totalUltimCinciLuniAnAtras;

	/**
	 * total inregistrati in ultimele sase luni ale anului trecut
	 *
	 */
	int totalUltimSaseLuniAnAtras;

	/**
	 * total inregistrati in ultimele sapte luni ale anului trecut
	 *
	 */
	int totalUltimSapteLuniAnAtras;

	/**
	 * total inregistrati in ultimele opt luni ale anului trecut
	 *
	 */
	int totalUltimOptLuniAnAtras;

	/**
	 * total inregistrati in ultimele noua luni ale anului trecut
	 *
	 */
	int totalUltimNouaLuniAnAtras;

	/**
	 * total inregistrati in ultimele zece luni ale anului trecut
	 *
	 */
	int totalUltimZeceLuniAnAtras;

	/**
	 * total inregistrati in ultimele unsprezece luni ale anului trecut
	 *
	 */
	int totalUltimUnspeLuniAnAtras;

	/**
	 * total inregistrati in ultimii doi ani
	 *
	 */
	int totalUltimiDoiAni;

	/**
	 * total inregistrati in ultimii trei ani
	 *
	 */
	int totalUltimiiTreiAni;

	/**
	 * total inregistrati in ultimii patru ani
	 *
	 */
	int totalUltimiiPatruAni;

	/**
	 * total inregistrati in ultimii cinci ani
	 *
	 */
	int totalUltimiiCinciAni;

	/**
	 * total inregistrati in mediul rural
	 *
	 */
	int mediuRural;

	/**
	 * total inregistrati in mediul urban
	 *
	 */
	int mediuUrban;

	/**
	 * total locuitori in mediul rural
	 *
	 */
	int locuitoriTotalRural;

	/**
	 * total locuitori in mediul urban
	 *
	 */
	int locuitoriTotalUrban;

	/**
	 * totalLuna12
	 *
	 */
	int totalLuna12;

	/**
	 * totalPana25
	 *
	 */
	int totalPana25;

	/**
	 * totalPanaBarbati25
	 *
	 */
	int totalPanaBarbati25;

	/**
	 * totalPana40
	 *
	 */
	int totalPana40;

	/**
	 * totalPanaBarbati40
	 *
	 */
	int totalPanaBarbati40;

	/**
	 * totalPana60
	 *
	 */
	int totalPana60;

	/**
	 * totalPanaBarbati60
	 *
	 */
	int totalPanaBarbati60;

	/**
	 * otalPanaMayor60
	 *
	 */
	int totalPanaMayor60;

	/**
	 * otalPanaMayor60
	 *
	 */
	int totalAntCinciAni;

}
