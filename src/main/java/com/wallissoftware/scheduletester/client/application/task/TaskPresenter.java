package com.wallissoftware.scheduletester.client.application.task;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.user.client.Random;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class TaskPresenter extends PresenterWidget<TaskPresenter.MyView> implements TaskiUiHandlers {
    interface MyView extends View, HasUiHandlers<TaskiUiHandlers> {

        void writeAverage(double d);

        void writeResult(String string);

        void clearResults();

        int getRunCount();

        boolean getHighVarience();

        boolean getUnpredictable();

        int getLow();

        int getHigh();
    }

    private RepeatingTask task;

    @Inject
    TaskPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);

        getView().setUiHandlers(this);
    }

    int unpredictableCounter;

    boolean unpredictableOsc;

    @Override
    public void runTest() {
        getView().clearResults();
        runTest(getView().getLow(), getView().getHigh(), getView().getHighVarience(), getView().getUnpredictable(), new ArrayList<Double>(), getView().getRunCount());
    }

    public TaskPresenter setTask(RepeatingTask task) {
        this.task = task;
        return this;
    }

    private void runTest(final int low, final int high, final boolean highVarience, final boolean unpredictable, final List<Double> times, final int runCount) {
        if (runCount > 0) {
            final double startTime = getPerformanceNow();
            Scheduler.get().scheduleIncremental(new RepeatingCommand() {

                @Override
                public boolean execute() {
                    boolean repeat = task.execute();
                    if (repeat) {
                        int varyLoop = calculateVaryLoop(low, high, highVarience, unpredictable);
                        incUnpredictableCounter();
                        while (repeat && varyLoop-- > 0) {
                            repeat = task.execute();
                        }
                    }
                    if (!repeat) {
                        double time = getPerformanceNow() - startTime;
                        getView().writeResult(task.getResult() + " in " + time + "ms");
                        times.add(time);
                        task.reset();
                        runTest(low, high, highVarience, unpredictable, times, runCount - 1);
                    }
                    return repeat;
                }

            });
        } else if (!times.isEmpty()) {
            double total = 0;
            for (Double time : times) {
                total += time;
            }
            getView().writeAverage(total / times.size());
        }

    }

    private int calculateVaryLoop(int low, int high, boolean highVarience, boolean unpredictable) {
        if (unpredictable) {
            return unpredictableOsc ? high : low;
        } else if (highVarience) {
            return Random.nextInt(high - low) + low;
        }
        return 0;
    }

    private void incUnpredictableCounter() {
        if (unpredictableCounter++ % 50 == 0) {
            unpredictableOsc = !unpredictableOsc;
        }
    }

    private native double getPerformanceNow() /*-{
                                              return performance.now();
                                              }-*/;

}