package io.jpress.commons.url;


import com.jfinal.core.JFinal;
import io.jpress.JPressOptions;

public class JPressUrlUtil {


    public static String getUrl(Object... paths) {
        return buildUrl(paths);
    }


    private static String buildUrl(Object... paths) {
        boolean isWebFlatUrlEnable = isWebFlatUrlEnable();
        StringBuilder url = new StringBuilder(JFinal.me().getContextPath());
        for (int i = 0; i < paths.length; i++) {
            if (isWebFlatUrlEnable) {
                url.append(toFlat(i, paths[i].toString()));
            } else {
                url.append(paths[i].toString());
            }
        }
        return url.append(JPressOptions.getAppUrlSuffix()).toString();
    }



    private static String toFlat(int index, String path) {
        char[] chars = new char[path.length()];
        path.getChars(index == 0 ? 1 : 0, chars.length, chars, index == 0 ? 1 : 0);
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '/') {
                chars[i] = '-';
            }
        }
        if (index == 0) {
            chars[0] = '/';
        }
        return new String(chars);
    }


    private static boolean isWebFlatUrlEnable() {
        return JPressOptions.getAsBool("web_flat_url_enable", false);
    }

}
