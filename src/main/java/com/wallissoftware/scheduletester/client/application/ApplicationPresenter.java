package com.wallissoftware.scheduletester.client.application;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.PermanentSlot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.wallissoftware.scheduletester.client.application.fibonacci.FibonacciPresenter;
import com.wallissoftware.scheduletester.client.application.fps.FPSPresenter;
import com.wallissoftware.scheduletester.client.place.NameTokens;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> {

    interface MyView extends View {
    }

    static final PermanentSlot<FPSPresenter> FPS_SLOT = new PermanentSlot<>();
    
    static final PermanentSlot<FibonacciPresenter> FIB_SLOT = new PermanentSlot<>();
    
    @ProxyStandard
	@NameToken(NameTokens.home)
	interface MyProxy extends ProxyPlace<ApplicationPresenter> {
	}

    @Inject
    ApplicationPresenter(EventBus eventBus, MyView view, MyProxy proxy, FPSPresenter fpsPresenter, FibonacciPresenter fibPresenter) {
        super(eventBus, view, proxy, RevealType.Root);
        setInSlot(FPS_SLOT, fpsPresenter);
        setInSlot(FIB_SLOT, fibPresenter);
    }
}
