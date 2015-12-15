package com.oicmap.beautifoto.screen.navigation;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oicmap.beautifoto.R;
import com.oicmap.beautifoto.common.eventbus.api.OnEventBusListener;
import com.oicmap.beautifoto.common.views.ClickListener;
import com.oicmap.beautifoto.common.views.drawer.FragmentDrawerListener;
import com.oicmap.beautifoto.common.views.recyclerview.RecyclerTouchListener;
import com.oicmap.beautifoto.screen.BaseFragment;
import com.oicmap.beautifoto.screen.navigation.adapter.NavigationDrawerAdapter;
import com.oicmap.beautifoto.screen.navigation.model.NavDrawerItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class FragmentDrawer extends BaseFragment {

    //============= CONSTANTS ==================================

    private static String TAG = FragmentDrawer.class.getSimpleName();

    //============= VARIABLES ==================================

    private NavigationDrawerAdapter adapter;

    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;

    private View containerView;

    private static String[] titles = null;

    private FragmentDrawerListener drawerListener;

    //============= VIEWS ======================================


    @Bind(R.id.drawerList)
    RecyclerView recyclerView;

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }

    public static List<NavDrawerItem> getData() {
        List<NavDrawerItem> data = new ArrayList<>();

        // preparing navigation drawer items
        for (int i = 0; i < titles.length; i++) {
            NavDrawerItem navItem = new NavDrawerItem();
            navItem.setTitle(titles[i]);
            data.add(navItem);
        }
        return data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // drawer labels
        titles = getActivity().getResources().getStringArray(R.array.nav_drawer_labels);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }

    @Override
    protected void initAfterViewCreated() {
        adapter = new NavigationDrawerAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                drawerListener.onDrawerItemSelected(view, position);
                mDrawerLayout.closeDrawer(containerView);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    public void setCurrentIndex(int index){
        adapter.setCurrentSelectedIndex(index);
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }


}