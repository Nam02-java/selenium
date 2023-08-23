package New.Ecosystem;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.apache.tomcat.jni.SSL.getSessionId;

public class Main_New_Ecosystem {

    private static String URL_WEBSITE = "https://ttsfree.com/#google_vignette";
    private static WebDriver driver;
    private static ChromeOptions options;

    public static void main(String[] args) throws InterruptedException, MalformedURLException, AWTException {
//        System.setProperty("java.awt.headless", "false"); // important line , do not delete this
//        System.setProperty("webdriver.chrome.silentOutput", "true");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pc\\Downloads\\chromedriver.exe");
        //  ChromeOptions options = new ChromeOptions();
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        //  options.addArguments("--headless");
        //  options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});


        //   = new ChromeDriver(options);


        openBrowerOnPage();

       // driver.get(URL_WEBSITE);



        Thread.sleep(20000);
        driver.findElement(By.xpath("//*[@id=\"input_text\"]")).sendKeys("test");

        System.out.println(driver.getTitle());

        Thread.sleep(10000000);
        driver.quit();

    }

    private static boolean openBrowerOnPage() {
        try {
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            RobotHabile robot = new RobotHabile();
            robot.start();
            driver.get(URL_WEBSITE);
            while (robot.isAlive()){

            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Erreur : Impossible de se connecter/acceder au service '"  + "'");
        }
        return false;
    }
}
