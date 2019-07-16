package com.ukuya.mspc.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ukuya.mspc.api.model.EventResponse;
import com.ukuya.mspc.callback.EventListAdapterCallback;
import com.ukuya.mspc.databinding.EventItemBinding;


public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ItemHolder> {
    EventResponse data;

    EventListAdapterCallback callback;

    public EventListAdapter(EventListAdapterCallback callback) {
        this.callback = callback;
        data = new EventResponse();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        EventItemBinding binding = EventItemBinding.inflate(layoutInflater, viewGroup, false);
        return new ItemHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        itemHolder.binding.setItem(data.getEvents().get(i));
        itemHolder.binding.setCurrentTime(System.currentTimeMillis());
        itemHolder.binding.executePendingBindings();
        if (i == data.getEvents().size() - 1 && data.getMeta().getCurrentPage() < data.getMeta().getPageCount()) {
            callback.getData(data.getMeta().getCurrentPage() + 1);
        }
    }

    @Override
    public int getItemCount() {
        return data.getEvents().size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        EventItemBinding binding;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

    }

    public EventResponse getData() {
        return data;
    }

    public void setData(EventResponse data) {
        if (data.getMeta().getCurrentPage() != 1) {
            this.data.getEvents().addAll(data.getEvents());
            this.data.setMeta(data.getMeta());
            this.data.setLinks(data.getLinks());
        } else {
            this.data = data;
        }
        notifyDataSetChanged();
    }
}
