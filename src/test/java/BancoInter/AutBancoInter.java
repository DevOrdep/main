package BancoInter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import javax.swing.JOptionPane;

class AutBancoInter {

	WebDriver driver;
	
	@BeforeEach
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		
		try {
			Runtime.getRuntime().exec("cmd /c start \"\" \"C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe\" --remote-debugging-port=9222 --user-data-dir=\"C:\\selenium\\AutomationProfile\"");
		} catch (Exception e) {
			System.out.println("Erro ao abrir o Chrome: " + e.getMessage());
		}
		

		Thread.sleep(3000);
	    
	    ChromeOptions options = new ChromeOptions();
	    options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
	    
	    driver = new ChromeDriver(options);
	    driver.get("https://nubank.com.br/");
	}

	@AfterEach
	public void tearDown() throws Exception {
		if(driver != null) {
			try {
				// 1. Fecha a aba do navegador de forma limpa pelo próprio Chrome
				((JavascriptExecutor) driver).executeScript("window.close();");
			} catch (Exception e) {
				// Ignora se a janela já tiver sido fechada
			}
			// 2. Desconecta o driver do Selenium
			driver.quit();
		}

		// 3. Exibe a sua mensagem na tela PRIMEIRO
		JOptionPane.showMessageDialog(
			null,
			"O teste foi realizado e executado!",
			"Automacao do Nubank",
			JOptionPane.WARNING_MESSAGE
		);
		
		// 4. Se ainda sobrar algum processo do Chrome travado, limpa depois que você clicar em "OK"
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() throws InterruptedException {
		Thread.sleep(6000);
		
	
		driver.findElement(By.cssSelector("#header-theme-holder > header > div > div > div.css-1pbtxhe > div > div.chakra-stack.css-z3tzy5 > button.chakra-button.css-aiuye1")).click();
		Thread.sleep(2000);

	
		WebElement campoCpf = driver.findElements(By.id("cpf")).get(1);
		digitarComoHumano(campoCpf, "797.983.060-15");

		WebElement campoNome = driver.findElement(By.id("name"));
		digitarComoHumano(campoNome, "Antonio da Silva");

		WebElement campoFone = driver.findElement(By.id("phone"));
		digitarComoHumano(campoFone, "11940028922");

		WebElement campoEmail = driver.findElement(By.id("email"));
		digitarComoHumano(campoEmail, "asdf@yahoo.com");

		WebElement campoConfEmail = driver.findElement(By.id("emailConfirmation"));
		digitarComoHumano(campoConfEmail, "asdf@yahoo.com");

		Thread.sleep(1500);


		driver.findElement(By.cssSelector("#form-modal-theme-root > div > div.css-1vd6swp > form > div.css-boh9f9 > div > label > span.chakra-checkbox__control.css-1y9ywel")).click();
		Thread.sleep(1500);
		
		WebElement botaoEnviar = driver.findElement(By.cssSelector("#form-modal-theme-root > div > div.css-1vd6swp > form > div.css-tz7fsy > button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", botaoEnviar);
		

		Thread.sleep(58000);
		
		// Validação do texto final de sucesso
		String texto = driver.findElement(By.cssSelector("#form-modal-theme-root > div > div.css-xo13ie > div.css-0 > div > h2")).getText();
		assertEquals("Informações enviadas", texto);
	}

	private void digitarComoHumano(WebElement elemento, String texto) throws InterruptedException {
		elemento.clear();
		for (char caractere : texto.toCharArray()) {
			elemento.sendKeys(String.valueOf(caractere));
			int atrasoAleatorio = 80 + (int)(Math.random() * 140); 
			Thread.sleep(atrasoAleatorio);
		}
		Thread.sleep(500); 
	}
}