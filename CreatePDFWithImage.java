import java.io.FileInputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

/**
* CreatePDFWithImage.java
*
* @author  Rakshit Shah
* @version 1.0
* @since   2016-08-22 
*/

public class CreatePDFWithImage {

    public static void main(String[] args) {

        String fileName = "pdfWithImage.pdf";
        String imageName = "BeingCoders_com.jpg";

        try {

            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();

            doc.addPage(page);

            PDXObjectImage image = new PDJpeg(doc, new FileInputStream(imageName));

            PDPageContentStream content = new PDPageContentStream(doc, page);

            content.drawImage(image, 180, 700);

            content.close();

            doc.save(fileName);

            doc.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}