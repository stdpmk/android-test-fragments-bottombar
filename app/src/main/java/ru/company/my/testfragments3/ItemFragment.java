package ru.company.my.testfragments3;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ItemFragment extends BaseFragment {

    String mName;

    public ItemFragment() {
        // Required empty public constructor
    }


    public static ItemFragment newInstance(String name) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString("name");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,  container,   savedInstanceState);


        View view = inflater.inflate(R.layout.fragment_item, container, false);

        ((TextView)view.findViewById(R.id.title)).setText(mName);

        ((AppCompatActivity)getActivity()).setTitle(mName);

        return view;
    }

}
