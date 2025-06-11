package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features",
    glue = {"stepdefinitions"},
    plugin = {
        "pretty",
        "html:target/CucumberReports/CucumberReport.html",
        "json:target/CucumberReports/CucumberReport.json"
    },
    monochrome = true
)
public class TestRunner {
	
}
