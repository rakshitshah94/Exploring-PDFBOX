import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import java.util.Calendar;
import java.text.SimpleDateFormat;
/**
* GetMetadata.java
*
* @author  Rakshit Shah
* @version 1.0
* @since   2016-08-22 
* 
* An example showing how to get info about a PDF file.
* Here is how it works
* 1. Load a PDF file. Make provisions to catch or throw IOException.
* 2. Call the getDocumentInformation() method on the PDDocument object.
* 3. Call the different get methods on the resulting PDDocumentInformation object.
* 4. The methods are 
*        getTitle() - Will get the document's title as a String. Can return null.
*        getAuthor() - Will get the name of the person who created the String. Can return null.
*        getSubject() - Will get the Subject as a String. Can return null.
*        getKeywords() - Will get the Keywords for this document as a String. Can return null.
*        getCreator() - Will get the Creator of the doc as a String. Can return null.
*        getProducer() - Will get the format from which the PDF was converted (if it was converted)
*        For example, a document that I created in Ms Word and converted to a PDF using a free PDF
*        converting tool had the creator as "PScript5.dll Version 5.2.2". Can return null.
*        getCreationDate() - Will get the Creation date and time as a Calendar object. Can return null.
*        getModificationDate() - Will get the date and time when the PDF was most recently modified
*        as a Calendar object. Can return null.
*        getTrapped() - Gets the trapped value as a String. Can return null. Look at the link below for more information on Trapped.
*
* 5. Create a static method to convert Calendar to date format dd-MM-yyyy. WARNING: I am not sure if my steps are right.
*/

public class GetMetadata {
    static String getProperDate(Calendar cal){
        // We use this method to convert the Calendar object to a more readable date.
        // WARNING: I am not sure if my steps are right.
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String properDate = dateFormat.format(cal.getTime());
        return properDate;
    }
    
    public static void main(String[] args) throws IOException {
        // Load the PDF. The PDDocument throws IOException
        PDDocument document = new PDDocument();
        document = PDDocument.load("C:\\Document1.pdf");
        
        // Get the PDDocumentInformation object from the PDDocument that you created
        PDDocumentInformation info = document.getDocumentInformation();
        
        // Refer to the comments at the start of the code
        System.out.println("Title " + info.getTitle());
        System.out.println("Author " + info.getAuthor());
        System.out.println("Subject " + info.getSubject());
        System.out.println("Keywords " + info.getKeywords());
        System.out.println("Creator " + info.getCreator());
        System.out.println("Producer " + info.getProducer());
        System.out.println("Created date " + getProperDate(info.getCreationDate()));
        System.out.println("Modified date " + getProperDate(info.getModificationDate()));
        System.out.println("Trapped " + info.getTrapped());
    }
}