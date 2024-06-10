package stepsDefinitions.base;

import automation.WebAutomator;
import drivers.driverFactory;
import exceptions.NoValidBrowserException;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.sql.Timestamp;

import static drivers.driverFactory.getAutomator;
import static java.sql.DriverManager.getDriver;

public class Hooks{
    public WebAutomator automator;

    @Before
    public void inicializar() throws NoValidBrowserException, IOException {
         this.automator = getAutomator();
    }

    @After
    public void finalizar() throws NoValidBrowserException, IOException {
        driverFactory.clear();
    }
}
