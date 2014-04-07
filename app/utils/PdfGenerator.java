package utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import models.CopyrightTransferForm;

import java.io.ByteArrayOutputStream;

/**
 * @author Piotr Góralczyk
 */
public class PdfGenerator {

    public static byte[] generate(CopyrightTransferForm form) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        document.add(createTable(form));
        document.close();
        return outputStream.toByteArray();
    }

    private static PdfPTable createTable(CopyrightTransferForm form) {
        PdfPTable table = new PdfPTable(2);
        // TODO fill with information
        return table;
    }

}
