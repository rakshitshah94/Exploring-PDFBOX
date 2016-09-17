import java.io.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.util.*;
/**
* FileSizeCounter.java
*
* @author  Rakshit Shah
* @version 1.0
* @since   2016-08-22 
*/
public class FileSizeCounter {
 public static void main(String[] args){
 PDDocument pd;
 try {
         // Input PDF file
         File input = new File("C:\\Rakshit.pdf");

        if (file.exists()) {
			double bytes = file.length();
			double kilobytes = (bytes / 1024);//If you want to count in Kilobytes
			double megabytes = (kilobytes / 1024);//If you want to count in Megabytes, do same for giga tera peta exa bytes...
			System.out.println("File Size of " + file.getName() + "in bytes : " + bytes);
			System.out.println("File Size of " + file.getName() + "in kilobytes : " + kilobytes);
			System.out.println("File Size of " + file.getName() + "in megabytes : " + megabytes);
		} else {
			System.out.println("Sorry !!! File does not Found!");
		}
 } catch (Exception e){
         e.printStackTrace();
        }
     }
}

