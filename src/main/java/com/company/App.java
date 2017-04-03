package com.company;

import com.google.common.primitives.Chars;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;


public class App {


    public static void main( String[] args ) {
        String someUrl = searchedURL();
        String linkForPage = getLinkForPage(someUrl,5);
        String itemLink = getItemLink(linkForPage);

    }

    public static String getLinkForPage(String someURL, int page){
        String correctPageLink="";
        char[] symbols = someURL.toCharArray();
        ArrayList<String> symbs = new ArrayList<String>();
        for(int i = 0; i < symbols.length; i++){
            symbs.add(String.valueOf(symbols[i]));
        }
        int indexForChange=-1;
        boolean pageNotChanged = true;
        for(int i = 0; i < symbs.size(); i++){
            if(symbs.get(i).equals("=")&&(symbs.get(i-1).equals("e")&&(symbs.get(i-2).equals("g")&&(symbs.get(i-3).equals("a")&&(symbs.get(i-4).equals("p")))))){
               indexForChange = i+1;

            }
            if(indexForChange == i && pageNotChanged) {
                symbs.remove(i);
                i--;
                if(symbs.get(i+1).equals("&")){

                    pageNotChanged = false;
                }

                continue;

            }

            correctPageLink =  correctPageLink+ String.valueOf(page);
        }

        return correctPageLink;
    }

    public static String searchedURL(){
        String searchedurl="";

        WebDriver driver = getWebDriver();

        driver.get("https://www.amazon.com/");

        WebElement searchField = driver.findElement(By.id("twotabsearchtextbox"));

        searchField.sendKeys("toys");

        Sleep(driver);

        driver.getCurrentUrl();

        WebElement serchBtn = driver.findElement(By.id("nav-search-submit-text"));

        serchBtn.click();

        Sleep(driver);

        driver.getCurrentUrl();

        WebElement nextPage = driver.findElement(By.id("pagnNextString"));

        nextPage.click();

        Sleep(driver);

        searchedurl=driver.getCurrentUrl();

        driver.quit();
        return searchedurl;
    }

    public static WebDriver getWebDriver() {
        System.setProperty("webdriver.gecko.driver", "D:\\Brackets\\geckodriver.exe");
        ProfilesIni allProfiles = new ProfilesIni();
        System.setProperty("webdriver.firefox.profile","default");
        String browserProfile = System.getProperty("webdriver.firefox.profile");
        FirefoxProfile profile = allProfiles.getProfile(browserProfile);
        profile.setAcceptUntrustedCertificates(true);
        WebDriver driver = new FirefoxDriver(profile);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        return driver;
    }

    public static void Sleep(WebDriver driver) {
        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
            driver.quit();
        }
    }

    public static String getItemLink(String searchLink){
        String itemLink="";
        WebDriver driver = getWebDriver();
        driver.get(searchLink);

        WebElement itemListElement = driver.findElement(By.id("s-results-list-atf"));
        List<WebElement> liElements = itemListElement.findElements(By.tagName("li"));

        WebElement foundElement = liElements.get(9);
        List<WebElement> aElements = foundElement.findElements(By.tagName("a"));
        itemLink = aElements.get(0).getAttribute("href");

        driver.quit();
        return itemLink;

    }




}
