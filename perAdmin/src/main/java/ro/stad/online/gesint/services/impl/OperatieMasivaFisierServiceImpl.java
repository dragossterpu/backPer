package ro.stad.online.gesint.services.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.faces.application.FacesMessage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.util.StringUtils;

import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.exceptions.GesintException;
import ro.stad.online.gesint.persistence.entities.Localitate;
import ro.stad.online.gesint.persistence.entities.Provincia;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.persistence.entities.enums.CanalAlertaEnum;
import ro.stad.online.gesint.persistence.entities.enums.EducatieEnum;
import ro.stad.online.gesint.persistence.entities.enums.RolEnum;
import ro.stad.online.gesint.persistence.entities.enums.SectiuniEnum;
import ro.stad.online.gesint.persistence.entities.enums.SexEnum;
import ro.stad.online.gesint.persistence.entities.enums.StatutCivilEnum;
import ro.stad.online.gesint.persistence.entities.enums.TipLocalitateEnum;
import ro.stad.online.gesint.services.LocalitateService;
import ro.stad.online.gesint.services.OperatieMasivaFisierService;
import ro.stad.online.gesint.services.ProvinciaService;
import ro.stad.online.gesint.services.UtilizatorService;
import ro.stad.online.gesint.util.FacesUtilities;
import ro.stad.online.gesint.util.Utilitati;
import ro.stad.online.gesint.web.beans.ApplicationBean;

/**
 * Implementación de la interfaz donde se define la lógica de negocio para los usuarios.
 * @author STAD
 */
@Service
public class OperatieMasivaFisierServiceImpl implements OperatieMasivaFisierService {

        /**
         * Servicio de usuarios.
         */
        @Autowired
        private UtilizatorService utilizatorService;

        /**
         * Variabila utilizata pentru a injecta serviciul provinciei.
         *
         */
        @Autowired
        private ProvinciaService provinciaService;

        /**
         * Encriptador para la contraseña.
         */
        @Autowired
        private PasswordEncoder passwordEncoder;

        /**
         * Componente de utilidades.
         */
        @Autowired
        private Utilitati utilitati;

        /**
         * Clase de utilidades para mensajes.
         */
        @Autowired
        private transient FacesUtilities facesUtilities;

        /**
         * Variable utilizada para inyectar el servicio de aplicación.
         */
        @Autowired
        private transient ApplicationBean applicationController;

        /**
         * Variabila utilizata pentru a injecta serviciul localitatilor.
         *
         */
        @Autowired
        private LocalitateService localitateService;

        /**
         * Localitate aleasa.
         */
        private Localitate localidad;

        /**
         * Variabila pentru cnp
         */
        private String cnp;

        /**
         * Variabila pentru data nasterii
         */
        private Date fecha;

        /**
         * Procesa la operación masiva de un fichero.
         * @param event evento de donde se obtendrá el fichero
         * @param tipoRegistro tipo de operación
         * @param mensajeExcepcion mensaje en caso de que ocurra un fallo
         */
        @Override
        public void procesareOperatieMasivaFisier(final FileUploadEvent event, final String tipoRegistro,
                        final String mensajeExcepcion) {
                try {
                        final String mensaje = cargaFicheroOperacionMasiva(event.getFile(), tipoRegistro);
                        if (StringUtils.isEmpty(mensaje)) {
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                                Constante.INREGISTRARE,
                                                "Toți utilizatorii au fost procesați cu succes.");
                        }

                        else {
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                Constante.EROAREMESAJ,
                                                Constante.EXISTERORI.concat(mensajeExcepcion) + mensaje);
                        }
                }
                catch (final TransactionException | IOException | NoSuchElementException te) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.EXISTERORI.concat(mensajeExcepcion));
                }
        }

        /**
         * Carga un excel de usuarios válidos.
         * @param uFile Fichero
         * @param operacion tipo de operación realizada
         * @return lista de usuarios
         * @throws IOException excepción lanzada
         */
        private String cargaFicheroOperacionMasiva(final UploadedFile uFile, final String operacion)
                        throws IOException {
                String mensajeSalida;
                final List<String> listaUtilizatoriExistentiBD = utilizatorService
                                .cautareListaNumeUtilizator(obtenerUsernamesDelFichero(devolverIteradorNuevo(uFile)));
                mensajeSalida = citesteFisierSalveaza(listaUtilizatoriExistentiBD, devolverIteradorNuevo(uFile),
                                operacion);

                return mensajeSalida;
        }

        /**
         * Devuelve un iterador de filas nuevo para el fichero.
         * @param uFile fichero a leer
         * @return iterador de filas
         * @throws IOException Excepción de entrada/salida.
         */
        private Iterator<Row> devolverIteradorNuevo(final UploadedFile uFile) throws IOException {
                final Workbook workbook = new XSSFWorkbook(uFile.getInputstream());
                final Sheet sheet = workbook.getSheetAt(0);
                return sheet.iterator();
        }

        /**
         * Obtiene la lista de nombres de usuarios del fichero.
         * @param rowIterator iterador de filas del fichero
         * @return lista de nombres de usuarios
         */
        private List<String> obtenerUsernamesDelFichero(final Iterator<Row> rowIterator) {
                final List<String> listaUsernames = new ArrayList<>();
                final DataFormatter dataFormatter = new DataFormatter();
                rowIterator.next();
                while (rowIterator.hasNext()) {
                        final String username = dataFormatter.formatCellValue(rowIterator.next().cellIterator().next());
                        if (!StringUtils.isEmpty(username.trim())) {
                                listaUsernames.add(username);
                        }
                }

                return listaUsernames;
        }

        /**
         * Lee el fichero fila a fila y realiza las operaciones pertinentes.
         * @param listaBBDD lista de usuarios que se encuentran en BBDD.
         * @param iteradorFilas iterador de filas
         * @param operacion operación que se va a realizar
         * @throws ParseException
         * @returnmensaje de salida con los usuarios correctos y en estado de error
         */
        private String citesteFisierSalveaza(final List<String> listaBBDD, final Iterator<Row> iteradorFilas,
                        final String operacion) {
                final DataFormatter dataFormatter = new DataFormatter();
                final List<Utilizator> listaUtilizatoriSalvare = new ArrayList<>();
                final StringBuilder mensaje = new StringBuilder();
                Row row;
                iteradorFilas.next();
                while (iteradorFilas.hasNext()) {
                        row = iteradorFilas.next();
                        final String username = dataFormatter.formatCellValue(row.cellIterator().next());
                        if (!StringUtils.isEmpty(username.trim())) {
                                final String resultatObtinut = obtinereListaSalvare(listaBBDD, operacion,
                                                listaUtilizatoriSalvare, row, username);
                                if (!StringUtils.isEmpty(resultatObtinut)) {
                                        mensaje.append(resultatObtinut);
                                }
                        }
                }
                String hayErrores = "Rezolvați-le și încercați din nou.";
                if (!listaUtilizatoriSalvare.isEmpty() && operacion.equals(Constante.INREGISTRARE)) {
                        utilizatorService.salvat(listaUtilizatoriSalvare);
                        hayErrores = hayErrores.concat(" Restul membrilor au fost salvați cu succes.");
                }
                else if (operacion.equals(Constante.ELIMINARE)) {
                        utilizatorService.bajaLogica(listaBBDD);
                        hayErrores = hayErrores.concat(" Restul membrilor s-au eliminat cu succes.");
                }
                else if (operacion.equals(Constante.BLOCARE)) {
                        utilizatorService.desactivar(listaBBDD);
                        hayErrores = hayErrores.concat(" Restul membrilor s-au blocat cu succes.");
                }

                if (!StringUtils.isEmpty(mensaje.toString())) {
                        mensaje.append(hayErrores);
                }

                return mensaje.toString();
        }

        /**
         * Llena la lista con los usuarios a guardar en BBDD.
         * @param listaBBDD lista de usuarios que existen en BBDD
         * @param operacion operación a realizar
         * @param listaUtilizatoriSalvare lista de usuarios que se van a guardar
         * @param row fila del fichero
         * @param username nombre de usuario
         * @return mensaje con los resultados de la operación
         * @throws ParseException
         */
        private String obtinereListaSalvare(final List<String> listaBBDD, final String operacion,
                        final List<Utilizator> listaUtilizatoriSalvare, final Row row, final String username) {
                final DataFormatter dataFormatter = new DataFormatter();
                final StringBuilder mensaje = new StringBuilder();
                if (operacion.equals(Constante.INREGISTRARE)) {
                        if (!listaBBDD.contains(username)) {
                                final Utilizator usuario = new Utilizator();
                                usuario.setUsername(username);
                                try {
                                        obtenerDatosUsuarioAlta(usuario, row.cellIterator(), dataFormatter, mensaje);
                                        listaUtilizatoriSalvare.add(usuario);
                                }
                                catch (final Exception e) {
                                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                        Constante.EROAREMESAJ, Constante.EROAREMESAJ);
                                }
                        }
                        else {
                                anadirRegistroError(mensaje, row,
                                                (Constante.MEMBRUL + username + "' există deja în baza de date."));
                        }
                }
                else {
                        if (!listaBBDD.contains(username) && operacion.equals(Constante.INREGISTRARE)) {
                                anadirRegistroError(mensaje, row,
                                                (Constante.MEMBRUL + username + "' nu este găsit în baza de date."));
                        }
                }

                return mensaje.toString();
        }

        /**
         * Obtiene los datos del usuario que va a ser creado en BBDD del fichero de excel.
         * @param usuario usuario al que se añadirán los datos
         * @param colIterator iterador de columnas
         * @param dataFormatter formateador de columnas
         * @throws ParseException
         * @throws PdyrhException excepción de PDYRH
         */
        private void obtenerDatosUsuarioAlta(final Utilizator usuario, final Iterator<Cell> colIterator,
                        final DataFormatter dataFormatter, final StringBuilder mensaje) throws GesintException {
                String cellValue;
                cnp = Constante.SPATIU;
                colIterator.next();
                // Nume
                cellValue = dataFormatter.formatCellValue(colIterator.next());
                usuario.setName(cellValue);
                // Prenume
                cellValue = dataFormatter.formatCellValue(colIterator.next());
                usuario.setLastName(cellValue);
                // CNP
                cellValue = dataFormatter.formatCellValue(colIterator.next());
                usuario.setIdCard(cellValue);
                cnp = usuario.getIdCard();
                // Utilizator
                cellValue = dataFormatter.formatCellValue(colIterator.next());
                usuario.setUsername(cellValue);
                // Email
                usuario.setEmail(cellValue);
                // Email personal
                usuario.setPersonalEmail(cellValue);
                // Rol
                cellValue = dataFormatter.formatCellValue(colIterator.next());
                utilitati.existeRol(RolEnum.valueOf(cellValue));
                usuario.setRole(RolEnum.valueOf(cellValue));
                // TELEFON
                cellValue = dataFormatter.formatCellValue(colIterator.next());
                usuario.setPhone(cellValue);
                // NUMAR SI SERIA CARD DE IDENTITATE
                cellValue = dataFormatter.formatCellValue(colIterator.next());
                usuario.setNumberCard(cellValue);
                // ADRESA
                cellValue = dataFormatter.formatCellValue(colIterator.next());
                usuario.setAddress(cellValue);
                // DATA NASTERII
                cellValue = dataFormatter.formatCellValue(colIterator.next());
                final SimpleDateFormat formatoDeFecha = new SimpleDateFormat(Constante.FORMATDATE);
                try {

                        usuario.setBirthDate(formatoDeFecha.parse(cellValue));
                        fecha = usuario.getBirthDate();
                }
                catch (final ParseException e1) {
                        mensaje.append("Eroare apărută la procesarea datei de naștere");
                        utilitati.procesarExcepcion(e1, SectiuniEnum.OTROS,
                                        "Eroare apărută la procesarea datei de naștere", facesUtilities);
                }
                // NIVEL DE EDUCATIE
                cellValue = dataFormatter.formatCellValue(colIterator.next());
                utilitati.existeEducatie(EducatieEnum.valueOf(cellValue));
                usuario.setEducation(EducatieEnum.valueOf(cellValue));
                // LOC DE MUNCA
                cellValue = dataFormatter.formatCellValue(colIterator.next());
                usuario.setWorkplace(cellValue);
                // SEX
                cellValue = dataFormatter.formatCellValue(colIterator.next());
                utilitati.existeSex(SexEnum.valueOf(cellValue));
                final String sex = cellValue;
                if (valideazaCnp(sex, fecha, cnp)) {
                        usuario.setSex(SexEnum.valueOf(cellValue));
                }
                else {
                        mensaje.append("Datele introduse în registru nu sunt corecte. Verificați , data nașterii, sexul sau cnp-ul membrului.");
                }
                // STARE CIVILA
                cellValue = dataFormatter.formatCellValue(colIterator.next());
                utilitati.existeCivilStatus(StatutCivilEnum.valueOf(cellValue));
                usuario.setCivilStatus(StatutCivilEnum.valueOf(cellValue));
                // PROVINCIA
                cellValue = obtenerIdCelda(dataFormatter.formatCellValue(colIterator.next()).trim());
                utilitati.esEntero(cellValue);
                Provincia provincia = new Provincia();
                try {
                        provincia.setIndicator(String.valueOf(cellValue));
                }
                catch (final NumberFormatException e) {
                        mensaje.append(" Codul judeţului trebuie să fie numeric.");
                        throw new GesintException("Codul judeţului trebuie să fie numeric.");
                }
                provincia = provinciaService.findById(provincia.getIndicator());
                if (provincia != null) {
                        usuario.setProvince(provincia);
                }
                else {
                        mensaje.append(" Judeţul nu este prezent în baza de date.");
                        throw new GesintException("Judeţul nu este prezent în baza de date.");
                }
                // TIPUL LOCALITATII
                cellValue = dataFormatter.formatCellValue(colIterator.next());
                utilitati.existeTypeLocality(TipLocalitateEnum.valueOf(cellValue));
                final TipLocalitateEnum tipulLocalitatii = TipLocalitateEnum.valueOf(cellValue);
                // LOCALITATEA
                cellValue = dataFormatter.formatCellValue(colIterator.next());
                // Cautam localitatea dupa nume
                this.localidad = localitateService.localidadByNameIgnoreCaseAndProvincia(cellValue, provincia);
                // Daca nu exista o cream
                if (localidad == null) {
                        usuario.setLocality(localitateService.crearLocalidad(cellValue, provincia, tipulLocalitatii));
                }
                else {
                        usuario.setLocality(localidad);
                }
                usuario.setPassword(passwordEncoder.encode("1"));
                // MEMBRU ACTIV
                usuario.setValidated(true);
                // SCANAL DE COMUNICARE
                usuario.setAlertChannel(CanalAlertaEnum.EMAIL);
        }

        /**
         * Metoda de validare a unicității numelui de utilizator.
         * @return boolean
         */
        private boolean valideazaCnp(final String sex, final Date fecha, final String cnp) {
                boolean resultat = true;
                if (cnp.length() == 11) {
                        final String an = cnp.substring(1, 3);
                        final String luna = cnp.substring(3, 5);
                        final String zi = cnp.substring(5, 7);
                        final String cnpul = cnp.substring(0, 1);
                        final Date data = fecha;
                        final SimpleDateFormat sdf = new SimpleDateFormat("yy");
                        final SimpleDateFormat lsdf = new SimpleDateFormat(Constante.MM);
                        final SimpleDateFormat zsdf = new SimpleDateFormat("dd");
                        final String anString = sdf.format(data);
                        final String lunaString = lsdf.format(data);
                        final String ziString = zsdf.format(data);
                        if (an.equals(anString) && luna.equals(lunaString) && zi.equals(ziString)
                                        && (sex.equals("MAN") && cnpul.equals("1")
                                                        || sex.equals("WOMAN") && cnpul.equals("2"))) {
                                resultat = true;
                        }
                        else {
                                resultat = false;
                        }
                }
                else {
                        resultat = false;
                }
                return resultat;
        }

        /**
         * Añade un registro de error al StringBuilder.
         * @param mensaje mensaje al que se añadirá el registro erróneo
         * @param row fila en la que se ha producido el error
         * @param e el error que ha casuado el problema
         */
        private void anadirRegistroError(final StringBuilder mensaje, final Row row, final String e) {
                if (StringUtils.isEmpty(mensaje)) {
                        mensaje.append("Următoarele înregistrări conțin un tip de eroare și nu au fost salvate:");
                }
                mensaje.append("înregistrarea membrului de pe rândul ").append(row.getRowNum())
                                .append(":  al fișierului de proces masiv").append(e);
        }

        /**
         * Obține ID-ul unui corp sau al unei celule din excel.
         * @param celda valor de la celda
         * @return id obtenido
         */
        private String obtenerIdCelda(final String celda) {

                String valor = Constante.SPATIU;
                valor = celda.substring(0, celda.indexOf(Constante.LINIE)).trim();
                return valor;
        }
}
