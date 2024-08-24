package CommonLibs.Implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {

	public static String[][] getSheetTCData(String workBook, String sheetName, String testCaseName) {

		String[][] array2 = null;
		String filerValue[][] = null;

		try {
			FileInputStream fis = new FileInputStream(new File(workBook));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet worksheet = workbook.getSheet(sheetName);

			XSSFFormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

			int colCount = 0;
			int rowCount = 0;

			for (Row row : worksheet) {
				for (Cell cell : row) 
				{
					switch (formulaEvaluator.evaluateInCell(cell).getCellType()) 
					{
					case NUMERIC:
						if (rowCount == 0) 
						{
							colCount++;
						}
						break;

					case STRING:
						if (rowCount == 0) 
						{
							colCount++;
						}
						break;
					default:
						break;
					}
				}
				rowCount++;

			}

			String[][] sheetData = new String[rowCount][colCount];

			for (int readRow = 1; readRow < rowCount; readRow++) 
			{
				for (int readCol = 0; readCol < colCount; readCol++) 
				{
					XSSFRow rowNum = worksheet.getRow(readRow);
					XSSFCell currCell = rowNum.getCell((short) readCol);
					try {
						switch (formulaEvaluator.evaluate(currCell).getCellType()) 
						{
						case NUMERIC:
							sheetData[readRow][readCol] = (new DataFormatter().formatCellValue(currCell));
							break;

						case STRING:
							String temp1 = currCell.getStringCellValue();
							if (temp1 == null) {
								sheetData[readRow][readCol] = null;
							} else {
								sheetData[readRow][readCol] = temp1;
							}

							break;
						default:
							break;

						}
					} catch (Exception e) {

					}
				}
			}

			List<String[]> l = new ArrayList<String[]>(Arrays.asList(sheetData));

			l.remove(0);
			array2 = l.toArray(new String[][] {});

			int countFilter = 0;
			for (int row = 0; row < array2.length; row++) 
			{
				if (array2[row][0].equals(testCaseName)) 
				{
					countFilter++;
				}
			}

			filerValue = new String[countFilter][colCount - 1];
			int finalFilerRow = 0;
			boolean filterValueFound = false;
			for (int row = 0; row < array2.length; row++) 
			{
				if (array2[row][0].equals(testCaseName)) 
				{
					for (int col = 1; col < array2[row].length; col++) 
					{
						filerValue[finalFilerRow][col - 1] = array2[row][col];
						filterValueFound = true;
					}

					if (filterValueFound) {
						finalFilerRow++;
					}
				}
			}

			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filerValue;
	}
}

/*
	@DataProvider(name = "data-provider")
	public Object[][] dataProviderMethod() {
		return DataReader.getSheetTCData(".\\src\\main\\resources\\Test Data\\New Microsoft Excel Worksheet.xlsx","Sheet1", "APIBODY");
	}

	@Test (dataProvider = "data-provider")
	public void testMethod(String object, String objetc2) throws IOException, ParseException {
		System.out.println(object);
		System.out.println(object2);
	}
	
*/
