package com.ukuya.mspc.mvp.presenter;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.Utils;
import com.ukuya.mspc.mvp.view.SignView;

public class SignPresenter {
    private SignView view;

    public SignPresenter(SignView view) {
        this.view = view;
    }

    public void onClickLogin(String login, String password) {
        validateData(login, password);
    }

    private void validateData(String login, String password) {
        if (view == null)
            return;
        if (login.isEmpty()) {
            view.showErrorSnackbar("Please enter name or email");
            return;
        } else if (!(RegexUtils.isEmail(login) || RegexUtils.isTel(login))) {
            view.showErrorSnackbar("Please enter username or email correctly");
            return;
        } else if (password.isEmpty()) {
            view.showErrorSnackbar("Please enter password");
            return;
        } else if (password.length() < 6) {
            view.showErrorSnackbar("Password must be at least 6 characters long.");
            return;
        } else {
            view.gotoMain();
        }
    }

}
