package com.wallissoftware.scheduletester.client.application.task;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class TaskModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
            bindPresenterWidget(TaskPresenter.class, TaskPresenter.MyView.class, TaskView.class);
    }
}