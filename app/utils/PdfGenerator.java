package utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import models.copyright.*;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.immutable.List;

import java.io.*;

public class PdfGenerator {

    private static final String FONT_ALIAS = "LiberationSans";
    private static final String FONT_NAME = "LiberationSans-Regular";
    private static final String FONT_PATH = "public/fonts/" + FONT_NAME + ".ttf";
    private static final String FONT_ENCODING = BaseFont.IDENTITY_H;
    private static final int BASIC_FONT_SIZE = 11;
    private static final int HEADER_FONT_SIZE = 16;
    private static final int FONT_STYLE = Font.NORMAL;
    private static final BaseColor FONT_COLOR = BaseColor.BLACK;
    private static final boolean EMBED_FONT = BaseFont.EMBEDDED;
    private static final int PARAGRAPH_SPACING = 15;
    private static final int INDENTATION = 20;
    private static final int SPACING_BEFORE_TABLE = 10;

    private static final Font BASIC_FONT;
    private static final Font HEADER_FONT;

    static {
        FontFactory.register(FONT_PATH, FONT_ALIAS);
        BASIC_FONT = FontFactory.getFont(FONT_ALIAS, FONT_ENCODING, EMBED_FONT, BASIC_FONT_SIZE, FONT_STYLE, FONT_COLOR);
        HEADER_FONT = FontFactory.getFont(FONT_ALIAS, FONT_ENCODING, EMBED_FONT, HEADER_FONT_SIZE, FONT_STYLE, FONT_COLOR);
    }

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
            addRequest(requestIterator.next(), document, journalID);
        document.close();
    }

    private static void addRequest(CopyrightTransferRequestWrapper wrapper, Document document, long journalID)
            throws DocumentException, IOException {
        document.newPage();
        document.add(getJournalLogo(document, journalID));

        document.add(createHeader("Consent to Publish"));
        java.util.List<String> list = getConsentToPublishText(journalID);
        for (int i = 0; i < list.size(); i++) {
            String line = list.get(i);
            Paragraph consentParagraph = createConsentItem(i, line);
            document.add(consentParagraph);
        }

        CopyrightTransferRequest request = wrapper.copyrightTransferRequest();
        Copyright copyright = wrapper.copyright();
        CorrespondingAuthor correspondingAuthor = wrapper.correspondingAuthor();
        List<Contribution> contributionList = wrapper.contributionList();

        document.add(createParagraph("Date confirmed", request.dateConfirmed()));
        document.add(createParagraph("IP address", request.ipAddress()));
        document.add(createParagraph("Paper ID", copyright.ojsArticleId()));
        document.add(createParagraph("Paper title", copyright.title()));
        document.add(createParagraph("Corresponding author", createAuthorLine(correspondingAuthor)));
        document.add(createParagraph("Contribution of authors", ""));
        document.add(createContributionTable(contributionList));
        document.add(createParagraph("Financial disclosure", copyright.financialDisclosure()));
    }

    private static Image getJournalLogo(Document document, long journalID) throws BadElementException, IOException {
        Image image = JournalUtilProvider.getLogoImage(journalID);
        float scalePercentage = ((document.getPageSize().getWidth() - document.leftMargin()
                - document.rightMargin()) / image.getWidth()) * 100;

        image.scalePercent(scalePercentage);
        return image;
    }

    private static Paragraph createHeader(String text) {
        Paragraph paragraph = new Paragraph(text, HEADER_FONT);
        paragraph.setSpacingBefore(PARAGRAPH_SPACING);
        paragraph.setSpacingAfter(PARAGRAPH_SPACING);
        return paragraph;
    }


    private static java.util.List<String> getConsentToPublishText(long journalID) throws IOException {
        List<String> consentToPublishText = JournalUtilProvider.getConsentToPublishText(journalID);
        return scala.collection.JavaConversions.asJavaList(consentToPublishText);
    }

    private static Paragraph createConsentItem(int i, String line) {
        Paragraph paragraph = new Paragraph((i + 1) + ". " + line, BASIC_FONT);
        paragraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        return paragraph;
    }

    private static Paragraph createParagraph(String propertyName, Object property) {
        Paragraph paragraph = new Paragraph(propertyName + ":\n" + property.toString(), BASIC_FONT);
        paragraph.setIndentationLeft(INDENTATION);
        paragraph.setFirstLineIndent(-INDENTATION);
        paragraph.setSpacingBefore(PARAGRAPH_SPACING);
        paragraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        return paragraph;
    }

    private static String createAuthorLine(CorrespondingAuthor correspondingAuthor) {
        return "Name: " + correspondingAuthor.getFullName() +
                "\nAffiliation: " + correspondingAuthor.affiliation() +
                "\nE-mail: " + correspondingAuthor.email();
    }

    private static PdfPTable createContributionTable(List<Contribution> contributionList) {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        createTableHeader(table);
        for (Iterator<Contribution> contributionIter = contributionList.iterator(); contributionIter.hasNext(); ) {
            Contribution contribution = contributionIter.next();
            addContributionRow(table, contribution);
        }
        table.setSpacingBefore(SPACING_BEFORE_TABLE);
        return table;
    }

    private static void createTableHeader(PdfPTable table) {
        table.addCell(createPhrase("Author name"));
        table.addCell(createPhrase("Affiliation"));
        table.addCell(createPhrase("Contribution to the paper"));
        table.addCell(createPhrase("Estimated % of the total contribution"));
    }

    private static Phrase createPhrase(String text) {
        return new Phrase(text, BASIC_FONT);
    }

    private static void addContributionRow(PdfPTable table, Contribution contribution) {
        table.addCell(createPhrase(contribution.getFullAuthorName()));
        table.addCell(createPhrase(contribution.affiliation()));
        table.addCell(createPhrase(contribution.contribution()));
        table.addCell(createPhrase(Integer.toString(contribution.percent())));
    }

}
