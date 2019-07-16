package com.ukuya.mspc.mvp.view;

public interface BaseView {
    void showErrorSnackbar(String message);
    void showInfoSnackbar(String message);
    void finishActivity();
    void showProgressBar();
    void hideProgressBar();
}
