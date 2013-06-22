import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

import com.csvreader.CsvReader;




public class LoadData {

	public static boolean checkChar(char c) {
		int asc = (int)c;
		if(asc >= 48 && asc <= 57) return true;
		
		if (asc >= 65  && asc <= 90) return true;
		
		if (asc >= 97 && asc <= 122) return true;
		
		if(asc == 20 || asc == 44 || asc == 46 ) return true;
		
		return false;
	}
	
	public ArrayList<String[]> readCsv(String csvFilePath){
        try {    
             
             ArrayList<String[]> csvList = new ArrayList<String[]>();
             CsvReader reader = new CsvReader(csvFilePath,',',Charset.forName("UTF-8"));
             int i = 0;
             while(reader.readRecord() && i < 100){
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
		boolean first = false;
		for(char c : s.toCharArray()) {
			char temp = c;
			if(!checkChar(c) && first  && temp != ' ' && temp != ',' && temp != '.' && temp != '(' && temp != ')'
					&& temp != '\'' && temp != '\"') 
				return false;
			if(checkChar(c)) first = false;
			else first = true;
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
