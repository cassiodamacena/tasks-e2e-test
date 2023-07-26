package br.ce.wcaquino.tasks.e2e;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

public class TasksTest {

    public WebDriver acessarAplicacao() throws MalformedURLException {
        WebDriver driver = new ChromeDriver();
        //ChromeOptions chromeOptions = new ChromeOptions();
        //WebDriver driver = new RemoteWebDriver(new URL("http://192.168.56.1:4444/wd/hub"), chromeOptions);

        driver.navigate().to("http://localhost:8001/tasks/");
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }


    @Test
    public void deveCadastrarTarefaComSucesso() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();
        try{
            driver.findElement(By.id("addTodo")).click();
            driver.findElement(By.id("task")).sendKeys("Teste via selenium");
            driver.findElement(By.id("dueDate")).sendKeys("31/12/2050");
            driver.findElement(By.id("saveButton")).click();
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", mensagem);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void naoDeveCadastrarTarefaSemDescricao() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();
        try{
            driver.findElement(By.id("addTodo")).click();
            driver.findElement(By.id("dueDate")).sendKeys("31/12/2023");
            driver.findElement(By.id("saveButton")).click();
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", mensagem);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void naoDeveCadastrarTarefaSemData() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();
        try{
            driver.findElement(By.id("addTodo")).click();
            driver.findElement(By.id("task")).sendKeys("Teste via selenium");
            driver.findElement(By.id("saveButton")).click();
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date", mensagem);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void naoDeveCadastrarTarefaComDataPassada() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();
        try{
            driver.findElement(By.id("addTodo")).click();
            driver.findElement(By.id("task")).sendKeys("Teste via selenium");
            driver.findElement(By.id("dueDate")).sendKeys("20/06/2023");
            driver.findElement(By.id("saveButton")).click();
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", mensagem);
        } finally {
            driver.quit();
        }
    }


}
