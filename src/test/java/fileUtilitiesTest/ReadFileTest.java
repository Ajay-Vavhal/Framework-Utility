package fileUtilitiesTest;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import BasePackage.BaseTest;
import fileUtilities.TxtFileUtility;
import fileUtilities.PropertiesUtility;


/**
 *  This is Test class which contain Test method for Text file and Properties files
 */
public class ReadFileTest extends BaseTest {	
	/**
	 * This test case is related to text file which verify the text file is exist or not on given path
	 * @throws IOException
	 */
	@Test(enabled=true, priority=1)
	public void verifyTextFileExist() throws IOException {
		String fileName ="TestFile";	
		TxtFileUtility fuObj = new TxtFileUtility(System.getProperty("user.dir") + "\\filesTestData\\" + fileName + ".txt");
		fuObj.isTxtFileExist();
	}

	/**
	 * This test case is related to text file which create new text file
	 * @throws IOException
	 */
	@Test(enabled=true, priority=2)
	public void createTextFile() throws IOException {
		String fileName ="newfile";
		TxtFileUtility fuObj = new TxtFileUtility(System.getProperty("user.dir") + "\\filesTestData\\");
		File file=fuObj.createNewTxtFile(fileName);
		System.out.println("File Name :"+file.getName());	
	}

	/**
	 * This test case is related to text file which which write data in text file
	 * @throws IOException
	 */
	@Test(enabled=true, priority=3)
	public void writeDataInTextFile() throws IOException {
		String fileName ="newfile";
		TxtFileUtility fuObj = new TxtFileUtility(System.getProperty("user.dir") + "\\filesTestData\\" + fileName + ".txt");
		String data ="write data in txt file";
		fuObj.writeDataInTxtFile(data);
	}

	/**
	 * This test case is related to text file which read data from text file
	 * @throws IOException
	 */
	@Test(enabled=true, priority=4)
	public void readTextFile() throws IOException {
		String fileName ="TestFile";
		TxtFileUtility fuObj = new TxtFileUtility(System.getProperty("user.dir") + "\\filesTestData\\" + fileName + ".txt");
		String data = fuObj.readDataFromTxtFile();
		System.out.println(data);
	}

	/**
	 * This test case is related to text file which delete text file by name
	 * @throws IOException
	 */
	@Test(enabled=true, priority=5)
	public void deleteTextFile() throws IOException {
		String fileName ="newFile";
		TxtFileUtility fuObj = new TxtFileUtility(System.getProperty("user.dir") + "\\filesTestData\\");
		File file=fuObj.deleteTxtFile(fileName);
		System.out.println("File Name :"+file.getName());	
	}


	/**
	 * This test case is related to properties file which verify properties file exist or not
	 * @throws IOException
	 */
	@Test(enabled=true, priority=6)
	public void verifyPropertiesFileExist() throws IOException {
		PropertiesUtility.isPropertiesFileExist(System.getProperty("user.dir") + "\\filesTestData\\basicInfo.properties");
	}

	/**
	 * This test case is related to properties file which create new properties file 
	 * @throws IOException
	 */
	@Test(enabled=true, priority=7)
	public void createPropertiesFile() throws IOException {
		//pass file name you want to create and path 
		File file = PropertiesUtility.createNewPropertiesFile("config", System.getProperty("user.dir") + "\\filesTestData\\");
		System.out.println("File Name :"+file.getName());	
	}

	/**
	 * This test case is related to properties file which set data in properties file 
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@Test(enabled=true, priority=8)
	public void writeInPropertiesFile() throws IOException {
		//Arguments passed : file path,key & value
		PropertiesUtility.setProperties(System.getProperty("user.dir") + "\\filesTestData\\config.properties", "UserName", "TeamLead123");	
	}

	/**
	 * This test case is related to properties file which give you value of given key 
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@Test(enabled=true, priority=9)
	public void readFromPropertiesFile() throws IOException {
		String path = System.getProperty("user.dir") + "\\filesTestData\\basicInfo.properties";
		String browserValue = PropertiesUtility.getProperties(path, "Browser");
		String urlValue = PropertiesUtility.getProperties(path, "URL");
		String passValue = PropertiesUtility.getProperties(path, "Password");
		System.out.println("Browser :"+browserValue);
		System.out.println("URL :"+urlValue);
		System.out.println("Password :"+passValue);

	}

	/**
	 * This test case is related to properties file which delete properties file on given path
	 * @throws IOException
	 */
	@Test(enabled=true, priority=10)
	public void deletePropFile() throws IOException {
		File file = PropertiesUtility.deletePropertiesFile( System.getProperty("user.dir") + "\\filesTestData\\config.properties");
		System.out.println("File Name :"+file.getName());	
	}
}