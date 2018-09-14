package com.sddy.baseui;

import android.view.View;

public interface Presenter extends View.OnClickListener {

    @Override
    void onClick(View v);
}
