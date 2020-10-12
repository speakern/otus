package ru.otus.cachehw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sergey
 * created on 14.12.18.
 *
 * Свой cache engine
 * Цель: Научится применять WeakHashMap,
 * понять базовый принцип организации кеширования.
 * Закончите реализацию MyCache из вебинара.
 * Используйте WeakHashMap для хранения значений.
 *
 * Добавьте кэширование в DBService из задания про Hibernate ORM или "Самодельный ORM".
 * Для простоты скопируйте нужные классы в это ДЗ.
 *
 * Убедитесь, что ваш кэш действительно работает быстрее СУБД и сбрасывается при недостатке памяти.
 */
public class HWCacheDemo {
    private static final Logger logger = LoggerFactory.getLogger(HWCacheDemo.class);
    HwCache<Object, Object> cache = new MyCache<>();

    public static void main(String[] args) {
        HWCacheDemo hwCacheDemo = new HWCacheDemo();
        hwCacheDemo.demo();
        System.out.println("Finish");
    }

    private void demo() {

        // пример, когда Idea предлагает упростить код, при этом может появиться "спец"-эффект
        HwListener<Object, Object> listener = new HwListener<Object, Object>() {
            @Override
            public void notify(Object key, Object value, String action) {
                logger.info("key:{}, value:{}, action: {}", key, value, action);
            }
        };

        cache.addListener(listener);
        Object object = new Object();
        cache.put(object, new Object());

        System.gc();

        logger.info("getValue:{}", cache.get(1));
        cache.remove(object);
        cache.removeListener(listener);
        System.out.println("finish");
    }
}
