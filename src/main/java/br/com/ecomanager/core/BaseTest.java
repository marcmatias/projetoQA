package br.com.ecomanager.core;

import static br.com.ecomanager.commons.Properties.RANDOM_ID;
import static br.com.ecomanager.commons.Properties.RANDOM_ID_ALT;
import static br.com.ecomanager.core.TLDriverFactory.getDriver;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;

import br.com.ecomanager.page.confgerais.LoginPage;

/**
* Teste base com métodos para reutilização em todos os testes.
* 
* @author Marcel Marques Matias
* 
* @version 1.0
* @since   10-11-2018
*/
public class BaseTest {

	protected LoginPage loginPage;

	/**
	 * Acessar área administrativa do sistema
	 */
	public void acessoAdm() {
		loginPage = new LoginPage();
		loginPage.setLogin("admin");
		loginPage.setSenha("Av123456");
		loginPage.signIn();
	}
	
	/**
	 * Captura o nome da classe em que é chamado
	 * @return Retorna como string o nome (+ ID) da classe que chamou o método
	 */
	public String nameClass() {
		String name = this.getClass().getSimpleName();
		return name + RANDOM_ID;
	}
	/**
	 * Captura o nome da classe em que é chamado versão para teste de edição
	 * @return Retorna como string o nome (+ ID_ALT) da classe que chamou o método
	 */
	public String nameClassAlt() {
		String name = this.getClass().getSimpleName();
		return name + RANDOM_ID_ALT;
	}
	
	/**
	 * Varivel que faz o controle de decremento de tamanho de array de abas
	 * do navegador de acordo com a quantidade de abas fechadas no método clienteEnviarTexto.
	 */
	public Integer tabArraySizeChange = 0;
	
	/**
	 * Alterna entre as abas do navegador	
	 * @param i Recebe do teste o tamanho do array de abas do navegador que está executando os testes
	 */
	public void switchTab(int i) {
		ArrayList<String> tabs = new ArrayList<String> (getDriver().getWindowHandles());
		getDriver().switchTo().window(tabs.get(i));
	}
	/**
	 * Cria novas abas através de comando javascript
	 * @param i Recebe do teste o tamanho do array de abas do navegador que está executando os testes
	 */
	public void newTab(int i) {
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		jse.executeScript("window.open()");
		switchTab(i);
	}
}