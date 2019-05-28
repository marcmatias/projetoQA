package br.com.ecomanager.consumos;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.ecomanager.core.BaseTest;
import br.com.ecomanager.page.consumo.ConsumosPage;
import br.com.ecomanager.utils.GeneralButtonsPage;


public class ConsumoTest extends BaseTest {
	
	private ConsumosPage page;
	private GeneralButtonsPage gbp;
	
	/*********** ACESSO AO MODULO ABA DE ATENDIMENTO ***********/
	public void acessoAoModulo() {
		acessoAdm();
		page = new ConsumosPage();
		gbp = new GeneralButtonsPage();
		page.clickModuloGerenciar();
	}
	/*********** CADASTRO DE CONSUMO ***********/
	@Parameters({ "estabelecimento", "predio", "sala", "kwh" })
	@Test(priority = 0, description="Cadastro de aba de consumo no sistema")
	public void fillUserForm(String estabelecimento, String predio, String sala, String kwh) {
		acessoAoModulo();
		page.clickModuloGerenciar();
		page.clickConsumo();
		page.clickNovo();
		page.clickSelectEstabelecimento(estabelecimento);
		gbp.waitElement(1000);
		page.clickSelectPredio(predio);
		gbp.waitElement(1000);
		page.clickSelectSala(sala);
		page.fillKWH(kwh);
		page.clickButtonCadastrar();
		gbp.waitElement(5000);
	}

}
