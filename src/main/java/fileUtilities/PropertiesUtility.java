package fileUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

/**
 * This is an Properties Utility class which contain generic method related with properties file
 *
 */
public class PropertiesUtility {

	private PropertiesUtility() {	
	}
	
	/**
	 * This method is used to check properties file is exits or not
	 * @param path :file path
	 * @return :boolean
	 */
	public static boolean isPropertiesFileExist(String path) {
		File f = new File(path);
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
	 * This method is used to create new  properties file 
	 * @param fileName :fileName
	 * @param path : file Path
	 * @return File
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("unused")
	public static File createNewPropertiesFile(String fileName,String path) throws FileNotFoundException {

		String src=path+"\\"+fileName+".properties";
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
	 * This method is used to get values of key in properties file
	 * @param path : File path
	 * @param key : Key
	 * @return : String
	 * @throws IOException
	 */
	public static String getProperties(String path,String key) throws IOException 
	{
		Properties prop = new Properties();
		InputStream input = new FileInputStream(path);
		prop.load(input);
		String value=prop.getProperty(key);	
		return value;
	}

	/**
	 * This method is used to set keys and value in properties file
	 * @param path FilePath
	 * @param key : key
	 * @param value : value
	 * @throws IOException
	 */
	public static void setProperties(String path,String key,String value) throws IOException 
	{
		FileInputStream in = new FileInputStream(path);
		Properties props = new Properties();
		props.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream(path);
		props.setProperty(key,value);
		props.store(out, null);
		out.close();
	}

	/**
	 * This method is used to delete properties file using file path 
	 * @param path : filePath
	 * @return : File
	 * @throws FileNotFoundException
	 */
	public static File deletePropertiesFile(String path) throws FileNotFoundException {
		File file = new File(path);
		if (file.exists()) {
			if(file.delete())
			{
				System.out.println("Successfully deleted file name is : "+file.getName());
			}
		}     
		else
		{
			System.out.println("File does not exists with name : "+file.getName());
		}
		return file;
	}
}