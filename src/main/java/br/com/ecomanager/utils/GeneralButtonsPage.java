package br.com.ecomanager.utils;

import static br.com.ecomanager.commons.InvokedMethodListener.reportLog;
import static br.com.ecomanager.commons.Properties.MSG_EXCLUIRSEMSUCESSO;
import static br.com.ecomanager.commons.Properties.MSG_EXCLUIRSUCESSO;
import static br.com.ecomanager.commons.Properties.MSG_SEMSELECIONAR;
import static br.com.ecomanager.commons.Properties.getCurrentPlatform;
import static br.com.ecomanager.core.TLDriverFactory.getDriver;
import static com.aventstack.extentreports.Status.INFO;
import static com.aventstack.extentreports.Status.WARNING;
import static org.openqa.selenium.Platform.LINUX;
import static org.openqa.selenium.Platform.MAC;
import static org.openqa.selenium.Platform.WINDOWS;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import br.com.ecomanager.core.DSL;

/**
 * Página com os botões e métodos de acesso mais comuns utilizados nos testes
 * 
 */
public class GeneralButtonsPage {

	private DSL dsl = new DSL();

	public GeneralButtonsPage() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "(//button[@class='btn btn-success'])[2]")
	private WebElement newButton;

	@FindBy(xpath = "//span[@class='select2-search select2-search--dropdown']//input[@type='search']")
	private WebElement search;

	@FindBy(xpath = "//div[@class='toast-message']")
	private WebElement balaoInformacao;

	@FindBy(xpath = "//a[@class='btn-link ']")
	private WebElement nomeListagem; 

	@FindBy(xpath = "(//input[@type='checkbox']/..//span)[3]")
	private WebElement checkBoxListagem;

	@FindBy(xpath = "(//button[@class='btn btn-danger'])[2]")
	private WebElement buttonExcluir;

	@FindBy(xpath = "//button[contains(text(),'Sim')]")
	private WebElement buttonSimExcluir;

	@FindBy(xpath = "//button[contains(text(),'Não')]")
	private WebElement buttonNaoExcluir;

	@FindBy(xpath = "(//button[@class='btn btn-success '])[2]")
	private WebElement saveButton;

	@FindBy(xpath = "(//button[@class='btn btn-info'])[2]")
	private WebElement buttonFiltro;

	@FindBy(xpath = "//input[@class='form-control ']")
	private WebElement campoFiltro;

	@FindBy(name = "filtro.nome")
	private WebElement nomeFiltro;

	@FindBy(xpath = "//button[@class='btn btn btn-info']")
	private WebElement buttonPesquisar;

	@FindBy(xpath = "//button[@class='toast-close-button']")
	private WebElement closeBalao;

	/**
	 * Seta texto no campo nome do formulário de filtro de pesquisa
	 * @param value Texto que preencherá o campo nome
	 */
	public void setNomeFiltro(String value) {
		dsl.explicityWaitClickable(nomeFiltro);
		dsl.preencherCampo(nomeFiltro, value);
		dsl.reportLogs("Preencheu o campo Nome no filtro", INFO);
	}

	/**
	 * Clica no botão novo 
	 */
	public void clickButtonNovo() {
		dsl.clickButton(newButton);
		dsl.reportLogs("Clicou no botão (Novo)", INFO);
	}
	/**
	 * Preenche o search do select clicado
	 * @param value Recebe texto que preencherá o search do select clicado
	 */
	public void searchSelect(String value) {
		dsl.explicityWaitClickable(search);
		dsl.preencherCampoEnter(search, value);
		dsl.reportLogs("Preencheu o search Select", INFO);
	}
	/**
	 * Valida se o balão com a mensagem é válida de acordo com a mensagem esperada
	 * @param value Recebe a mensagem que vai ser comparada com o balão de texto exibido pelo sistema
	 */
	public void validarTrueBalao(String value) {
		try {
			dsl.explicityWaitVisible(balaoInformacao);
		} catch (TimeoutException e) {
			dsl.reportLogs(e.getMessage(), WARNING);
			e.getBuildInformation();
		}
		dsl.validarMensagem(value, balaoInformacao);
		waitElement(500);
		dsl.clickButton(closeBalao);
		dsl.reportLogs("Validou o balão de informação", INFO);
	}
	/**
	 * Seleciona primeiro elemento na listagem de cadastos
	 */
	public void selecionarNomeListagem() {
		dsl.explicityWaitClickable(nomeListagem);
		dsl.clickButton(nomeListagem);
		dsl.reportLogs("Selecionou o primeiro elemento da listagem", INFO);
	}
	/**
	 * Seleciona o checkbox do primeiro elemento da listagem de cadastros
	 */
	public void checkBoxListagem() {
		dsl.clickButton(checkBoxListagem);
		dsl.reportLogs("Selecionou o primeiro checkbox da listagem", INFO);
	}
	/**
	 * Clica no botão excluir
	 */
	public void clickButtonExcluir() {
		dsl.clickButton(buttonExcluir);
		dsl.reportLogs("Clicou no botão excluir", INFO);
	}
	/**
	 * Clica no botão sim do modal que estiver sendo exibido na tela 
	 */
	public void clickButtonSimModal() {
		dsl.explicityWaitClickable(buttonSimExcluir);
		dsl.clickButton(buttonSimExcluir);
		dsl.reportLogs("Clicou no botão Sim no modal de Excluir", INFO);
	}
	/**
	 * Clica no botão não do modal que estiver sendo exibido na tela 
	 */
	public void clickButtonNaoModal() {
		dsl.clickButton(buttonNaoExcluir);
		dsl.reportLogs("Clicou no botão Não no modal de Excluir", INFO);
	}
	/**
	 * Clica no botão salvar e continuar do formulário
	 */
	public void clickSaveButton() {
		dsl.explicityWaitClickable(saveButton);
		dsl.clickButton(saveButton);
		dsl.reportLogs("Clicou no botão Salvar e Continuar", INFO);
	}
	/**
	 * Clica no botão filtro
	 */
	public void clickButtonFiltro() {
		dsl.clickButton(buttonFiltro);
		dsl.reportLogs("Clicou no botão Filtro", INFO);
	}
	/**
	 * Preenche o campo filtro com o valor setado
	 * @param value Texto que preencherá o campo filtro
	 */
	public void setCampoFiltro(String value) {
		dsl.preencherCampo(campoFiltro, value);
		dsl.reportLogs("Preencheu o campo no filtro", INFO);
	}
	/**
	 * Clica no botão pesquisar 
	 */
	public void clickButtonPesquisar() {
		dsl.clickButton(buttonPesquisar);
		dsl.reportLogs("Clicou no botão de Pesquisa no Filtro", INFO);
	}
	/**
	 * Insere imagem para fazer upload no formulário em que é utilizado
	 * 
	 * @param value nome da Imagem que vai se fazer o upload
	 * @return retorna o endereço junto com o nome da imagem que vai ser feito o upload
	 */
	public String insertIMG(String value) {
		String localOS = "";
		if(getCurrentPlatform() == WINDOWS) {
			String localWin = "//target//test-classes//Files//"+value;
			localOS = localWin ;
		} else if (getCurrentPlatform() == LINUX || getCurrentPlatform() == MAC) {
			String localLin = "/target/test-classes/Files/"+value;
			localOS = localLin;
		}
		dsl.reportLogs("Inseriu a imagem", INFO);
		return localOS;
	}
	/**
	 * Scrolla a página em que é chamado
	 * @param value Recebe o valor relativo a quantidade que será scrollado para cima com valor negativo ou para baixo com valor positivo
	 */
	public void scroll(String value) {
		dsl.scrollDSL(value);
	}
	/**
	 * Aguarda tempo indicado antes de continuar a execução de passos do teste
	 * @param i quantidade de tempo que irá aguardar antes de proceder os passos seguintes
	 */
	public void waitElement(int i) {
		dsl.waitElementDSL(i);
	}

	/**
	 * Acessar elemento pelo filtro
	 */
	public void acessoPeloFiltro(String value) {
		clickButtonFiltro();
		try {
			setCampoFiltro(value);
		} catch (Exception e) {
			setNomeFiltro(value);
		}
		clickButtonPesquisar();
		selecionarNomeListagem();
	}
	/**
	 * Excluir elemento desejado passando o valor dele como parâmetro
	 * @param value Recebe nome do elemento cadastrado
	 */
	public void excluirComSucesso(String value) {
		clickButtonFiltro();
		try {
			setCampoFiltro(value);
		} catch (Exception e) {
			setNomeFiltro(value);
		}
		clickButtonPesquisar();
		try {
			checkBoxListagem();
		} catch (IndexOutOfBoundsException e) {
			reportLog(e.getMessage(), Status.WARNING);
		}
		clickButtonExcluir();
		clickButtonSimModal();
		validarTrueBalao(MSG_EXCLUIRSUCESSO);
	}
	/**
	 * Excluir sem selecionar elemento
	 */
	public void excluirSemElementoSelecionado() {
		clickButtonExcluir();
		clickButtonSimModal();
		validarTrueBalao(MSG_SEMSELECIONAR);
	}
	/**
	 * Excluir elemento sem sucesso
	 * @param value Recebe nome do elemento cadastrado com vinculos que não permitem sua exclusão do sistema
	 */
	public void excluirSemSucesso(String value) {
		clickButtonFiltro();
		setCampoFiltro(value);
		clickButtonPesquisar();
		checkBoxListagem();
		clickButtonExcluir();
		clickButtonSimModal();
		validarTrueBalao(MSG_EXCLUIRSEMSUCESSO);
	}
}
