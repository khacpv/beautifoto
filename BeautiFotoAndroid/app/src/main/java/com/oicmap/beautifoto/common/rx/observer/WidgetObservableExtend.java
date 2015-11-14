package com.oicmap.beautifoto.common.rx.observer;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by khacpham on 7/31/15.
 */
public class WidgetObservableExtend {
    public static Observable<String> observeSelect(Spinner spinner) {
        if(spinner == null){
            return Observable.empty();
        }
        final PublishSubject<String> selectSubject = PublishSubject.create();
        // for production code, unsubscribe, UI thread assertions are needed
        // see WidgetObservable from rxandroid for example
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                selectSubject.onNext(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return selectSubject;
    }

    public static Observable<Integer> observePageChanged(ViewPager viewPager){
        final PublishSubject<Integer> subject = PublishSubject.create();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                subject.onNext(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return subject;
    }
}
