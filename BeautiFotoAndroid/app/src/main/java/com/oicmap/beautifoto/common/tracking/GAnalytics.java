package com.oicmap.beautifoto.common.tracking;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.oicmap.beautifoto.R;

/**
 * Created by khacpham on 9/7/15.
 */
public class GAnalytics {

    public static GAnalytics instance;

    private Application application;

    private Tracker mTracker;

    private GAnalytics(Application application){
        this.application = application;
    }

    public static GAnalytics getInstance(Application application){
        if(instance == null){
            instance = new GAnalytics(application);
        }
        return instance;
    }

    public synchronized Tracker getTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(application.getApplicationContext());
            analytics.enableAutoActivityReports(application);
            mTracker = analytics.newTracker(R.xml.app_tracker);
            mTracker.enableAdvertisingIdCollection(true);
        }
        return mTracker;
    }


    /**
     * @param screen TheScreen.class.getName()
     * */
    public synchronized void trackScreen(String screen) {
        Tracker t = getTracker();
        t.setScreenName(screen);
        t.send(new HitBuilders.AppViewBuilder().build());
    }

    /**
     *
     * */
    public synchronized void trackEvent(String category, String action, String label) {
        Tracker t = getTracker();
        t.send(new HitBuilders.EventBuilder().setCategory(category)
            .setAction(action)
            .setLabel(label)
            .build());

    }
    public static class Category {
        public static final String CORE = "TUYA";
    }

    public static class ScreenName {
        public static final String CAMERA="Camera Screen";
        public static final String EDIT="Edit Screen";
        public static final String LOGIN="Login Screen";
        public static final String NOTI="Noti Screen";
        public static final String PHOTO_DEVICE="Photo Device chooser Screen";
    }

    public static class Action{
        public static final String LOGIN="LOGIN";
        public static final String CAPTURE="CAPTURE";
        public static final String OPEN_NOTI="OPEN_NOTI";
        public static final String OPEN_GALLERY="OPEN_GALLERY";
        public static final String SWIPE_FONT="SWIPE_FONT";
        public static final String SWIPE_OVERLAY="SWIPE_OVERLAY";
        public static final String EDIT_PHOTO="EDIT_PHOTO";
        public static final String SEND_TO="SENT_TO";
        public static final String DISMISS_NOTI="DISMISS_NOTI";
        public static final String REP_NOTI="REP_NOTI";

    }


    public static class Label{
        //LOGIN
        public static final String FB="FACEBOOK";
        public static final String INS="INSTAGRAM";
        public static final String MERGE_FB_INS="MERGE_FACEBOOK_INSTAGRAM";
        public static final String MERGE_INS_FB="MERGE_INSTAGRAM_FACEBOOK";
        public static final String ONE_FRIEND_CLICK="ONE_FRIEND_CLICK";
        public static final String NOFRIEND_CLICK="NOFRIEND_CLICK";

        //CAMERA
        public static final String CAMERA="CAMERA";
        public static final String NOTI_COUNT="NOTI_COUNT";
        public static final String PHOTO_DEVICE="PHOTO_DEVICE";

        //EDIT
        public static final String EDIT_BY_CAPTURE="SEND_BY_CAPTURE";
        public static final String EDIT_BY_ALBUM="EDIT_BY_ALBUM";
        public static final String PAGER_FONT="PAGER_FONT";
        public static final String PAGER_OVERLAY="PAGER_OVERLAY";

        public static final String SEND_NORMAL="SEND_NORMAL";
        public static final String SEND_INSTAGRAM="SEND_INSTAGRAM";

        //NOTI
        public static final String NOTI_SWIPE="NOTI_SWIPE";
        public static final String NOTI_MINIMIZE="NOTI_MINIMIZE";

        public static final String NOTI_REP_SUGGEST="NOTI_REP_SUGGEST";
        public static final String NOTI_REP_KEYBOARD="NOTI_REP_KEYBOARD";

    }
}
