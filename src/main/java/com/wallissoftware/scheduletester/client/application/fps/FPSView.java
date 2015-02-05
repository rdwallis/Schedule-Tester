package com.wallissoftware.scheduletester.client.application.fps;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

class FPSView extends ViewImpl implements FPSPresenter.MyView {
    interface Binder extends UiBinder<Widget, FPSView> {
    }

    @UiField Label fpsLabel;


    @Inject
    FPSView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setFps(int fps) {
        fpsLabel.setText(fps + " fps");
        
    }
    
}