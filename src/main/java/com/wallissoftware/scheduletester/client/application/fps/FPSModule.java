package com.wallissoftware.scheduletester.client.application.fps;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class FPSModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
            bindSingletonPresenterWidget(FPSPresenter.class, FPSPresenter.MyView.class, FPSView.class);
    }
}