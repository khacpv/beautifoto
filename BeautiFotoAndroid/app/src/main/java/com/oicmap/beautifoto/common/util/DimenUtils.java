package com.oicmap.beautifoto.common.util;

import android.content.Context;

/**
 * Created by khacpham on 9/23/15.
 */
public class DimenUtils {

    public static float pxToSp(Context context, float px){
        return px/context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static float pxToDp(Context context,float px){
        return px/context.getResources().getDisplayMetrics().density;
    }

    public static int dpToPixel(Context context,float dip){
        return (int)(dip * context.getResources().getDisplayMetrics().density);
    }

    public static int spToPixel(Context context,float sp){
        return (int)(sp * context.getResources().getDisplayMetrics().scaledDensity);
    }
}
