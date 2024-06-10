package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

@Test
@CucumberOptions(features={"src/test/java/features/"},glue = {"stepsDefinitions"},monochrome = false,
        dryRun = false,tags="@login",plugin={"pretty","html:target/cucumber"})
public class MainRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios(){
        return super.scenarios();
    }
}