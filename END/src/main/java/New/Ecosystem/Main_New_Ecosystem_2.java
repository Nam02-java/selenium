package New.Ecosystem;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.apache.tomcat.jni.SSL.getSessionId;

public class Main_New_Ecosystem_2 {

    private static String URL_WEBSITE = "https://ttsfree.com/#google_vignette";

    public static void main(String[] args) throws InterruptedException, MalformedURLException, AWTException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pc\\Downloads\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("debuggerAddress", "localhost:9222");

        System.out.println("1");
        WebDriver driver = new ChromeDriver(options);
        System.out.println("2");

        driver.get(URL_WEBSITE);


        //   Thread.sleep(20000);

        //   driver.findElement(By.xpath("//*[@id=\"input_text\"]")).sendKeys("test");
//
//        System.out.println(driver.getTitle());
//
//        Thread.sleep(10000000);
//        driver.quit();

    }
//    @BeforeMethod
//    public void setupDriver() {
//        System.setProperty("webdriver.http.factory", "jdk-http-client");
//        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
//        // Tạo đối tượng ChromeOptions class để thiết đặt nâng cao
//        ChromeOptions options = new ChromeOptions();
//        // Chuyển địa chỉ trình gỡ lỗi đến port đúng với máy chủ đã bật (9222)
//        // Do mình đang chạy thử nghiệm trên local nên sử dụng localhost
//        options.setExperimentalOption("debuggerAddress", "localhost:9222");
//
//        driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//    }

}

//        System.setProperty("java.awt.headless", "false"); // important line , do not delete this
//        System.setProperty("webdriver.chrome.silentOutput", "true");

//  options.addArguments("--headless");
//  options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
/**
 * Phiên bản 115.0.5790.173 (Phiên bản Chính thức) (64 bit)
 * C:\Program Files\Google\Chrome\Application>chrome.exe --remote-debugging-port=9988 --user-data-dir=C:\Users\Pc\Downloads\chromedata
 */
