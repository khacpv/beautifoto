package com.oicmap.beautifoto.common.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.Collection;

/**
 * String utils.
 *
 * @author Lorensius W. L. T <lorenz@londatiga.net>
 */
public class StringUtil {
    public static String streamToString(InputStream is) throws IOException {
        String str = "";

        if (is != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                reader.close();
            } finally {
                is.close();
            }

            str = sb.toString();
        }

        return str;
    }

    public static String getText(Context context, int resId) {
        try {
            return context.getResources().getText(resId).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isSubsetOf(Collection<String> subset, Collection<String> superSet) {
        for (String string : subset) {
            if (!superSet.contains(string)) {
                return false;
            }
        }
        return true;
    }

    /**
     * check str contains CJK char or not.
     * */
    public static boolean isCJK(String str){
        int length = str.length();
        for (int i = 0; i < length; i++){
            char ch = str.charAt(i);
            if (isCJK(ch)){
                return true;
            }
        }
        return false;
    }

    /**
     * check ch is a CJK or not.
     * */
    public static boolean isCJK(char ch){
        Character.UnicodeBlock block = Character.UnicodeBlock.of(ch);
        return Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(block) ||
                Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS.equals(block) ||
                Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A.equals(block);
    }

    /**
     * convert unicode to plain text to search
     * */
    public static String getPlainText(String text){
        return text == null ? "" : Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
    }
}