package ro.stad.online.gesint.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.persistence.entities.Provincia;

/**
 * Métodos de utilidades.
 * @author STAD
 */
public class Generator {

        /**
         * Números.
         */
        public static final String NUMBERS = "123456789";

        /**
         * Números de cuerpos.
         */
        public static final String CUERPONUMBERS = "12789";

        /**
         * Tipo de denuncia.
         */
        public static final String TIPODENUNCIA = "456";

        /**
         * Subtipos de denuncia.
         */
        public static final String[] SUBTIPOS = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

        /**
         * Números 2.
         */
        public static final String NUMBERS2 = "12";

        /**
         * Letras.
         */
        public static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        /**
         * Subtipos de denuncia.
         */
        public static final String[] NUMEROS = { "1", "2", "3", "1", "2", "3", "1", "2", "1", "1", "3", "1", "1", "1",
                        "2", "2", "1", "1", "2", "3" };

        /**
         * Obtiene un apellido final de la lista.
         * @return apellido al azar
         */
        public static String apellidoFinal() {
                final String[] apellidos = { "Niță", "Pop", "Popa", "Popescu", "Ionescu", "Nemeș", "Stan", "Dumitrescu",
                                "Dima", "Gheorghiu", "Ioniță", "Marin", "Tudor", "Dobre", "Barbu", "Nistor", "Florea",
                                "Ene", "Dinu", "Georgescu", "Stoica", "Diaconu", "Diaconescu", "Mazilescu", "Ababei",
                                "Aanei", "Nistor", "Mocanu", "Oprea", "Voinea", "Dochioiu", "Albu", "Tabacu", "Manole",
                                "Cristea", "Toma", "Stănescu", "Pușcașu", "Tomescu", "Meceanu", "Ionescu", "Marinescu",
                                "Georgescu", "Vasilescu", "Stoican", "Maican", "Gheorghiu", "Vasiliu", "Pâclea",
                                "Pecaru", "Stoicescu", "Tomescu", "Reaboiu", "Rîciu", "Roateș", "Scafariu", "Sebeșanu",
                                "Șdirea", "Șabac", "Theodorescu", "Acatrinei", "Babageanu", "Bâclea", "Bâgioi",
                                "Beceanu", "Bicăjan", "Bechir", "Băcean", "Becleanu", "Bedean", "Bidirean", "Bilcan",
                                "Ionescu", "Georgescu", "Vasilescu", "Balagiu", "Bâtrânu", "Bălăcean", "Cabariu",
                                "Cîju", "Căciuleanu", "Câmpureanu", "Chelarașu", "Calcea", "Cîrje", "Cherchez",
                                "Cheregi", "Cimponeriu", "Cămui", "Cerbeanu", "Deaconu", "Diamandescu", "Diceanu",
                                "Dăncescu", "Delcescu", "Fabriș", "Ficior", "Federciuc", "Feișan", "Feleacă",
                                "Filimonescu", "Gâdinceanu", "Giblea", "Geană", "Ginculescu", "Găinariu", "Henciu",
                                "Hăncescu", "Herdean", "Idriceanu", "Ienășescu", "Iftimia", "Jeberean", "Jecan",
                                "Jătăreanu", "Leacă", "Laciu", "Lăceanu", "Logofătu", "Mecineanu", "Macavei",
                                "Mândrescu", "Măgădan", "Miculeasa", "Meiroșu", "Nădișan", "Năescu", "Nuță", "Nicoale",
                                "Nanciu", "Nicolea", "Obăcescu", "Ogaru", "Odobeștianu", "Ogrezeanu", "Pâcleanu",
                                "Pârgaru", "Plăvănescu", "Pălean", "Pencescu", "Păltinișanu", "Rebegeanu", "Robaciu",
                                "Răcășanu", "Râșnoveanu", "Rudișteanu", "Săbiescu", "Secăleanu", "Secașu", "Săcășean",
                                "Scărlătescu" };
                final String apellido = apellidos[1 + new Random().nextInt(150)];
                return apellido.toUpperCase();
        }

        /**
         * Obtiene un apellido final de la lista.
         * @return apellido al azar
         */
        public static String apellidoFinal2() {
                final String[] apellidos = { "Niță", "Pop", "Popa", "Popescu", "Ionescu", "Acsinte", "Stan",
                                "Dumitrescu", "Băcan", "Gheorghiu", "Ioniță", "Marin", "Tudor", "Dobre", "Barbu",
                                "Nistor", "Florea", "Ene", "Dinu", "Georgescu", "Stoica", "Diaconu", "Diaconescu",
                                "Bicescu", "Bârgoveanu", "Băiceanu", "Nistor", "Căilnicean", "Oprea", "Voinea",
                                "Cârciumăreasa", "Albu", "Călescu", "Manole", "Cristea", "Călimăceanu", "Stănescu",
                                "Cîșlariu", "Tomescu", "Dacian", "Ionescu", "Marinescu", "Georgescu", "Vasilescu",
                                "Daniilescu", "Dalaban", "Gheorghiu", "Vasiliu", "Davidescu", "Dășanu", "Stoicescu",
                                "Tomescu", "Deaconu", "Dedulescu", "Denciu", "Despinescu", "Dobriceanu", "Dodea",
                                "Dogaru", "Theodorescu", "Dițulescu", "Eftimie", "Enăceanu", "Fiertu", "Filipoiu",
                                "Firescu", "Firuleasa", "Fîntînaru", "Gabrea", "Gabrilescu", "Gafiței", "Garabagiu",
                                "Ionescu", "Georgescu", "Vasilescu", "Podaru", "Gălățeanu", "Geamănu", "Habageriu",
                                "Hangiu", "Hedeșan", "Helgiu", "Henciu", "Herdeș", "Hergehelegiu", "Iacob", "Ieftimie",
                                "Ienășescu", "Ieremia", "Iftodi", "Deaconu", "Ignăteanu", "Ignătescu", "Ilinca",
                                "Ipătescu", "Iriminescu", "Isăceanu", "Ispir", "Istrătescu", "Ivănoiu", "Jderu",
                                "Jeleanu", "Jercălău", "Jidaru", "Jilăveanu", "Largu", "Lateș", "Lazariu", "Lăpușan",
                                "Lefterescu", "Leahă", "Licsandru", "Lișcă", "Lupșan", "Lunceanu", "Maftei", "Manea",
                                "Manole", "Mareș", "Marinescu", "Matei", "Măgureanu", "Medeleanu", "Micescu", "Miclea",
                                "Miclescu", "Mîndrescu", "Neacșu", "Neaga", "Nicolae", "Noica", "Nuțu", "Ogaru",
                                "Ogăraru", "Paicu", "Panait", "Pârvu", "Pleșan", "Poenaru", "Popa", "Popescu",
                                "Popovici", "Preda", "Radu", "Robu", "Roceanu", "Romașcanu", "Rontea", "Roșca",
                                "Roșianu", "Rotaru" };
                final String apellido = apellidos[1 + new Random().nextInt(150)];
                return apellido.toUpperCase();
        }

        /**
         * Obtiene un apellido final de la lista.
         * @return apellido al azar
         */
        public static String apellidoFinal3() {
                final String[] apellidos = { "Popescu", "Pop", "Popa", "Popescu", "Ionescu", "Samoilă", "Stan",
                                "Dumitrescu", "Savu", "Sabău", "Ioniță", "Marin", "Tudor", "Săceanu", "Barbu", "Nistor",
                                "Florea", "Săftoiu", "Dinu", "Georgescu", "Stoica", "Diaconu", "Diaconescu", "Sălăgean",
                                "Barbu", "Sălișteanu", "Nistor", "Sârbu", "Oprea", "Voinea", "Sebeșanu", "Albu",
                                "Secășanu", "Manole", "Cristea", "Silișteanu", "Stănescu", "Silvășanu", "Tomescu",
                                "Simionescu", "Ionescu", "Marinescu", "Georgescu", "Vasilescu", "Suciu", "Surdu",
                                "Gheorghiu", "Vasiliu", "Strungaru", "Stoica", "Stoicescu", "Tomescu", "Stîngă",
                                "Dedulescu", "Denciu", "Stejaru", "Viziru", "Stângă", "Dogaru", "Vișănescu", "Stanciu",
                                "Eftimie", "Stan", "Stamate", "Sitaru", "Firescu", "Simionescu", "Sima", "Sîrbu",
                                "Gabrilescu", "Slătineanu", "Sorescu", "Ionescu", "Georgescu", "Vasilescu", "Spătaru",
                                "Seciu", "Seceanu", "Tabără", "Talpă", "Tanasă", "Tapalagă", "Tatomirescu", "Tăbăcar",
                                "Tănase", "Tănăsie", "Tătar", "Tătărășanu", "Tâmplaru", "Teacă", "Deaconu", "Teașcă",
                                "Teișanu", "Teodoreanu", "Teodorescu", "Ticușan", "Timofe", "Toader", "Todea",
                                "Todoran", "Topliceanu", "Trașă", "Trușcă", "Tudor", "Uceanu", "Udescu", "Udrescu",
                                "Lazariu", "Ulea", "Ulmeanu", "Ungheanu", "Ungureanu", "Ungurușan", "Urâtu", "Urderean",
                                "Maftei", "Manea", "Manole", "Ureche", "Marinescu", "Matei", "Urleanu", "Ursu",
                                "Urzică", "Urziceanu", "Uțescu", "Vamăș", "Neacșu", "Vanța", "Nicolae", "Vasilache",
                                "Vasilescu", "Vasiliu", "Vatamanu", "Văcariu", "Panait", "Vădean", "Vărzariu",
                                "Vânătoru", "Popa", "Popescu", "Velcean", "Preda", "Radu", "Vereștiuc", "Vermeșan",
                                "Viașu", "Vidrașcu", "Roșca", "Vlașcu", "Vințu", "Niță", "Pop", "Popa", "Popescu",
                                "Ionescu", "Acsinte", "Stan", "Dumitrescu", "Băcan", "Gheorghiu", "Ioniță", "Marin",
                                "Tudor", "Dobre", "Barbu", "Nistor", "Florea", "Ene", "Dinu", "Georgescu", "Stoica",
                                "Diaconu", "Diaconescu", "Bicescu", "Bârgoveanu", "Băiceanu", "Nistor", "Căilnicean",
                                "Oprea", "Voinea", "Cârciumăreasa", "Albu", "Călescu", "Manole", "Cristea",
                                "Călimăceanu", "Stănescu", "Cîșlariu", "Tomescu", "Dacian", "Ionescu", "Marinescu",
                                "Georgescu", "Vasilescu", "Daniilescu", "Dalaban", "Gheorghiu", "Vasiliu", "Davidescu",
                                "Dășanu", "Stoicescu", "Tomescu", "Deaconu", "Dedulescu", "Denciu", "Despinescu",
                                "Dobriceanu", "Dodea", "Dogaru", "Theodorescu", "Dițulescu", "Eftimie", "Enăceanu",
                                "Fiertu", "Filipoiu", "Firescu", "Firuleasa", "Fîntînaru", "Gabrea", "Gabrilescu",
                                "Gafiței", "Garabagiu", "Ionescu", "Georgescu", "Vasilescu", "Podaru", "Gălățeanu",
                                "Geamănu", "Habageriu", "Hangiu", "Hedeșan", "Helgiu", "Henciu", "Herdeș",
                                "Hergehelegiu", "Iacob", "Ieftimie", "Ienășescu", "Ieremia", "Iftodi", "Deaconu",
                                "Ignăteanu", "Ignătescu", "Ilinca", "Ipătescu", "Iriminescu", "Isăceanu", "Ispir",
                                "Istrătescu", "Ivănoiu", "Jderu", "Jeleanu", "Jercălău", "Jidaru", "Jilăveanu", "Largu",
                                "Lateș", "Lazariu", "Lăpușan", "Lefterescu", "Leahă", "Licsandru", "Lișcă", "Lupșan",
                                "Lunceanu", "Maftei", "Manea", "Manole", "Mareș", "Marinescu", "Matei", "Măgureanu",
                                "Medeleanu", "Micescu", "Miclea", "Miclescu", "Mîndrescu", "Neacșu", "Neaga", "Nicolae",
                                "Noica", "Nuțu", "Ogaru", "Ogăraru", "Paicu", "Panait", "Pârvu", "Pleșan", "Poenaru",
                                "Popa", "Popescu", "Popovici", "Preda", "Radu", "Robu", "Roceanu", "Romașcanu",
                                "Rontea", "Roșca", "Roșianu", "Rotaru", "Niță", "Pop", "Popa", "Popescu", "Ionescu",
                                "Nemeș", "Stan", "Dumitrescu", "Dima", "Gheorghiu", "Ioniță", "Marin", "Tudor", "Dobre",
                                "Barbu", "Nistor", "Florea", "Ene", "Dinu", "Georgescu", "Stoica", "Diaconu",
                                "Diaconescu", "Mazilescu", "Ababei", "Aanei", "Nistor", "Mocanu", "Oprea", "Voinea",
                                "Dochioiu", "Albu", "Tabacu", "Manole", "Cristea", "Toma", "Stănescu", "Pușcașu",
                                "Tomescu", "Meceanu", "Ionescu", "Marinescu", "Georgescu", "Vasilescu", "Stoican",
                                "Maican", "Gheorghiu", "Vasiliu", "Pâclea", "Pecaru", "Stoicescu", "Tomescu", "Reaboiu",
                                "Rîciu", "Roateș", "Scafariu", "Sebeșanu", "Șdirea", "Șabac", "Theodorescu",
                                "Acatrinei", "Babageanu", "Bâclea", "Bâgioi", "Beceanu", "Bicăjan", "Bechir", "Băcean",
                                "Becleanu", "Bedean", "Bidirean", "Bilcan", "Ionescu", "Georgescu", "Vasilescu",
                                "Balagiu", "Bâtrânu", "Bălăcean", "Cabariu", "Cîju", "Căciuleanu", "Câmpureanu",
                                "Chelarașu", "Calcea", "Cîrje", "Cherchez", "Cheregi", "Cimponeriu", "Cămui",
                                "Cerbeanu", "Deaconu", "Diamandescu", "Diceanu", "Dăncescu", "Delcescu", "Fabriș",
                                "Ficior", "Federciuc", "Feișan", "Feleacă", "Filimonescu", "Gâdinceanu", "Giblea",
                                "Geană", "Ginculescu", "Găinariu", "Henciu", "Hăncescu", "Herdean", "Idriceanu",
                                "Ienășescu", "Iftimia", "Jeberean", "Jecan", "Jătăreanu", "Leacă", "Laciu", "Lăceanu",
                                "Logofătu", "Mecineanu", "Macavei", "Mândrescu", "Măgădan", "Miculeasa", "Meiroșu",
                                "Nădișan", "Năescu", "Nuță", "Nicoale", "Nanciu", "Nicolea", "Obăcescu", "Ogaru",
                                "Odobeștianu", "Ogrezeanu", "Pâcleanu", "Pârgaru", "Plăvănescu", "Pălean", "Pencescu",
                                "Păltinișanu", "Rebegeanu", "Robaciu", "Răcășanu", "Râșnoveanu", "Rudișteanu",
                                "Săbiescu", "Secăleanu", "Secașu", "Săcășean", "Scărlătescu" };
                final String apellido = apellidos[1 + new Random().nextInt(450)];
                return apellido.toUpperCase();
        }

        /**
         * Obtiene un cnp.
         * @return cnp al azar
         */
        public static String generaCnp(final String sex, final Date fecha, final Provincia prov) {
                String cnp = Constante.SPATIU;
                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                final String anString = sdf.format(fecha);

                final SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
                final String day = dayFormat.format(fecha);

                final SimpleDateFormat monthFormat = new SimpleDateFormat(Constante.MM);
                final String month = monthFormat.format(fecha);
                final String judet = generaJudet(prov.getIndicator());

                return cnp = genereazaCnp(sex, anString.substring(2), month, day, judet);
        }

        /**
         * Obtiene una sector de la lista.
         * @return sectoral azar
         */
        public static String genereazaCnp(final String sex, final String an, final String luna, final String ziua,
                        final String judet) {
                final int S = Integer.valueOf(sex) * 2;

                final int AA1 = (Integer.valueOf(an.substring(0, 1)) / 10) * 7;

                final int AA2 = (Integer.valueOf(an.substring(1, 2)) % 10) * 9;

                final int LL1 = (Integer.valueOf(luna.substring(0, 1)) / 10) * 1;

                final int LL2 = (Integer.valueOf(luna.substring(1, 2)) % 10) * 4;

                final int ZZ1 = (Integer.valueOf(ziua.substring(0, 1)) / 10) * 6;

                final int ZZ2 = (Integer.valueOf(ziua.substring(1, 2)) % 10) * 3;

                final int JJ1 = (Integer.valueOf(judet.substring(0, 1)) / 10) * 5;

                final int JJ2 = (Integer.valueOf(judet.substring(1, 2)) % 10) * 8;

                final String nrGenerat = nrGenerat();
                final int NNN1 = (Integer.valueOf(nrGenerat.substring(0, 1)) / 100) * 2;

                final int NNN2 = (Integer.valueOf(nrGenerat.substring(1, 2)) / 10) * 7;

                final int NNN3 = (Integer.valueOf(nrGenerat.substring(2, 3)) % 10) * 9;

                final int cifraControl = S + AA1 + AA2 + LL1 + LL2 + ZZ1 + ZZ2 + JJ1 + JJ2 + NNN1 + NNN2 + NNN3;

                int cifraControlFinal;
                final int rest = cifraControl % 11;
                if (rest == 10) {

                        cifraControlFinal = 1;

                }
                else {

                        cifraControlFinal = rest;

                }
                final String controlFinal = Integer.toString(cifraControlFinal);

                final StringBuilder cnpValid = new StringBuilder("").append(sex)

                                .append(an).append(luna).append(ziua)

                                .append(judet).append(nrGenerat).append(controlFinal);

                return cnpValid.toString();
        }

        /**
         * Obtiene una sector de la lista.
         * @return sectoral azar
         */
        public static String nrGenerat() {
                final Random random = new Random();

                final int min = 1;

                final int max = 999;

                final int nrGenerat = random.nextInt(max - min) + min;

                final DecimalFormat formatare = new DecimalFormat("000");

                final String getNNN = formatare.format(nrGenerat);
                return getNNN;
        }

        /**
         * Obtiene una sector de la lista.
         * @return sectoral azar
         */
        public static String sector() {
                final String[] sectoare = { "41", "41", "42", "43", "44", "45", "46" };
                final String sector = sectoare[1 + new Random().nextInt(6)];
                return sector;
        }

        /**
         * Obtiene un cnp.
         * @return cnp al azar
         */
        public static String generaJudet(final String prov) {
                String judet = Constante.SPATIU;
                if (prov.equals(Constante.AB)) {
                        judet = "01";
                }
                else if (prov.equals(Constante.AR)) {
                        judet = "02";
                }
                else if (prov.equals(Constante.AG)) {
                        judet = "03";
                }
                else if (prov.equals(Constante.BC)) {
                        judet = "04";
                }
                else if (prov.equals(Constante.BH)) {
                        judet = "05";
                }
                else if (prov.equals(Constante.BN)) {
                        judet = "06";
                }
                else if (prov.equals(Constante.BT)) {
                        judet = "07";
                }
                else if (prov.equals(Constante.BV)) {
                        judet = "08";
                }
                else if (prov.equals(Constante.BR)) {
                        judet = "09";
                }
                else if (prov.equals(Constante.BZ)) {
                        judet = "10";
                }
                else if (prov.equals(Constante.CS)) {
                        judet = "11";
                }
                else if (prov.equals(Constante.CJ)) {
                        judet = "12";
                }
                else if (prov.equals(Constante.CT)) {
                        judet = "13";
                }
                else if (prov.equals(Constante.CV)) {
                        judet = "14";
                }
                else if (prov.equals(Constante.DB)) {
                        judet = "15";
                }
                else if (prov.equals(Constante.DJ)) {
                        judet = "16";
                }
                else if (prov.equals(Constante.GL)) {
                        judet = "17";
                }
                else if (prov.equals(Constante.GJ)) {
                        judet = "18";
                }
                else if (prov.equals(Constante.HR)) {
                        judet = "19";
                }
                else if (prov.equals(Constante.HD)) {
                        judet = "20";
                }
                else if (prov.equals(Constante.IL)) {
                        judet = "21";
                }
                else if (prov.equals(Constante.IS)) {
                        judet = "22";
                }
                else if (prov.equals(Constante.IF)) {
                        judet = "23";
                }
                else if (prov.equals(Constante.MM)) {
                        judet = "24";
                }
                else if (prov.equals(Constante.MH)) {
                        judet = "25";
                }
                else if (prov.equals(Constante.MS)) {
                        judet = "26";
                }
                else if (prov.equals(Constante.NT)) {
                        judet = "27";
                }
                else if (prov.equals(Constante.OT)) {
                        judet = "28";
                }
                else if (prov.equals(Constante.PH)) {
                        judet = "29";
                }
                else if (prov.equals(Constante.SM)) {
                        judet = "30";
                }
                else if (prov.equals(Constante.SJ)) {
                        judet = "31";
                }
                else if (prov.equals(Constante.SB)) {
                        judet = "32";
                }
                else if (prov.equals(Constante.SV)) {
                        judet = "33";
                }
                else if (prov.equals(Constante.TL)) {
                        judet = "34";
                }
                else if (prov.equals(Constante.TM)) {
                        judet = "35";
                }
                else if (prov.equals(Constante.TL)) {
                        judet = "36";
                }
                else if (prov.equals(Constante.VS)) {
                        judet = "37";
                }
                else if (prov.equals(Constante.VL)) {
                        judet = "38";
                }
                else if (prov.equals(Constante.VN)) {
                        judet = "39";
                }
                else if (prov.equals(Constante.CL)) {
                        judet = "51";
                }
                else if (prov.equals(Constante.GR)) {
                        judet = "52";
                }
                else {
                        judet = sector();

                }
                return judet;
        }

        /**
         * Obtiene el código postal.
         * @return codigo postal aleatorio
         */
        public static String getCodigoPostal() {
                return getRandomChars(NUMBERS, 5);
        }

        /**
         * Obtiene el código postal.
         * @return codigo postal aleatorio
         */
        public static String getOption() {
                return getRandomChars(NUMBERS, 1);
        }

        /**
         * Obtiene el código postal.
         * @return codigo postal aleatorio
         */
        public static String getNumeros() {
                return getRandomChars(NUMBERS, 3);
        }

        /**
         * Obtiene el número de cuerpo.
         * @return numero cuerpo
         */
        public static String getCuerpoNumber() {
                return getRandomChars(CUERPONUMBERS, 1);
        }

        /**
         * Obtener DNI.
         * @return dni + letra
         */
        public static String getDni() {
                return getPinLetters() + " " + getPinNumber();
        }

        /**
         * Obtiene número de identidad.
         * @return número aleatorio
         */
        public static String getIdentidad() {
                return getRandomChars(NUMBERS2, 1);
        }

        /**
         * Obtiene numero id schengen.
         * @return número aleatorio
         */
        public static String getNumberIdShen() {
                return getRandomChars(NUMBERS, 10);
        }

        /**
         * Obtiene número de alias.
         * @return numero aleatorio
         */
        public static String getNumeroAlias() {
                return getRandomChars(NUMBERS, 4);
        }

        /**
         * Obtiene el número de calle.
         * @return número de calle
         */
        public static String getNumeroCalle() {
                return getRandomChars(NUMBERS, 2);
        }

        /**
         * Obtiene orden de búsqueda.
         * @return numero aleatorio
         */
        public static String getOrdenBus() {
                return getRandomChars(NUMBERS, 3);
        }

        /**
         * Obtener letra de pin.
         * @return letra aleatoria.
         */
        public static String getPinLetters() {
                return getRandomChars(LETTERS, 2);
        }

        /**
         * Obtiene el número pin.
         * @return número aleatorio
         */
        public static String getPinNumber() {
                return getRandomChars(NUMBERS, 6);
        }

        /**
         * Obtiene un subtipo de denuncia al azar.
         * @return subtipo de denuncia
         */
        public static String getNumero() {
                return NUMEROS[1 + new Random().nextInt(19)];
        }

        /**
         * Obtiene letras aleatorias.
         * @param key clave
         * @param length tamaño
         * @return Cadena de caracteres
         */
        public static String getRandomChars(final String key, final int length) {
                final StringBuilder numeroCalle = new StringBuilder();
                final Random r = new Random();

                for (int i = 0; i < length; i++) {
                        numeroCalle.append(key.charAt(r.nextInt(key.length())));
                }
                return numeroCalle.toString();
        }

        /**
         * Obtiene un subtipo de denuncia al azar.
         * @return subtipo de denuncia
         */
        public static String getSubtipoDenunciaNumber() {
                return SUBTIPOS[1 + new Random().nextInt(9)];
        }

        /**
         * Obtiene el número de cuerpo.
         * @return numero cuerpo
         */
        public static String getTelefon() {
                String telefono = null;
                final String resto = getRandomChars(NUMBERS, 7);
                telefono = "07".concat(resto);
                return telefono;
        }

        /**
         * Obtiene tipo de denuncia.
         * @return tipo de denuncia aleatoria.
         */
        public static String getTipoDenunciaNumber() {
                return getRandomChars(TIPODENUNCIA, 1);
        }

        /**
         * Obtener unidad.
         * @return unidad + SIAD1
         */
        public static String getUnidad() {
                return getUnidadNumber() + "SIAD1";
        }

        /**
         * Obtener número de unidad.
         * @return número unidad aleatorio
         */
        public static String getUnidadNumber() {
                return getRandomChars(NUMBERS, 13);
        }

        /**
         * Obtiene un nombre final de la lista.
         * @return nombre al azar
         */
        public static String meserii() {
                final String[] nombres = { "Mecanic auto", "Inginer", "Medic", "Ospătar", "Avocat", "Şofer", "Poliţist",
                                "Specialist IT", "Bucătar", "Comerciant-vânzător", "Tinichigiu-vopsitor auto",
                                "Brutar-patisier", "Profesor", "Economist", "Manager", "Contabil", "Profesor",
                                "Arhitect", "Funcţionar bancar", "Lucrător comercial", "Asistent medical",
                                "Agent de securitate", "Manipulant mărfuri", "Constructor", "Șomer", "Agricultură",
                                "Apicultură", "Zootecnie", "Apicultura", "Liber profesioneist", "Muzician", "Tâmplar",
                                "Hotelărie", "Comerț", "Operatori de instalații", "Pompier", "Industria prelucrătoare",
                                "Industria miniera", "Industria chimică", "Stomatolog", "Măcelar", "Tehnician",
                                "Ajutor in producţie", "Brutar", "Bucătar", "Casier", "Chelner", "Coafeză/Frizer",
                                "Croitor", "Electrician", "Grădinar" };
                return nombres[1 + new Random().nextInt(50)];

        }

        /**
         * Obtiene un nombre final de la lista.
         * @return nombre al azar
         */
        public static String nombreFinal() {
                final String[] nombres = { "Andreea", "Elena", "Alexandra", "Denisa", "Ioana", "Gabriela", "Ștefania",
                                "Daria", "Mihaela", "Gabriela", "Eugenia", "Cristina", "Bianca", "Georgiana", "Nicleta",
                                "Teodora", "Diana", "Alexia", "Ionela", "Maria", "Cristina", "Viorica", "Iulia",
                                "Ileana", "Tudora", "Ana", "Florentina", "Larisa", "Sara", "Miruna", "Olga", "Amelia",
                                "Adela", "Anastasia", "Roxana", "Rebeca", "Raluca", "Gina", "Georgeta", "Sofia",
                                "Natalia", "Irina", "Antonia", "Daciana", "Manuela", "Adelina", "Dana Maria", "Andra",
                                "Alina", "Delia", "Isabela", "Anița", "Adina", "Andreea", "Angela", "Aurelia",
                                "Camelia", "Carmen", "Cătălina", "Cecilia", "Claudia", "Doina", "Dumitra", "Elvira",
                                "Eugenia", "Gabriela", "Georgeta", "Georgiana", "Lacramiora", "Ilinca", "Jana", "Laura",
                                "Lavinia", "Lia", "Liliana", "Luminița", "Margareta", "Melania", "Mirela", "Monica",
                                "Nicoleta", "Oana", "Olimpia", "Olivia", "Paula", "Petronela", "Ramona", "Rodica",
                                "Ruxandra", "Silvia", "Sorina", "Ștefania", "Stela", "Tatiana", "Valentina", "Vasilica",
                                "Victoria", "Violeta", "Virginia", "Viviana", "Romanița" };
                return nombres[1 + new Random().nextInt(90)];

        }

        /**
         * Obtiene un nombre final de la lista.
         * @return nombre al azar
         */
        public static String nombreFinalHombre() {
                final String[] nombres = { "Mihai", "Ion", "Vasile", "Marius", "Grigore", "Adrian", "Iulian", "Marian",
                                "Nicolae", "Gabriel", "Eugen", "Cristi", "Cristian", "Tudor", "Bogdan", "Ionel",
                                "Claudiu", "Ovidiu", "Dan", "Marin", "Cristian", "Viorel", "Iulian", "David", "Tudorel",
                                "Darius", "Cornel", "Marius", "Andrei", "Tudorel", "Denis", "Robert", "Sebastian",
                                "Luca", "Luis", "David", "Ioan", "Rareș", "Anghel", "Nicușor", "Costin", "Cosmin",
                                "Alin", "Anton", "Aurel", "Călin", "Cătălin", "Cezar", "Ciprian", "Constantin",
                                "Costel", "Daniel", "Dorin", "Doru", "Dumitru", "Emil", "Eugen", "Filip", "Florin",
                                "Gabriel", "George", "Gheorghe", "Grigore", "Horia", "Iacob", "Ioan", "Ion", "Iulian",
                                "Ionel", "Laurențiu", "Liviu", "Marin", "Marius", "Mihai", "Mircea", "Nelu", "Nicolae",
                                "Octavian", "Ovidiu", "Panait", "Paraschiv", "Petre", "Radu", "Răzvan", "Remus",
                                "Robert", "Sebastian", "Sergiu", "Ștefan", "Silviu", "Teodor" };
                return nombres[1 + new Random().nextInt(90)];

        }

        /**
         * Obtiene un nombre de calle final de la lista.
         * @return nombre de calle al azar
         */
        public static String nombresCalleFinal() {
                final String[] nombresCalle = { "Aleea Băişoara", "Aleea Cetinei", "Aleea Ghindei",
                                "Aleea Sf. Capistrano", "Aleea Sf. Eugeniu", "Aleea Sportivilor", "Aleea Stejarilor",
                                "Aleea Vilelor", "B-dul 1 Decembrie 1918", "B-dul Ferdinand I", "B-dul Horea",
                                "B-dul Încoronării", "B-dul Revoluţiei 1989", "Bulevardul Transilvaniei",
                                "Calea Ciugudului", "Calea Labului", "Calea Moţilor", "Esplanada Încoronării",
                                "Esplanada Obeliscului", "Piaţa Alessandria", "Piaţa Amfiteatru", "Piaţa Cetăţii",
                                "Piaţa Consiliul Europei", "Piaţa Episcop Emilian Birdaş", "Piaţa Ion I. C. Brătianu",
                                "Piaţa Iuliu Maniu", "Piaţa Mihai Viteazul", "Piaţa Tricolorului", "Piaţa Visconti",
                                "Şoseaua de Centură", "Strada 11 Iunie", "Strada 9 Mai", "Strada Abrudului",
                                "Strada Afrodita", "Strada Agapia", "Strada Alba Regia", "Strada Albac",
                                "Strada Albăstrelelor", "Strada Albinelor", "Strada Alcala de Henares",
                                "Strada Alecu Russo", "Strada Alessandria", "Strada Alexandru cel Bun",
                                "Strada Alexandru Ioan Cuza", "Strada Alexandru Lăpuşneanu",
                                "Strada Alexandru Odobescu", "Strada Alexandru Papiu Ilarian",
                                "Strada Alexandru Rosetti", "Strada Alexandru Vlahuţă", "Strada Alexandru Zane",
                                "Strada Almaşului", "Strada Aluniş", "Strada Alunului", "Strada Ampoiţa",
                                "Strada Ampoiului", "Strada Amsberg", "Strada Amurgului", "Strada Ana Aslan",
                                "Strada Ana Ipătescu", "Strada Andrei Mureşanu", "Strada Anghel Saligny",
                                "Strada Anina", "Strada Antigona", "Strada Anton Pann", "Strada Apollo",
                                "Strada Apulum", "Strada Apuseni", "Strada Aradului", "Strada Arbuştilor",
                                "Strada Ardealului", "Strada Arieşeni", "Strada Arieşului", "Strada Arinilor",
                                "Strada Armoniei", "Strada Arnsberg", "Strada Aromei", "Strada Aron Pumnul",
                                "Strada Arsenie Boca", "Strada Arţarului", "Strada Artemis", "Strada Atelierului",
                                "Strada Atena", "Strada Augustin Bena", "Strada Aurel Vlaicu", "Strada Aurorei",
                                "Strada Avram Iancu", "Strada Avrig", "Strada Azuga", "Strada Azur",
                                "Strada Baba Novac", "Strada Baladei", "Strada Banatului", "Strada Barbu Catargiu",
                                "Strada Barbu Lăutaru", "Strada Barbu Ştefănescu Delavrancea", "Strada Basarabiei",
                                "Strada Basmului", "Strada Bayonne", "Strada Beclean", "Strada Bega", "Strada Bicaz",
                                "Strada Biruinţei", "Strada Bistra", "Strada Blandiana", "Strada Bobâlna",
                                "Strada Bogdan Petriceicu Haşdeu", "Strada Brădişor", "Strada Bradului",
                                "Strada Brânduşei", "Strada Bronzului", "Strada Bucegi", "Strada Bucium",
                                "Strada Bucovinei", "Strada Bucureşti", "Strada Bujorului", "Strada Bulza",
                                "Strada Bunta", "Strada Buşteni", "Strada Busuiocului", "Strada Buziaş",
                                "Strada Cabanei", "Strada Caisului", "Strada Călăraşilor", "Strada Călimăneşti",
                                "Strada Calistrat Hogaş", "Strada Călugăreni", "Strada Călugărilor", "Strada Calypso",
                                "Strada Camil Baltazar", "Strada Camil Petrescu", "Strada Câmpeni", "Strada Câmpului",
                                "Strada Cantonului", "Strada Căpâlna", "Strada Căprioarei", "Strada Cărăbuşului",
                                "Strada Caraiman", "Strada Carol Davila", "Strada Carpaţi", "Strada Carpenului",
                                "Strada Carul Mare", "Strada Carul Mic", "Strada Casandra", "Strada Castanului",
                                "Strada Cătinei", "Strada Cavnic", "Strada Cedrului", "Strada Cenade",
                                "Strada Cerbului", "Strada Cetăţii", "Strada Christian Tell", "Strada Cigaş",
                                "Strada Cindrel", "Strada Ciobănaşului", "Strada Ciocârliei",
                                "Strada Ciprian Porumbescu", "Strada Cireşului", "Strada Ciucaşului", "Strada Clăbucet",
                                "Strada Cloşca", "Strada Coastei", "Strada Cocorilor", "Strada Colinelor",
                                "Strada Colţeşti", "Strada Comarnic", "Strada Condeiului", "Strada Constantin Brâncuşi",
                                "Strada Constantin Daicoviciu", "Strada Constantin Galeriu", "Strada Constantin Noica",
                                "Strada Cordovanilor", "Strada Cornel Medrea", "Strada Corneliu Baba",
                                "Strada Corniştei", "Strada Costache Negruzzi", "Strada Costache Romanescu",
                                "Strada Covasna", "Strada Cozia", "Strada Crai Nou", "Strada Craivei", "Strada Crişan",
                                "Strada Crişanei", "Strada Cronos", "Strada Cucului", "Strada Cumpenei",
                                "Strada Curcubeului", "Strada Cutina", "Strada Dacilor", "Strada Dafinului",
                                "Strada Daliei", "Strada David Prodan", "Strada Dealul Furcilor", "Strada Dealului",
                                "Strada Decebal", "Strada Diana", "Strada Digului", "Strada Dimitrie Anghel",
                                "Strada Dimitrie Bolintineanu", "Strada Dimitrie Cantemir", "Strada Dimitrie Paciurea",
                                "Strada Dinu Lipatti", "Strada Dobrogei", "Strada Doctor Aurel Lazăr",
                                "Strada Doctor Aurel Vlad", "Strada Doctor Ioan Raţiu", "Strada Doinei", "Strada Dorna",
                                "Strada Dr. Aurel Lazăr", "Strada Dr. Cornel Ciugudean", "Strada Dr. Ioan Dragomir",
                                "Strada Drăgăşani", "Strada Drâmbarului", "Strada Dumbrăviţei", "Strada Duzilor",
                                "Strada Eftimie Murgu", "Strada Elena Cuza", "Strada Elixirului", "Strada Emil Isac",
                                "Strada Emil Racoviţă", "Strada Emile Zola", "Strada Energiei",
                                "Strada Episcop Emilian Birdaş", "Strada Eugen Ionescu", "Strada Eugen Lovinescu",
                                "Strada Fabricilor", "Strada Făgăraş", "Strada Făgetului", "Strada Fagului",
                                "Strada Fântânele", "Strada Feneş", "Strada Fericet", "Strada Flora", "Strada Florilor",
                                "Strada Fortuna", "Strada Francisca", "Strada Frasinului", "Strada Fraţii Jderi",
                                "Strada Frederic Mistral", "Strada Frunzei", "Strada Fuiorului",
                                "Strada Gabriel Bethlen", "Strada Gârda", "Strada Gardeniei", "Strada Gării",
                                "Strada Garoafei", "Strada Garoafelor", "Strada Geamăna", "Strada Gemenilor",
                                "Strada Gemina", "Strada George Bacovia", "Strada George Bariţiu",
                                "Strada George Călinescu", "Strada George Coşbuc", "Strada George Emil Palade",
                                "Strada George Enescu", "Strada George Sand", "Strada George Topârceanu",
                                "Strada Gheorghe Doja", "Strada Gheorghe Magheru", "Strada Gheorghe Marinescu",
                                "Strada Gheorghe Petraşcu", "Strada Gheorghe Pop de Băseşti", "Strada Gheorghe Şincai",
                                "Strada Gheorghe Sion", "Strada Gheorghe Ţiţeica", "Strada Ghioceilor",
                                "Strada Ghişiţelor", "Strada Gilău", "Strada Gladiolelor", "Strada Gloriei",
                                "Strada Gorunului", "Strada Govora", "Strada Grădinilor", "Strada Grâului",
                                "Strada Greierului", "Strada Grigore Antipa", "Strada Grigore Moisil",
                                "Strada Grigore Vieru", "Strada Gruia Novac", "Strada Gutuiului", "Strada Haiducilor",
                                "Strada Haţeg", "Strada Hebe", "Strada Heleşteului", "Strada Henri Coandă",
                                "Strada Hera", "Strada Hobiţa", "Strada Holdei", "Strada Horezu", "Strada Huedin",
                                "Strada Humuleşti", "Strada I. C. Brătianu", "Strada Iancu Jianu", "Strada Iaşilor",
                                "Strada Iasomiei", "Strada Iederii", "Strada Ienupărului", "Strada Ilie Cleopa",
                                "Strada Ineu", "Strada Inului", "Strada Ioan Alexandru", "Strada Ioan Buteanu",
                                "Strada Ioan Petru Culianu", "Strada Ioan Slavici", "Strada Ion Andreescu",
                                "Strada Ion Agârbiceanu", "Strada Ion Arion", "Strada Ion Barbu",
                                "Strada Ion Budai-Deleanu", "Strada Ion Creangă", "Strada Ion Ghica",
                                "Strada Ion Heliade Rădulescu", "Strada Ion Lăncrănjan", "Strada Ion Luca Caragiale",
                                "Strada Ion Mincu", "Strada Ion Minulescu", "Strada Ionel Pop",
                                "Strada Ionel Teodoreanu", "Strada Iosif Jumanca", "Strada Iosif Sârbu",
                                "Strada Iosif Vulcan", "Strada Ipoteşti", "Strada Iris", "Strada Irisului",
                                "Strada Izlaz", "Strada Izvorul Alb", "Strada Izvorului", "Strada Jidvei",
                                "Strada Jules Verne", "Strada Jupiter", "Strada La Dinamită", "Strada La Recea",
                                "Strada Lăcrimioarei", "Strada Lacului", "Strada Lalelelor", "Strada Lancrăm",
                                "Strada Lăpuşului", "Strada Lavandei", "Strada Libertăţii", "Strada Liceului",
                                "Strada Liliacului", "Strada Lipova", "Strada Lipovenilor", "Strada Livezii",
                                "Strada Liviu Rebreanu", "Strada Lombardia", "Strada Lotru", "Strada Lucian Blaga",
                                "Strada Lugoj", "Strada Luminilor", "Strada Luncii", "Strada Lupa Capitolina",
                                "Strada Lupeni", "Strada Lupşa", "Strada Măceşului", "Strada Magnoliei",
                                "Strada Măgurei", "Strada Maia", "Strada Maior Tiberiu Iancu", "Strada Mănăstirii",
                                "Strada Maramureşului", "Strada Mărăşeşti", "Strada Mărăşti", "Strada Marcus Aurelius",
                                "Strada Marin Preda", "Strada Marin Sorescu", "Strada Marte", "Strada Măslinului",
                                "Strada Matei Basarab", "Strada Matei Corvin", "Strada Merilor", "Strada Merişor",
                                "Strada Meteş", "Strada Mierlei", "Strada Mihai Eminescu", "Strada Mihai Viteazul",
                                "Strada Mihail Jora", "Strada Mihail Kogălniceanu", "Strada Mihail Sadoveanu",
                                "Strada Milenium", "Strada Militari", "Strada Minerva", "Strada Mioriţei",
                                "Strada Mirajului", "Strada Mircea cel Bătrân", "Strada Mircea Eliade",
                                "Strada Miron Costin", "Strada Mitologiei",
                                "Strada Mitropolit Alexandru Şterca Şuluţiu", "Strada Mitropolit Andrei Şaguna",
                                "Strada Mitropolit Simion Ştefan", "Strada Modena", "Strada Mogoş", "Strada Mohorului",
                                "Strada Moldovei", "Strada Molidului", "Strada Moneasa", "Strada Morii",
                                "Strada Muncel", "Strada Munteniei", "Strada Mureşului", "Strada Muşeţelului",
                                "Strada Muzeului", "Strada Muzicanţilor", "Strada Nada Florilor", "Strada Nădlac",
                                "Strada Nanului", "Strada Napoca", "Strada Negoiu", "Strada Negrileasa",
                                "Strada Negru Vodă", "Strada Nemesis", "Strada Neptun", "Strada Nichita Stănescu",
                                "Strada Nicolae Bălcescu", "Strada Nicolae Creţulescu", "Strada Nicolae Golescu",
                                "Strada Nicolae Grigorescu", "Strada Nicolae Labiş", "Strada Nicolae Linca",
                                "Strada Nicolae Pleşoianu", "Strada Nicolae Titulescu", "Strada Nicolae Tonitza",
                                "Strada Nina Cassian", "Strada Nopţii", "Strada Nucilor", "Strada Nufărului",
                                "Strada Oaşa", "Strada Ocnele Mari", "Strada Ocoliş", "Strada Octavian Goga",
                                "Strada Octavian Paler", "Strada Odiseea", "Strada Oituz", "Strada Olăneşti",
                                "Strada Olimp", "Strada Olteniei", "Strada Orfeu", "Strada Orhideelor",
                                "Strada Orizontului", "Strada Orlea", "Strada Oscar Han", "Strada Otilia Cazimir",
                                "Strada Păcii", "Strada Padiş", "Strada Pădurii", "Strada Păltiniş",
                                "Strada Paltinului", "Strada Panait Istrati", "Strada Pandora", "Strada Păpădiei",
                                "Strada Parâng", "Strada Pârâul Iovului", "Strada Pârâul lui Stoica", "Strada Pârâului",
                                "Strada Păuliş", "Strada Peleaga", "Strada Pepinierei", "Strada Perla Harghitei",
                                "Strada Pescarilor", "Strada Petrache Poenaru", "Strada Petre Ispirescu",
                                "Strada Petreşti", "Strada Petroşani", "Strada Petru Cercel", "Strada Petru Dobra",
                                "Strada Petru Maior", "Strada Petuniei", "Strada Piatra Corbului",
                                "Strada Piatra Craiului", "Strada Pictor Sava Henţia", "Strada Piersicului",
                                "Strada Pietrar", "Strada Pietroasa", "Strada Pinului", "Strada Platanului",
                                "Strada Plevnei", "Strada Plopilor", "Strada Poarta Sărutului",
                                "Strada Poiana Narciselor", "Strada Poiana Ruscăi", "Strada Poiana Vadului",
                                "Strada Poieniţei", "Strada Poligonului", "Strada Ponor", "Strada Popa Vasile Claudiu",
                                "Strada Postăvarului", "Strada Predeal", "Strada Prieteniei", "Strada Primăverii",
                                "Strada Prislop", "Strada Profesor Eugen Hulea", "Strada Putna", "Strada Răchitei",
                                "Strada Rădeşti", "Strada Radna", "Strada Râmeţ", "Strada Rânca", "Strada Rândunelelor",
                                "Strada Rapsodiei", "Strada Rariştei", "Strada Răşinari", "Strada Râului",
                                "Strada Râuşor", "Strada Răzoare", "Strada Răzorului", "Strada Recaş",
                                "Strada Regimentul V Vânători", "Strada Regina Maria", "Strada Republicii",
                                "Strada Reteag", "Strada Retezat", "Strada Rodiei", "Strada Rodnei", "Strada Rogozului",
                                "Strada Rohia", "Strada Roica", "Strada Rojomal", "Strada Roma", "Strada Romaniţa",
                                "Strada Roşia Montană", "Strada Rotundă", "Strada Rozelor", "Strada Sabin Bălaşa",
                                "Strada Sadu", "Strada Sălaj", "Strada Salcâmului", "Strada Salciei", "Strada Salviei",
                                "Strada Samuel Micu", "Strada Şardului", "Strada Sarmigezetusa", "Strada Saturn",
                                "Strada Scărişoara", "Strada Scurtă", "Strada Sebeşului", "Strada Secarei",
                                "Strada Selişte", "Strada Septimiu Albini", "Strada Septimius Severus",
                                "Strada Serenadei", "Strada Sergiu Celibadache", "Strada Şesuri", "Strada Sibiel",
                                "Strada Sihastrului", "Strada Simion Balint", "Strada Simion Bărnuţiu",
                                "Strada Sînzienelor", "Strada Siretului", "Strada Şiria", "Strada Sitarului",
                                "Strada Sliven", "Strada Soarelui", "Strada Socului", "Strada Sofia", "Strada Sohodol",
                                "Strada Şoimului", "Strada Someş", "Strada Sovata", "Strada Spicului",
                                "Strada Spiru Haret", "Strada Stadionului", "Strada Stăneşti", "Strada Stânjenelului",
                                "Strada Ştefan Augustin Doinaş", "Strada Ştefan cel Mare", "Strada Ştefan Luchian",
                                "Strada Ştefan Pascu", "Strada Stejarului", "Strada Streiului", "Strada Strunga",
                                "Strada Stufului", "Strada Suceviţa", "Strada Şugag", "Strada Şurianului",
                                "Strada Taberei", "Strada Take Ionescu", "Strada Tălmaciu", "Strada Târgului",
                                "Strada Ţarina", "Strada Tarniţa", "Strada Teilor", "Strada Theodor Pallady",
                                "Strada Tibru", "Strada Tihuţa", "Strada Timişului", "Strada Timotei Cipariu",
                                "Strada Tinereţii", "Strada Tineretului", "Strada Tisa", "Strada Tismana",
                                "Strada Titu Maiorescu", "Strada Topliţei", "Strada Toporaşilor", "Strada Traian",
                                "Strada Traian Vuia", "Strada Trandafirilor", "Strada Transilvaniei", "Strada Trestiei",
                                "Strada Tribunalului", "Strada Trifoiului", "Strada Tuberozelor",
                                "Strada Tudor Arghezi", "Strada Tudor Vladimirescu", "Strada Tulnicului",
                                "Strada Turnătoriei", "Strada Unirii", "Strada Uranus", "Strada Uricani",
                                "Strada Vacanţei", "Strada Vadului", "Strada Văii", "Strada Vâlcele",
                                "Strada Valea Aramei", "Strada Valea Argintului", "Strada Valea Aurului",
                                "Strada Valea Devii", "Strada Valea Frumoasei", "Strada Valea Mică",
                                "Strada Valea Popii", "Strada Valeriu Braniştei", "Strada Vânătorilor", "Strada Varese",
                                "Strada Vârtop", "Strada Vasile Alecsandri", "Strada Vasile Goldiş", "Strada Veneţia",
                                "Strada Venus", "Strada Verona", "Strada Vesta", "Strada Viadana",
                                "Strada Victor Babeş", "Strada Victor Hugo", "Strada Victoriei", "Strada Vidra",
                                "Strada Viilor", "Strada Violetelor", "Strada Viorelelor", "Strada Vlădeasa",
                                "Strada Voineasa", "Strada Voroneţ", "Strada Vrejului", "Strada Zarandului",
                                "Strada Zărneşti", "Strada Zarzarului", "Strada Zăvoi", "Strada Zefirului",
                                "Strada Zenit", "Strada Zlatnei", "Strada Zori de Zi", "StradaTheodor Aman" };
                final String nombreCalle = nombresCalle[1 + new Random().nextInt(650)];
                return nombreCalle;
        }

        /**
         * Obtiene un nombre final de la lista.
         * @return nombre al azar
         */
        public static String nombreSex() {
                final String[] nombres = { "MAN", "WOMAN" };
                return nombres[1 + new Random().nextInt(1)];

        }

        /**
         * Subtipos de denuncia.
         */
        public static String nombresMail() {
                final String[] mails = { "@gmail.com", "@hotmail.com", "@yahoo.com" };
                final String mail = mails[1 + new Random().nextInt(2)];
                return mail;

        }

        /**
         * Obtiene una fecha aleatoria.
         * @return fecha
         */
        public static Date obtenerFechaNastere() {
                Calendar fecha;
                Random aleatorio;
                aleatorio = new Random();
                fecha = Calendar.getInstance();
                fecha.set(aleatorio.nextInt(41) + 1959, aleatorio.nextInt(12) + 1, aleatorio.nextInt(30) + 1);
                new SimpleDateFormat(Constante.FORMATDATE);
                return fecha.getTime();
        }

        /**
         * Obtiene una fecha aleatoria.
         * @return fecha
         */
        public static Date obtenerFechaRegistru() {
                Calendar fecha;
                Random aleatorio;
                aleatorio = new Random();
                fecha = Calendar.getInstance();
                fecha.set(2018, aleatorio.nextInt(11) + 1, aleatorio.nextInt(30) + 1);
                new SimpleDateFormat(Constante.FORMATDATE);
                return fecha.getTime();
        }

        /**
         * Obtiene una hora aleatoria.
         * @return hora
         */
        public static Date obtenerHoraNastere() {
                Calendar hora;
                Random aleatorio;
                aleatorio = new Random();
                hora = Calendar.getInstance();
                hora.set(aleatorio.nextInt(41) + 1959, aleatorio.nextInt(12) + 1, aleatorio.nextInt(30) + 1);
                new SimpleDateFormat("hh/mm");
                return hora.getTime();
        }

        /**
         * Obtiene una hora aleatoria.
         * @return hora
         */
        public static Date obtenerHoraRegistru() {
                Calendar hora;
                Random aleatorio;
                aleatorio = new Random();
                hora = Calendar.getInstance();
                hora.set(2018, aleatorio.nextInt(11) + 1, aleatorio.nextInt(30) + 1);
                new SimpleDateFormat("hh/mm");
                return hora.getTime();
        }

        /**
         * Obtiene un pais final de la lista.
         * @return pais al azar
         */
        public static String paisFinal() {
                final String[] paises = { "ESPAÑA", "ALEMANIA", "RUSIA", "INGLATERA", "ESPAÑA", "ESPAÑA", "INGLATERA",
                                "RUSIA", "ESPAÑA", "ESPAÑA", "ESPAÑA", "ESPAÑA", "ESPAÑA", "ESPAÑA", "ESPAÑA", "ESPAÑA",
                                "ESPAÑA", "ESPAÑA", "ESPAÑA", "ALEMANIA", "ESPAÑA", "ESPAÑA", "ESPAÑA", "ESPAÑA",
                                "ESPAÑA", "ESPAÑA" };

                return paises[1 + new Random().nextInt(25)];
        }

        /**
         * Obtiene una provincia final de la lista.
         * @return provincia al azar
         */
        public static String provinciasFinal() {
                final String[] provincias = { Constante.AB, Constante.AB, Constante.AR, Constante.AG, Constante.BC, Constante.BH, Constante.BN, Constante.BT, Constante.BV, Constante.BR, Constante.B, Constante.BZ,
                                Constante.CS, Constante.CL, Constante.CJ, Constante.CT, Constante.CV, Constante.DB, Constante.DJ, Constante.GL, Constante.GR, Constante.GJ, Constante.HD, Constante.IL, Constante.IS, Constante.IF,
                                Constante.MM, Constante.MH, Constante.MS, Constante.NT, Constante.OT, Constante.PH, Constante.SM, Constante.SJ, Constante.SB, Constante.SV, Constante.TR, Constante.TM, Constante.TL, Constante.VS,
                                Constante.VL, Constante.VN, Constante.HR };
                final String provincia = provincias[1 + new Random().nextInt(42)];
                return provincia;
        }

        /**
         * Evitamos que se pueda instanciar la clase.
         */
        private Generator() {
                throw new IllegalAccessError("Generator class");
        }

}