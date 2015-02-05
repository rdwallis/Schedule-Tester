package com.wallissoftware.scheduletester.client.application.fibonacci;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

class FibonacciView extends ViewWithUiHandlers<FibonacciUiHandlers> implements FibonacciPresenter.MyView {
    interface Binder extends UiBinder<Widget, FibonacciView> {
    }

    @UiField IntegerBox runCount;
    
    @UiField VerticalPanel resultPanel;
    
    @Inject
    FibonacciView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    @UiHandler("runButton")
    void onRunButtonClick(ClickEvent event) {
        getUiHandlers().runTest(runCount.getValue());
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
    
}