package com.oicmap.beautifoto.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.oicmap.beautifoto.R;
import com.oicmap.beautifoto.common.views.drawer.FragmentDrawerListener;
import com.oicmap.beautifoto.screen.friend.FriendFragment;
import com.oicmap.beautifoto.screen.home.HomeFragment;
import com.oicmap.beautifoto.screen.message.MessageFragment;
import com.oicmap.beautifoto.screen.navigation.FragmentDrawer;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends BaseActivity implements FragmentDrawerListener,android.support.v7.widget.SearchView.OnQueryTextListener {

    //============= CONSTANTS ==================================

    private static String TAG = MainActivity.class.getSimpleName();

    //============= VARIABLES ==================================

    ActionBar mActionBar;


    private FragmentDrawer drawerFragment;

    private Fragment homeFragment = new HomeFragment();

    private Fragment friendFragment = new FriendFragment();

    private Fragment messageFragment = new MessageFragment();

    //============= VIEWS ======================================

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setTitle(getString(R.string.app_name));
            mActionBar.setDisplayShowHomeEnabled(true);
        }

        handleIntent(getIntent());

        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem mSearchMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(mSearchMenuItem);
        searchView.setOnQueryTextListener(this);

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        subscriber.onNext(newText);
                        return false;
                    }
                });
            }
        })
        .debounce(1000, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<String>() {
            @Override
            public void call(final String s) {

            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    @OnClick(R.id.fab)
    public void onFabClick(View fabView){
        showSnackBar("no internet", null);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = homeFragment;
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = friendFragment;
                title = getString(R.string.title_friends);
                break;
            case 2:
                fragment = messageFragment;
                title = getString(R.string.title_setting);
                break;
            default:
                break;
        }

        drawerFragment.setCurrentIndex(position);

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            setActionTitle(title);
        }
    }

    public void setActionTitle(String title){
        if(mActionBar != null){
            mActionBar.setTitle(title);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        showSnackBar(query,"undo");
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        showSnackBar(newText,"undo");
        return true;
    }

    public void showSnackBar(String title,String action){
        showSnackBar(title, action, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void showSnackBar(String title,String action,View.OnClickListener listener){
        Snackbar snackbar = Snackbar.make(coordinatorLayout, title, Snackbar.LENGTH_LONG);
        if(!TextUtils.isEmpty(action)) {
            snackbar.setAction(action, listener);
        }

        // Changing message text color
        snackbar.setActionTextColor(getTitleColor());

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);

        snackbar.show();
    }
}
