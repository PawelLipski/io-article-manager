package utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import models.copyright.Contribution;
import models.copyright.Copyright;
import org.joda.time.DateTime;
import scala.collection.Iterator;
import scala.collection.immutable.List;

import java.io.ByteArrayOutputStream;

/**
 * @author Piotr Góralczyk
 */
public class PdfGenerator {

    public static byte[] generate(Copyright copyright, DateTime dateFilled, String ipAddress) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        document.add(new Paragraph("Copyright transfer form\n\n"));
        document.add(createParagraph("Date filled", dateFilled));
        document.add(createParagraph("IP address", ipAddress));
        document.add(createParagraph("\nArticle ID", "1"));
        document.add(createParagraph("Article title", copyright.title()));
        document.add(createParagraph("\nCorresponding author", "Name: " + copyright.correspondingAuthor().name() + "\n\t\t\t\tAffiliation: " + copyright.correspondingAuthor().affiliation() + "\n\t\t\t\tE-mail: " + copyright.correspondingAuthor().email()));
        document.add(createParagraph("\nContribution of authors", ""));
        document.add(createContributionTable(copyright.contribution()));
        document.add(createParagraph("\nFinancial disclosure", copyright.financialDisclosure()));
        document.close();
        return outputStream.toByteArray();
    }

    private static PdfPTable createContributionTable(List<Contribution> contributionList) {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.addCell("Author name");
        table.addCell("Affiliation");
        table.addCell("Contribution to the paper");
        table.addCell("Estimated % of the total contribution");
        for (Iterator<Contribution> contributionIter = contributionList.iterator(); contributionIter.hasNext(); ) {
            Contribution contribution = contributionIter.next();
            table.addCell(contribution.authorName());
            table.addCell(contribution.affiliation());
            table.addCell(contribution.contribution());
            table.addCell(Integer.toString(contribution.percent()));
        }
        return table;
    }

    private static Paragraph createParagraph(String propertyName, Object property) {
        return new Paragraph(propertyName + ":\n\t\t\t\t" + property.toString());
    }

}
