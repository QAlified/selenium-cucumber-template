package automation;
import static org.testng.Assert.assertFalse;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import config.Config;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.CoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

public class VisualTesting {
	
	private WebDriver driver;
	private AShot ashot;
	
	private String baselineFolder = Config.SCREENSHOT_PATH + "baselines" + File.separator;
	private String checkpointFolder = Config.SCREENSHOT_PATH + "checkpoints" + File.separator;
	private String compFolder = Config.SCREENSHOT_PATH + "comparisons" + File.separator;
	
	
	public VisualTesting(WebDriver driver) {
		this.driver = driver;
		this.ashot = new AShot().shootingStrategy(ShootingStrategies.scaling(Config.SCALING_FACTOR));
	}
	
	public VisualTesting(WebDriver driver, ShootingStrategy s, CoordsProvider cp) {
		this.driver = driver;
		this.ashot = new AShot()
				.shootingStrategy(s)
				.coordsProvider(cp);
	}
	
	public void setShootingStrategy(ShootingStrategy s) {
		this.ashot.shootingStrategy(s);
	}
	
	public void setCoordsProvider(CoordsProvider cp) {
		this.ashot.coordsProvider(cp);
	}
	
	
	public Screenshot takeElementScreenshot(WebElement e) {
		return this.ashot.takeScreenshot(this.driver, e);
	}
	
	public Screenshot takeScreenshot() {
		return this.ashot.takeScreenshot(this.driver);
	}
	
	
	public void addIgnoredElement(By ignoredElem) {
			this.ashot.addIgnoredElement(ignoredElem);
		}
	
	
	public void addIgnoredElements(Set<By> ignoredElems) {
			for (By elem:ignoredElems) {
				this.ashot.addIgnoredElement(elem);
			}
		}

	
	
	public void saveScreenshotAsBaseline(Screenshot s, String name) {
		try {
			ImageIO.write(s.getImage(), "PNG", new File(baselineFolder + name));
		} catch (IOException e) {
			System.out.println("Unable to set Baseline screenshot with name " + name);
		}
	}
	
	
	public void saveScreenshotAsCheckpoint(Screenshot s, String name) {
		try {
			ImageIO.write(s.getImage(), "PNG", new File(checkpointFolder + name));
		} catch (IOException e) {
			System.out.println("Unable to set screenshot with name " + name);
		}
	}
	
	public void Capture(String newBase,ExtentTest test,String name) {
		Screenshot s = this.takeScreenshot();
		if (newBase.contentEquals("true") || (loadBaseline(name)==null) ) {
			saveScreenshotAsBaseline(s,name);
			test.log(Status.PASS, "The base is created...");
		}
		else {
			saveScreenshotAsCheckpoint(s, name);
			assertFalse(checkScreenshots(loadBaseline(name),loadCheckpoint(name),name,test));
			test.log(Status.PASS, "Checkpoints and comparisons are created!");
		}
	}
	
	public void Capture(String newBase,ExtentTest test,String name,By elem) {
		Screenshot s;
		if (newBase.contentEquals("true") || (loadBaseline(name)==null) ) {
			s = takeScreenshot();
			saveScreenshotAsBaseline(s,name);
			test.log(Status.PASS, "The base is created...");
		}
		else {
			addIgnoredElement(elem);
			s = takeScreenshot();
			assertFalse(checkScreenshots(loadBaseline(name),s,name,test));
			saveScreenshotAsCheckpoint(s, name);
			test.log(Status.PASS, "Checkpoints and comparisons are created!");
		}
	}
	
	public void Capture(String newBase,ExtentTest test,String name,Set<By> elems) {
		Screenshot s;
		if (newBase.contentEquals("true") || (loadBaseline(name)==null) ) {
			s = takeScreenshot();
			saveScreenshotAsBaseline(s,name);
			test.log(Status.PASS, "The base is created...");
		}
		else {
			addIgnoredElements(elems);
			s = takeScreenshot();
			checkScreenshots(loadBaseline(name),s,name,test);
			saveScreenshotAsCheckpoint(s, name);
			test.log(Status.PASS, "Checkpoints and comparisons are created!");
		}
	}
	
	public void CaptureElem(String newBase,WebElement elem,ExtentTest test,String name,By ignored) { 
		Screenshot s;
		if (newBase.contentEquals("true")) {
			s = takeElementScreenshot(elem);
			saveScreenshotAsBaseline(s,name);
			test.log(Status.PASS, "Base was overwritten!");
		}
		else {
			addIgnoredElement(ignored);
			s = takeElementScreenshot(elem);
			assertFalse(checkScreenshots(loadBaseline(name),s,name,test));
			saveScreenshotAsCheckpoint(s,name);
			test.log(Status.PASS, "Checkpoints and comparisons are created!");
		}
	}
	public void visibleElement(By elem) {
		
		 JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	     WebElement element = driver.findElement(elem);
	     jsExecutor.executeScript("arguments[0].style.visibility=''", element);
	}
	
	public void hiddenElement(By elem) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement ignore = this.driver.findElement(elem);
       jsExecutor.executeScript("arguments[0].style.visibility='hidden'", ignore);
	}
	public void CaptureElem(String newBase,WebElement elem,ExtentTest test,String name,Set<By> ignored) { 
		Screenshot s;
		if (newBase.contentEquals("true")) {
			s = takeElementScreenshot(elem);
			saveScreenshotAsBaseline(s,name);
			test.log(Status.PASS, "Base was overwritten!");
		}
		else {
			addIgnoredElements(ignored);
			s = takeElementScreenshot(elem);
			assertFalse(checkScreenshots(loadBaseline(name),s,name,test));
			saveScreenshotAsCheckpoint(s,name);
			test.log(Status.PASS, "Checkpoints and comparisons are created!");
		}
	}
	
	public void CaptureElem(String newBase,WebElement elem,ExtentTest test,String name) {
		Screenshot s = this.takeElementScreenshot(elem);
		if (newBase.contentEquals("true")) {
			saveScreenshotAsBaseline(s,name);
			test.log(Status.PASS, "Base was overwritten!");
		}
		else {
			saveScreenshotAsCheckpoint(s,name);
			assertFalse(checkScreenshots(loadBaseline(name),s,name,test));
			test.log(Status.PASS, "Checkpoints and comparisons are created!");
		}
	}
	
	
	public boolean checkScreenshots(Screenshot baseline, Screenshot checkpoint, String compName,ExtentTest test) {
		System.out.println("Ignored Elements :" + checkpoint.getIgnoredAreas().toString());
		baseline.setIgnoredAreas(checkpoint.getIgnoredAreas()); 
		baseline.setCoordsToCompare(checkpoint.getCoordsToCompare()); 
		
		ImageDiff diff = new ImageDiffer().makeDiff(baseline, checkpoint);
		if (diff.hasDiff()) {
			test.log(Status.INFO, "there are differences");
			try {
				ImageIO.write(diff.getMarkedImage(), "PNG", new File(compFolder + compName));
				test.log(Status.FAIL, 
	                    "The base image is different to the checkpoint image"
	            ).fail("Expected image:")
	            .fail(MediaEntityBuilder.createScreenCaptureFromPath(baselineFolder+compName).build())
	            .fail("Image obtained:")
	            .fail(MediaEntityBuilder.createScreenCaptureFromPath(checkpointFolder+compName).build())
	            .fail("differences:")
	            .fail(MediaEntityBuilder.createScreenCaptureFromPath(compFolder+compName).build());
				return true;
			} catch (IOException e) {
				System.out.println("Unable to write Diff image with name " + compName);
				return true;
			}
		} else {
			test.log(Status.INFO, "there aren't differences");
			return false;
		}
	}
	
	public Screenshot loadBaseline(String name) {
		try {
			return new Screenshot(ImageIO.read(new File(baselineFolder + name)));
		} catch (IOException e) {
			System.out.println("Unable to load baseline image with name " + name);
		}
		return null;
	}
	
	public Screenshot loadCheckpoint(String name) {
		try {
			return new Screenshot(ImageIO.read(new File(checkpointFolder + name)));
		} catch (IOException e) {
			System.out.println("Unable to load checkpoint image with name " + name);
		}
		return null;
	}
	

}