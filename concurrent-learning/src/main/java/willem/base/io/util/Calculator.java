package willem.base.io.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author weiyu
 * @description
 * @create 2018/6/5 13:53
 * @since 1.0.0
 */
public final class Calculator {
    private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

    public static Object cal(String expression) throws ScriptException{
        return jse.eval(expression);
    }
}
