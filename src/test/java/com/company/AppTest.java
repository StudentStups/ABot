package com.company;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void test1(){
        System.setProperty("webdriver.gecko.driver", "D:\\Brackets\\geckodriver.exe");
        ProfilesIni allProfiles = new ProfilesIni();
        System.setProperty("webdriver.firefox.profile","default");
        String browserProfile = System.getProperty("webdriver.firefox.profile");
        FirefoxProfile profile = allProfiles.getProfile(browserProfile);
        profile.setAcceptUntrustedCertificates(true);
        WebDriver driver = new FirefoxDriver(profile);
        driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

        driver.get("https://www.amazon.com/");
        WebElement searchField = driver.findElement(By.id("twotabsearchtextbox"));
        searchField.sendKeys("toys");
        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
            driver.quit();
        }
        driver.getCurrentUrl();
        WebElement serchBtn = driver.findElement(By.id("nav-search-submit-text"));
        serchBtn.click();

        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
            driver.quit();
        }
        driver.getCurrentUrl();

        WebElement nextPage = driver.findElement(By.id("pagnNextString"));
        nextPage.click();

        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
            driver.quit();
        }
        String myUrl = driver.getCurrentUrl();
        String newUrl = myUrl.replace("page=2","page=5");
        driver.get(newUrl);
        driver.getCurrentUrl();


        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
            driver.quit();
        }

        WebElement target = driver.findElement(By.id("result_105"));
        WebElement cont = target.findElement(By.className("s-item-container"));
        WebElement targetCont = cont.findElement(By.className("a-row a-spacing-base"));
        WebElement base = targetCont.findElement(By.className("a-column a-span12 a-text-left"));
        base.click();
        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
            driver.quit();
        }
        driver.getCurrentUrl();

        driver.quit();
    }


}
