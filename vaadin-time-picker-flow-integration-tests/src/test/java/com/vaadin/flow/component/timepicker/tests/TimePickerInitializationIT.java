/**
 * Copyright 2019 HealthPivots, all rights reserved
 *
 * @Created: Oct 15, 2019
 */
package com.vaadin.flow.component.timepicker.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.vaadin.flow.component.timepicker.testbench.TimePickerElement;
import com.vaadin.flow.testutil.AbstractComponentIT;
import com.vaadin.flow.testutil.TestPath;

@TestPath("time-picker-initialization")
public class TimePickerInitializationIT extends AbstractComponentIT {

    /** 
     * Maximum is `Locale.getAvailableLocales()` about 159 that makes the
     * test to take several minutes, testing a few of them also guarantees
     * that the component is working correctly.
     */
    public static final int MINIMUM_NUMBER_OF_LOCALES_TO_TEST = 4;

    @Before
    public void init() {
        open();
        $(TimePickerElement.class).waitForFirst();
    }

    @Override
    protected int getDeploymentPort() {
        return super.getDeploymentPort();
    }

    @Test
    public void testInitialValue_nonDefaultLocale_initialValueLocalizedCorrectly() {
        runInitialLoadValueTestPattern("en-US", "15:00");
        runInitialLoadValueTestPattern("en-CA", "03:00");
        runInitialLoadValueTestPattern("fi-FI", "15:00");
        runInitialLoadValueTestPattern("no-NO", "00:00");
        runInitialLoadValueTestPattern("ar-SY", "15:00");
        runInitialLoadValueTestPattern("ar-JO", "11:00");
        runInitialLoadValueTestPattern("zh-TW", "15:00");
        runInitialLoadValueTestPattern("ko-KR", "23:00");
        runInitialLoadValueTestPattern("es-PA", "15:00");
    }

    private void runInitialLoadValueTestPattern(String locale, String time) {
        String url = getTestURL() + "/" + locale + "-" + time;
        this.getDriver().get(url);
        waitForElementPresent(By.tagName("vaadin-time-picker"));

        String error = verifyValueProperty(time);
        Assert.assertNull(
                "Wrong value on page load for locale " + locale + ": " + error,
                error);
        error = verifyFormat();
        Assert.assertNull(
                "Wrong format on page load for locale " + locale + ": " + error,
                error);

    }

    private String verifyFormat() {
        String timePickerInputValue = getTimePickerTextFieldValueWithNormalSpaces();
        String formattedTextValue = getLabelValue();
        if (formattedTextValue.equals(timePickerInputValue)) {
            return null;
        } else {
            return "expected: " + formattedTextValue + " actual: "
                    + timePickerInputValue;
        }
    }

    private String getLabelValue() {
        return $("div").id("formatted-time").getText();
    }

    /**
     * Calls {@code getTimePickerTextFieldValue()} for {@code
     * TimePickerElement} and replaces non-breaking space characters (char 160)
     * with normal spaces (char 32) for easier comparison. Small number of
     * locales (such as es-PA) seem to use those for their localized
     * timestamps.
     *
     * @return space-normalized timestamp
     */
    private String getTimePickerTextFieldValueWithNormalSpaces() {
        return getTimePickerElement().getTimePickerTextFieldValue().replace((char)160, (char)32);
    }

    private String verifyValueProperty(String value) {
        String timePickerValue = getTimePickerElement().getValue();
        if (value.equals(timePickerValue)) {
            return null;
        } else {
            return "expected: " + value + " actual: " + timePickerValue;
        }
    }

    private TimePickerElement getTimePickerElement() {
        try {
        	return $(TimePickerElement.class).waitForFirst();
        } catch (NoSuchElementException e) {
        	Assert.fail("TimePicker failed to initialize properly, no TimePicker found.");
        	return null;
        }
    }
}
