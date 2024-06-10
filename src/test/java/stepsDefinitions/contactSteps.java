package stepsDefinitions;
import automation.WebAutomator;
import exceptions.NoValidBrowserException;
import io.cucumber.java.en.*;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pom.Contact;
import stepsDefinitions.base.Hooks;

import java.io.IOException;

import static drivers.driverFactory.getAutomator;


public class contactSteps{
    private WebAutomator automator = getAutomator();
    public contactSteps() throws NoValidBrowserException, IOException {
    }

    Contact c = new Contact();
    /*METODOS*/
    @Given("Se ingresa en la pagina Web Driver Test {string}")
    public void se_ingresa_en_la_pagina_web_driver_test(String web) {
        c.NavegarALaPagina(web);
    }

    @When("Entro en Contact US")
    public void entro_en_contact_us() {
        c.ingresoContact();
    }

    @And("Agrego primer nombre {string}")
    public void agrego_primer_nombre(String name) {
        c.agregoNombre(name);
    }

    @And("Agrego primer apellido {string}")
    public void agrego_primer_apellido(String lastname) {
        c.agregoApellido(lastname);
    }

    @And("Agrego email {string}")
    public void agrego_email(String mail) {
        c.agregoEmail(mail);
    }

    @And("Agrego comentario {string}")
    public void agrego_comentario(String comment) {
        c.agregoComentario(comment);
    }

    @And("Cliqueo en Submit")
    public void cliqueo_en_submit() {
       c.enviar();
    }

    @Then("Se agregaron los datos correctamente")
    public void se_agregaron_los_datos_correctamente() {
        c.chequeoEnvio("Thank You for your Message!");
    }
}
