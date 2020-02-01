package ro.stad.online.gesint.util;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;

/**
 * Generator de cabeceras y pie para PDF.
 *
 * @author EZENTIS
 *
 */
@Getter
@Setter
public class HeaderFooterPdf implements IEventHandler {

	/**
	 * Dcoumento.
	 */
	private Document doc;

	/**
	 * Imagen de cabecera.
	 */
	private Image headerRepetido;

	/**
	 * Imagen de cabecera.
	 */
	private Image header1;

	/**
	 * Imagen de cabecera.
	 */
	private Image header2;

	/**
	 * Imagen de pie.
	 */
	private Image footer1;

	/**
	 * 
	 * @param document Documentul al que se quiere añadir el header/footer
	 * @param imagenUno Ruta de la imagen en la primera posición del header
	 * @param imagenDos Ruta de la imagen en la segunda posición del header
	 * @param cabeceraRepetido Ruta de la imagen que se repite en todas las páginas del header (salvo la primera)
	 * @param pie Ruta de la imagen del footer
	 * @throws IOException excepción se puede lanzar al crear las imágenes a partir de las rutas
	 */
	public HeaderFooterPdf(final Document document, final String imagenUno, final String imagenDos,
			final String cabeceraRepetido, final String pie) throws IOException {
		this.doc = document;
		this.headerRepetido = creareImagine(cabeceraRepetido);
		this.header1 = creareImagine(imagenUno);
		this.header2 = creareImagine(imagenDos);
		this.footer1 = creareImagine(pie);
	}

	/**
	 * Crea una cabecera y un pie de solicitud de documentación.
	 * 
	 * @param event Evento que dispara la generación
	 */
	@Override
	public void handleEvent(final Event event) {
		crearHeader(event);
		if (this.footer1 != null) {
			crearFooter(event);
		}
	}

	/**
	 * Crea una cabecera de un documento.
	 * 
	 * @param event Evento que dispara la generación
	 */
	private void crearHeader(final Event event) {
		final PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
		final PdfDocument pdfDoc = docEvent.getDocument();
		final PdfPage page = docEvent.getPage();
		final Rectangle pageSize = docEvent.getPage().getPageSize();

		// centrado x = (pageSize.getRight() - doc.getRightMargin() - (pageSize.getLeft() + doc.getLeftMargin())) / 2 +
		// doc.getLeftMargin()
		final PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

		if (pdfDoc.getPageNumber(page) == 1) {
			final Rectangle rect = new Rectangle(pdfDoc.getDefaultPageSize().getX() + doc.getLeftMargin(),
					pdfDoc.getDefaultPageSize().getTop() - doc.getTopMargin(), 523, header1.getImageHeight());
			header1.setFixedPosition(pageSize.getLeft() + doc.getLeftMargin(), pageSize.getTop() - doc.getTopMargin());
			header2.setFixedPosition(pageSize.getRight() - doc.getRightMargin() - header2.getImageScaledWidth(),
					pageSize.getTop() - doc.getTopMargin());
			final Canvas c = new Canvas(canvas, pdfDoc, rect);
			c.add(header1);
			c.add(header2);
			c.close();
		}
		else {
			final Rectangle rect = new Rectangle(pdfDoc.getDefaultPageSize().getX() + doc.getLeftMargin(),
					pdfDoc.getDefaultPageSize().getTop() - doc.getTopMargin(), 523, headerRepetido.getImageHeight());
			headerRepetido.setFixedPosition(
					pageSize.getRight() - doc.getRightMargin() - headerRepetido.getImageScaledWidth(),
					pageSize.getTop() - doc.getTopMargin() + 10);
			final Canvas c = new Canvas(canvas, pdfDoc, rect);
			c.add(headerRepetido);
			c.close();
		}
	}

	/**
	 * Crea un pie de página de un documento.
	 * 
	 * @param event Evento que dispara la generación
	 */
	private void crearFooter(final Event event) {
		final PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
		final PdfDocument pdfDoc = docEvent.getDocument();
		final PdfPage page = docEvent.getPage();
		final Rectangle pageSize = docEvent.getPage().getPageSize();

		footer1.setFixedPosition(
				(pageSize.getRight() - doc.getRightMargin() - (pageSize.getLeft() + doc.getLeftMargin())) / 2
						+ doc.getLeftMargin(),
				pageSize.getBottom() + 10);

		final PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
		final Rectangle rectFooter = new Rectangle(pdfDoc.getDefaultPageSize().getX() + doc.getLeftMargin(),
				pdfDoc.getDefaultPageSize().getBottom(), 523, footer1.getImageHeight());

		final Canvas canvas = new Canvas(pdfCanvas, pdfDoc, rectFooter);
		canvas.add(footer1);
		canvas.close();
	}

	/**
	 * Crea una imagen a partir de la ruta.
	 * 
	 * @param path Ruta de la imagen
	 * @return Imagen de itext
	 * @throws IOException Excepción que se puede lanzar al crear la imagen
	 */
	private Image creareImagine(final String path) throws IOException {
		Image imagen = null;
		if (path != null) {
			final File file = new ClassPathResource(path).getFile();
			imagen = new Image(ImageDataFactory.create(file.getPath()));
			imagen.scaleAbsolute(imagen.getImageWidth() * Constante.ESCALA,
					imagen.getImageHeight() * Constante.ESCALA);
		}
		return imagen;
	}
}