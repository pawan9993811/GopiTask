package EXEL_POI;

public class ReadingExcelsheet {
public static void main(String[] args) {

Xls_Reader datatable = new Xls_Reader("D:\\project selenium\\FrameWork\\Mavensample\\"
		+ "src\\main\\java\\MavenSam\\Mavensample\\Datatest.xlsx");


// datatable.getRowCount(sheetName)
int rowdata =  datatable.getRowCount("Records");
System.out.println("Total Rows in excel sheet "+rowdata);
//How to get the data from excelsheet
// datatable.getCellData(sheetName, colNum, rowNum)

String data=  datatable.getCellData("Records", "City", 3);
System.out.println("This is data from 3rd row ,colomn name is city and sheet name records"+data);
String data2=  datatable.getCellData("Records", "City", 2);
System.out.println("data from 2nd row,colomn name is city and sheet name records "+data2);
//How to set data in excel sheet
//datatable.setCellData(sheetName, colName, rowNum, data2)
datatable.setCellData("Records", "City", 10, "new data entered ");
}
}
