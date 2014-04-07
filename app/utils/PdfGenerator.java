package utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import models.AuthorContribution;
import models.CopyrightTransferForm;
import scala.collection.Iterator;
import scala.collection.immutable.List;

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
        document.add(createParagraph("Article ID", form.copyrightData().id()));
        document.add(createParagraph("Article title", form.copyrightData().title()));
        document.add(createParagraph("Corresponding author", form.copyrightData().correspondingAuthor()));
        document.add(createParagraph("Contribution of authors", ""));
        document.add(createContributionTable(form.copyrightData().contribution()));
        document.add(createParagraph("Financial disclosure", form.copyrightData().financialDisclosure()));
        document.add(createParagraph("Date filled", form.date()));
        document.add(createParagraph("IP address", form.ipAddress()));
        document.close();
        return outputStream.toByteArray();
    }

    private static PdfPTable createContributionTable(List<AuthorContribution> contribution) {
        PdfPTable table = new PdfPTable(5);
        table.addCell("Name");
        table.addCell("Surname");
        table.addCell("Affiliation");
        table.addCell("Contribution to the paper");
        table.addCell("Estimated % of the total contribution");
        for (Iterator<AuthorContribution> contributionIter = contribution.iterator(); contributionIter.hasNext(); ) {
            AuthorContribution authorContribution = contributionIter.next();
            table.addCell(authorContribution.author().name());
            table.addCell(authorContribution.author().surname());
            table.addCell(authorContribution.author().affiliation());
            table.addCell(authorContribution.contribution());
            table.addCell(Integer.toString(authorContribution.contributionPercent()));
        }
        return table;
    }

    private static Paragraph createParagraph(String propertyName, Object property) {
        return new Paragraph(propertyName + ": " + property.toString());
    }

}
