package com.wallissoftware.scheduletester.client.application.task.fib;

import com.wallissoftware.scheduletester.client.application.task.RepeatingTask;

public class FibTask implements RepeatingTask{
    
    double first = 0, second = 1;
    
    int count = 0;

    private int n;
    
    public FibTask(int n) {
        this.n = n;
    }

    @Override
    public boolean execute() {
        double temp = second;
        second += first;
        first = temp;
        return count++ < n;
    }

    @Override
    public String getResult() {
        return "Calculated the " + n + "th fibonacci number as " + second;
    }

    @Override
    public void reset() {
        first = 0;
        second = 1;
        count = 0;
    }

}
