package com.wallissoftware.scheduletester.client.application;

import javax.inject.Inject;

import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.PermanentSlot;
import com.gwtplatform.mvp.client.presenter.slots.Slot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.wallissoftware.scheduletester.client.application.fps.FPSPresenter;
import com.wallissoftware.scheduletester.client.application.task.TaskPresenter;
import com.wallissoftware.scheduletester.client.application.task.fib.FibTask;
import com.wallissoftware.scheduletester.client.place.NameTokens;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> implements ApplicationUiHandlers {

    interface MyView extends View, HasUiHandlers<ApplicationUiHandlers> {
    }

    static final PermanentSlot<FPSPresenter> FPS_SLOT = new PermanentSlot<>();
    
    static final Slot<TaskPresenter> TEST_SLOT = new Slot<>();
    
    @ProxyStandard
	@NameToken(NameTokens.home)
	interface MyProxy extends ProxyPlace<ApplicationPresenter> {
	}

    private final Provider<TaskPresenter> taskPresenterProvider;

    @Inject
    ApplicationPresenter(EventBus eventBus, MyView view, MyProxy proxy, FPSPresenter fpsPresenter, Provider<TaskPresenter> taskPresenterProvider) {
        super(eventBus, view, proxy, RevealType.Root);
        getView().setUiHandlers(this);
        setInSlot(FPS_SLOT, fpsPresenter);
        this.taskPresenterProvider = taskPresenterProvider;
        addFibTest();
    }

    @Override
    public void addFibTest() {
        addToSlot(TEST_SLOT, taskPresenterProvider.get().setTask(new FibTask(9999999)));
    }

    @Override
    public void runAllTests() {
        for (TaskPresenter tp: getChildren(TEST_SLOT)) {
            tp.runTest();
        }
        
    }
}
