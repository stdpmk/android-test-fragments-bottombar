package ru.company.my.testfragments3;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    private String mTag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTag = this.getClass().getName();

        if (BuildConfig.DEBUG) {
            Log.d(mTag, "in frag, onCreate");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (BuildConfig.DEBUG) {
            Log.d(mTag, "in frag, onCreateView");
        }
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (BuildConfig.DEBUG) {
            Log.d(mTag, "in frag, onDestroyView");
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Log.d(mTag, "in frag, onActivityCreated");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (BuildConfig.DEBUG) {
            Log.d(mTag, "in frag, onStart");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (BuildConfig.DEBUG) {
            Log.d(mTag, "in frag, onStop");
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (BuildConfig.DEBUG) {
            Log.d(mTag, "in frag, onResume");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (BuildConfig.DEBUG) {
            Log.d(mTag, "in frag, onPause");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (BuildConfig.DEBUG) {
            Log.d(mTag, "in frag, onSaveInstanceState");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG) {
            Log.d(mTag, "in frag, onDestroy");
        }
    }

}
