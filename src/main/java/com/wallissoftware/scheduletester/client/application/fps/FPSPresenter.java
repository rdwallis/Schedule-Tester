package com.wallissoftware.scheduletester.client.application.fps;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class FPSPresenter extends PresenterWidget<FPSPresenter.MyView> {
    interface MyView extends View {

        void setFps(int fps);
    }

    @Inject
    FPSPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);
    }

    @Override
    protected void onBind() {
        super.onBind();
        startFPSMeter();
    }

    private native void startFPSMeter() /*-{
        var that = this;
        $doc.addEventListener('fps',
            function(evt) {
                $entry(that.@com.wallissoftware.scheduletester.client.application.fps.FPSPresenter::updateFps(I)(evt.fps));
            },
        false);
        $wnd.FPSMeter.run();      
    }-*/;
    
    private void updateFps(int fps) {
        getView().setFps(fps);
    }
    
    

}