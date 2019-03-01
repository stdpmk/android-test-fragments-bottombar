package ru.company.my.testfragments3;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;


public class TabFragment extends BaseFragment {


    private String mName;
    private String mTitle;

    RecyclerView mRecyclerView;
    Button mButton;

    Adapter mAdapter;

    public TabFragment() {
        // Required empty public constructor
    }

    public static TabFragment newInstance(String name, String title) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString("name");
            mTitle = getArguments().getString("title");
        }

        if (mName.equals("tab1")) {
            setHasOptionsMenu(true);
        }

        mAdapter = new Adapter();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.action_bar_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        //prepareSearchView(searchItem);
    }

    public String getName() {
        return mName;
    }

    private void fillDataAdapter() {

        Calendar cal = Calendar.getInstance();
        int sec = cal.get(Calendar.SECOND);

        int n = 20 + sec % 20;

        List<String> list = new ArrayList();
        for (int i = 0; i < n; i++) {
            list.add( "Item " + i );
        }

        mAdapter.clear();
        mAdapter.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater,  container,   savedInstanceState);


        View view = inflater.inflate(R.layout.fragment_tab, container, false);

        ((AppCompatActivity)getActivity()).setTitle(mTitle);
        ((TextView)view.findViewById(R.id.title)).setText(mTitle);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mButton = view.findViewById(R.id.button);
        setupRecyclerListeners();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClicked();
            }
        });

        mRecyclerView.setAdapter(mAdapter);
        fillDataAdapter();

        return view;
    }

    private void setupRecyclerListeners() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                mRecyclerView.getContext(),
                LinearLayoutManager.VERTICAL
        );
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        //Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();

                        String name = mAdapter.getItem(position);

                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_main_container, ItemFragment.newInstance(name))
                                .addToBackStack("item")
                                .commit();
                    }
                }
        );
    }

    public void onButtonClicked() {

    }

    ///

    public static class Adapter extends BaseRecyclerViewAdapter<String, Adapter.ViewHolder> {
        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle;
            public ViewHolder(View view) {
                super(view);
                tvTitle = view.findViewById(R.id.tv_title);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBind(ViewHolder holder, int position, String data) {
            holder.tvTitle.setText(data);
        }
    }
}
