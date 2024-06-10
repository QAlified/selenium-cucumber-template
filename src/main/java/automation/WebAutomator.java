package automation;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.time.Duration;
import java.util.function.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import browsers.Browser;
import config.Config;
import exceptions.NoValidBrowserException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;

public class WebAutomator {
	
	//WebDriver Wrapper
	private WebDriver driver;
	//WebDriver wait

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	private String titulo;
	private WebDriverWait wait;
	
	//Contemplar agregar parametro para modo HeadLess
    @SuppressWarnings("deprecation")
	public WebAutomator(Browser browser,boolean isHeadless, boolean isIncognito, long max_wait) throws NoValidBrowserException, IOException {
		switch (browser) {
		case FIREFOX:
			FirefoxProfile profile = new FirefoxProfile();
	        profile.setPreference("dom.webnotifications.enabled", false);
	        profile.setPreference("toolkit.startup.max_resumed_crashes", "-1");
	        FirefoxOptions options = new FirefoxOptions();
	        options.setProfile(profile);
		    System.setProperty("webdriver.gecko.driver", Config.DRIVER_PATH + "geckodriver.exe");
		    driver = new FirefoxDriver(options);
			break;

		case CHROME:
			System.setProperty("webdriver.chrome.driver", Config.DRIVER_PATH + "chromedriver.exe");
			ChromeOptions cOptions = new ChromeOptions();
			cOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(cOptions);
			break;
		default:
			throw new NoValidBrowserException(browser.toString());
	}
		
		wait = new WebDriverWait(driver,max_wait);
	}
	
	//Getters y Setters
	public WebDriver getDriver() {
		return this.driver;
	}
	
	//Browser API
	public void maximizeWindows() {
		this.driver.manage().window().maximize();
	}
	
	public void back() {
		this.driver.navigate().back();
	}
	
	public void forward() {
		this.driver.navigate().forward();
	}
	
	public void refresh() {
		this.driver.navigate().refresh();
	}
	
	public void goTo(String url) {
		this.driver.get(url);
	}
	
	public void closeBrowser() {
		this.driver.quit();
	}
	
	public void closeCurrentTab() {
		this.driver.close();
	}
	
	public String getCurrentUrl() {
		return this.driver.getCurrentUrl();
	}
	
	//Elements API
	public UIElement find(By by) {
		return this.waitUntilPresent(by);
	}
	
	public UIElement findChild(By parent, By child) {
		return this.wait(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, child));
	}
		
	private UIElement waitUntilPresent(By by) {
		return this.wait(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	private UIElement wait(ExpectedCondition<WebElement> conditions) {
		return new UIElement(this, this.wait.until(conditions));
	}
	
	public UIElement waitUntilVisible(By by) {
		return this.wait(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public UIElement waitUntilClickable(By by) {
		return this.wait(ExpectedConditions.elementToBeClickable(by));
	}
	
	//Cookies API
	public void deleteAllCookies() {
		this.driver.manage().deleteAllCookies();
	}
	
	public Set<Cookie> getAllCookies() {
		return this.driver.manage().getCookies();
	}
	
	public Cookie getCookie(String cookie) {
		return this.driver.manage().getCookieNamed(cookie);
	}
	
	public void addCookie(Cookie cookie) {
		this.driver.manage().addCookie(cookie);
	}
}
