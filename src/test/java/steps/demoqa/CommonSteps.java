package steps.demoqa;

import api_assured.ApiUtilities;
import driver.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonSteps extends ApiUtilities {

    Date currentDate = new Date();
    String screenShotFileName = currentDate.toString().replace(" ","-").replace(":","-");


    @After
    public void after(Scenario scenario) {
       try {
           if (scenario.isFailed()) {
               Exception exception = new RuntimeException();
               log.error(scenario.getName() + " is failed!", exception);
               Driver.srcFile = ((TakesScreenshot)Driver.driver).getScreenshotAs(OutputType.FILE);
               FileUtils.copyFile(Driver.srcFile, new File("src/test/resources/screenshots"
                       +screenShotFileName + ".png"));
               log.error("Taking screenshot!", exception);
           }
           else log.success(scenario.getName() + " passed!");
       } catch (Exception exception){
           exception.printStackTrace();
       }
        Driver.terminate();
    }

    @Given("Navigate to {}")
    public void navigate(String url){
        Driver.setup();
        Driver.driver.get(url);
        log.info("Navigating to " +url);
    }

    @Given("Wait for {} seconds")
    public void waitForSeconds(double seconds){
        log.info("Waiting for " +seconds + " seconds");
        try {
            TimeUnit.MILLISECONDS.sleep((long) seconds);
        } catch (InterruptedException ignored) {}
    }

}
