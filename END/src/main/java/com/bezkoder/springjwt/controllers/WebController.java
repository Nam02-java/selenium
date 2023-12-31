package com.bezkoder.springjwt.controllers;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.time.Duration;
import java.util.Map;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/web")
@PreAuthorize("hasRole('USER')")
public class WebController {

    private static String URL_WEBSITE = "https://ttsfree.com/#google_vignette";
    private static String male_voice = "//*[@id=\"voice_name_bin\"]/div[2]/label";
    private static String female_voice = "//*[@id=\"voice_name_bin\"]/div[1]/label";
    private static File chosenFile = null;
    private static String fileName;
    private static WebDriver driver;

    private static String Vietnamese = "138";

    private static String xpath_vietnameseToText = "138. Vietnamese (Vietnam) - VN";

     @GetMapping("/ttsfree")
    public ResponseEntity<?> ttsfreeAPI(@RequestParam Map<String, String> params) throws InterruptedException, IOException {
        String text = params.get("Text");
        String voice = params.get("Voice");
        fileName = params.get("FileName");

        File directory = new File("C:\\Voice\\");
        File[] files = directory.listFiles(File::isFile);

        for (int i = 0; i < files.length; i++) {
            String name = files[i].getName();
            String target=name.copyValueOf(".mp3".toCharArray());
            name=name.replace(target, "");
            if (name.equals(fileName)) {
                return ResponseEntity.ok(new String("The file name is duplicate , please change the name"));
            }
        }


        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pc\\Downloads\\chromedriver-win64\\chromedriver.exe");

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", "C:\\Voice\\");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "localhost:9222");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        //khởi tạo thao tác với lăn chuột
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement Element = driver.findElement(By.xpath("//*[@id=\"input_text\"]"));

        js.executeScript("arguments[0].scrollIntoView();", Element);

        driver.findElement(By.xpath("//*[@id=\"input_text\"]")).clear();

        driver.findElement(By.xpath("//*[@id=\"input_text\"]")).sendKeys(text);

        if (driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).getText().equals(xpath_vietnameseToText)) {
        } else {
            driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).click();
            waitForElementToSendKeys(10, "/html/body/span/span/span[1]/input", Vietnamese);
            driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(Keys.ENTER);
        }

        if (voice.equals("Male")) {
            waitForElementToClick(10, male_voice);
        } else if (voice.equals("Female")) {
            waitForElementToClick(10, female_voice);
        }

        driver.findElement(By.xpath("//*[@id=\"frm_tts\"]/div[2]/div[2]/div[1]/a")).click();

        js.executeScript("arguments[0].scrollIntoView();", Element); // thi thoảng web sẽ tự kéo xuống sau khi convert giọng nói nên dòng này sẽ có nhiệm vụ lăn chuột về nơi nút dowload hiển thị

        waitForElementUnstable(5, 30, "//*[@id=\"progessResults\"]/div[2]/center[1]/div/a");

        getLastModified("C:\\Users\\Pc\\Downloads\\");
        Files.move(Paths.get(String.valueOf(chosenFile)), Paths.get("C:\\Voice\\" + fileName + ".mp3"), StandardCopyOption.REPLACE_EXISTING);

        return ResponseEntity.ok(new String("Successfully"));
    }

    public void waitForElementDownload_Button(int seconds, String waitConditionLocator_Before) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }


    public void waitForElementToSendKeys(int seconds, String waitConditionLocator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(waitConditionLocator))).sendKeys(text);
    }

    public void waitForElementUnstable(int timeOut, int pollingEvery, String xpath) throws InterruptedException {
        Thread.sleep(2500);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut)).pollingEvery(Duration.ofSeconds(pollingEvery)).ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
    }

    public void waitForElementToClick(int seconds, String waitConditionLocator) {
        check = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(waitConditionLocator))).click();
        check = true;
    }

    public static File getLastModified(String directoryFilePath) throws InterruptedException {
        Thread.sleep(2000);
        File directory = new File(directoryFilePath);
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        chosenFile = null;

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

    public static void convert_file_name(String fileName) {
        String file_path = "C:\\Users\\Admin-DL\\Downloads\\";
        File rename = new File(file_path + fileName + ".mp3");

        boolean flag = chosenFile.renameTo(rename);


        if (flag == true) {
            System.out.println("File Successfully Rename");
        } else {
            System.out.println("Operation Failed");
        }
    }
}


