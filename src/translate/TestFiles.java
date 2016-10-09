package translate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import http.WebRequest;
//有个bug，会报400错误，可是还没找到办法解决
public class TestFiles {
	
	public static void main(String[] args) throws Exception {
		String fileName,target;
		for(int i = 987; i <= 999;i++){
			fileName = "D:/ChnSentiCorp_htl_ba_2000/neg/neg." + i + ".txt";
			target = "D:/Translate/neg/neg." + i + ".txt";
			FileReader file = new FileReader(fileName);
			BufferedReader reader = new BufferedReader(file);
			FileWriter fileWriter=new FileWriter(target,true);
			String line = null;
			while((line = reader.readLine())!= null) {
				String[] tmp = line.split("/.");		
				for(int j=0; j < tmp.length; j++){			
					tmp[j]=tmp[j].trim();
					System.out.println(tmp[j]);
					String html = Google.translate(tmp[j], "中文简体","英语");
					if(html == null){
						System.out.println("翻译失败");
					
					}
						String s = WebRequest.mid(html, "[[[", "]");
						Pattern p=Pattern.compile("\"(.*?)\",\"");
						Matcher m=p.matcher(s);
			            while(m.find()){System.out.println(m.group(1));fileWriter.write(m.group(1)+"\n");}
				}
			}
			fileWriter.flush();   
			fileWriter.close();
			reader.close();
		}		
		
	}
}
