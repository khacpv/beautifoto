package com.oicmap.beautifoto.common.activeandroid;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

/**
 * Created by khacpham on 9/8/15.
 */
public class ActiveAndroidConf {

    public static void initAaDb(Application application,String dbName, int dbVer){
        Configuration dbConfiguration = new Configuration.Builder(application).setDatabaseName(dbName).setDatabaseVersion(dbVer).create();
        ActiveAndroid.initialize(dbConfiguration);
    }
}
