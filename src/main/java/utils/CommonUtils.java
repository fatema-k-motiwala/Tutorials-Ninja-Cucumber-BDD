package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class CommonUtils {
	
	public static final int MIN_TIME = 3;
	public static final int AVERAGE_TIME = 10;
	public static final int MAX_TIME = 30;

	public static String generateEmailWithNanoTime() {
		long nanoTime = System.nanoTime();
		return "user_" + nanoTime + "@example.com";
	}
	
	public static Properties loadPropertiesFile() {
		Properties prop = new Properties();
		FileReader fr = null;
		try {
			fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\ProjectConfig.properties");
			prop.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	return prop;
	}
	
	public static Properties storePropertiesFile(Properties prop) {
	    String path = System.getProperty("user.dir") + "\\src\\test\\resources\\config\\ProjectConfig.properties";
	    try (FileWriter fw = new FileWriter(path)) {
	        prop.store(fw, "Updated Properties file");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return prop;
	}


	public static boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) throws IOException {

		BufferedImage bufferedActualImage = ImageIO.read(new File(actualImagePath));
		BufferedImage bufferedExpectedImage = ImageIO.read(new File(expectedImagePath));
		ImageDiffer differ = new ImageDiffer();
		ImageDiff imageDiff = differ.makeDiff(bufferedExpectedImage, bufferedActualImage);
		return imageDiff.hasDiff();
	}

	public static void takeScreenshot(WebDriver driver, String screenshotPath) {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);

		try {
			FileHandler.copy(srcScreenshot, new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
