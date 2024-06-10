package stepsDefinitions;
import automation.WebAutomator;
import exceptions.NoValidBrowserException;
import io.cucumber.java.en.*;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.testng.Assert;
import pom.Login;
import java.io.IOException;
import static drivers.driverFactory.getAutomator;

public class loginSteps{
    private WebAutomator automator = getAutomator();

    public loginSteps() throws NoValidBrowserException, IOException {
    }
    Login l = new Login(); // Inicializo para que sea visible el POM hacia todos//
    /*METODOS*/

    @Given("Se ingresa en la pagina Web Driver Test Login {string}")
    public void entro_en_pagina_webdrivertest(String url) throws NoValidBrowserException, IOException {
        l.NavegarALaPagina(url);
    }
    @When("Entro en Login Portal")
    public void entro_en_login_portal() {
        l.ingresoPortal();
    }
    @And("Agrego user {string}")
    public void agrego_user(String username) {
        l.ingresoUsuario(username);
    }
    @And("Agrego password {string}")
    public void agrego_password(String password) {
        l.ingresoPass(password);
    }

    @Then("Cliqueo en Login y verifico que entra")
    public void cliqueo_en_login_y_entra() {
        l.chequeoYPasa();
    }

    @Then("Cliqueo en Login y verifico que no entra")
    public void cliqueo_en_login_y_no_entra() {
        l.chequeoYFalla();
    }

    @Then("Cliqueo en Login y verifico {string}")  /*El mas general*/
    public void cliqueo_en_login_validation(String validation) {
        l.chequeo(validation);
    }
}