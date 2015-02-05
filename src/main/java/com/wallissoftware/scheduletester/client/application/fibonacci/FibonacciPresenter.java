package com.wallissoftware.scheduletester.client.application.fibonacci;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class FibonacciPresenter extends PresenterWidget<FibonacciPresenter.MyView> implements FibonacciUiHandlers {
    interface MyView extends View, HasUiHandlers<FibonacciUiHandlers> {

        void writeAverage(double d);

        void writeResult(String string);

        void clearResults();
    }

    @Inject
    FibonacciPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);

        getView().setUiHandlers(this);
    }

    @Override
    public void runTest(int runCount) {
        getView().clearResults();
        runTest(new ArrayList<Double>(), runCount);
    }
    
    private void runTest(final List<Double> times, final int runCount) {
        if (runCount > 0) {
            final int n = 999999;
            calculateFibonacci(n, new Callback<List<Double>, Void>() {

                @Override
                public void onFailure(Void reason) {
                }

                @Override
                public void onSuccess(List<Double> result) {
                    getView().writeResult("Calculated the " + n +"th fibonnaci number as " + result.get(0) + " in " + result.get(1));
                    times.add(result.get(1));
                    runTest(times, runCount - 1);
                }
                
            });
        } else if (!times.isEmpty()){
            double total = 0;
            for (Double time: times) {
                total += time;
            }
            getView().writeAverage(total / times.size());
        }

    }

    private void calculateFibonacci(final int n, final Callback<List<Double>, Void> callback) {
        final double startTime = getPerformanceNow();
        Scheduler.get().scheduleIncremental(new RepeatingCommand() {
            
            double first = 0, second = 1;
            
            int count = 0;

            @Override
            public boolean execute() {
                double temp = second;
                second += first;
                first = temp;
                if (count++ > n) {
                    List<Double> result = new ArrayList<>();
                    result.add(second);
                    result.add(getPerformanceNow() - startTime);
                    callback.onSuccess(result);
                    return false;
                }

                return true;
            }
            
        });
        
    }

    private native double getPerformanceNow() /*-{
        return performance.now();
    }-*/;

}