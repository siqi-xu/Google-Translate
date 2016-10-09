package translate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import http.WebRequest;

public class Test {
	
	public static void main(String[] args) throws Exception {
			FileReader file = new FileReader("D:/OneWord-positive-NN-RemoveSame.txt");
			BufferedReader reader = new BufferedReader(file);
			FileWriter fileWriter=new FileWriter("D:/OneWord-positive-NN-RemoveSame-ID.txt",true);
			String line = null;
			while((line = reader.readLine())!= null) {
					String html = Google.translate(line, "英语","印尼语");
					if(html == null){
						System.out.println("翻译失败");
					}
					System.out.println(html);
					String s = WebRequest.mid(html, "[[[", "]");
					Pattern p=Pattern.compile("\"(.*?)\",\"");
					Matcher m=p.matcher(s);
					
		            while(m.find()){
		            	String result = m.group(1);
		            	fileWriter.write(result + "\n");
		            	fileWriter.flush();
		            	System.out.println(result);
		            	
		            	}
		               
					
				}
			
			fileWriter.close();
			reader.close();
			
		}		
	
		
}
