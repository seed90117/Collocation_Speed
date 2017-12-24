package IO;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import Values.Data;
import Values.Item;

public class LoadFile {

	public String loadfile(JFileChooser open)
	{
		String loadPath = "";
		String fileName = "";
		
		//*****預設路徑*****//
		open.setCurrentDirectory(new File("C:\\Desktop"));
		
		//*****設定Title*****//
		open.setDialogTitle("Choose dataset");
		
		//*****是否按下Load*****//
		if(open.showDialog(open, "Load") == JFileChooser.APPROVE_OPTION)
		{
			//*****取得路徑*****//
			File filepath = open.getSelectedFile();
			fileName = filepath.getName();
			
			//*****路徑轉為String*****//
			loadPath = filepath.getPath().toString();
			
			//*****讀取檔案*****//
			String[] name = fileName.split("\\.");
			if (name[1].equals("xls")) {
				ReadExcel readExcel = new ReadExcel();
				getExcelData(readExcel, loadPath);
				return "File Name: " + fileName;
			} else if(name[1].equals("xlsx")) {
				return "Type of file is wrong, change type to xls.";
			} else {
				return "Please choose Excel file.";
			}
		} else {
			return "File Name: ";
		}
	}
	
	// 取得Excel內資料
	private void getExcelData(ReadExcel excel, String filePath) {
		Data data = Data.getInstance();
		excel.chooseExcel(filePath); // 選擇Excel
		String[] sheetNames = excel.getSheetName(); // 取得Excel所有活頁簿名稱
		excel.chooseSheet(sheetNames[0]); // 取得第一個活頁簿名稱
		int row = excel.getSheetRow(); // 取得總行數(有資料為主)
		int column = excel.getSheetColumns(); // 取得總欄數(有資料為主)
		data.setRow(row-1); // 設定資料行數
		data.setColumn(column-1); // 設定資料欄數
		data.setPersonDataSize(row-1); // 設定個人資料總數
		ArrayList<String> itemNames = new ArrayList<>(); // 新增ArrayList用於存放欄位名稱
		for (int r=0; r<row; r++) {
			for (int c=1; c<column; c++) {
				if (r==0) { // 第0行為欄位名稱
					itemNames.add(excel.getData(c, r)); // 將欄位名稱放入ArrayList
				} else {
					int itemCount = c-1; // 欄位計數為讀取欄數減一
					int personCount = r-1; // 資料筆數計數為讀取行數減一
					Item item = new Item(); // 新增Item物件
					item.item = itemNames.get(itemCount); // 放入欄位名稱
					item.value = excel.getData(c, r); // 存入值
					data.setPersonData(personCount, item); // 放入資料內
				}
			}
		}
		excel.closeWorkbook();
	}
}
