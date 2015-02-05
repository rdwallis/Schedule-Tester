package com.wallissoftware.scheduletester.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.wallissoftware.scheduletester.client.application.fps.FPSModule;
import com.wallissoftware.scheduletester.client.application.fibonacci.FibonacciModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
		install(new FibonacciModule());
        install(new FPSModule());
        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
    }
}