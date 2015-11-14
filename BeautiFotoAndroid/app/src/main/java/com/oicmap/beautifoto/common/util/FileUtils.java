package com.oicmap.beautifoto.common.util;

import android.os.Environment;

import com.oicmap.beautifoto.config.Constant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileUtils {

    /**
     * Check file is exist or not
     *
     * @param filePath
     * @return
     */
    public static boolean isFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * extract name of file from url
     */
    public static String getFileNameFromUrl(String url) {
        int lastIndex = url.lastIndexOf("/") + 1;
        url = url.substring(lastIndex);
        return url;
    }

    /**
     * make folder hidden media file
     *
     * @param absolutePath
     */
    public static void makeFolderHidden(String absolutePath) {
        File hiddenFile = new File(String.format("%s/.nomedia", absolutePath));
        if (!hiddenFile.exists()) {
            try {
                hiddenFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * get folder after extract zip file
     *
     * @param filePath
     * @return
     */
    public static String getForlderExtractZip(String filePath) {
        int index = filePath.lastIndexOf(".");
        return filePath.substring(0, index);
    }

    /**
     * @return /sdcard/0/.DEFAULT/folder
     */
    private static String getFolderDownload(String folder) {
        String fullPath =
                String.format("%s/%s/%s", Environment.getExternalStorageDirectory().getPath(), Constant.AppDir.DEFAULT, folder);
        File folderFile = new File(fullPath);
        if (!folderFile.exists()) {
            folderFile.mkdirs();
        }
        return fullPath;
    }

    /**
     * @return /sdcard/0/.DEFAULT/folder
     */
    public static String getFolderPath(String folder) {
        String fullPath =
                String.format("%s/%s/%s", Environment.getExternalStorageDirectory().getPath(), Constant.AppDir.DEFAULT, folder);
        File f = new File(fullPath);
        if (!f.exists()) {
            f.mkdirs();
        }
        FileUtils.makeFolderHidden(fullPath);
        return fullPath;
    }

    /**
     * return file in folder. if folder isn't exist, create first.
     */
    public static String getFilePath(String folder, String fileName) {
        String folderPath = getFolderPath(folder);
        File folderFile = new File(folderPath);
        if (!folderFile.exists()) {
            folderFile.mkdirs();
        }
        return folderPath + "/" + fileName;
    }

    /**
     * @return file name in folder from url
     */
    public static String getFilePathFromUrl(String url, String folder) {
        return String.format("%s/%s", getFolderPath(folder), getFileNameFromUrl(url));
    }

    public static boolean saveByteToFile(byte[] data,String filePath) throws IOException {
        OutputStream oStream = null;
        try {
            File imageFile =
                    new File(filePath);
            oStream = new FileOutputStream(imageFile);
            oStream.write(data);
            oStream.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(oStream!=null) {
                oStream.close();
            }
        }
        return false;
    }

    public static boolean deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
                return true;
            }
        } catch (Exception e) {
            if (e != null) {
                e.printStackTrace();
                return false;
            }
        }
return false;

    }

}
