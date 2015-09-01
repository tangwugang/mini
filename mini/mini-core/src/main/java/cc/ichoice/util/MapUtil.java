package cc.ichoice.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

/**
 * 映射操作工具类
 * @author twg
 *
 */
public class MapUtil {
	/**
     * 判断 Map 是否非空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return MapUtils.isNotEmpty(map);
    }

    /**
     * 判断 Map 是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return MapUtils.isEmpty(map);
    }

    /**
     * 转置 Map
     */
    public static <K, V> Map<K, V> invertToLinkedHashMap(Map<K, V> source) {
        Map<K, V> target = null;
        if (isNotEmpty(source)) {
            target = new LinkedHashMap<K, V>(source.size());
            for (Map.Entry<K, V> entry : source.entrySet()) {
                target.put(entry.getKey(),entry.getValue());
            }
        }
        return target;
    }

}
