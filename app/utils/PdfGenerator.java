package utils;

import com.google.common.io.Files;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import models.copyright.Contribution;
import models.copyright.CopyrightTransferRequest;
import scala.collection.Iterator;
import scala.collection.immutable.List;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author Piotr Góralczyk
 */
public class PdfGenerator {

    public static void generate(CopyrightTransferRequest request, File file) throws DocumentException, IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        generate(request, outputStream);
    }

    public static byte[] generate(CopyrightTransferRequest request) throws DocumentException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        generate(request, outputStream);
        return outputStream.toByteArray();
    }

    private static void generate(CopyrightTransferRequest request, OutputStream outputStream) throws DocumentException, IOException {
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
        document.add(createParagraph("Date filled", request.dateFilled()));
        document.add(createParagraph("IP address", request.ipAddress()));
        document.add(createParagraph("\nPaper ID", request.copyrightData().ojsId()));
        document.add(createParagraph("Paper title", request.copyrightData().title()));
        document.add(createParagraph("\nCorresponding author", "Name: " + request.copyrightData().correspondingAuthor().name() + "\n\t\t\t\tAffiliation: " + request.copyrightData().correspondingAuthor().affiliation() + "\n\t\t\t\tE-mail: " + request.copyrightData().correspondingAuthor().email()));
        document.add(createParagraph("\nContribution of authors", ""));
        document.add(createContributionTable(request.copyrightData().contribution()));
        document.add(createParagraph("\nFinancial disclosure", request.copyrightData().financialDisclosure()));
        document.close();
        outputStream.close();
    }

    private static String[] getConsentToPublishText() throws IOException {
        File file = new File( "./public/resources/Computer_Science_ctp.txt");
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
