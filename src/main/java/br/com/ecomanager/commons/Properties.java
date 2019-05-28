package br.com.ecomanager.commons;


import static org.openqa.selenium.Platform.LINUX;
import static org.openqa.selenium.Platform.MAC;
import static org.openqa.selenium.Platform.WINDOWS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Platform;

/**
* <h1>Propriedades e Variáveis Estáticas Reutilizáveis em Testes</h1>
* 
* Classe em que ficam setadas as variáveis estáticas e
* os métodos mais utilizadas no sistema.
*
* @author Israel Araújo Rodrigues
* @author Marcel Marques Matias
* @author Thifany Déborah de Souza Brito
* 
* @version 1.0
* @since   10-11-2018
*/
public class Properties {

	public static Platform platform;
	public static String RANDOM_NAME = randomGenerator(3, true, false);
	public static String RANDOM_NAME_ALT = randomGenerator(3, true, false);
	public static String RANDOM_ID = randomGenerator(3, false, true);
	public static String RANDOM_ID_ALT = randomGenerator(4, false, true);
	public static String RANDOM_PHONE = randomGenerator(11, false, true);
	public static String RANDOM_PHONE_ALT = randomGenerator(11, false, true);
	public static String RANDOM_NUMBER = randomGenerator(5, false, true);
	public static String RANDOM_NUMBER_ALT = randomGenerator(5, false, true);
	
	public static String LOREM_TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit,"
			+ " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
	public static String CAMPO_OBRIGATORIO = "Este campo é obrigatório.";
	public static String MSG_SEMSELECIONAR = "Selecione pelo menos um item a ser excluído";
	public static String MSG_EXCLUIRSUCESSO = "Os itens selecionados foram excluídos com sucesso";
	public static String MSG_EXCLUIRSEMSUCESSO = "Algum(ns) item(ns) selecionado(s) não podem ser excluido(s).";
	public static String OPE_SUCESSO = "Operação realizada com sucesso.";
	
	
	/**
	 * Método para geração de caracteres aleatórios
	 * 
	 * @param size Quantos digitos vão ser printados no retorno
	 * @param letters Se o resultado printado terá letras
	 * @param numbers  Se o resultado printado terá números
	 * @return Retorna variável com letras e/ou números com a quantidade de carácteres setado
	 */
	public static String randomGenerator(int size, boolean letters, boolean numbers) {
		int length = size;
		boolean useLetters = letters;
		boolean useNumbers = numbers;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

		return generatedString;
	}
	
	/**
	 * Verificação automática de sistema operacional que está executando os testes
	 * 
	 * @return Retorna a variável relativa ao sistema operacional que está executando os testes
	 */
	public static Platform getCurrentPlatform() {
		if (platform == null) {
			String operSys = System.getProperty("os.name").toLowerCase();
			if (operSys.contains("win")) {
				platform = WINDOWS;
			} else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
				platform = LINUX;
			} else if (operSys.contains("mac")) {
				platform = MAC;
			}
		}
		return platform;
	}
	
	/**
	 * Retorna a data e hora do momento em que o método é chamado
	 * 
	 * @return Retorna String com data e hora
	 */
	public static String getCurrentLocalDateTimeStamp() {
	    return LocalDateTime.now()
	       .format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));
	}
	
	/**
	 * Retorna a data do momento em que o método é chamado
	 * 
	 * @return Retorna String com data e hora
	 */
	public static String getCurrentLocalDateStamp() {
	    return LocalDateTime.now()
	       .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}
	
}
