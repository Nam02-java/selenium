package New.Ecosystem;

import com.bezkoder.springjwt.SpringBootSecurityJwtApplication;
import org.apache.coyote.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;


public class Main_New_Ecosystem_3 {

    private static String URL_WEBSITE = "https://ttsfree.com/#google_vignette";

    private static String male_voice = "//*[@id=\"voice_name_bin\"]/div[2]/label";

    private static String female_voice = "//*[@id=\"voice_name_bin\"]/div[1]/label";

    public static void main(String[] args) throws InterruptedException {
        Main_New_Ecosystem_3.demo();
    }

    public static void demo() throws InterruptedException {

        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pc\\Downloads\\chromedriver-win64\\chromedriver.exe");
        // Tạo đối tượng ChromeOptions class để thiết đặt nâng cao
        ChromeOptions options = new ChromeOptions();
        // Chuyển địa chỉ trình gỡ lỗi đến port đúng với máy chủ đã bật (9222)
        // Do mình đang chạy thử nghiệm trên local nên sử dụng localhost
        options.setExperimentalOption("debuggerAddress", "localhost:9222");


        WebDriver driver = new ChromeDriver(options);

        //  driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        //driver.get(URL_WEBSITE);

        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"input_text\"]")).sendKeys("alo 123");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).click();

        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("138");

        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(Keys.ENTER);

        Thread.sleep(2000);
        driver.findElement(By.xpath(male_voice)).click();

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"frm_tts\"]/div[2]/div[2]/div[1]/a")).click();

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"progessResults\"]/div[2]/center[1]/div/a")).click();
    }
}
//C:\Program Files\Google\Chrome\Application
//chrome.exe --remote-debugging-port=9222 --user-data-dir="C:\Users\Pc\Downloads\chromedriver-win64\chromedata"

class Mainnn {
    public static void main(String[] args) throws IOException {
        //     Desktop.getDesktop().open(new File("C:\\Users\\Pc\\Downloads"));
        getLastModified("C:\\Users\\Pc\\Downloads");
    }

    public static File getLastModified(String directoryFilePath) {
        File directory = new File(directoryFilePath);
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null) {
            for (File file : files) {
                if (file.lastModified() > lastModifiedTime) {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }

        System.out.println(chosenFile);
        return chosenFile;
    }
}