package pom;
import static drivers.driverFactory.getAutomator;
import automation.WebAutomator;
import exceptions.NoValidBrowserException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class Contact {
    /*SELECTORES VAN ABAJO*/
    private By contact = By.id("contact-us");
    private By nombre = By.name("first_name");
    private By apellido = By.name("last_name");
    private By email = By.name("email");
    private By comentario = By.name("message");
    private By enviar = By.xpath("//div[@id=\"form_buttons\"]/input[@value='SUBMIT']");
    private By mensaje = By.xpath("//div[@id='contact_reply']/h1");

    private WebAutomator automator;

    public Contact() throws NoValidBrowserException, IOException {
        this.automator = getAutomator();
    }

    public void NavegarALaPagina(String url){
        this.automator.goTo(url);
    }

    public void ingresoContact(){
        String dirLogin = this.automator.find(contact).getWebElement().getAttribute("href");
        this.automator.getDriver().get(dirLogin);
    }

    public void agregoNombre(String name){
        this.automator.find(nombre).setText(name);
    }

    public void agregoApellido(String lastname){
        this.automator.find(apellido).setText(lastname);
    }

    public void agregoEmail(String mail){
        this.automator.find(email).setText(mail);
    }

    public void agregoComentario(String comment){
        this.automator.find(comentario).setText(comment);
    }

    public void enviar(){
        this.automator.find(enviar).click();
    }

    public void chequeoEnvio(String validation){
        WebElement mensajeFinal = this.automator.find(mensaje).getWebElement();
        Assert.assertEquals(mensajeFinal.getText(),validation);
    }
}
