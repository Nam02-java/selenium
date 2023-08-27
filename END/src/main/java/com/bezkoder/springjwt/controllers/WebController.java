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
    public void test(@RequestParam Map<String, String> params) throws InterruptedException {
        String text = params.get("Text");
        String voice = params.get("Voice");
        fileName = params.get("FileName");

        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pc\\Downloads\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "localhost:9222");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        //khởi tạo thao tác với lăn chuột
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement Element = driver.findElement(By.xpath("//*[@id=\"input_text\"]"));

        js.executeScript("arguments[0].scrollIntoView();", Element);

        driver.findElement(By.xpath("//*[@id=\"input_text\"]")).clear();

        waitForElementToSendKeys(10, "//*[@id=\"input_text\"]", text);

        if (driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).getText().equals(xpath_vietnameseToText)) {
        } else {
            driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).click();
            waitForElementToSendKeys(10, "/html/body/span/span/span[1]/input", Vietnamese);
            driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(Keys.ENTER);
        }

        if (voice.equals("Male")) {
            driver.findElement(By.xpath(male_voice)).click();
        } else if (voice.equals("Female")) {
            driver.findElement(By.xpath(female_voice)).click();
        }

        driver.findElement(By.xpath("//*[@id=\"frm_tts\"]/div[2]/div[2]/div[1]/a")).click();

        js.executeScript("arguments[0].scrollIntoView();", Element); // thi thoảng web sẽ tự kéo xuống sau khi convert giọng nói nên dòng này sẽ có nhiệm vụ lăn chuột về nơi nút dowload hiển thị
        waitForElementUnstable(5, 30, "//*[@id=\"progessResults\"]/div[2]/center[1]/div/a");

        Thread.sleep(2000);
        getLastModified("C:\\test\\");
        convert_file_name();
    }


    public void waitForElementToSendKeys(int seconds, String waitConditionLocator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(waitConditionLocator))).sendKeys(text);
    }

    public void waitForElementUnstable(int timeOut, int pollingEvery, String xpath) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut)).pollingEvery(Duration.ofSeconds(pollingEvery)).ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }


    public void waitForElementToClick(int seconds, String waitConditionLocator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(waitConditionLocator))).click();
    }

    public static File getLastModified(String directoryFilePath) {
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
        return chosenFile;
    }

    public static void convert_file_name() {
        String file_path = "C:\\test\\";
        File rename = new File(file_path + fileName + ".mp3");

        boolean flag = chosenFile.renameTo(rename);


        if (flag == true) {
            System.out.println("File Successfully Rename");
        } else {
            System.out.println("Operation Failed");
        }
    }
}


//////////
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/api/web")
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
    public void ttsfreeAPI(@RequestParam Map<String, String> params) throws InterruptedException, IOException {
        String text = params.get("Text");
        String voice = params.get("Voice");
        fileName = params.get("FileName");

        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Public\\Videos\\Dowload\\ChromeDriverWork\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "localhost:9222");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        //khởi tạo thao tác với lăn chuột
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement Element = driver.findElement(By.xpath("//*[@id=\"input_text\"]"));

        js.executeScript("arguments[0].scrollIntoView();", Element);

        driver.findElement(By.xpath("//*[@id=\"input_text\"]")).clear();

        waitForElementToSendKeys(10, "//*[@id=\"input_text\"]", text);

        if (driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).getText().equals(xpath_vietnameseToText)) {
        } else {
            driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).click();
            waitForElementToSendKeys(10, "/html/body/span/span/span[1]/input", Vietnamese);
            driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(Keys.ENTER);
        }

        if (voice.equals("Male")) {
            driver.findElement(By.xpath(male_voice)).click();
        } else if (voice.equals("Female")) {
            driver.findElement(By.xpath(female_voice)).click();
        }

        driver.findElement(By.xpath("//*[@id=\"frm_tts\"]/div[2]/div[2]/div[1]/a")).click();

        js.executeScript("arguments[0].scrollIntoView();", Element); // thi thoảng web sẽ tự kéo xuống sau khi convert giọng nói nên dòng này sẽ có nhiệm vụ lăn chuột về nơi nút dowload hiển thị
        //waitForElementUnstable(5, 30, "//*[@id=\"progessResults\"]/div[2]/center[1]/div/a");
        Thread.sleep(8000);
        System.out.println("1");
        driver.findElement(By.xpath("//*[@id=\"progessResults\"]/div[2]/center[1]/div/a"));
        System.out.println("2");

        //  Thread.sleep(6000);
        // getLastModified("C:\\Users\\Admin-DL\\Downloads");
        // Thread.sleep(3000);
        // Files.move(Paths.get(String.valueOf(chosenFile)), Paths.get("C:\\Users\\Public\\Videos\\TestDowload\\New folder2\\" + fileName + ".mp3"), StandardCopyOption.REPLACE_EXISTING);

    }



    @GetMapping("/testDowload")
    public void testDowloadAPI(@RequestParam Map<String, String> params) throws InterruptedException, IOException {
        String text = params.get("Text");
        String voice = params.get("Voice");
        fileName = params.get("FileName");

        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Public\\Videos\\Dowload\\ChromeDriverWork\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "localhost:9222");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement Element = driver.findElement(By.xpath("//*[@id=\"input_text\"]"));

        js.executeScript("arguments[0].scrollIntoView();", Element);

        //waitForElementUnstable(5, 30, "/html/body/section[2]/div[2]/form/div[2]/div[2]/div[1]/div[4]/div[2]/center[1]/div/a");

        Thread.sleep(4000);
        System.out.println("1");
        driver.findElement(By.xpath("//*[@id=\"frm_tts\"]/div[2]/div[2]/div[1]/a")).click();
        System.out.println("2");
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"progessResults\"]/div[2]/center[1]/div/a"));
        System.out.println("3");
    }


    public void waitForElementToSendKeys(int seconds, String waitConditionLocator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(waitConditionLocator))).sendKeys(text);
    }

    public void waitForElementUnstable(int timeOut, int pollingEvery, String xpath) {
        System.out.println("1");
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut)).pollingEvery(Duration.ofSeconds(pollingEvery)).ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        System.out.println("2");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        System.out.println("3");


    }


    public void waitForElementToClick(int seconds, String waitConditionLocator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(waitConditionLocator))).click();
    }

    public static File getLastModified(String directoryFilePath) {
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

    //C:\Users\Public\Videos\TestDowload
    public static void main(String[] args) throws IOException, InterruptedException {
        /**
         * 1
         */
        String rename = "hihi";
        //getLastModified("C:\\Users\\Admin-DL\\Downloads");
        //  convert_file_name(rename);
        getLastModified("C:\\Users\\Admin-DL\\Downloads");
        Thread.sleep(2000);
        Files.move(Paths.get(String.valueOf(chosenFile)), Paths.get("C:\\Users\\Public\\Videos\\TestDowload\\New folder2\\" + rename + ".mp3"), StandardCopyOption.REPLACE_EXISTING);
    }
}

class test {
    public static void main(String[] args) {


    }
}

//C:\Program Files\Google\Chrome\Application
//chrome.exe --remote-debugging-port=9222 --user-data-dir="C:\Users\Public\Videos\Dowload\ChromeDriverWork\chromedriver-win64\chromedata"
