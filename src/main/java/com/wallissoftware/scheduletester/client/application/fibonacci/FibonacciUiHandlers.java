package com.wallissoftware.scheduletester.client.application.fibonacci;

import com.gwtplatform.mvp.client.UiHandlers;

interface FibonacciUiHandlers extends UiHandlers {

    void runTest(int runCount);
}