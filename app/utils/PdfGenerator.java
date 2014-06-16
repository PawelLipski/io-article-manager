package utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import models.copyright.*;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.immutable.List;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author Piotr Góralczyk
 */
public class PdfGenerator {

    public static void generate(Seq<CopyrightTransferRequestWrapper> requests, File file, long journalId)
            throws DocumentException, IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        generate(requests, outputStream, journalId);
        outputStream.close();
    }

    public static byte[] generate(Seq<CopyrightTransferRequestWrapper> requests, long journalId)
            throws DocumentException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        generate(requests, outputStream, journalId);
        outputStream.close();
        return outputStream.toByteArray();
    }

    private static void generate(Seq<CopyrightTransferRequestWrapper> requests, OutputStream outputStream, long journalID)
            throws DocumentException, IOException {

        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        for (Iterator<CopyrightTransferRequestWrapper> requestIterator = requests.iterator(); requestIterator.hasNext();)
            addRequestToDocument(requestIterator.next(), document, journalID);
        document.close();
    }

    private static void addRequestToDocument(CopyrightTransferRequestWrapper wrapper, Document document, long journalID)
            throws DocumentException, IOException {
        document.newPage();
        document.add(getJournalLogo(document, journalID));
        document.add(new Paragraph("Consent to Publish\n"));
        java.util.List<String> list = getConsentToPublishText(journalID);
        for (int i = 0; i < list.size(); i++) {
            String line = list.get(i);
            Paragraph consentParagraph = new Paragraph((i+1)+":\t\t" + line);
            consentParagraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            document.add(consentParagraph);
        }
        CopyrightTransferRequest request = wrapper.copyrightTransferRequest();
        Copyright copyright = wrapper.copyright();
        CorrespondingAuthor correspondingAuthor = wrapper.correspondingAuthor();
        List<Contribution> contributionList = wrapper.contributionList();

        document.add(new Paragraph("\nCopyright transfer form\n\n"));
        document.add(createParagraph("Date confirmed", request.dateConfirmed()));
        document.add(createParagraph("IP address", request.ipAddress()));
        document.add(createParagraph("\nPaper ID", copyright.ojsArticleId()));
        document.add(createParagraph("Paper title", copyright.title()));
        document.add(createParagraph("\nCorresponding author",
                "Name: " + correspondingAuthor.getFullName() +
                        "\n\t\t\t\tAffiliation: " + correspondingAuthor.affiliation() +
                        "\n\t\t\t\tE-mail: " + correspondingAuthor.email()));
        document.add(createParagraph("\nContribution of authors", ""));
        document.add(createContributionTable(contributionList));
        document.add(createParagraph("\nFinancial disclosure", copyright.financialDisclosure()));
    }

    private static Image getJournalLogo(Document document, long journalID) throws BadElementException, IOException {
        Image image = JournalUtilProvider.getLogoImage(journalID);
        float scalePercentage = ((document.getPageSize().getWidth() - document.leftMargin()
                - document.rightMargin()) / image.getWidth()) * 100;

        image.scalePercent(scalePercentage);
        return image;
    }

    private static java.util.List<String> getConsentToPublishText(long journalID) throws IOException {
        List<String> consentToPublishText = JournalUtilProvider.getConsentToPublishText(journalID);
        return scala.collection.JavaConversions.asJavaList(consentToPublishText);
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
            table.addCell(contribution.getFullAuthorName());
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
