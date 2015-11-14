package com.oicmap.beautifoto.common.util;

import android.content.Context;

/**
 * Created by Eo on 10/5/2015.
 */
public class ResourceUtils {


    public static String getStringResource(Context context, int id) {
        return context.getResources().getString(id);
    }

    public static int getColorResource(Context context, int id) {
        return context.getResources().getColor(id);
    }

    public static String[] getStringArrayResource(Context context, int id) {
        return context.getResources().getStringArray(id);
    }

}
