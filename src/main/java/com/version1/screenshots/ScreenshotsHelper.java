package com.version1.screenshots;
import java.util.HashMap;
import java.util.Map;

class ScreenshotsHelper {
    private static final ThreadLocal<Map<String, Object>> screenShots = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    public static void add(String name, byte[] image) {
    	screenShots.get().put(name, image.clone());
    }

    public static Map<String, Object> getScreenShotsForCurrentTest() {
        return screenShots.get();
    }

    public static void tidyUpAfterTestRun() {
    	screenShots.remove();
    }
}
