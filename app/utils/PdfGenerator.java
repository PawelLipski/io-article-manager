package utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import models.copyright.Contribution;
import models.copyright.CopyrightTransferRequest;
import play.api.Play;
import scala.collection.Iterator;
import scala.collection.immutable.List;

import java.io.*;

/**
 * @author Piotr Góralczyk
 */
public class PdfGenerator {

    public static void generate(List<CopyrightTransferRequest> requests, File file) throws DocumentException, IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        generate(requests, outputStream);
        outputStream.close();
    }

    public static byte[] generate(List<CopyrightTransferRequest> requests) throws DocumentException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        generate(requests, outputStream);
        outputStream.close();
        return outputStream.toByteArray();
    }

    private static void generate(List<CopyrightTransferRequest> requests, OutputStream outputStream) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        for (Iterator<CopyrightTransferRequest> requestIterator = requests.iterator(); requestIterator.hasNext();) {
            addRequestToDocument(requestIterator.next(), document);
        }
        document.close();
    }

    private static void addRequestToDocument(CopyrightTransferRequest request, Document document) throws DocumentException, IOException {
        document.newPage();
        document.add(getJournalLogo(document));
        document.add(new Paragraph("Consent to Publish\n"));
        java.util.List<String> list = getConsentToPublishText();
        for (int i = 0; i < list.size(); i++) {
            String line = list.get(i);
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
    }

    private static Image getJournalLogo(Document document) throws BadElementException, IOException {
        Image image = Image.getInstance(
                Play.resource("public/images/Computer_Science_logo.png", Play.current()).get());
        float scalePercentage = ((document.getPageSize().getWidth() - document.leftMargin()
                - document.rightMargin()) / image.getWidth()) * 100;

        image.scalePercent(scalePercentage);
        return image;
    }

    private static java.util.List<String> getConsentToPublishText() throws IOException {
        BufferedReader br;
        br = new BufferedReader(
                new InputStreamReader(
                        Play.resourceAsStream("public/resources/Computer_Science_ctp.txt", Play.current()).get()
                )
        );
        java.util.List<String> list = new java.util.LinkedList<String>();
        String line;
        while ((line = br.readLine()) != null)
            list.add(line);
        br.close();
        return list;
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
