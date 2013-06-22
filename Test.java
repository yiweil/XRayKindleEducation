import java.nio.charset.Charset;
import java.util.ArrayList;

import com.csvreader.CsvReader;




public class Test {

	public boolean check(char c) {
		if (c >= 65  && c <= 90)
			return false;
		
		if (c >= 97 && c <= 122)
			return false;
		
		if (c >= 32 && c <= 126)
			return false;
		return true;
	}
	
	public ArrayList<String[]> readCsv(String csvFilePath){
        try {    
             
            ArrayList<String[]> csvList = new ArrayList<String[]>();
             CsvReader reader = new CsvReader(csvFilePath,',',Charset.forName("SJIS"));
             int i = 0;
             while(reader.readRecord() && i < 100){
            	 String[] s = reader.getValues();
            	 String wiki = s[4];
            	 int count = 0;
            	 boolean kuohao = false;
            	 for(int j = 0; j < wiki.length(); j++) {
            		 if (kuohao = true)
            			 if (wiki.charAt(j) == ')')
            				 kuohao = false;
            		 
            		 if (wiki.charAt(j) == '(')
            			 kuohao = true;
            		 
            		 if (kuohao == false && check(wiki.charAt(j)))
            			 count++;
            	 }
            	 if(count > 10) {
            		 csvList.add(s);
            		 i++;
            	 }
             }            
             reader.close();
             return csvList;
             
        }catch(Exception ex){
            System.out.println(ex);
            return null;
        }
    }
    
	public static void main(String[] args) {
		Test test = new Test();
		ArrayList<String[]> dataSet = test.readCsv("/Users/XinLin/Downloads/kindle-education-xray/kindle-education-xray/klo-dataset-train.txt");

        for(int row=0;row<100;row++){
        	for(int col = 0; col < dataSet.get(row).length; col++) {
        		String  cell = dataSet.get(row)[col];
        		System.out.println(cell);
        	}
        }
	
	}
}
