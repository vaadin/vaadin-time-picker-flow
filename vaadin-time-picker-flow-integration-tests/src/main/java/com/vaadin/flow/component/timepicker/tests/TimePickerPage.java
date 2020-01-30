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

import java.time.Duration;
import java.util.Arrays;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.Route;

@Route("time-picker-it")
public class TimePickerPage extends Div {

    private final TimePicker timePicker;

    public TimePickerPage() {
        timePicker = new TimePicker();

        NativeSelect stepSelector = new NativeSelect();
        stepSelector.setWidth("70px");
        stepSelector.setOptions(
                Arrays.asList("0.5s", "10s", "1m", "15m", "30m", "1h"));
        stepSelector.setId("step-picker");
        // stepSelector.setValue("1h");

        stepSelector.addValueChangeListener(event -> {
            if (event.getValue() != null && !event.getValue().isEmpty()) {
                Duration step = Duration
                        .parse("PT" + event.getValue().toUpperCase());
                timePicker.setStep(step);
            }
        });

        add(stepSelector, timePicker);
    }

}
