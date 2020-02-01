package ro.stad.online.gesint.model.filters;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Obiectul căutării statisticilor..
 *
 * @author STAD
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltruStatistica implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 7637962187780804682L;

        /**
         * Data minima pentru rezultate.
         */
        private Date dataIncepand;

        /**
         * Date dataUltimAn.
         */
        private Date dataUltimAn;

        /**
         * Date dataUltimiDoiAni.
         */
        private Date dataUltimiDoiAni;

        /**
         * Date dataUltimiTreiAni.
         */
        private Date dataUltimiTreiAni;

        /**
         * Date dataUltimiPatruAni.
         */
        private Date dataUltimiPatruAni;

        /**
         * Date dataUltimiCinciAni.
         */
        private Date dataUltimiCinciAni;

        /**
         * Date dataUltimaLuna.
         */
        private Date dataUltimaLuna;

        /**
         * Date dataUltimDouaLuni.
         */
        private Date dataUltimDouaLuni;

        /**
         * Date dataUltimTreiLuni.
         */
        private Date dataUltimTreiLuni;

        /**
         * Date dataUltimPatruLuni.
         */
        private Date dataUltimPatruLuni;

        /**
         * Date dataUltimCinciLuni.
         */
        private Date dataUltimCinciLuni;

        /**
         * Date dataUltimSaseLuni.
         */
        private Date dataUltimSaseLuni;

        /**
         * Date dataUltimSapteLuni.
         */
        private Date dataUltimSapteLuni;

        /**
         * Date dataUltimOptLuni.
         */
        private Date dataUltimOptLuni;

        /**
         * Date dataUltimNouaLuni.
         */
        private Date dataUltimNouaLuni;

        /**
         * Date dataUltimZeceLuni.
         */
        private Date dataUltimZeceLuni;

        /**
         * Date dataUltimUnspeLuni.
         */
        private Date dataUltimUnspeLuni;

        /**
         * Date dataUltimaLunaAnTrecut.
         */
        private Date dataUltimaLunaAnTrecut;

        /**
         * Date dataUltimDouaLuniAnTrecut.
         */
        private Date dataUltimDouaLuniAnTrecut;

        /**
         * Date dataUltimTreiLuniAnTrecut.
         */
        private Date dataUltimTreiLuniAnTrecut;

        /**
         * Date dataUltimPatruLuniAnTrecut.
         */
        private Date dataUltimPatruLuniAnTrecut;

        /**
         * Date dataUltimCinciLuniAnTrecut.
         */
        private Date dataUltimCinciLuniAnTrecut;

        /**
         * Date dataUltimSaseLuniAnTrecut.
         */
        private Date dataUltimSaseLuniAnTrecut;

        /**
         * Date dataUltimSapteLuniAnTrecut.
         */
        private Date dataUltimSapteLuniAnTrecut;

        /**
         * Date dataUltimOptLuniAnTrecut.
         */
        private Date dataUltimOptLuniAnTrecut;

        /**
         * Date dataUltimNouaLuniAnTrecut.
         */
        private Date dataUltimNouaLuniAnTrecut;

        /**
         * Date dataUltimZeceLuniAnTrecut.
         */
        private Date dataUltimZeceLuniAnTrecut;

        /**
         * Date dataUltimUnspeLuniAnTrecut.
         */
        private Date dataUltimUnspeLuniAnTrecut;

        /**
         * Date fechaPana25.
         */
        private Date dataPana25;

        /**
         * Date dataPana40.
         */
        private Date dataPana40;

        /**
         * Date dataPana60.
         */
        private Date dataPana60;
}
