package br.com.ecomanager.commons;


import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
* <h1>Gerenciamento de Opções de Navegadores</h1>
* 
* Classe onde ficam setadas as opções de configuração
* que cada navegador vai abrir na execução dos testes.
* 
*/
public class OptionsManager {

	/**
	 * Get Chrome Options
	 * @param browser Recebe variável de inicialização de browser
	 * @return Retorna as opções recolhidas para inicialização de Webdriver
	 */
	public ChromeOptions getChromeOptions(String browser) {
		ChromeOptions options = new ChromeOptions();
		if(browser.equals("chrome")) {
			options.addArguments("--start-maximized");
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("disable-infobars");
			// options.addArguments("--incognito");
		} else if (browser.equals("chrome-headless")) {
			options.addArguments("--headless");
			options.addArguments("window-size=1366x768");
			options.addArguments("--proxy-server='direct://'");
			options.addArguments("--proxy-bypass-list=*");
		} else if (browser.equals("chrome-headless-fabrica")) {
			options.addArguments("--headless");
			options.addArguments("window-size=1366x768");
		}
		return options;
		/*
		 * ChromeDriverService service = new ChromeDriverService.Builder()
		 * .usingAnyFreePort() .build(); ChromeDriver driver = new ChromeDriver(service,
		 * options);
		 */
	}	
	/**
	 * Get Firefox Options
	 * @param browser Recebe variável de inicialização de browser
	 * @return Retorna as opções recolhidas para inicialização de Webdriver
	 */
	public FirefoxOptions getFirefoxOptions(String browser) {
		FirefoxOptions options = new FirefoxOptions();
		if(browser.equals("firefox")) {
			options.addArguments("--start-maximized");
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--disable-popup-blocking");      
		} else if (browser.contentEquals("firefox-headless")) {
			options.addArguments("--headless");
			options.addArguments("window-size=1366x768");
			options.addArguments("--proxy-server='direct://'");
			options.addArguments("--proxy-bypass-list=*");
		}  else if (browser.contentEquals("firefox-headless-fabrica")) {
			options.addArguments("--headless");
			options.addArguments("window-size=1366x768");
		}      
		return options;
	}
}