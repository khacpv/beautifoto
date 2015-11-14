package com.oicmap.beautifoto.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.oicmap.beautifoto.R;
import com.oicmap.beautifoto.common.views.drawer.FragmentDrawerListener;
import com.oicmap.beautifoto.screen.friend.FriendFragment;
import com.oicmap.beautifoto.screen.home.HomeFragment;
import com.oicmap.beautifoto.screen.message.MessageFragment;
import com.oicmap.beautifoto.screen.navigation.FragmentDrawer;

public class MainActivity extends AppCompatActivity implements FragmentDrawerListener,android.support.v7.widget.SearchView.OnQueryTextListener {

    private static String TAG = MainActivity.class.getSimpleName();

    Toolbar mToolbar;

    ActionBar mActionBar;

    private FragmentDrawer drawerFragment;

    private Fragment homeFragment = new HomeFragment();

    private Fragment friendFragment = new FriendFragment();

    private Fragment messageFragment = new MessageFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setTitle(getString(R.string.app_name));
            mActionBar.setDisplayShowHomeEnabled(true);
        }

        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);

        handleIntent(getIntent());
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
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(mSearchMenuItem);
        searchView.setOnQueryTextListener(this);
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
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
