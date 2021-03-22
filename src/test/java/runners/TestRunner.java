package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "stepDefs",
        features = "src/test/resources/functionalTests"
        //tags = "@tag1 and @tag2"
)
public class TestRunner {
}
