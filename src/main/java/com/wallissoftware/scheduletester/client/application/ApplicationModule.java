package com.wallissoftware.scheduletester.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.wallissoftware.scheduletester.client.application.fps.FPSModule;
import com.wallissoftware.scheduletester.client.application.task.TaskModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new TaskModule());
        install(new FPSModule());
        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
    }
}