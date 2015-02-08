package com.wallissoftware.scheduletester.client.application.task;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

class TaskView extends ViewWithUiHandlers<TaskiUiHandlers> implements TaskPresenter.MyView {
    interface Binder extends UiBinder<Widget, TaskView> {
    }

    @UiField IntegerBox runCount,high, low;
    
    @UiField VerticalPanel resultPanel;
    
    @UiField CheckBox unpredictable, highVarience;
    
    @Inject
    TaskView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    @UiHandler("runButton")
    void onRunButtonClick(ClickEvent event) {
        getUiHandlers().runTest();
    }

    @Override
    public void writeAverage(double d) {
        resultPanel.insert(new Label("Avg Time to Run: " + d), 0);
       
    }

    @Override
    public void writeResult(String result) {
        resultPanel.add(new Label(result));
        
    }

    @Override
    public void clearResults() {
        resultPanel.clear();
        
    }

    @Override
    public int getRunCount() {
       return runCount.getValue();
    }

    @Override
    public boolean getHighVarience() {
        return highVarience.getValue();
    }

    @Override
    public boolean getUnpredictable() {
        return unpredictable.getValue();
    }

    @Override
    public int getLow() {
        return low.getValue();
    }

    @Override
    public int getHigh() {
        return high.getValue();
    }
    
}