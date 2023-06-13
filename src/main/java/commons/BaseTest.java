package commons;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;

	protected WebDriver getBrowserDriver(String browserName) {

		switch (browserName) {
		case "chrome":
			driver = WebDriverManager.chromedriver().create();
			break;
		case "firefox":
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case "edge":
			driver = WebDriverManager.edgedriver().create();
			break;
		case "opera":
			driver = WebDriverManager.operadriver().create();
			break;

		default:
			throw new RuntimeException("Please enter correct browser name!");
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(GlobalConstants.APP_URL);
		return driver;

	}

	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	protected void closeBrowserAndDriver() {
		String cmd = null;
		try {
			String osName = GlobalConstants.OS_NAME;
//			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
//			log.info("Driver instance name = " + driverInstanceName);

			String browserDriverName = null;

			if (driverInstanceName.contains("chrome")) {
				browserDriverName = "chromedriver";
			} else if (driverInstanceName.contains("firefox")) {
				browserDriverName = "geckodriver";
			} else if (driverInstanceName.contains("edge")) {
				browserDriverName = "msedgedriver";
			} else if (driverInstanceName.contains("opera")) {
				browserDriverName = "operadriver";
			} else {
				browserDriverName = "safaridriver";
			}

			if (osName.contains("window")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
			} else {
				cmd = "pkill " + browserDriverName;
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
//			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
