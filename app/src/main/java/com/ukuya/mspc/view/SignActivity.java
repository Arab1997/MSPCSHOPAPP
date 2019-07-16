package com.ukuya.mspc.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.SnackbarUtils;
import com.ukuya.mspc.R;
import com.ukuya.mspc.databinding.ActivitySignBinding;
import com.ukuya.mspc.mvp.presenter.SignPresenter;
import com.ukuya.mspc.mvp.view.SignView;

public class SignActivity extends AppCompatActivity implements SignView {
    ActivitySignBinding binding;
    SignPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(SignActivity.this, R.layout.activity_sign);
        presenter = new SignPresenter(this);
        binding.cardViewSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickLogin(binding.edLogin.getText().toString(), binding.edPassword.getText().toString());
            }
        });

    }

    @Override
    public void gotoMain() {
        Intent intent = new Intent(SignActivity.this, MainActivity.class);
        startActivity(intent);
        finishActivity();
    }

    @Override
    public void showErrorSnackbar(String message) {
        SnackbarUtils
                .with(binding.cardViewSingIn.getRootView())
                .setBgColor(Color.RED)
                .setMessage(message).show();
    }

    @Override
    public void showInfoSnackbar(String message) {
        SnackbarUtils
                .with(binding.cardViewSingIn.getRootView())
                .setBgColor(Color.GREEN)
                .setMessage(message).show();

    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
