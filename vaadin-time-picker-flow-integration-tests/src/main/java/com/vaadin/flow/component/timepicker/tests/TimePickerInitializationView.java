/**
 * Copyright 2019 HealthPivots, all rights reserved
 *
 * @Created: Oct 15, 2019
 */
package com.vaadin.flow.component.timepicker.tests;

import java.time.LocalTime;
import java.util.Locale;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.component.timepicker.demo.TimePickerView;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

/**
 * TimePickerIntializationView
 */
@Route("time-picker-initialization")
public class TimePickerInitializationView extends Div implements HasUrlParameter<String> {

	/**
	 * Instantiates a new time picker initialization view.
	 */
	public TimePickerInitializationView() {
		initTime(Locale.US, LocalTime.of(13, 42));
	}

	private void initTime(Locale locale, LocalTime time) {
		// this must be first to test initialization
		UI.getCurrent().setLocale(locale);

		removeAll();
		
		final TimePicker timePicker = new TimePicker();
		timePicker.setLabel(locale.getLanguage().concat("-").concat(locale.getCountry()));
		
		final TimePickerView.LocalTimeTextBlock browserFormattedTime = new TimePickerView.LocalTimeTextBlock();
        browserFormattedTime.setId("formatted-time");
        browserFormattedTime.setStep(timePicker.getStep());
        browserFormattedTime.setLocale(locale);
        
        // set listener before setting time
        timePicker.addValueChangeListener(event -> browserFormattedTime.setLocalTime(event.getValue()));
        timePicker.setValue(time);
        
        add(timePicker, browserFormattedTime);
	}
	
    @Override
    public void setParameter(BeforeEvent beforeEvent,
            @OptionalParameter String initialValue) {
        if (initialValue != null) {
            // eg. fi-FI-10-30
            String[] split = initialValue.split("\\W");
            Locale locale = new Locale(split[0], split[1]);

            initTime(locale, LocalTime.of(Integer.parseInt(split[2]),
                    Integer.parseInt(split[3])));
        }
    }
}
