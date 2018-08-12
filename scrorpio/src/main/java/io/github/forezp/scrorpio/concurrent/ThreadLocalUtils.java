//package io.github.forezp.scrorpio.concurrent;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Supplier;
//
//
//public class ThreadLocalUtils {
//    private static final ThreadLocal<Map<String, Object>> local = ThreadLocal.withInitial(HashMap::new);
//
//    public static <T> T put(String key, T value) {
//        local.get().put(key, value);
//        return value;
//    }
//
//    public static void remove(String key) {
//        local.get().remove(key);
//    }
//
//    public static void clear() {
//        local.remove();
//    }
//
//    public static <T> T get(String key) {
//        return ((T) local.get().get(key));
//    }
//
//    public static <T> T get(String key, Supplier<T> other) {
//        T val = ((T) local.get().get(key));
//        if (null != val) return val;
//        val = other.get();
//        local.get().put(key, val);
//        return val;
//    }
//
//    public static <T> T getAndRemove(String key) {
//        try {
//            return ((T) local.get().get(key));
//        } finally {
//            local.get().remove(key);
//        }
//    }
//}
