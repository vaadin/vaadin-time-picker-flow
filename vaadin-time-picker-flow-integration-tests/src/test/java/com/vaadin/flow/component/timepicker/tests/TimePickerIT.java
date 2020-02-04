/*
 * Copyright 2000-2017 Vaadin Ltd.
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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vaadin.flow.component.timepicker.demo.TimePickerView;
import com.vaadin.flow.component.timepicker.testbench.TimePickerElement;
import com.vaadin.flow.demo.ComponentDemoTest;

/**
 * Integration tests for the {@link TimePickerView}.
 */
public class TimePickerIT extends ComponentDemoTest {

    @Before
    public void init() {
        $(TimePickerElement.class).waitForFirst();
    }

    @Test
    public void selectTimeOnSimpleTimePicker() {
        WebElement picker = layout.findElement(By.id("simple-picker"));
        WebElement message = layout.findElement(By.id("simple-picker-message"));

        executeScript("arguments[0].value = '10:08'", picker);

        waitUntil(driver -> message.getText().contains("Hour: 10\nMinute: 8"));

        executeScript("arguments[0].value = ''", picker);

        waitUntil(driver -> "No time is selected".equals(message.getText()));
    }

    @Test
    public void selectTimeOnDisabledTimePicker() {
        WebElement picker = layout.findElement(By.id("disabled-picker"));
        WebElement message = layout
                .findElement(By.id("disabled-picker-message"));

        executeScript("arguments[0].value = '10:15'", picker);
        Assert.assertEquals(
                "The message should not be shown for the disabled picker", "",
                message.getText());
    }

    @Test
    public void timePickerWithMinAndMaxSetting() {
        TimePickerElement picker = $(TimePickerElement.class)
                .id("time-picker-min-max");
        picker.scrollIntoView();
        picker.openDropDown();
        Assert.assertEquals(
                "The first item in the dropdown should be the min value",
                "5:00 AM", picker.getItemText(0));
        Assert.assertEquals(
                "The last item in the dropdown should be the max value",
                "6:00 PM", picker.getLastItemText());
    }

    @Override
    protected String getTestPath() {
        return ("/vaadin-time-picker");
    }
}
