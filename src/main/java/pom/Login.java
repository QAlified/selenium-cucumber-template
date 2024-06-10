package pom;
import static drivers.driverFactory.getAutomator;
import automation.WebAutomator;
import exceptions.NoValidBrowserException;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.IOException;

public class Login {
    private By buttonLogin = By.id("login-portal");
    private By user = By.id("text");
    private By pass = By.id("password");
    private By login = By.id("login-button");
    private WebAutomator automator;

    public Login() throws NoValidBrowserException, IOException {
        this.automator = getAutomator();
    }


    public void NavegarALaPagina(String url){
        this.automator.goTo(url);
    }
    public void ingresoPortal(){
        String dirLogin = this.automator.find(buttonLogin).getWebElement().getAttribute("href");
        this.automator.getDriver().get(dirLogin);
    }

    public void ingresoUsuario(String username){
        this.automator.find(user).setText(username);
    }
    public void ingresoPass(String password){
        this.automator.find(pass).setText(password);
    }

    public void chequeoYFalla(){
        this.automator.find(login).click();
        String alerta = this.automator.getDriver().switchTo().alert().getText();
        Assert.assertEquals(alerta,"validation failed");
    }

    public void chequeoYPasa(){
        this.automator.find(login).click();
        String alerta = this.automator.getDriver().switchTo().alert().getText();
        Assert.assertEquals(alerta,"validation succedeed");
    }

    public void chequeo(String validation){
        this.automator.find(login).click();
        String alerta = this.automator.getDriver().switchTo().alert().getText();
        Assert.assertEquals(alerta,validation);
    }
}
