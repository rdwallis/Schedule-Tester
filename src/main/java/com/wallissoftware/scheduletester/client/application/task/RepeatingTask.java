package com.wallissoftware.scheduletester.client.application.task;

import com.google.gwt.core.client.Scheduler.RepeatingCommand;

public interface RepeatingTask extends RepeatingCommand{
    
    String getResult();
    
    void reset();

}
