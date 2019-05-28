package br.com.ecomanager.page.confgerais;

import static br.com.ecomanager.core.TLDriverFactory.getDriver;
import static com.aventstack.extentreports.Status.INFO;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.ecomanager.core.DSL;

public class LoginPage{
	
	private DSL dsl = new DSL();

	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	/******** LOGIN ***********/
	@FindBy(id = "id_username")
	private WebElement idLogin;

	@FindBy(id = "id_password")
	private WebElement senha;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-block']")
	private WebElement button;

	public void setLogin(String value) {
		dsl.preencherCampo(idLogin, value);
	}
	public void setSenha(String value) {
		dsl.preencherCampo(senha, value);
	}
	public void signIn() {
		dsl.clickButton(button);
	}

}
