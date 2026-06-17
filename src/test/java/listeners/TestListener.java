package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTests;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result)
    {
        Object currentClass = result.getInstance();
        WebDriver driver =((BaseTests)currentClass).getDriver();
        ScreenshotUtils.captureScreenshot(driver, result.getName());
    }
}
