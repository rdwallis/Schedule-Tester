package com.wallissoftware.scheduletester.client;

import com.google.gwt.junit.client.GWTTestCase;

public class SandboxGwtTest extends GWTTestCase {
    @Override
    public String getModuleName() {
        return "com.wallissoftware.scheduletester.ScheduleTester";
    }

    public void testSandbox() {
        assertTrue(true);
    }
}