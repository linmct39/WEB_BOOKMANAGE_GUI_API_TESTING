package runner;

import org.testng.annotations.Test;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        publish = true, 
        features = {
            "src/test/resources/features/UI_Login.feature",
            "src/test/resources/features/API_Login.feature",
            "src/test/resources/features/UI_Register.feature",
            "src/test/resources/features/API_Register.feature",
            "src/test/resources/features/UI_Home.feature",
            "src/test/resources/features/API_Home.feature",
            "src/test/resources/features/UI_Search.feature",
            "src/test/resources/features/UI_Cart.feature"
        }, 
        glue = { "steps", "core" }, 
        plugin = { 
            "pretty",
            "summary",
            "junit:target/cucumber.xml",
            "html:target/cucumber/index.html",
            "json:target/cucumber.json"
        }, 
        monochrome = true
)
@Test
public class TestRunner extends AbstractTestNGCucumberTests {
}
