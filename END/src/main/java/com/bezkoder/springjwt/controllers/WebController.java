package com.bezkoder.springjwt.controllers;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

    @GetMapping("/ttsfree")
    private static void control_web(@RequestParam Map<String, String> params) throws InterruptedException {
        String text = params.get("Text");
        String voice = params.get("Voice");
        fileName = params.get("FileName");

        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pc\\Downloads\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "localhost:9222");

        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"input_text\"]")).clear();

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"input_text\"]")).sendKeys(text);

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).click();

        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("138");

        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(Keys.ENTER);

        if (voice.equals("Male")) {
            Thread.sleep(2000);
            driver.findElement(By.xpath(male_voice)).click();
        } else if (voice.equals("Female")) {
            Thread.sleep(2000);
            driver.findElement(By.xpath(female_voice)).click();
        }

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"frm_tts\"]/div[2]/div[2]/div[1]/a")).click();

        Thread.sleep(8000);

        driver.findElement(By.xpath("//*[@id=\"progessResults\"]/div[2]/center[1]/div/a")).click();

        Thread.sleep(2000);
        getLastModified("C:\\Users\\Pc\\Downloads");
        Thread.sleep(2000);
        convert_file_name();
        System.out.println("rename : " + chosenFile);
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
        File rename = new File("C:\\Users\\Pc\\Downloads\\" + fileName + ".mp3");

        boolean flag = chosenFile.renameTo(rename);


        if (flag == true) {
            System.out.println("File Successfully Rename");
        } else {
            System.out.println("Operation Failed");
        }
    }
}

