package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.BaseClass;
import utils.ExtentReportManager;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class Hooks {

    ExtentReports extent = ExtentReportManager.getReportInstance();
    ExtentTest test;

    @Before
    public void beforeScenario(Scenario scenario) {
        test = extent.createTest(scenario.getName());
        scenario.log("🔹 Scenario Started: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotPath = BaseClass.takeScreenshot(scenario.getName());

            test.fail("❌ Scenario Failed: " + scenario.getName(),
			        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

        } else {
            test.pass("✅ Scenario Passed: " + scenario.getName());
        }

        extent.flush(); // Finalize report
    }
}
