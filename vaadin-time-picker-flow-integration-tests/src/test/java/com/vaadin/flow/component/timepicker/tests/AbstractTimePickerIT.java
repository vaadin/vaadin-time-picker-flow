package com.vaadin.flow.component.timepicker.tests;

import com.vaadin.flow.testutil.AbstractComponentIT;
import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.parallel.Browser;
import org.junit.Before;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.lang.management.ManagementFactory;
import java.util.List;

public abstract class AbstractTimePickerIT extends AbstractComponentIT {

    @Override
    protected List<DesiredCapabilities> getBrowserCapabilities(
        Browser... browsers) {
        return enhanceCapabilities(super.getBrowserCapabilities(browsers));
    }

    static List<DesiredCapabilities> enhanceCapabilities(
        List<DesiredCapabilities> capabilities) {
        capabilities.stream()
            .filter(cap -> "chrome".equalsIgnoreCase(cap.getBrowserName()))
            .forEach(AbstractTimePickerIT::enhanceCapabilities);
        return capabilities;
    }

    static void enhanceCapabilities(DesiredCapabilities cap) {
        final ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu");
        cap.setCapability(ChromeOptions.CAPABILITY, options);
    }
}
