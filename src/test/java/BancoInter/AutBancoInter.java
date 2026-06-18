package BancoInter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.JOptionPane;

class AutBancoInter {

	WebDriver driver;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://inter.co/");
	}

	@AfterEach
	public void tearDown() throws Exception {
		if(driver != null) {
	        driver.quit();
	    }

	    JOptionPane.showMessageDialog(
	        null,
	        "O teste foi realizado e executado!",
	        "Automacao do Inter",
	        JOptionPane.WARNING_MESSAGE
	    );
	}

	@Test
	public void test() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#gatsby-focus-wrapper > header > div > nav > div.sc-dtnsVa.ibuRRv > div > div > span:nth-child(6) > button")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("name")).sendKeys("Pedro Silva");
		driver.findElement(By.id("phone")).sendKeys("(11) 94002-8922");
		driver.findElement(By.id("email")).sendKeys("asdf@yahoo.com");
		driver.findElement(By.id("socialId")).sendKeys("051.546.840-10");
		driver.findElement(By.id("dateOfBirth")).sendKeys("01/01/2001");
		driver.findElement(By.cssSelector("body > div.sc-dILkzW.jmczzn > div.sc-hUheUT.eReyjh > div > form > div.sc-jNDflC.RXypR > label")).click();
		driver.findElement(By.cssSelector("body > div.sc-dILkzW.jmczzn > div.sc-hUheUT.eReyjh > div > form > button")).click();
		Thread.sleep(3000);
		String texto = driver.findElement(By.cssSelector("body > div.sc-dILkzW.jmczzn > div.sc-hUheUT.eReyjh > div > p.sc-kLEbxe.icOEAg")).getText();
		assertEquals("Prontinho! Recebemos os seus dados.", texto);
		System.out.println("Valor Mostrado: " + texto);
	}

}
