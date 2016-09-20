import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;

/**
* RotatedPDFChecker.java
*
* @author  Rakshit Shah
* @version 1.0
* @since   2016-08-23 
*
*
* 1. First check out height and width of PDF pages
* 2. Find Fontstyle used in PDF, because PDFBOX supports only 14 standard FONTSTYLES.
*  		Link : https://pdfbox.apache.org/1.8/cookbook/workingwithfonts.html
* 3. Lets try to find out Rotation of Pages : 
*		Logic : If Height is greater than width, it is Portrait 
*					else If width is greater than height, it is landscape
* Note : If someone scanned file with different angle or we can say bad scanning, It won't detect Rotation angle of that Image, Page or content.
*/

public class RotatedPDFChecker {
	
	String path = "C:/Rakshit.pdf";
	PDDocument document = null;

	public void RotatedPDFChecker() throws IOException {
		
		try {
			document = PDDocument.load(new File(path));
			document.getClass();
			if (!document.isEncrypted()) {

				List<?> pages = document.getDocumentCatalog().getAllPages();
				Iterator<?> iter = pages.iterator();
				PDPage page = (PDPage) iter.next();
				PDRectangle mediaBox = page.findMediaBox();
				List<?> allPages = document.getDocumentCatalog().getAllPages();

				// To get FontStyle of Different fonts used in PDF - START
				Map<String, PDFont> pageFonts = page.getResources().getFonts();//This line will read fontstyle name[Used For all kind of fonts]
				//System.out.println("Fontstyles Number Count ::: " + pageFonts.size());
				for (String key : pageFonts.keySet()) {
					System.out.println(key+" - "+pageFonts.get(key));
					System.out.println(pageFonts.get(key).getBaseFont());
				}
				// To get FontStyle of Different fonts used in PDF - END

				//Extracting text from PDF pages
				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);
				PDFTextStripper Tstripper = new PDFTextStripper();
				String st = Tstripper.getText(document);

				if (st != null) {
					System.out.println("\nFetched Text:\n" + st);
				}

				// Extracting Image from PDF and save to particular Directory
				if (true) {
					while (iter.hasNext()) {
						PDResources resources = page.getResources();
						Map<?, ?> pageImages = null;

						try {
							pageImages = resources.getImages();
						} catch (IOException e) {
							e.printStackTrace();
						}
						if (pageImages != null) {
							Iterator<?> imageIter = pageImages.keySet().iterator();
							while (imageIter.hasNext()) {
								String key = (String) imageIter.next();
								PDXObjectImage image = (PDXObjectImage) pageImages.get(key);
				                image.write2file("C:\\BeingCoders\\image" + i);
				                i ++;
							}
						} 
					}

				}

				// Find Rotation of each PDF page
				int rotation = page.findRotation();
				System.out.println("Rotation : " + rotation);

				// conditions for deciding Landscape or Portrait
				boolean isLandscape = mediaBox.getWidth() > mediaBox.getHeight();
				boolean isPortrait = mediaBox.getWidth() < mediaBox.getHeight();
				
				if(isLandscape==true){
					System.out.println("Landscape");
				}
				else if(isPortrait==true){
					System.out.println("Portrait");
				}
				
				if(isLandscape == true){
					isLandscape = mediaBox.getWidth() > mediaBox.getHeight();
					for (int i = 0; i < allPages.size(); i++) {

						if (rotation == 90 || rotation == 270 ) {
							System.out.println("1 isLandscape: " + i + " => " + isLandscape);
						} else {
							System.out.println("2 isLandscape: " + i + " => " + !isLandscape);
						}
					}
				}
				else if(isPortrait == true){
					isPortrait = mediaBox.getWidth() < mediaBox.getHeight();
					for (int i = 0; i < allPages.size(); i++) {

						if (rotation == 90 || rotation == 270) {
							System.out.println("3 isPortrait: " + i + " => " + isPortrait);
						} else {
							System.out.println("4 isPortrait: " + i + " => " + !isPortrait);
						}
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) throws IOException {
		RotatedPDFCheck rpc = new RotatedPDFCheck();
		rpc.RotatedPDFChecker();
	}
}
