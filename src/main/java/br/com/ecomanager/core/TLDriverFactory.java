package br.com.ecomanager.core;

import static br.com.ecomanager.commons.Properties.getCurrentPlatform;
import static java.lang.System.getProperty;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import br.com.ecomanager.commons.OptionsManager;

public class TLDriverFactory {

	
	private static OptionsManager optionsManager = new OptionsManager();
	private static ThreadLocal<RemoteWebDriver> tlDriver = new ThreadLocal<>();

	public static synchronized RemoteWebDriver getDriver() {
		return tlDriver.get();
	}
	/**
	 * Seta o driver que vai ser usado durante o teste.
	 * 
	 * Baseado no sistema operacional que está executando o código este método irá apontar para
	 * o driver que deve ser executado durante os testes executados. Apenas a inicial da variável
	 * browser é utilizada, pois o que se segue é se o navegador vai executar como headless ou 
	 * em modo interface. Essas opções são consideradas apenas na OptionsManager.
	 * 
	 * @param browser Parâmetro enviado pelo suite.xml do TestNG o nome do navegador que vai rodar o teste
	 */
	public static synchronized void setDriver(String browser) {
		switch (getCurrentPlatform()) {
		case WINDOWS:
			if (browser.startsWith("firefox")) {
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions(browser)));
			} else if (browser.startsWith("chrome")) {
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions(browser)));
			}
			break;
		case LINUX:
			if (browser.startsWith("firefox")) {
				System.setProperty("webdriver.gecko.driver", getProperty("user.dir") + "/src/test/resources/Drivers/geckodriver");
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions(browser)));
			} else if (browser.startsWith("chrome")) {
				System.setProperty("webdriver.chrome.driver", getProperty("user.dir") + "/src/test/resources/Drivers/chromedriver");
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions(browser)));
			}
			break;
		default:
			if (browser.startsWith("firefox")) {
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions(browser)));
			} else if (browser.startsWith("chrome")) {
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions(browser)));
			}
			break;
		}
	}
	/**
	 * Finaliza o driver da thread que chama este método.
	 * 
	 * Quando chamado, se o driver for diferente de nulo ele será finalizado
	 * e setado como null.
	 */
	public static void resetDriver() {
		RemoteWebDriver driver = getDriver();
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
	/**
	 * Recebe URL que será acessada para execução de bateria de testes
	 * @param url Recebe o endereço que será acessado para os testes que serão executados
	 */
	public static void startUrl(String url) {
		getDriver().get(url);
	}
	/**
	 *	Seta o tamanho e a posição que a janela do navegador de testes com interface
	 *
	 *  Usado para otimizção de uso de espaço de tela pelo testador
	 *  ou para verificação de site/sistema testado com navegador em
	 *  diferentes tamanhos de tela/janela.
	 *  
	 * @param browserWindowsSize Recebe as opções maximized ou windowed
	 */
	public static void setSizeAndPosition(String browserWindowsSize) {
		switch (browserWindowsSize) {
		case "maximized":
			getDriver().manage().window().maximize();
			break;
			
		case "windowed":
			getDriver().manage().window().setSize(new Dimension(1280,720));
			getDriver().manage().window().setPosition(new Point(310, 10));
			break;
		
		default:
			getDriver().manage().window().maximize();
			break;
		}
	}
	/**
	 * Seta o tamanho como maximizado para navegador de testes com interface. 
	 */
	public static void setSizeAndPosition() {
			getDriver().manage().window().maximize();
	}
	
}
