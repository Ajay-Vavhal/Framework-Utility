package excelUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import exceptions.ColumnNameNotFoundException;
import exceptions.NoRowFoundException;
import exceptions.ObjectLengthNotCorrectException;
import exceptions.SheetNotFoundException;

/**
 * This is Excel Utility class which contain generic method related with excel files
 */
public class ExcelUtilities {

	private String path;
	private Workbook workbook;	
	public FileInputStream fi;
	public FileOutputStream fo;
	public Sheet sheet;
	public Row row;
	public Cell cell;
	public CellStyle style;


	public ExcelUtilities() {
	}

	public ExcelUtilities(String path) throws Throwable {
		this.path = path;
		this.workbook=getWorkbook(path);
	}

	/** 
	 * This method is used to get Workbook object
	 * @param filePath
	 * @return 
	 * @return
	 * @throws Throwable
	 */
	public boolean isFilePresent(String path) {
		File file =new File(path);
		if (file.exists()) {
			return true;
		}else {
			return false;
		}	
	}

	@SuppressWarnings("resource")
	public Workbook getWorkbook(String filePath) throws Throwable {
		FileInputStream fi = new FileInputStream(filePath);
		Workbook workbook = null;

		if (filePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(fi);
		} else if (this.path.endsWith("xls")) {
			workbook = new HSSFWorkbook(fi);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}
		return workbook;
	}


	/**
	 * This method is used to get Sheet Name
	 * @param Path or
	 * @param workbook or
	 * @param sheetIndex
	 * @return SheetName
	 * @throws Throwable
	 */
	public String getSheetname(String path, int sheetIndex) throws Throwable {		
		Workbook workbook = getWorkbook(path);		
		String sheetName = getSheetname(workbook,sheetIndex);
		return sheetName;
	}

	public String getSheetname(Workbook workbook,int sheetIndex) throws Throwable {		
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		String sheetName = sheet.getSheetName();
		return sheetName;
	}

	public String getSheetname(int sheetIndex) throws Throwable {		
		Sheet sheet = this.workbook.getSheetAt(sheetIndex);
		String sheetName = sheet.getSheetName();
		return sheetName;
	}

	/**
	 * Get sheet  name base on test script name 
	 * @param TestScriptName
	 * @return
	 * @throws Throwable
	 */
	public String getSheetName(String TestScriptName,String filePath) throws Throwable {

		//		Workbook book = getWorkbook(System.getProperty("driverFilePath"));
		//		Sheet testScriptSheetObj = getSheetObject(book,"TestScripts");

		Workbook book = getWorkbook(filePath);
		String sheetName= "TestScripts";
		Sheet testScriptSheetObj = getSheetObject(book,sheetName);

		ArrayList<ArrayList<String>> testDataSheetNameList = getMultipleColumnDataBasedOnOneColumnValue(testScriptSheetObj,"TestScriptName",TestScriptName,"TestDataSheetName");
		String testDataSheetName=(testDataSheetNameList.get(0)).get(0);
		return testDataSheetName;
	}


	/**
	 * This method is used to get Sheet Names
	 * @param workbook
	 * @return
	 */
	public List<String> getAllSheetNames()
	{
		List<String> sheetNames = new ArrayList<String>();
		for (int i = 0; i < this.workbook.getNumberOfSheets(); i++) {
			String sheetName=this.workbook.getSheetName(i);
			sheetNames.add(sheetName);
		}
		return sheetNames;		
	}

	public List<String> getAllSheetNames(Workbook workbook)
	{
		List<String> sheetNames = new ArrayList<String>();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			String sheetName=workbook.getSheetName(i);
			sheetNames.add(sheetName);
		}
		return sheetNames;		
	}

	public List<String> getAllSheetNames(String path) throws Throwable{
		Workbook workbook = getWorkbook(path);
		return getAllSheetNames(workbook);		
	}

	/**
	 * @param sheetObj
	 * @return
	 */
	public static List<String> getAllColmnNames(Sheet sheetObj) {
		DataFormatter formatter = new DataFormatter();
		Row rowObj = sheetObj.getRow(0);
		ArrayList<String> columnNames = new ArrayList<String>();

		for(Cell cellObj:rowObj) {
			if(!isCellEmpty(cellObj)) {
				columnNames.add(formatter.formatCellValue(cellObj));
			}
		}		
		return columnNames;		
	}

	/**
	 * This method is return total number of sheets of particular excel file
	 * @param workbook
	 * @return
	 */
	public int getNumberOfSheets()
	{
		int numberOfSheets=this.workbook.getNumberOfSheets();
		return numberOfSheets;	
	}

	public int getNumberOfSheets(Workbook workbook)
	{
		int numberOfSheets=workbook.getNumberOfSheets();
		return numberOfSheets;	
	}

	public int getNumberOfSheets(String path) throws Throwable{
		Workbook workbook = getWorkbook(path);
		return getNumberOfSheets(workbook);	
	}


	/**
	 * This method is used to get Sheet object 
	 * @param workbook
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 */
	public Sheet getSheetObject(Workbook workbook,String sheetName) throws Throwable {
		Sheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
			throw new SheetNotFoundException("Sheet: "+sheetName+" not found");
		}
		return sheet;
	}

	public Sheet getSheetObject(String sheetName) throws Throwable {
		return getSheetObject(this.workbook,sheetName);
	}

	public Sheet getSheetObject(String path,String sheetName) throws Throwable {
		Workbook workbook = getWorkbook(path);		
		return getSheetObject(workbook,sheetName);
	}


	/** 
	 * This method is return total number of rows count
	 * @param workbook
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 */
	public static boolean isRowEmpty(Row row) {
		boolean isEmpty = true;
		DataFormatter dataFormatter = new DataFormatter();

		if (row != null) {
			for (Cell cell : row) {
				if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
					isEmpty = false;
					break;
				}
			}
		}
		return isEmpty;
	}

	/**
	 * @param cellObj
	 * @return
	 */
	public static boolean isCellEmpty(Cell cellObj) {
		boolean isEmpty = true;
		DataFormatter dataFormatter = new DataFormatter();

		if (dataFormatter.formatCellValue(cellObj).trim().length() > 0) {
			isEmpty = false;
		}
		return isEmpty;
	}

	/**
	 * @param rowObj
	 * @return
	 */
	public static int getNonEmptyCellCountInRow(Row rowObj) {
		int count=0;
		for(Cell cellObj:rowObj) {
			if(!isCellEmpty(cellObj)) {
				count++;
			}
		}
		return count;		
	}

	public static int getRowCount(Sheet sheetObject) throws Throwable {
		int count=0;
		for(Row row:sheetObject) {
			if(!isRowEmpty(row)) {
				count++;
			}
		}
		return count;
	}


	public int getRowCount(String sheetName) throws Throwable {
		Sheet sheetObject = getSheetObject(sheetName);
		int rowcount = getRowCount(sheetObject);
		return rowcount;
	}

	public int getRowCount(Workbook workbook,String sheetName) throws Throwable {
		Sheet sheetObject = getSheetObject(workbook,sheetName);
		int rowcount = getRowCount(sheetObject);
		return rowcount;
	}

	public int getRowCount(String path,String sheetName) throws Throwable {
		Sheet sheetObject = getSheetObject(path,sheetName);
		int rowcount = getRowCount(sheetObject);
		return rowcount;
	}


	/**
	 * This method is return total number of columns count
	 * @param workbook
	 * @param sheetName
	 * @param rownum
	 * @return
	 * @throws Throwable
	 */

	public int getColumnCount(String sheetName, int rownum) throws Throwable {
		Sheet sheet = getSheetObject(sheetName);
		Row row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		return cellcount;
	}

	public int getColumnCount(Workbook workbook,String sheetName, int rownum) throws Throwable {
		Sheet sheet = getSheetObject(workbook,sheetName);
		Row row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		return cellcount;
	}

	public int getColumnCount(String path, String sheetName, int rownum) throws Throwable {
		Sheet sheet = getSheetObject(path,sheetName);
		Row row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		return cellcount;
	}


	/**
	 * This method is return particular row data
	 * @param workbook
	 * @param sheetName
	 * @param rownum
	 * @return
	 * @throws Throwable
	 */
	public ArrayList<String> getRowData(String sheetName, int rownum) throws Throwable {
		Sheet sheetObject = getSheetObject(sheetName);		
		return getRowData(sheetObject,rownum);
	}

	public ArrayList<String> getRowData(Workbook workbook,String sheetName, int rownum) throws Throwable {
		Sheet sheetObject = getSheetObject(workbook,sheetName);		
		return getRowData(sheetObject,rownum);
	}

	public ArrayList<String> getRowData(String path,String sheetName, int rownum) throws Throwable {
		Sheet sheetObject = getSheetObject(path,sheetName);		
		return getRowData(sheetObject,rownum);
	}

	public ArrayList<String> getRowData(Sheet sheetObject, int rownum) throws Throwable {
		DataFormatter formatter = new DataFormatter();
		ArrayList<String> data = new ArrayList<String>();		

		Row rowObject = sheetObject.getRow(rownum);		
		int coulmnsCount = rowObject.getPhysicalNumberOfCells();

		for (int i = 0; i < coulmnsCount; i++) {
			Cell cellObject = rowObject.getCell(i);
			String cellValue = formatter.formatCellValue(cellObject);
			data.add(cellValue);
		}
		return data;
	}


	/**
	 * This method returns All column data based on column Index
	 * @param workbook
	 * @param sheetName
	 * @param column
	 * @return
	 * @throws Throwable
	 */

	public ArrayList<String> getColumnDataByIndex(Workbook workbook,String sheetName, int columnIndx) throws Throwable {
		Sheet sheetObject = getSheetObject(workbook,sheetName);
		return getColumnDataByIndex(sheetObject,columnIndx);
	}

	public ArrayList<String> getColumnDataByIndex(String path,String sheetName, int columnIndx) throws Throwable {
		Sheet sheetObject = getSheetObject(path,sheetName);
		return getColumnDataByIndex(sheetObject,columnIndx);
	}


	public ArrayList<String> getColumnDataByIndex(String sheetName, int columnIndx) throws Throwable {
		Sheet sheetObject = getSheetObject(sheetName);
		return getColumnDataByIndex(sheetObject,columnIndx);
	}


	public ArrayList<String> getColumnDataByIndex(Sheet sheetObject, int columnIndx) throws Throwable {
		DataFormatter formatter = new DataFormatter();
		ArrayList<String> data = new ArrayList<String>();

		int rowCount = getRowCount(sheetObject);		
		for (int i = 1; i < rowCount; i++) {
			Row rowObj = sheetObject.getRow(i);
			Cell cellObj = rowObj.getCell(columnIndx);
			String cellValue = formatter.formatCellValue(cellObj);
			data.add(cellValue);
		}
		return data;
	}

	/**
	 * This method returns All column data based on column Name
	 * @param workbook
	 * @param sheetName
	 * @param column
	 * @return
	 * @throws Throwable
	 * Author: Rajeev
	 */

	public ArrayList<String> getColumnDataByColumnName(Workbook workbook,String sheetName, String columnName) throws Throwable {
		Sheet sheetObject = getSheetObject(workbook,sheetName);
		return getColumnDataByIndex(sheetObject,getColumnIndexFrmColumnName(sheetObject,columnName));
	}

	public ArrayList<String> getColumnDataByColumnName(String path,String sheetName, String columnName) throws Throwable {
		Sheet sheetObject = getSheetObject(path,sheetName);
		return getColumnDataByIndex(sheetObject,getColumnIndexFrmColumnName(sheetObject,columnName));
	}


	public ArrayList<String> getColumnDataByColumnName(String sheetName, String columnName) throws Throwable {
		Sheet sheetObject = getSheetObject(sheetName);
		return getColumnDataByIndex(sheetObject,getColumnIndexFrmColumnName(sheetObject,columnName));
	}


	public ArrayList<String> getColumnDataByColumnName(Sheet sheetObject, String columnName) throws Throwable {
		return getColumnDataByIndex(sheetObject,getColumnIndexFrmColumnName(sheetObject,columnName));
	}

	/**
	 * The Method extracts Row data for columns given based on criteria(value equals) for another column
	 * @param sheetObject
	 * @param Column2Check
	 * @param Criteria
	 * @param extColumnName
	 * @return
	 * @throws Throwable
	 */
	public  ArrayList<ArrayList<String>> getMultipleColumnDataBasedOnOneColumnValue(Sheet sheetObject, String Column2Check, String Criteria, String... extColumnName) throws Throwable 
	{

		ArrayList<ArrayList<String>> columData = new ArrayList<ArrayList<String>>();
		String[][]srchCriteria={{Column2Check,Criteria}};
		ArrayList<HashMap<String, String>> rowAllData = getRowData(sheetObject,srchCriteria);

		for(HashMap<String, String> map:rowAllData) {
			ArrayList<String> temp = new ArrayList<String>();
			for(String colname:extColumnName) {
				temp.add(map.get(colname));
			}
			columData.add(temp);
		}
		return columData;
	}

	/**
	 * This method returns the column index for a given column name
	 * @param workbook
	 * @param sheetName
	 * @param srchColumnName
	 * @return
	 * @throws Throwable
	 * Author: Rajeev
	 */
	public int getColumnIndexFrmColumnName(String path, String sheetName, String srchColumnName)throws Throwable {
		Sheet sheet = getSheetObject(path, sheetName);
		return getColumnIndexFrmColumnName(sheet,srchColumnName);
	}

	public int getColumnIndexFrmColumnName(Workbook workbook, String sheetName, String srchColumnName)throws Throwable {
		Sheet sheet = getSheetObject(workbook, sheetName);		
		return getColumnIndexFrmColumnName(sheet,srchColumnName);
	}

	public int getColumnIndexFrmColumnName(String sheetName, String srchColumnName)throws Throwable {
		Sheet sheet = getSheetObject(sheetName);		
		return getColumnIndexFrmColumnName(sheet,srchColumnName);
	}

	public static int getColumnIndexFrmColumnName(Sheet sheet, String srchColumnName)throws Throwable {
		DataFormatter formatter = new DataFormatter();
		Row row = sheet.getRow(0);
		int totalColumns = row.getPhysicalNumberOfCells();
		int columnIndex=-1;
		for (int i = 0; i < totalColumns; i++) {
			Cell cellObject = row.getCell(i);
			String extColumnName = formatter.formatCellValue(cellObject).trim().toUpperCase();
			srchColumnName=srchColumnName.trim().toUpperCase();
			if (srchColumnName.equals(extColumnName)) {
				columnIndex = i;
				break;
			}
		}		
		if (columnIndex==-1) {
			throw new ColumnNameNotFoundException("Column Name: "+srchColumnName+" is not found in the excel sheet");
		}else
		{
			return columnIndex;
		}
	}

	/**
	 * This method is return particular cell data
	 * @param workbook
	 * @param sheetName
	 * @param rownum
	 * @param colnum
	 * @return
	 * @throws Throwable
	 */
	public String getCellData(String path,String sheetName, int rownum, int colnum) throws Throwable {
		Sheet sheetObj = getSheetObject(path,sheetName);
		Row rowObj = sheetObj.getRow(rownum);
		return getCellData(rowObj,colnum);
	}

	public String getCellData(Workbook workbook, String sheetName,int rownum,int colnum) throws Throwable {
		Sheet sheetObj = getSheetObject(workbook,sheetName);
		Row rowObj = sheetObj.getRow(rownum);
		return getCellData(rowObj,colnum);
	}


	public String getCellData(String sheetName,int rownum,int colnum) throws Throwable {
		Sheet sheetObj = getSheetObject(sheetName);
		Row rowObj = sheetObj.getRow(rownum);
		return getCellData(rowObj,colnum);
	}


	public String getCellData(Sheet sheetObj,int rownum,int colnum) throws Throwable {
		Row rowObj = sheetObj.getRow(rownum);
		return getCellData(rowObj,colnum);
	}

	public String getCellData(Row rowObj,int colnum) throws Throwable {
		Cell cellObj = rowObj.getCell(colnum);		
		return getCellData(cellObj);
	}

	public String getCellData(Cell cellObj) throws Throwable {
		DataFormatter formatter = new DataFormatter();	
		String data = formatter.formatCellValue(cellObj); 
		return data;
	}


	/** 
	 * This method is used to check sheet is exist or not 
	 * @param workbook
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 */
	public boolean isSheetPresent(String path,String sheetName) throws Throwable {		
		return (isSheetPresent(getWorkbook(path),  sheetName));
	}

	public boolean isSheetPresent(String sheetName) throws Throwable {
		return (isSheetPresent(this.workbook,  sheetName));
	}

	public boolean isSheetPresent(Workbook workbook, String SheetName) throws SheetNotFoundException {
		boolean isSheetExist=false;	

		List<String> allSheetNames = getAllSheetNames(workbook);

		for(String sheetName:allSheetNames) {
			if (sheetName.trim().toUpperCase().equals(sheetName.trim().toUpperCase())) {
				isSheetExist = true;
				break; 
			}
		}
		return isSheetExist;
	}

	/**
	 * This method is used to set or replace particular cell data
	 * @param excelPath
	 * @param sheetName
	 * @param rownum
	 * @param colnum
	 * @param data
	 * @return 
	 * @throws Throwable 
	 */
	public Workbook setOrReplaceCellData(Workbook workbook,String sheetName, int rownum, int colnum, String data) throws Throwable {
		Sheet sheetObject = getSheetObject(workbook,sheetName);

		if (sheetObject.getRow(rownum) == null) {
			sheetObject.createRow(rownum);
		}
		Row rowObj = sheetObject.getRow(rownum);
		setOrReplaceCellData(rowObj,colnum,data);
		return workbook;
	}

	public Workbook setOrReplaceCellData(String filePath,String sheetName, int rownum, int colnum, String data) throws Throwable {
		Sheet sheetObject = getSheetObject(filePath,sheetName);

		if (sheetObject.getRow(rownum) == null) {
			sheetObject.createRow(rownum);
		}
		Row rowObj = sheetObject.getRow(rownum);
		setOrReplaceCellData(rowObj,colnum,data);
		return workbook;
	}

	public Sheet setOrReplaceCellData(Sheet sheetObject, int rownum, int colnum, String data) throws Throwable {

		if (sheetObject.getRow(rownum) == null) {
			sheetObject.createRow(rownum);
		}			
		Row rowObj = sheetObject.getRow(rownum);
		setOrReplaceCellData(rowObj,colnum,data);
		return sheetObject;
	}


	public Cell setOrReplaceCellData(Row rowObj, int colnum, String data) throws Throwable {		
		Cell cellObj = rowObj.createCell(colnum);
		cellObj.setCellValue(data);
		return cellObj;
	}

	/**
	 * The method writes the workbook obj to a file
	 * @param workbook
	 * @param filePath
	 */
	public void writeWorkbook(Workbook workbook,String filePath) {
		try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Get row number using column name and it's value
	 * @param workbook
	 * @param sheetName
	 * @param columnName
	 * @param columnValue
	 * @return
	 * @throws Throwable
	 */
	public int getRowNumForColumnNameAndCellValue(Workbook workbook, String sheetName, String columnName,	String srchString) throws Throwable {
		DataFormatter formatter = new DataFormatter();
		int rowNum = 0;

		Sheet sheet = getSheetObject(workbook, sheetName);

		int columnIndexFrmColumnName = getColumnIndexFrmColumnName(sheet,columnName);

		int rowCount = getRowCount(sheet);

		for (int j = 1; j < rowCount; j++) {
			Row row = sheet.getRow(j);
			Cell cellObj = row.getCell(columnIndexFrmColumnName);
			String cellValue = formatter.formatCellValue(cellObj).trim().toUpperCase();
			if (cellValue.equals(srchString.trim().toUpperCase())) {
				rowNum = j;
				break;
			}
		}
		return rowNum;
	}

	/**
	 * Get column value using column name and row number
	 * @param workbook
	 * @param sheetName
	 * @param columnName
	 * @param rowNum
	 * @return
	 * @throws Throwable
	 */
	public String getCellValueByColumnName(Workbook workbook, String sheetName, String columnName, int rowNum)throws Throwable {
		DataFormatter formatter = new DataFormatter();
		Sheet sheetObj = getSheetObject(workbook, sheetName);

		int columnIndexFrmColumnName = getColumnIndexFrmColumnName(sheetObj,columnName);

		Row row = sheetObj.getRow(rowNum);
		Cell cellObj = row.getCell(columnIndexFrmColumnName);
		String cellValue = formatter.formatCellValue(cellObj);	
		return cellValue;
	}

	/**
	 * Get column value using column name and row number
	 * @param FilePath
	 * @param sheetName
	 * @param columnName
	 * @param rowNum
	 * @return
	 * @throws Throwable
	 */
	public String getCellValueByColumnName(String filePath, String sheetName, String columnName, int rowNum)throws Throwable {
		DataFormatter formatter = new DataFormatter();		
		Workbook workbook = getWorkbook(filePath);	
		Sheet sheetObj = getSheetObject(workbook, sheetName);
		int columnIndexFrmColumnName = getColumnIndexFrmColumnName(sheetObj,columnName);
		Row row = sheetObj.getRow(rowNum);
		Cell cellObj = row.getCell(columnIndexFrmColumnName);
		String cellValue = formatter.formatCellValue(cellObj);	
		return cellValue;
	}

	/**
	 * Get column value using sheet object, column name and row number
	 * @param sheetObj
	 * @param columnName
	 * @param rowNum
	 * @return
	 * @throws Throwable
	 */
	public String getCellValueByColumnName(Sheet sheetObj, String columnName, int rowNum)throws Throwable {
		DataFormatter formatter = new DataFormatter();		

		int columnIndexFrmColumnName = getColumnIndexFrmColumnName(sheetObj,columnName);

		Row row = sheetObj.getRow(rowNum);
		Cell cellObj = row.getCell(columnIndexFrmColumnName);
		String cellValue = formatter.formatCellValue(cellObj);	
		return cellValue;
	}

	/**
	 * Method to get All row data for a condition or None
	 * @param sheetObject
	 * @param srchCriteriaArray
	 * @return
	 * @throws Throwable
	 */
	public static ArrayList<HashMap<String, String>> getRowData(Sheet sheetObject,String[][] srchCriteriaArray) throws Throwable
	{	
		for(String [] arr:srchCriteriaArray) {
			if(arr.length !=2) {
				throw new ObjectLengthNotCorrectException("Sreach Object inner Array Should have 2 elements, "
						+ "while "+arr.length+" was given");
			}
		}

		DataFormatter formatter = new DataFormatter();

		ArrayList<HashMap<String,String>> allRowData = new ArrayList<HashMap<String,String>>();	

		List<String> allColmnNames = ExcelUtilities.getAllColmnNames(sheetObject);		
		int rowCount=getRowCount(sheetObject);
		boolean oneRowFound=false;

		for(int i=1;i<rowCount;i++) {
			boolean srchFlag=true;
			for(String [] arr:srchCriteriaArray) {
				int column2CheckIndx=ExcelUtilities.getColumnIndexFrmColumnName(sheetObject,arr[0]);
				Row rowObj=sheetObject.getRow(i);
				Cell cellObj=rowObj.getCell(column2CheckIndx);
				String cellValue=formatter.formatCellValue(cellObj);
				srchFlag = srchFlag && (cellValue.trim().toUpperCase()).equals(arr[1].trim().toUpperCase());			
			}
			if (srchFlag){
				oneRowFound=true;
				HashMap<String,String> rowData=new HashMap<String,String>();
				for(String colName:allColmnNames) {
					int columnIndex = ExcelUtilities.getColumnIndexFrmColumnName(sheetObject, colName);
					rowData.put(colName.trim(),formatter.formatCellValue(sheetObject.getRow(i).getCell(columnIndex)).trim());
				}
				allRowData.add(rowData);
			}
		}
		if(!oneRowFound) {	
			String message = "";
			for(String [] arr:srchCriteriaArray) {
				message = message + arr[0]+"="+arr[1]+" ";
			}
			throw new NoRowFoundException("No Rows found in sheet="+sheetObject.getSheetName()+" for search criteria "+ message);
		}
		return allRowData; 
	}

	public static ArrayList<HashMap<String, String>> getRowData(Sheet sheetObject) throws Throwable
	{	
		DataFormatter formatter = new DataFormatter();

		ArrayList<HashMap<String,String>> allRowData = new ArrayList<HashMap<String,String>>();	

		List<String> allColmnNames = ExcelUtilities.getAllColmnNames(sheetObject);		
		int rowCount=getRowCount(sheetObject);

		for(int i=1;i<rowCount;i++) {
			HashMap<String,String> rowData=new HashMap<String,String>();
			for(String colName:allColmnNames) {
				int columnIndex = ExcelUtilities.getColumnIndexFrmColumnName(sheetObject, colName);
				rowData.put(colName,formatter.formatCellValue(sheetObject.getRow(i).getCell(columnIndex)));
			}
			allRowData.add(rowData);
		}
		if(allRowData.size() ==0) {
			throw new NoRowFoundException("No Rows found for sheet "+ sheetObject.getSheetName());
		}
		return allRowData; 
	}

	public ArrayList<HashMap<String, String>> getTestData(String TestScriptName,String filePath) throws Throwable {

		String testDataSheetName =getSheetName(TestScriptName,filePath);
		Workbook book = getWorkbook(filePath);
		Sheet testDataSheetObj = getSheetObject(book, testDataSheetName);
		String clientCode=System.getProperty("clientCode");

		ArrayList<HashMap<String, String>> allTestData=new ArrayList<HashMap<String, String>>();
		if(clientCode.trim().toUpperCase()=="ALL") {
			allTestData = ExcelUtilities.getRowData(testDataSheetObj);
		}else {
			String[][] srchData= {{"ClientCode",clientCode}};
			allTestData = ExcelUtilities.getRowData(testDataSheetObj,srchData);
		}
		return allTestData;
	}

	/**
	 * Fills green color in required cell
	 * @param sheetName
	 * @param rownum
	 * @param colnum
	 * @throws IOException
	 */
	public void fillGreenColor(String filePath,String sheetName,int rownum,int colnum) throws IOException
	{
		//		fi=new FileInputStream(System.getProperty("driverFilePath"));
		fi=new FileInputStream(filePath);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);

		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);

		style=workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.LIME.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 

		cell.setCellStyle(style);

		fo=new FileOutputStream(filePath);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}

	/**
	 * Fills red color in required cell
	 * @param sheetName
	 * @param rownum
	 * @param colnum
	 * @throws IOException
	 */
	public void fillRedColor(String filePath,String sheetName,int rownum,int colnum) throws IOException
	{
		//		fi=new FileInputStream(System.getProperty("driverFilePath"));
		fi=new FileInputStream(filePath);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);

		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);

		style=workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  

		cell.setCellStyle(style);

		fo=new FileOutputStream(filePath);
		workbook.write(fo);

		workbook.close();
		fi.close();
		fo.close();
	}
	
	/**
	 * Fills yellow color in required cell
	 * @param sheetName
	 * @param rownum
	 * @param colnum
	 * @throws IOException
	 */
	public void fillYellowColor(String filePath,String sheetName,int rownum,int colnum) throws IOException
	{
		//		fi=new FileInputStream(System.getProperty("driverFilePath"));
		fi=new FileInputStream(filePath);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);

		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);

		style=workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  

		cell.setCellStyle(style);		

		fo=new FileOutputStream(filePath);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}

	/**
	 * This method returns matched row found
	 * @param sheetObject
	 * @param srchCriteriaArray
	 * @return
	 * @throws Throwable
	 */
	public static int getRowNumber(Sheet sheetObject,String[][] srchCriteriaArray) throws Throwable
	{	
		for(String [] arr:srchCriteriaArray) {
			if(arr.length !=2) {
				throw new ObjectLengthNotCorrectException("Sreach Object inner Array Should have 2 elements, "
						+ "while "+arr.length+" was given");
			}
		}
		DataFormatter formatter = new DataFormatter();

		int rowNumberFound=0;		
		int rowCount=getRowCount(sheetObject);
		boolean oneRowFound=false;

		for(int i=1;i<rowCount;i++) {
			boolean srchFlag=true;
			for(String [] arr:srchCriteriaArray) {
				int column2CheckIndx=getColumnIndexFrmColumnName(sheetObject,arr[0]);
				Row rowObj=sheetObject.getRow(i);
				Cell cellObj=rowObj.getCell(column2CheckIndx);
				String cellValue=formatter.formatCellValue(cellObj);
				srchFlag = srchFlag && (cellValue.trim().toUpperCase()).equals(arr[1].trim().toUpperCase());			
			}
			if (srchFlag){
				oneRowFound=true;
				rowNumberFound= i;
				break;
			}
		}
		if(!oneRowFound) {	
			String message = "";
			for(String [] arr:srchCriteriaArray) {
				message = message + arr[0]+"="+arr[1]+" ";
			}
			throw new NoRowFoundException("No Rows found in sheet="+sheetObject.getSheetName()+" for search criteria "+ message);
		}
		return rowNumberFound; 
	}

	/**
	 * This method set pass percentage in testscript excel sheet
	 * @param passPercentage
	 * @throws Throwable
	 */
	public void setPassPercentage(double passPercentage,String filePath,String testScriptName,String sheetName) throws Throwable {

		//		String testScriptName= System.getProperty("testScriptName");		
		//		String sheetName1 = getSheetName(testScriptName);
		//		Workbook book = getWorkbook(System.getProperty("driverFilePath"));
		//		String sheetName = "TestScripts";

		Workbook book = getWorkbook(filePath);
		Sheet sheetObj = getSheetObject(book,sheetName);

		String[][]srchCriteria={{"TestScriptName",testScriptName}};
		int matchedRow = getRowNumber(sheetObj, srchCriteria);

		int col_Num = -1;
		Row rowObj = sheetObj.getRow(0);
		for(int i = 0; i < rowObj.getLastCellNum(); i++)
		{
			if(rowObj.getCell(i).getStringCellValue().trim().equals("PassPercentage"))
			{
				col_Num = i;
			}
		}
		rowObj = sheetObj.getRow(matchedRow);
		if(rowObj == null)
			rowObj = sheetObj.createRow(matchedRow);

		Cell cellObj = rowObj.getCell(col_Num);
		if(cellObj == null)
			cellObj = rowObj.createCell(col_Num);

		cellObj.setCellValue(passPercentage+" %");

		cellObj = rowObj.createCell(++col_Num);
		if(passPercentage==100.0) {
			cellObj.setCellValue("PASS");
			FileOutputStream fo=new FileOutputStream(filePath);
			//FileOutputStream fo=new FileOutputStream(System.getProperty("driverFilePath"));				
			book.write(fo);	
			book.close();
			fillGreenColor(filePath,sheetName,matchedRow,col_Num);
		}else {
			cellObj.setCellValue("FAIL");
			FileOutputStream fo=new FileOutputStream(filePath);
			//FileOutputStream fo=new FileOutputStream(System.getProperty("driverFilePath"));				
			book.write(fo);	
			book.close();
			fillRedColor(filePath,sheetName,matchedRow,col_Num);
		}
	}

	/**
	 * This method returns total test cases 
	 * @param passedTests
	 * @param failedTests
	 * @param skipedTests
	 * @return
	 */
	public int getTotalTestCases(int passedTests, int failedTests, int skipedTests) {
		int totalTestCases= passedTests + failedTests + skipedTests;
		return totalTestCases;
	}

	/**
	 * This method returns test script percentage 
	 * @param obtained
	 * @param total
	 * @return
	 */
	public double calculatePercentage(double obtained, double total) {
		double percentage= obtained * 100 / total;
		DecimalFormat df = new DecimalFormat("#.##");    
		percentage = Double.valueOf(df.format(percentage));
		return percentage;
	}
}