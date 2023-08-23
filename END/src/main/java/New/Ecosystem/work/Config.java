package New.Ecosystem.work;

import com.bezkoder.springjwt.payload.response.MessageResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

@RestController
@RequestMapping("/api/web")
public class Config {
    private static String URL_WEBSITE = "https://ttsfree.com/#google_vignette";
    private static String male_voice = "//*[@id=\"voice_name_bin\"]/div[2]/label";
    private static String female_voice = "//*[@id=\"voice_name_bin\"]/div[1]/label";

    private static void control_web() throws InterruptedException {
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

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> test() throws IOException {
        return ResponseEntity.ok(new MessageResponse("Public api function successfully !"));
    }
}
