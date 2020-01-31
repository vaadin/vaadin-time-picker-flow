/*
 * Copyright 2000-2020 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.component.timepicker.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.timepicker.testbench.TimePickerElement;
import com.vaadin.flow.testutil.AbstractComponentIT;
import com.vaadin.flow.testutil.TestPath;

/**
 * Integration tests for the {@link TimePickerPage}.
 */
@TestPath("time-picker-it")
public class TimePickerNewIT extends AbstractComponentIT {

    @Before
    public void init() {
        open();
        $(TimePickerElement.class).waitForFirst();
    }

    @Test
    public void timePickerWithDifferentStep() {
        TimePickerElement picker = $(TimePickerElement.class)
                .id("step-setting-picker");
        picker.scrollIntoView();
        picker.openDropDown();
        Assert.assertEquals("Item in the dropdown is not correct", "1:00 AM",
                picker.getItemText(1));
        picker.closeDropDown();
        executeScript("arguments[0].value = '12:31'", picker);

        selectStep("0.5s");
        validatePickerValue(picker, "12:31:00.000");

        selectStep("10s");
        validatePickerValue(picker, "12:31:00");

        // for the auto formatting of the value to work, it needs to match the
        // new step
        executeScript("arguments[0].value = '12:30:00'", picker);
        selectStep("30m"); // using smaller step will cause the drop down to be
                           // big and then drop down iron list does magic that
                           // messes the item indexes
        validatePickerValue(picker, "12:30");
    }

    private void selectStep(String step) {
        NativeSelectElement select = $(NativeSelectElement.class)
                .id("step-picker");
        select.setValue(step, true);
    }

    private void validatePickerValue(TimePickerElement picker, String value) {
        Assert.assertEquals("Invalid time picker value", value,
                picker.getValue());
    }
}
