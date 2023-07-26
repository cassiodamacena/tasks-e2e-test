package br.ce.wcaquino.tasks.prod;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class HealthCheckIT {

    @Test
    public void healthCheck() throws MalformedURLException {
        WebDriver driver = new ChromeDriver();

        try{
            driver.navigate().to("http://192.168.1.100:9999/tasks/");
            String version = driver.findElement(By.id("version")).getText();
            Assert.assertTrue(version.startsWith("build_"));
        }finally {
            driver.quit();
        }
    }

}
