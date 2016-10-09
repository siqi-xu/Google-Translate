package translate;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ScriptEngineTest {
	public static void main(String[] args) throws Exception {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
 
		String jsFileName="js.js";
		// ��ȡjs�ļ�
		FileReader reader = new FileReader(jsFileName);
		// ִ��ָ���ű�
		engine.eval(reader);
		if(engine instanceof Invocable) {
			Invocable invoke = (Invocable)engine;
			// ����merge��������������������
			// c = merge(2, 3);
			String c = (String) invoke.invokeFunction("VL", "Billy");
			System.out.println("c = " + c);
		}
		reader.close();
	}
}