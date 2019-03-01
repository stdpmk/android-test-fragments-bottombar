package ru.company.my.testfragments3;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;


public class MainFragment extends BaseFragment {

    BottomNavigationView mNav;

    List<String> mFragmentNames = Arrays.asList(new String[]{
            "tab1",
            "tab2",
            "tab3"
    });

    TabFragment mTab1;
    TabFragment mTab2;
    TabFragment mTab3;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_tab1:
                    selectTab("tab1");
                    return true;
                case R.id.navigation_tab2:
                    selectTab("tab2");
                    return true;
                case R.id.navigation_tab3:
                    selectTab("tab3");
                    return true;
            }
            return false;
        }
    };

    public MainFragment() {
        // Required empty public constructor
    }

     public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        if (savedInstanceState == null) {
            addTabs();
        }
        setHasOptionsMenu(true);
    }

    private void addTabs() {
        mTab1 = TabFragment.newInstance("tab1", "Tab1");
        mTab2 = TabFragment.newInstance("tab2", "Tab2");
        mTab3 = TabFragment.newInstance("tab3", "Tab3");

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.add(R.id.fragment_tab_container, mTab3,  "tab3").hide(mTab3)
                .add(R.id.fragment_tab_container, mTab2, "tab2").hide(mTab2)
                .add(R.id.fragment_tab_container, mTab1, "tab1").show(mTab1)
                .commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater,  container,   savedInstanceState);

        View view =  inflater.inflate(R.layout.fragment_main, container, false);

        mNav = view.findViewById(R.id.navigation);

        mNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void hideAllFragmentsNotCommit(FragmentManager fragmentManager, FragmentTransaction fragmentTransaction) {
        for (String name : mFragmentNames) {
            Fragment fragment = fragmentManager.findFragmentByTag(name);
            if (fragment != null && !fragment.isHidden()) {
                fragmentTransaction.hide(fragment);
            }
        }
    }

    private void selectTab(String fragmentName) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        hideAllFragmentsNotCommit(fragmentManager, ft);

        Fragment fr = fragmentManager.findFragmentByTag(fragmentName);
        if (fr != null) {
            if (fr.isAdded()) {
                ft.show(fr);
            } else {
                ft.add(R.id.fragment_tab_container, fr, fragmentName);
            }
        }
        ft.commit();
    }
}
