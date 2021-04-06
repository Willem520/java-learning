package willem.base.tool;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weiyu
 * @description
 * @create 2018/5/25 15:51
 * @since 1.0.0
 */
public class BeanUtil {

    private BeanUtil(){

    }

    /**
     * 将java beam转换为map
     * @param t
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    public static <T> Map bean2Map(T t) throws IllegalAccessException {
        if (t == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        Field[] declaredFields = t.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(t));
        }
        return map;
    }

    /**
     * 将map转换为java bean
     * @param map
     * @param clz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T map2Bean(Map<String,Object> map, Class<T> clz) throws IllegalAccessException, InstantiationException {
        T obj = clz.newInstance();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field :  fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            field.set(obj,map.get(field.getName()));
        }
        return obj;
    }
}
