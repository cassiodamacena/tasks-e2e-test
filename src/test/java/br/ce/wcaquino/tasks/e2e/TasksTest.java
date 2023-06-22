package br.ce.wcaquino.tasks.e2e;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessarAplicacao(){
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8001/tasks/");
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    @Test
    public void deveCadastrarTarefaComSucesso(){
        WebDriver driver = acessarAplicacao();
        try{
            driver.findElement(By.id("addTodo")).click();
            driver.findElement(By.id("task")).sendKeys("Teste via selenium");
            driver.findElement(By.id("dueDate")).sendKeys(String.valueOf(LocalDate.now()));
            driver.findElement(By.id("saveButton")).click();
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Succes!", mensagem);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void naoDeveCadastrarTarefaSemDescricao(){
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
    public void naoDeveCadastrarTarefaSemData(){
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
    public void naoDeveCadastrarTarefaComDataPassada(){
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
