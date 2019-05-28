package br.com.ecomanager.page.consumo;

import static br.com.ecomanager.core.TLDriverFactory.getDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import br.com.ecomanager.core.DSL;
//import br.com.ecomanager.utils.GeneralButtonsPage;

public class ConsumosPage{
	
	public ConsumosPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	private DSL dsl = new DSL();
//	private GeneralButtonsPage gbp = new GeneralButtonsPage();
	
	/*********** ACESSO AO MODULO GERENCIAR ***********/
	@FindBy(xpath = "//a[@id='collapse_gerenciar']")
	private WebElement gerenciar;

	/*********** ACESSOS AO FORMULÁRIO ***********/
	@FindBy(xpath = "//a[contains(text(),'Consumo')]")
	private WebElement consumo; 
	
	@FindBy(xpath = "//a[contains(text(),'Novo')]")
	private WebElement novo; 
	
	@FindBy(xpath = "//select[@id='id_estabelecimento']")
	private WebElement selectEstabelecimento; 

	@FindBy(xpath = "//select[@id='id_predio']")
	private WebElement selectPredio; 
	
	@FindBy(xpath = "//select[@id='id_sala']")
	private WebElement selectSala; 
	
	@FindBy(xpath = "//input[@id='id_kwh']")
	private WebElement kwh; 

	@FindBy(xpath = "//input[@class='btn btn-primary']")
	private WebElement buttonCadastrar; 
	
	/*********** CRIACAO DA ABA DE ATENDIMENTO ***********/
	public void clickModuloGerenciar() {
		dsl.clickButton(gerenciar);
	}
	
	public void clickConsumo() {
		dsl.clickButton(consumo);
	}

	public void clickNovo() {
		dsl.clickButton(novo);
	}
	
	public void clickSelectEstabelecimento(String value) {
		dsl.selecionarCombo(selectEstabelecimento, value);
	}

	public void clickSelectPredio(String value) {
		dsl.selecionarCombo(selectPredio, value);
	}
	
	public void clickSelectSala(String value) {
		dsl.selecionarCombo(selectSala, value);
	}
	
	public void fillKWH(String value) {
		dsl.preencherCampo(kwh, value);
	}

	public void clickButtonCadastrar() {
		dsl.clickButton(buttonCadastrar);
	}
	
}
