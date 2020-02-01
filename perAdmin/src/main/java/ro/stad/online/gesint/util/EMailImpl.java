package ro.stad.online.gesint.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.mitchellbosecke.pebble.error.PebbleException;

import lombok.Getter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.exceptions.EMailException;
import ro.stad.online.gesint.persistence.entities.Alerta;
import ro.stad.online.gesint.persistence.entities.Documente;
import ro.stad.online.gesint.persistence.entities.Utilizator;

/**
 * Implementación de la clase EMail.
 *
 * @author STAD
 *
 */
@Component("correoElectronico")
@Getter
public class EMailImpl implements EMail {

        /**
         * Session.
         */
        private transient Session session;

        /**
         * Envío de correos a uno o varios destinatari en formato html con o sin archivos adjuntos.
         * @param destino Lista de destinatari destinatar
         * @param paramPlantilla Lista de destinatar en copia
         * @param titlutitlu titlu del correo
         * @param cuerpo Cuerpo del correo
         * @param adjuntos Lista de ficheros adjuntos
         * @param plantilla ruta del archivo de la plantilla pebble
         * @param parametrosExtra parametros que se usan en la plantilla
         * @throws IOException
         * @throws PebbleException
         * @throws EMailException excepción al enviar el correo
         */
        private void enviarCorreo(final String destino, final String titlu, final String cuerpo,
                        final List<Documente> adjuntos, final String plantilla,
                        final Map<String, String> paramPlantilla) throws IOException, PebbleException {
                try {

                        final Properties props = new Properties();
                        props.setProperty("mail.smtp.host", "mail.per.ro");
                        props.setProperty("mail.smtp.starttls.enable", "false");
                        props.setProperty("mail.transport.protocol", "smtp");
                        props.setProperty("mail.smtp.port", "26");
                        props.setProperty("mail.smtp.user", "dragos.sterpu@per.ro");
                        props.setProperty("mail.smtp.auth", "true");
                        props.put("mail.smtp.debug", "true");
                        final Session session = Session.getInstance(props, new javax.mail.Authenticator() {

                                @Override
                                protected PasswordAuthentication getPasswordAuthentication() {
                                        return new PasswordAuthentication("dragos.sterpu@per.ro", "Per20182018");
                                }
                        });
                        final Map<String, Object> parametros = new HashMap<>();
                        if (paramPlantilla != null) {
                                parametros.putAll(paramPlantilla);
                        }
                        final MimeMessage message = new MimeMessage(session);
                        final MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                        final String htmlContent = Utilitati.generarTextoConPlantilla(plantilla, parametros);
                        helper.setText(htmlContent, true);
                        final List<File> listfila = new ArrayList<>();
                        File tempFile = null;
                        if (!adjuntos.isEmpty()) {
                                for (final Documente doc : adjuntos) {
                                        tempFile = File.createTempFile(doc.getNume(), null);
                                        listfila.add(tempFile);
                                }

                                for (final File adj : listfila) {
                                        helper.addAttachment(adj.getName(), adj);
                                }
                        }
                        helper.setFrom(new InternetAddress("secretariat@per.ro"));
                        // message.setFrom(new InternetAddress("secretariat@per.ro"));
                        message.addRecipients(Message.RecipientType.TO,
                                        new InternetAddress[] { new InternetAddress(destino) });
                        message.setSubject(titlu);
                        message.setFrom(new InternetAddress("secretariat@per.ro"));
                        // message.setText("blabla");
                        final BodyPart texto = new MimeBodyPart();
                        texto.setContent("text", "text/html");
                        final Transport t = session.getTransport("smtp");
                        t.connect("dragos.sterpu@per.ro", "Per20182018");
                        t.sendMessage(message, message.getAllRecipients());
                        t.close();
                        if (!adjuntos.isEmpty()) {
                                tempFile.deleteOnExit();
                        }
                }
                catch (MailException | MessagingException e) {
                        throw new EMailException(e);
                }

        }

        /**
         *
         * Envío de correos electrónico sin adjuntos con plantilla personalizada. destinatari, titlu, datos del cuerpo
         * del mensaje y ruta de la plantilla se reciben como parámetros
         * @param paramDestino destinatari separados por ','
         * @param paramtitlu del correo
         * @param plantilla ruta del archivo de la plantilla pebble
         * @param paramPlantilla parametros del cuerpo del correo que se usan en la plantilla
         *
         */
        @Override
        public void trimitereEmail(final String paramDestino, final String paramtitlu,
                        final String templatecorreorestablecerpassword, final Map<String, String> paramPlantilla) {
                try {
                        enviarCorreo(paramDestino, "Restabilire utilizator sau parola", null, null,
                                        templatecorreorestablecerpassword, paramPlantilla);
                }
                catch (IOException | PebbleException e) {

                }

        }

        /**
         * Envío de alerta.
         * @param alerta Alerta
         * @param usuario Utilizator
         * @return Date
         * @throws PebbleException
         */
        @Override
        public Date send(final Alerta alerta, final List<Utilizator> utilizatoriSelectionati,
                        final List<Documente> documenteIncarcate, final String plantilla,
                        final Map<String, String> paramPlantilla) throws PebbleException {
                final Date fechaEnvio = null;
                final String titlu = alerta.getTipAlerta().getDescription().concat(". ").concat(alerta.getTitlu());
                final String cuerpo = alerta.getDescriere();
                String destino = Constante.SPATIU;
                if (!utilizatoriSelectionati.isEmpty()) {
                        for (final Utilizator usu : utilizatoriSelectionati) {
                                destino = usu.getUsername();
                                try {
                                        enviarCorreo(destino, titlu, cuerpo, documenteIncarcate, plantilla,
                                                        paramPlantilla);
                                }
                                catch (final IOException e) {
                                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                        Constante.EROAREMESAJ, Constante.DESCEROAREMESAJ);
                                }

                        }
                }

                return fechaEnvio;
        }
}
