package com.vaadin.flow.component.timepicker.tests;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("select")
public class NativeSelectElement extends TestBenchElement {

    public void setValue(String value) {
        setProperty("value", value);
    }

    public void setValue(String value, boolean triggerChangeAndWait) {
        setValue(value);
        if (triggerChangeAndWait) {
            dispatchEvent("change");
            waitForVaadin();
        }
    }

    public String getValue() {
        return getPropertyString("value");
    }
}
