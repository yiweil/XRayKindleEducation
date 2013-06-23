import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

import com.csvreader.CsvReader;




public class LoadData {

	public static boolean checkChar(Character c) {
		if(Character.isAlphabetic(c)) return true;
		if(Character.isLetter(c)) return true;
		if(Character.isDigit(c)) return true;
		if(Character.isSpaceChar(c)) return true;
		if(Character.isWhitespace(c)) return true;
		
		return false;
	}
	
	public ArrayList<String[]> readCsv(String csvFilePath){
        try {    
             
             ArrayList<String[]> csvList = new ArrayList<String[]>();
             CsvReader reader = new CsvReader(csvFilePath,',',Charset.forName("UTF-8"));
             int i = 0;
             while(reader.readRecord() && i < 10000){
            	 String[] s = reader.getValues();
            	 csvList.add(s);
            	 i++;
             }            
             reader.close();
             return csvList;
             
        }catch(Exception ex){
            System.out.println(ex);
            return null;
        }
    }
	
	public static boolean checkString(String s) {
		boolean isFirst = false;
		boolean isSecond = false;
		for(char c : s.toCharArray()) {
			if(!checkChar(c) && isFirst  && isSecond) 
				return false;
			else if(!checkChar(c) && isFirst) isSecond = true;
			else if(!checkChar(c) && !isFirst) isFirst = true;
			else {
				isFirst = false;
				isSecond = false;
			}
		}
		return true;
	}
    
	public static void main(String[] args) {
		LoadData test = new LoadData();
		ArrayList<String[]> dataSet = test.readCsv("C:\\Users\\sunwenji\\Downloads\\kindle-education-xray\\kindle-education-xray\\klo-dataset-train.txt");
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		for(String[] s : dataSet) {
			String oneLine = "";
			for(String string : s) {
				oneLine += string;
				oneLine += ",";
			}
			oneLine = oneLine.substring(0, oneLine.length() - 1);
			System.out.println(oneLine);
			
			if(!checkString(oneLine)) {
				String input = scanner.next();
				if(input.equals("c")) continue;
			}
		}
	
	}
}
