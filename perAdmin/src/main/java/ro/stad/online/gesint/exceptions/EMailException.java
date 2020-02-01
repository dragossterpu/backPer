package ro.stad.online.gesint.exceptions;

import java.util.Arrays;

/**
 * 
 * O nouă excepție personalizată este creată pentru erorile din clasa E-MailImp.
 * 
 * @author STAD
 *
 */

public class EMailException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        /**
         * Mesaj de afișat atunci când apare o excepție..
         */
        private static final String MESAJ = "A apărut o eroare la trimiterea e-mailului";

        /**
         * Obiecttul clasei Throwable.
         */
        private final Throwable e;

        /**
         * O nouă excepție de tip EMailException este generată și eroarea detectată de sistem și primită ca parametru
         * este stocată.
         * 
         * @param ex Obiect care conține datele de excepție
         */
        public EMailException(final Throwable ex) {
                this.e = ex;
        }

        /**
         * Prezintă o eroare de tip.
         * 
         * @return Mesajul afișat
         */
        public String excError() {
                return MESAJ;
        }

        /**
         * Afișează tipul de eroare și adaugă StackTrace a excepției de poz, dacă doriți să descrieți mai detaliat
         * eroarea.
         * 
         * @return Mesaj complet cu stiva de mesaje de excepție.
         */
        public String excErrorCompleto() {
                return MESAJ.concat(Arrays.toString(e.getStackTrace()));
        }
}
