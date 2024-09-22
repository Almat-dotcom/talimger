package kz.talimger.util;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Component
public class PDFGenerator {

    @Value("${pdf.page-size}")
    private PageSize pageSize;

    public byte[] generatePDFWithQRCode(byte[] qrCodeBytes) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc, pageSize);

        Image qrImage = new Image(ImageDataFactory.create(qrCodeBytes));
        float x = (pageSize.getWidth() - qrImage.getImageWidth()) / 2;
        float y = (pageSize.getHeight() - qrImage.getImageHeight()) / 2;

        qrImage.setFixedPosition(x, y);
        document.add(qrImage);
        document.close();

        return outputStream.toByteArray();
    }
}