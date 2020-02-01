package ro.stad.online.gesint.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.constante.NumarMagic;
import ro.stad.online.gesint.exceptions.GesintException;
import ro.stad.online.gesint.persistence.entities.DocumentBlob;
import ro.stad.online.gesint.persistence.entities.Documente;
import ro.stad.online.gesint.persistence.entities.Sondaj;
import ro.stad.online.gesint.persistence.entities.TipDocument;
import ro.stad.online.gesint.services.DocumentService;

/**
 * Clasa pentru generarea PDF a statisticilor.
 *
 * @author STAD
 *
 */
@Setter
@Component("pdfGeneratorEstadisticas")
public class PdfGenerareStatistica extends PdfAbstractGenerator {

        /**
         * Numele statisticilor pdf.
         */
        private static final String NOMBREPDFESTADISTICAS = "RaportSondaj";

        /**
         * Sondaj.
         */
        private Sondaj sondaj;

        /**
         * Fișier cu imaginea de exportat.
         */
        private File fileImg;

        /**
         * Servicio.
         */
        @Autowired
        private transient DocumentService documentService;

        /**
         * Genereaza numele documentului
         *
         * @param nombrePdf Numele final al documentului
         * @return numeDocument Numele static al documentului
         */
        @Override
        public String creareNumeDocument(String nombrePdf) {
                final String dataSondajului = new SimpleDateFormat("dd_MM_yyyy").format(new Date());
                return nombrePdf.concat(dataSondajului).concat(".pdf");
        }

        /**
         * Genereaza conținutul care va fi afișat în PDF.
         *
         * @param document Document pdf la care va fi atașat conținutul
         * @throws GesintException excepție pe care o poți lanza
         */
        @Override
        public void creareCorpPdf(final Document document) throws GesintException {
                // Títlu
                final Paragraph titlu = new Paragraph("Raport statistic");

                titlu.setBold();
                titlu.setFontSize(NumarMagic.NUMBERSIXTEEN);
                titlu.setTextAlignment(TextAlignment.CENTER);
                titlu.setPadding(NumarMagic.NUMBERFIVE);
                document.add(titlu);
                // Fecha
                final Paragraph fecha = new Paragraph("Data emiterii documentului: "
                                + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
                fecha.setTextAlignment(TextAlignment.RIGHT);
                fecha.setPadding(NumarMagic.NUMBERFIVE);
                document.add(fecha);
                // Datos del informe
                document.add(creaTitlu("Datele initiale ale raportului"));

                // Data începerii sondajului titlu
                final Paragraph dataIncepereSpondaj = new Paragraph("Data începerii sondajului: "
                                + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(sondaj.getDataIncepere()));
                dataIncepereSpondaj.setTextAlignment(TextAlignment.LEFT);
                dataIncepereSpondaj.setBold();
                dataIncepereSpondaj.setFontSize(NumarMagic.NUMBERTWELVE);
                dataIncepereSpondaj.setPadding(NumarMagic.NUMBERFIVE);
                document.add(dataIncepereSpondaj);

                // Data finalizării sondajului titlu
                final Paragraph dataFinalizareSpondaj = new Paragraph("Data finalizarii sondajului: "
                                + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(sondaj.getDataFinalizare()));
                dataFinalizareSpondaj.setTextAlignment(TextAlignment.LEFT);
                dataFinalizareSpondaj.setBold();
                dataFinalizareSpondaj.setFontSize(NumarMagic.NUMBERTWELVE);
                dataFinalizareSpondaj.setPadding(NumarMagic.NUMBERFIVE);
                document.add(dataFinalizareSpondaj);

                // Intrebare sondaj titlu
                final Paragraph intrebareSpondajTitlu = new Paragraph("Întrebare sondaj");
                intrebareSpondajTitlu.setTextAlignment(TextAlignment.LEFT);
                intrebareSpondajTitlu.setBold();
                intrebareSpondajTitlu.setFontSize(NumarMagic.NUMBERTWELVE);
                intrebareSpondajTitlu.setPadding(NumarMagic.NUMBERFIVE);
                document.add(intrebareSpondajTitlu);
                // Intrebare sondaj
                final Paragraph intrebareSpondaj = new Paragraph(sondaj.getIntrebare());
                intrebareSpondaj.setTextAlignment(TextAlignment.LEFT);
                intrebareSpondaj.setPadding(NumarMagic.NUMBERFIVE);
                document.add(intrebareSpondaj);
                // Descriere sondaj titlu
                final Paragraph descriereSpondajTitlu = new Paragraph("Descriere sondaj");
                descriereSpondajTitlu.setTextAlignment(TextAlignment.LEFT);
                descriereSpondajTitlu.setBold();
                descriereSpondajTitlu.setFontSize(NumarMagic.NUMBERTWELVE);
                descriereSpondajTitlu.setPadding(NumarMagic.NUMBERFIVE);
                document.add(descriereSpondajTitlu);
                // Descriere sondaj
                final Paragraph descriereSpondaj = new Paragraph(sondaj.getDescriere());
                descriereSpondaj.setTextAlignment(TextAlignment.LEFT);
                descriereSpondaj.setPadding(NumarMagic.NUMBERFIVE);
                document.add(descriereSpondaj);
                document.add(new AreaBreak());

                // Datele sondajului
                final Paragraph dateSpondaj = new Paragraph("Datele sondajului");
                dateSpondaj.setTextAlignment(TextAlignment.LEFT);
                dateSpondaj.setBold();
                dateSpondaj.setFontSize(NumarMagic.NUMBERTWELVE);
                dateSpondaj.setPadding(NumarMagic.NUMBERFIVE);
                document.add(dateSpondaj);

                // Total voturi
                final Paragraph totalVoturi = new Paragraph(
                                "Total voturi valabil exprimate: ".concat(String.valueOf(sondaj.getTotalVoturi())));
                totalVoturi.setTextAlignment(TextAlignment.LEFT);
                totalVoturi.setBold();
                totalVoturi.setFontSize(NumarMagic.NUMBERTWELVE);
                totalVoturi.setPadding(NumarMagic.NUMBERFIVE);
                document.add(totalVoturi);

                String procentajDa = extractProcentajDa();
                String procentajNu = extractProcentajNu();
                String procentajAbt = extractProcentajAbt();
                // Total voturi da
                final Paragraph totalVoturiDa = new Paragraph(
                                "Total voturi DA: ".concat(String.valueOf(sondaj.getTotalVoturiDa()))
                                                .concat(" ce reprezinta un procentaj de : ").concat(procentajDa)
                                                .concat(Constante.PROCENTAJ));
                totalVoturiDa.setTextAlignment(TextAlignment.LEFT);
                totalVoturiDa.setBold();
                totalVoturiDa.setFontSize(NumarMagic.NUMBERTWELVE);
                totalVoturiDa.setPadding(NumarMagic.NUMBERFIVE);
                document.add(totalVoturiDa);

                // Total voturi nu
                final Paragraph totalVoturiNu = new Paragraph(
                                "Total voturi NU: ".concat(String.valueOf(sondaj.getTotalVoturiNu()))
                                                .concat(" ce reprezinta un procentaj de : ").concat(procentajNu)
                                                .concat(Constante.PROCENTAJ));
                totalVoturiNu.setTextAlignment(TextAlignment.LEFT);
                totalVoturiNu.setBold();
                totalVoturiNu.setFontSize(NumarMagic.NUMBERTWELVE);
                totalVoturiNu.setPadding(NumarMagic.NUMBERFIVE);
                document.add(totalVoturiNu);

                // Total voturi nu
                final Paragraph totalVoturiAbt = new Paragraph("Total abtineri: "
                                .concat(String.valueOf(sondaj.getTotalVoturiAbt()).concat(Constante.PROCENTAJ))
                                .concat(" ce reprezinta un procentaj de : ").concat(procentajAbt)
                                .concat(Constante.PROCENTAJ));
                totalVoturiAbt.setTextAlignment(TextAlignment.LEFT);
                totalVoturiAbt.setBold();
                totalVoturiAbt.setFontSize(NumarMagic.NUMBERTWELVE);
                totalVoturiNu.setPadding(NumarMagic.NUMBERFIVE);
                document.add(totalVoturiAbt);

                // Grafica
                document.add(new AreaBreak()); // Salt de página
                document.add(creaSubtitlu("Grafica"));

                try {

                        document.add(new Image(ImageDataFactory.create(fileImg.getPath())));
                        if (fileImg.exists()) {
                                fileImg.delete();
                        }
                }
                catch (final MalformedURLException e) {
                        throw new GesintException(e);
                }
        }

        /**
         * @return
         */
        private String extractProcentajAbt() {
                String procentajAbt = Constante.SPATIU;
                if (sondaj.getProcentajAbt() != null) {
                        procentajAbt = String.valueOf(sondaj.getProcentajAbt()).concat(Constante.PROCENTAJ);
                }
                else {
                        procentajAbt = obtinereProcentajTotalAbt(sondaj);
                }
                return procentajAbt;
        }

        /**
         * @return
         */
        private String extractProcentajNu() {
                String procentajNu = Constante.SPATIU;

                if (sondaj.getProcentajNu() != null) {
                        procentajNu = String.valueOf(sondaj.getProcentajNu()).concat(Constante.PROCENTAJ);
                }
                else {
                        procentajNu = obtinereProcentajTotalNu(sondaj);
                }
                return procentajNu;
        }

        /**
         * @return
         */
        private String extractProcentajDa() {
                String procentajDa = Constante.SPATIU;
                if (sondaj.getProcentajDa() != null) {
                        procentajDa = String.valueOf(sondaj.getProcentajDa()).concat(Constante.PROCENTAJ);
                }
                else {
                        procentajDa = obtinereProcentajTotalDa(sondaj);
                }
                return procentajDa;
        }

        /**
         * Creaza un paragraf formatat pentru a servi drept titlu secundar.
         *
         * @param text Textul subtítului
         * @return PParagraful formatat ca titlu secundar.
         */
        private Paragraph creaSubtitlu(final String text) {
                final Paragraph subTitlu = new Paragraph(text);
                subTitlu.setFontSize(12);
                subTitlu.setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(153, 201, 255)));
                subTitlu.setMarginTop(20);
                subTitlu.setMarginBottom(10);
                return subTitlu;
        }

        /**
         * Creaza un paragraf formatat pentru a servi drept titlu.
         *
         * @param text Titlul textului de generat
         * @return Paragraful formatat ca titlu.
         */
        private Paragraph creaTitlu(final String text) {
                final Paragraph titlu = new Paragraph(text);
                titlu.setBold();
                titlu.setFontSize(12);
                titlu.setTextAlignment(TextAlignment.CENTER);
                titlu.setPadding(10);
                return titlu;
        }

        /**
         * Genereaza un document PDF cu statistici de sondaje.
         *
         * @return pdf cu conținutul sondajului
         * @throws GesintException excepție care poate aparea
         */
        @Override
        public StreamedContent exportarPdf() throws GesintException {
                return crearPdf(NOMBREPDFESTADISTICAS, false, true);
        }

        /**
         *
         * Metoda care calculeaza procentajul total de membrii cu Da
         */
        private String obtinereProcentajTotalDa(Sondaj sond) {
                final int num = (int) (sond.getTotalVoturiDa() * NumarMagic.NUMBERHUNDRED);
                final float div = ((float) num / sond.getTotalVoturi());
                float divFinal = Math.round(div * NumarMagic.NUMBERHUNDRED) / 100f;
                return String.valueOf(divFinal);
        }

        /**
         *
         * Metoda care calculeaza procentajul total de membrii cu nu
         */
        private String obtinereProcentajTotalNu(Sondaj sond) {
                final int num = (int) (sond.getTotalVoturiNu() * NumarMagic.NUMBERHUNDRED);
                final float div = ((float) num / sond.getTotalVoturi());
                float divFinal = Math.round(div * NumarMagic.NUMBERHUNDRED) / 100f;
                return String.valueOf(divFinal);
        }

        /**
         *
         * Metoda care calculeaza procentajul de abtineri
         */
        private String obtinereProcentajTotalAbt(Sondaj sond) {
                final int num = (int) (sond.getTotalVoturiAbt() * NumarMagic.NUMBERHUNDRED);
                final float div = ((float) num / sond.getTotalVoturi());
                float divFinal = Math.round(div * NumarMagic.NUMBERHUNDRED) / 100f;
                return String.valueOf(divFinal);
        }

        /**
         * @param numeDocument
         * @param outputStreamOr
         */
        @Override
        public void inregistrareDocumentStatistica(final String numeDocument,
                        final ByteArrayOutputStream outputStreamOr) {
                final Documente doc = new Documente();
                final DocumentBlob docbl = new DocumentBlob();
                docbl.setFichero(outputStreamOr.toByteArray());
                docbl.setNombreFichero(numeDocument);
                doc.setFisier(docbl);
                doc.setNume(numeDocument);
                final List<Sondaj> list = new ArrayList<>();
                list.add(sondaj);
                doc.setSondaje(list);
                doc.setTipContinut("application/pdf");
                doc.setMateriaIndexada(numeDocument.substring(0, NumarMagic.NUMBERSIX));
                final TipDocument tip = new TipDocument();
                tip.setId(6L);
                doc.setTipDocument(tip);
                doc.setValidat(true);
                doc.setDescriere("Document sondaj "
                                .concat(numeDocument.substring(NumarMagic.NUMBERTWELVE, NumarMagic.NUMBERTWENTYTWO)));
                documentService.save(doc);
        }
}
