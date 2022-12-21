package com.version1.screenshots;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import cucumber.api.Scenario;
import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
public class CaptureScreenshots extends TestListenerAdapter {
	
	public static void cucumberScreenshots(Scenario scenario,EventFiringWebDriver webDriver,Logger LOG,String testId){
	        Map<String, Object> screenShots = ScreenshotsHelper.getScreenShotsForCurrentTest();
	        for (Map.Entry<String, Object> screenShot : screenShots.entrySet()) {
	             scenario.write(screenShot.getKey());
	             scenario.embed((byte[]) screenShot.getValue(), "image/png");
	        }
	        ScreenshotsHelper.tidyUpAfterTestRun();
	        if (scenario.isFailed()) {
	             scenario.write(webDriver.getCurrentUrl());
	             byte[] screenShot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
	             scenario.embed(screenShot, "image/png");
	        }
	}
 
	public static void testNGScreenshots(ITestResult result,EventFiringWebDriver webDriver,Logger LOG) {
		    Calendar calendar = Calendar.getInstance();
	        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	        String methodName = result.getMethod().getMethodName();
	        if(!result.isSuccess()){
	             File scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
	             try {
	                 String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports";
	                 File destFile = new File((String) reportDirectory+"/failure_screenshots/"+methodName+"_"+formater.format(calendar.getTime())+".png");
	                 FileUtils.copyFile(scrFile, destFile);
	                 LOG.debug("<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
	             } catch (IOException e) {
	                 e.printStackTrace();
	             }
	        }
	}
}
