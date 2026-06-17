package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtils {

    public static void captureScreenshot(WebDriver driver, String testName) {

        File source =
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        File destination =
                new File("screenshots/" + testName + ".png");

        destination.getParentFile().mkdirs();

        try {
            Files.copy(
                    source.toPath(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
