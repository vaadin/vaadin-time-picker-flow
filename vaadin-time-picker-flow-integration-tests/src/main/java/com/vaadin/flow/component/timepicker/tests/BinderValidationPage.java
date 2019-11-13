/*
 * Copyright 2000-2019 Vaadin Ltd.
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

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.Route;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Route("binder-validation")
public class BinderValidationPage extends Div {

    public static final String BINDER_ERROR_MSG = "binder";

    public BinderValidationPage() {
        Binder<AData> binder = new Binder<>(AData.class);
        TimePicker timePicker = new TimePicker("Date");

        // Set date field validation constraint
        timePicker.setMin("13:00:00");

        // Set invalid indicator label
        Element timeFieldElement = timePicker.getElement();
        timeFieldElement.addPropertyChangeListener("invalid", event -> {
            String label = timeFieldElement.getProperty("invalid", false)
                    ? "invalid"
                    : "valid";
            timeFieldElement.setProperty("label", label == null ? "" : label);
        });

        timePicker.setRequiredIndicatorVisible(true);

        binder.forField(timePicker).withValidator(value -> value != null &&
                value.compareTo(LocalTime.of(14, 0, 0)) > -1, BINDER_ERROR_MSG)
                .bind(AData::getTime, AData::setTime);

        add(timePicker);
    }

    public static class AData {

        @NotNull
        private LocalTime time;

        public LocalTime getTime() {
            return time;
        }

        public void setTime(LocalTime time) {
            this.time = time;
        }
    }

}
