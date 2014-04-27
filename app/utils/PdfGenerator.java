package utils;

import com.google.common.io.Files;
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

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author Piotr Góralczyk
 */
public class PdfGenerator {

    public static void generate(Copyright copyright, DateTime dateFilled, String ipAddress, File file) throws DocumentException, IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        generate(copyright, dateFilled, ipAddress, outputStream);
    }

    public static byte[] generate(Copyright copyright, DateTime dateFilled, String ipAddress) throws DocumentException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        generate(copyright, dateFilled, ipAddress, outputStream);
        return outputStream.toByteArray();
    }

    private static void generate(Copyright copyright, DateTime dateFilled, String ipAddress, OutputStream outputStream) throws DocumentException, IOException {
        String[] list = getConsentToPublishText();

        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        document.add(new Paragraph("Consent to Publish\n"));
        for (int i = 0; i < list.length; i++) {
            String line = list[i];
            Paragraph consentParagraph = new Paragraph((i+1)+":\t\t" + line);
            consentParagraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            document.add(consentParagraph);
        }
        document.add(new Paragraph("\nCopyright transfer form\n\n"));
        document.add(createParagraph("Date filled", dateFilled));
        document.add(createParagraph("IP address", ipAddress));
        document.add(createParagraph("\nArticle ID", "1"));
        document.add(createParagraph("Article title", copyright.title()));
        document.add(createParagraph("\nCorresponding author", "Name: " + copyright.correspondingAuthor().name() + "\n\t\t\t\tAffiliation: " + copyright.correspondingAuthor().affiliation() + "\n\t\t\t\tE-mail: " + copyright.correspondingAuthor().email()));
        document.add(createParagraph("\nContribution of authors", ""));
        document.add(createContributionTable(copyright.contribution()));
        document.add(createParagraph("\nFinancial disclosure", copyright.financialDisclosure()));
        document.close();
        outputStream.close();
    }

    private static String[] getConsentToPublishText() throws IOException {
        File file = new File( ".\\\\public\\\\resources\\\\Computer_Science_ctp.txt");
        return Files.readLines(file, Charset.forName("UTF-8")).toArray(new String[] {} );
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
