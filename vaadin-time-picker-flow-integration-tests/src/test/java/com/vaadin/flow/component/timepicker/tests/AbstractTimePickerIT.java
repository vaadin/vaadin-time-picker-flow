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

    private WebDriver createHeadlessChromeDriver() {
        System.out.println("*** Setup driver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--aggressive-cache-discard");
        options.addArguments("--disable-cache");
        options.addArguments("--disable-application-cache");
        options.addArguments("--disable-offline-load-stale-cache");
        options.addArguments("--disk-cache-size=0");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--no-proxy-server");
        options.addArguments("--log-level=3");
        options.addArguments("--silent");
        options.addArguments("--disable-browser-side-navigation");
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        options.setProxy(null);
        return TestBench.createDriver(new ChromeDriver(options));
    }

    @Override
    protected List<DesiredCapabilities> getBrowserCapabilities(
        Browser... browsers) {
        return enhanceCapabilities(super.getBrowserCapabilities(browsers));
    }

    static List<DesiredCapabilities> enhanceCapabilities(List<DesiredCapabilities> capabilities) {

        capabilities.stream().filter(cap -> "chrome".equalsIgnoreCase(cap.getBrowserName())).forEach(cap -> cap.setCapability("pageLoadStrategy","none"));
        return capabilities;
    }
}
