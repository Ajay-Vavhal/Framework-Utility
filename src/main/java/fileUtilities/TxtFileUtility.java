package fileUtilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This is File Utility class which contain generic method related with files
 *
 */
public class TxtFileUtility {

	private String filepath;
	public TxtFileUtility(String filepath){
		this.filepath = filepath;
	}

	/**
	 * This method is used to check Text file is exits or not
	 * @return : boolean
	 */
	public boolean isTxtFileExist() {
		File f = new File(filepath);
		if (f.exists()) {
			System.out.println("File Exists");
			return true;
		}
		else {
			System.out.println("File Does not Exists");
			return false;
		}
	}

	/**
	 * This method is used to create new Text file
	 * @param fileName : File Name
	 * @return : File
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("unused")
	public File createNewTxtFile(String fileName) throws FileNotFoundException {

		String src=filepath+"\\"+fileName+".txt";
		File file = new File(src);
		if (file.exists()) {
			System.out.println("File Already Exists with file name : "+fileName);
		}
		else {
			PrintStream out=new PrintStream(new File(src));
			System.out.println("New File Created with file name : "+fileName);
		}
		return file;
	}

	/**
	 *  This method is used to write data in Text file
	 * @param content : Data in string
	 * @throws IOException
	 */
	public void writeDataInTxtFile(String content) throws IOException {

		File file = new File(filepath);
		if (file.exists()) {
			BufferedWriter bw;
			FileWriter writer = new FileWriter(file,true);
			bw = new BufferedWriter(writer);
			bw.write(content);
			writer.write("\r\n");
			bw.close();
			writer.close();
		}
		else {
			System.out.println("File Does not Exists");
		}
	}

	/**
	 * This method is used to read data from Text file
	 * @return :String
	 * @throws IOException
	 */
	public String readDataFromTxtFile() throws IOException {
		String data = "";
		File file = new File(filepath);
		if (file.exists()) {
			data = new String(Files.readAllBytes(Paths.get(filepath)));	
			if(data.length()==0) {
				System.out.println("File is empty !!!");
			}
		}
		else {
			System.out.println("File Does not Exists");
		}
		return data;
	}

	/**
	 * This method is used to delete Text file
	 * @param fileName : File Name
	 * @return 
	 * @throws FileNotFoundException
	 */
	public  File deleteTxtFile(String fileName) throws FileNotFoundException {

		String src=filepath+"\\"+fileName+".txt";
		File file = new File(src);
		if (file.exists()) {
			if(file.delete())
			{
				System.out.println("Successfully deleted file name is : "+fileName);
			}
		}     
		else
		{
			System.out.println("File does not exists with name : "+fileName);
		}
		return file;
	}
}



