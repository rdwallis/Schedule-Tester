package com.wallissoftware.scheduletester.client.application.fibonacci;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class FibonacciModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
            bindSingletonPresenterWidget(FibonacciPresenter.class, FibonacciPresenter.MyView.class, FibonacciView.class);
    }
}