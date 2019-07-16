package com.ukuya.mspc.mvp.view;

import com.ukuya.mspc.api.model.EventResponse;

public interface EventView extends BaseView {
    void setData(EventResponse eventResponse);
}
