package translate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import http.NameValue;
import http.WebRequest;

public class Google {

	/**
	 * Key -> Value
	 * ����     -> ���ʱ�ʾ
	 */
	private static final Map<String,String> LANGUAGE = new HashMap<String,String>();
	
	static {
		LANGUAGE.put("������������", "sq");
		LANGUAGE.put("��������", "ar");
		LANGUAGE.put("�����ݽ���", "az");
		LANGUAGE.put("��������", "ga");
		LANGUAGE.put("��ɳ������", "et");
		LANGUAGE.put("��˹����", "eu");
		LANGUAGE.put("�׶���˹��", "be");
		LANGUAGE.put("����������", "bg");
		LANGUAGE.put("������", "is");
		LANGUAGE.put("������", "pl");
		LANGUAGE.put("��˹��", "fa");
		LANGUAGE.put("������", "af");
		LANGUAGE.put("�ϷǺ�����", "af");
		LANGUAGE.put("������", "da");
		LANGUAGE.put("����", "de");
		LANGUAGE.put("����", "ru");
		LANGUAGE.put("����", "fr");
		LANGUAGE.put("���ɱ���", "tl");
		LANGUAGE.put("������", "fi");
		LANGUAGE.put("��³������", "ka");
		LANGUAGE.put("�ż�������", "gu");
		LANGUAGE.put("���ؿ���¶���", "ht");
		LANGUAGE.put("����", "ko");
		LANGUAGE.put("������", "nl");
		LANGUAGE.put("����������", "gl");
		LANGUAGE.put("��̩��������", "ca");
		LANGUAGE.put("�ݿ���", "cs");
		LANGUAGE.put("���ɴ���", "kn");
		LANGUAGE.put("���޵�����", "hr");
		LANGUAGE.put("������", "la");
		LANGUAGE.put("����ά����", "lv");
		LANGUAGE.put("������", "lo");
		LANGUAGE.put("��������", "lt");
		LANGUAGE.put("����������", "ro");
		LANGUAGE.put("�������", "mt");
		LANGUAGE.put("������", "ms");
		LANGUAGE.put("�������", "mk");
		LANGUAGE.put("�ϼ�����", "bn");
		LANGUAGE.put("Ų����", "no");
		LANGUAGE.put("��������", "pt");
		LANGUAGE.put("����", "ja");
		LANGUAGE.put("�����", "sv");
		LANGUAGE.put("����ά����", "sr");
		LANGUAGE.put("������", "eo");
		LANGUAGE.put("˹�工����", "sk");
		LANGUAGE.put("˹����������", "sl");
		LANGUAGE.put("˹��ϣ����", "sw");
		LANGUAGE.put("̩¬����", "te");
		LANGUAGE.put("̩�׶���", "ta");
		LANGUAGE.put("̩��", "th");
		LANGUAGE.put("��������", "tr");
		LANGUAGE.put("����ʿ��", "cy");
		LANGUAGE.put("�ڶ�����", "ur");
		LANGUAGE.put("�ڿ�����", "uk");
		LANGUAGE.put("ϣ������", "iw");
		LANGUAGE.put("ϣ����", "el");
		LANGUAGE.put("��������", "es");
		LANGUAGE.put("��������", "hu");
		LANGUAGE.put("����������", "hy");
		LANGUAGE.put("�������", "it");
		LANGUAGE.put("�������", "yi");
		LANGUAGE.put("ӡ����", "hi");
		LANGUAGE.put("ӡ����", "id");
		LANGUAGE.put("Ӣ��", "en");
		LANGUAGE.put("Խ����", "vi");
		LANGUAGE.put("���ķ���", "zh-TW");
		LANGUAGE.put("���ļ���", "zh-CN");

	}
	/**
	 * GET �ȸ跭��
	 * @param value ��������ַ���
	 * @param src Դ����
	 * @param target Ŀ������
	 * @return ������JSON�ַ���
	 * @throws ScriptException 
	 * @throws NoSuchMethodException 
	 * @throws IOException 
	 */
	public static String translate(String value, String src, String target) throws ScriptException, NoSuchMethodException, IOException {
		src = LANGUAGE.get(src);
		target = LANGUAGE.get(target);
		if(src == null || target == null){
			return null;
		}
		NameValue form = new NameValue();
		form.add("client", "t");
		form.add("sl", src);
		form.add("tl", target);
		form.add("hl", "zh-CN");
		form.add("dt", "at");
		form.add("dt", "bd");
		form.add("dt", "ex");
		form.add("dt", "ld");
		form.add("dt", "md");
		form.add("dt", "qca");
		form.add("dt", "rw");
		form.add("dt", "rm");
		form.add("dt", "ss");
		form.add("dt", "t");
		form.add("ie", "UTF-8");
		form.add("oe", "UTF-8");
	//	form.add("otf", "1");
	//	form.add("srcrom", "0");
	//	form.add("ssel", "0");
	//	form.add("tsel", "4");
	//	form.add("pc", "1");
	//	form.add("kc", "2");
		
		
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		String c = null;
		String jsFileName="js.js";
		// ��ȡjs�ļ�
		FileReader reader = new FileReader(jsFileName);
		// ִ��ָ���ű�
		engine.eval(reader);
		if(engine instanceof Invocable) {
			Invocable invoke = (Invocable)engine;
			// ����merge��������������������
			// c = merge(2, 3);
			c = (String) invoke.invokeFunction("VL", value);
			//System.out.println("c = " + c);
		}
		reader.close();
		
		//String q = WebRequest.encode("����", "UTF-8");
		//System.out.println(q);
		form.add("tk", c);
		form.add("q", WebRequest.encode(value, "UTF-8"));
		
		String result = "";// ���صĽ��  
	    
	   
		String a;
		a= "https://translate.google.cn/translate_a/single?"+form.toString();
		System.out.println(a);
		//return WebRequest.get("https://translate.google.cn/translate_a/single?"+form.toString(), "UTF-8");
		BufferedReader in = null;// ��ȡ��Ӧ������  
		try{
		// ����URL����  
        java.net.URL connURL = new java.net.URL(a);  
        // ��URL����  
        java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL  
                .openConnection();  
        // ����ͨ������  
        httpConn.setRequestProperty("Accept", "*/*");  
        httpConn.setRequestProperty("Connection", "Keep-Alive");  
        httpConn.setRequestProperty("User-Agent",  
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");  
        // ����ʵ�ʵ�����  
        httpConn.connect();  
        // ��Ӧͷ����ȡ  
        Map<String, List<String>> headers = httpConn.getHeaderFields();  
        // �������е���Ӧͷ�ֶ�  
        for (String key : headers.keySet()) {  
            System.out.println(key + "\t��\t" + headers.get(key));  
        }  
        
        // ����BufferedReader����������ȡURL����Ӧ,�����ñ��뷽ʽ  
        in = new BufferedReader(new InputStreamReader(httpConn  
                .getInputStream(), "UTF-8"));  
        String line;  
        // ��ȡ���ص�����  
        while ((line = in.readLine()) != null) {  
            result += line;  
        }  
    } catch (Exception e) {  
        e.printStackTrace();  
    } finally {  
        try {  
            if (in != null) {  
                in.close();  
            }  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
    }  
    return result;  
		
	}
	


}
