package com.ukuya.mspc.view.fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SnackbarUtils;
import com.ukuya.mspc.MyApp;
import com.ukuya.mspc.R;
import com.ukuya.mspc.api.model.EventResponse;
import com.ukuya.mspc.callback.EventListAdapterCallback;
import com.ukuya.mspc.databinding.FragmentEventBinding;
import com.ukuya.mspc.mvp.presenter.EventPresenter;
import com.ukuya.mspc.mvp.view.EventView;
import com.ukuya.mspc.view.adapter.EventListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment implements EventView, SwipeRefreshLayout.OnRefreshListener {

    FragmentEventBinding binding;
    EventPresenter presenter;
    EventListAdapter adapter;

    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new EventPresenter(this);
        presenter.inject(MyApp.app.appComponent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DataBindingUtil.bind(view);
        binding.swipeRefreshLayout.setOnRefreshListener(this);
        adapter = new EventListAdapter(new EventListAdapterCallback() {
            @Override
            public void getData(int page) {
                presenter.getEventList(page, "creator,city,country");
            }
        });

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        binding.recyclerView.setAdapter(adapter);
        presenter.getEventList(1, "creator,city,country");
    }


    @Override
    public void showErrorSnackbar(String message) {
        SnackbarUtils
                .with(binding.recyclerView.getRootView())
                .setBgColor(Color.RED)
                .setMessage(message).show();
    }

    @Override
    public void showInfoSnackbar(String message) {
        SnackbarUtils
                .with(binding.recyclerView.getRootView())
                .setBgColor(Color.GREEN)
                .setMessage(message).show();

    }

    @Override
    public void finishActivity() {

    }

    @Override
    public void showProgressBar() {
        binding.swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgressBar() {
        binding.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setData(EventResponse eventResponse) {
        adapter.setData(eventResponse);
    }

    @Override
    public void onRefresh() {
        presenter.getEventList(1, "creator,city,country");
    }
}
