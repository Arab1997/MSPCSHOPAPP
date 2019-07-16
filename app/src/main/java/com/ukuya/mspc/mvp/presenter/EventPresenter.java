package com.ukuya.mspc.mvp.presenter;

import com.blankj.utilcode.util.NetworkUtils;
import com.ukuya.mspc.api.Api;
import com.ukuya.mspc.api.model.EventResponse;
import com.ukuya.mspc.di.component.AppComponent;
import com.ukuya.mspc.mvp.view.EventView;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class EventPresenter {
    EventView view;
    CompositeDisposable compositeDisposable;
    @Inject
    Api api;

    public EventPresenter(EventView view) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    public void inject(AppComponent appComponent){
        appComponent.inject(this);
    }

    public void getEventList(int page, String expand){
        if (view == null)
            return;
        if (NetworkUtils.isConnected()){
            if (page == 1)
                view.showProgressBar();
            compositeDisposable.add(api.getEventList(page, expand)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<EventResponse>() {
                @Override
                public void onNext(EventResponse eventResponse) {
                    view.hideProgressBar();
                    if (view == null)
                        return;
                    if (eventResponse != null){
                        view.setData(eventResponse);
                    }else {
                        view.showErrorSnackbar("List empty");
                    }
                }

                @Override
                public void onError(Throwable e) {
                    view.hideProgressBar();
                    view.showErrorSnackbar(e.getLocalizedMessage());
                }

                @Override
                public void onComplete() {

                }
            }));
        }else {
            view.showErrorSnackbar("Not connection!");
        }
    }

    public void onDestroy(){
        compositeDisposable.clear();
        compositeDisposable.dispose();
    }
}
