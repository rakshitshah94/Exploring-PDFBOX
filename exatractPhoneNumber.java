import java.io.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.util.*;
import java.util.regex.*;
/**
* extractPhoneNumber.java
*
* @author  Rakshit Shah
* @version 1.0
* @since   2016-08-22 
*/
public class extractPhoneNumber {
 public static void main(String[] args){
 PDDocument pd;
 try {
         // Input PDF file
         File input = new File("C:\\Rakshit.pdf");

         // StringBuilder to store the extracted text
         StringBuilder sb = new StringBuilder();
         pd = PDDocument.load(input);
         PDFTextStripper stripper = new PDFTextStripper();

         // Add text to the StringBuilder from the PDF
         sb.append(stripper.getText(pd));

         // consider 10 digits of mobile number. use below pattern.
         Pattern p = Pattern.compile("\\s\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\s");

         // Matcher refers to the actual text where the pattern will be found
         Matcher m = p.matcher(sb);

         while (m.find()){
             // here we will use group() method which refers to the next number that follows the pattern we have specified.
             System.out.println(m.group());
         }

         if (pd != null) {
             pd.close();
         }
 } catch (Exception e){
         e.printStackTrace();
        }
     }
}