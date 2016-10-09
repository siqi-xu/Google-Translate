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
	 * 语言     -> 单词表示
	 */
	private static final Map<String,String> LANGUAGE = new HashMap<String,String>();
	
	static {
		LANGUAGE.put("阿尔巴尼亚语", "sq");
		LANGUAGE.put("阿拉伯语", "ar");
		LANGUAGE.put("阿塞拜疆语", "az");
		LANGUAGE.put("爱尔兰语", "ga");
		LANGUAGE.put("爱沙尼亚语", "et");
		LANGUAGE.put("巴斯克语", "eu");
		LANGUAGE.put("白俄罗斯语", "be");
		LANGUAGE.put("保加利亚语", "bg");
		LANGUAGE.put("冰岛语", "is");
		LANGUAGE.put("波兰语", "pl");
		LANGUAGE.put("波斯语", "fa");
		LANGUAGE.put("布尔语", "af");
		LANGUAGE.put("南非荷兰语", "af");
		LANGUAGE.put("丹麦语", "da");
		LANGUAGE.put("德语", "de");
		LANGUAGE.put("俄语", "ru");
		LANGUAGE.put("法语", "fr");
		LANGUAGE.put("菲律宾语", "tl");
		LANGUAGE.put("芬兰语", "fi");
		LANGUAGE.put("格鲁吉亚语", "ka");
		LANGUAGE.put("古吉拉特语", "gu");
		LANGUAGE.put("海地克里奥尔语", "ht");
		LANGUAGE.put("韩语", "ko");
		LANGUAGE.put("荷兰语", "nl");
		LANGUAGE.put("加利西亚语", "gl");
		LANGUAGE.put("加泰罗尼亚语", "ca");
		LANGUAGE.put("捷克语", "cs");
		LANGUAGE.put("卡纳达语", "kn");
		LANGUAGE.put("克罗地亚语", "hr");
		LANGUAGE.put("拉丁语", "la");
		LANGUAGE.put("拉脱维亚语", "lv");
		LANGUAGE.put("老挝语", "lo");
		LANGUAGE.put("立陶宛语", "lt");
		LANGUAGE.put("罗马尼亚语", "ro");
		LANGUAGE.put("马耳他语", "mt");
		LANGUAGE.put("马来语", "ms");
		LANGUAGE.put("马其顿语", "mk");
		LANGUAGE.put("孟加拉语", "bn");
		LANGUAGE.put("挪威语", "no");
		LANGUAGE.put("葡萄牙语", "pt");
		LANGUAGE.put("日语", "ja");
		LANGUAGE.put("瑞典语", "sv");
		LANGUAGE.put("塞尔维亚语", "sr");
		LANGUAGE.put("世界语", "eo");
		LANGUAGE.put("斯洛伐克语", "sk");
		LANGUAGE.put("斯洛文尼亚语", "sl");
		LANGUAGE.put("斯瓦希里语", "sw");
		LANGUAGE.put("泰卢固语", "te");
		LANGUAGE.put("泰米尔语", "ta");
		LANGUAGE.put("泰语", "th");
		LANGUAGE.put("土耳其语", "tr");
		LANGUAGE.put("威尔士语", "cy");
		LANGUAGE.put("乌尔都语", "ur");
		LANGUAGE.put("乌克兰语", "uk");
		LANGUAGE.put("希伯来语", "iw");
		LANGUAGE.put("希腊语", "el");
		LANGUAGE.put("西班牙语", "es");
		LANGUAGE.put("匈牙利语", "hu");
		LANGUAGE.put("亚美尼亚语", "hy");
		LANGUAGE.put("意大利语", "it");
		LANGUAGE.put("意第绪语", "yi");
		LANGUAGE.put("印地语", "hi");
		LANGUAGE.put("印尼语", "id");
		LANGUAGE.put("英语", "en");
		LANGUAGE.put("越南语", "vi");
		LANGUAGE.put("中文繁体", "zh-TW");
		LANGUAGE.put("中文简体", "zh-CN");

	}
	/**
	 * GET 谷歌翻译
	 * @param value 待翻译的字符串
	 * @param src 源语言
	 * @param target 目标语言
	 * @return 翻译结果JSON字符串
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
		// 读取js文件
		FileReader reader = new FileReader(jsFileName);
		// 执行指定脚本
		engine.eval(reader);
		if(engine instanceof Invocable) {
			Invocable invoke = (Invocable)engine;
			// 调用merge方法，并传入两个参数
			// c = merge(2, 3);
			c = (String) invoke.invokeFunction("VL", value);
			//System.out.println("c = " + c);
		}
		reader.close();
		
		//String q = WebRequest.encode("不错", "UTF-8");
		//System.out.println(q);
		form.add("tk", c);
		form.add("q", WebRequest.encode(value, "UTF-8"));
		
		String result = "";// 返回的结果  
	    
	   
		String a;
		a= "https://translate.google.cn/translate_a/single?"+form.toString();
		System.out.println(a);
		//return WebRequest.get("https://translate.google.cn/translate_a/single?"+form.toString(), "UTF-8");
		BufferedReader in = null;// 读取响应输入流  
		try{
		// 创建URL对象  
        java.net.URL connURL = new java.net.URL(a);  
        // 打开URL连接  
        java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL  
                .openConnection();  
        // 设置通用属性  
        httpConn.setRequestProperty("Accept", "*/*");  
        httpConn.setRequestProperty("Connection", "Keep-Alive");  
        httpConn.setRequestProperty("User-Agent",  
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");  
        // 建立实际的连接  
        httpConn.connect();  
        // 响应头部获取  
        Map<String, List<String>> headers = httpConn.getHeaderFields();  
        // 遍历所有的响应头字段  
        for (String key : headers.keySet()) {  
            System.out.println(key + "\t：\t" + headers.get(key));  
        }  
        
        // 定义BufferedReader输入流来读取URL的响应,并设置编码方式  
        in = new BufferedReader(new InputStreamReader(httpConn  
                .getInputStream(), "UTF-8"));  
        String line;  
        // 读取返回的内容  
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
