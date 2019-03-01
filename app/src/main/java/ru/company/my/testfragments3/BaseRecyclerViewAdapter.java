package ru.company.my.testfragments3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

// Простой базовый класс для RecyclerView адаптара
public abstract class BaseRecyclerViewAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private List<T> mDataset;

    public BaseRecyclerViewAdapter() {
        mDataset = new ArrayList<>();
    }

    public void addAll(List<T> dataset) {
        mDataset.addAll(dataset);
    }

    public void add(T item) {
        mDataset.add(item);
    }

    public void remove(int idx) {
        mDataset.remove(idx);
    }

    public void clear() {
        mDataset.clear();
    }

    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(VH holder, int position) {
        T item = mDataset.get(position);
        onBind(holder, position, item);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public T getItem(int pos) {
        return mDataset.get(pos);
    }

    public abstract void onBind(VH holder, int position, T item);
}
