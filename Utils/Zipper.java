import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class Zipper {
	
	//Java 7
	 public void printEntries(PrintStream stream, String zip)  {
	        ZipFile zipFile = null;
	        try {
	            zipFile = new ZipFile(zip);
	            Enumeration<? extends ZipEntry> entries = zipFile.entries();
	            while (entries.hasMoreElements()) {
	                ZipEntry zipEntry = entries.nextElement();
	                stream.println(zipEntry.getName());
	            }
	        } catch (IOException e) {
	            // error while opening a ZIP file
	        } finally {
	            if (zipFile != null) {
	                try {
	                    zipFile.close();
	                } catch (IOException e) {
	                    // do something
	                }
	            }
	        }
	    }
	 //Java 7 try-with-recourses
	 public void printEntries2(PrintStream stream, String zip) {
	        try (ZipFile zipFile = new ZipFile(zip)) {
	            Enumeration<? extends ZipEntry> entries = zipFile.entries();
	            while (entries.hasMoreElements()) {
	                ZipEntry zipEntry = entries.nextElement();
	                stream.println(zipEntry.getName());
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 //Java 8
	 public void printEntries3(PrintStream stream, String zip) {
	        try (ZipFile zipFile = new ZipFile(zip)) {
	            zipFile.stream()
	                    .forEach(stream::println);
	        } catch (IOException e) {
	            // error while opening a ZIP file
	        	 e.printStackTrace();
	        }
	    }
	 
	 
	 //Java8 sorted and filter contents in Zip file
	 public void printEntries4(PrintStream stream, String zip) {
		    try (ZipFile zipFile = new ZipFile(zip)) {
		        Predicate<ZipEntry> isFile = ze -> !ze.isDirectory();
		        Predicate<ZipEntry> isJava = ze -> ze.getName().matches(".*java");
		        Comparator<ZipEntry> bySize = 
		                (ze1, ze2) -> Long.valueOf(ze2.getSize() - ze1.getSize()).intValue();
		        zipFile.stream()
		                .filter(isFile.and(isJava))
		                .sorted(bySize)
		                .forEach(ze -> print(stream, ze));
		    } catch (IOException e) {
		        // error while opening a ZIP file
		    	e.printStackTrace();
		    }
		}

		private void print(PrintStream stream, ZipEntry zipEntry) {
		    stream.println(zipEntry.getName() + ", size = " + zipEntry.getSize());
		}
	 
	 
	 
	 public static void main(String[] args) {
		 try {
			PrintStream stream = new PrintStream("D:/1/out.txt");
			new Zipper().printEntries3(stream, "D:/1/bootstrap.zip");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
