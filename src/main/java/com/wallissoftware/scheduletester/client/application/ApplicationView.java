package com.wallissoftware.scheduletester.client.application;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

class ApplicationView extends ViewWithUiHandlers<ApplicationUiHandlers> implements ApplicationPresenter.MyView {
    interface Binder extends UiBinder<Widget, ApplicationView> {
    }

    @UiField
    SimplePanel fpsPanel;
    
    @UiField FlowPanel testPanel;

    @Inject
    ApplicationView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        bindSlot(ApplicationPresenter.FPS_SLOT, fpsPanel);
        bindSlot(ApplicationPresenter.TEST_SLOT, testPanel);
    }
    
    @UiHandler("addFibTestButton")
    void onAddFibTestClick(ClickEvent event) {
        getUiHandlers().addFibTest();
    }
    
    @UiHandler("runTests")
    void onRunAllTestsClick(ClickEvent event) {
        getUiHandlers().runAllTests();
    }

}