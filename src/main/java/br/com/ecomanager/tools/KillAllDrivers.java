package br.com.ecomanager.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <h1>Classe que limpa todos os processos gerados pelos drivers Chrome e Firefox da máquina</h1>
 * 
 *  É comum o caso de testes serem abruptamente parados antes
 *  de finalizar a execução do código quando se está fazendo  a
 *  automação de testes. Isso faz com que webdrivers permaneçam
 *  abertos nos processos da máquina e consumam hardware
 *  desnecessáriamente. Esta classe quando executada faz a contagem
 *  de quantos desses processos estão na memória, os exibe e os killa. 
 * 
 */
public class KillAllDrivers {

	public static void main(String[] args) throws IOException {
		Runtime r = Runtime.getRuntime();
		if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
			printLines(processExec(r, "taskkill /F /IM chromedriver.exe /T"), "Chrome");
			printLines(processExec(r, "taskkill /F /IM geckodriver.exe /T"), "Firefox");			  
		}
		else {
			printLines(processExec(r, "killall chromedriver"), "Chrome");
			printLines(processExec(r, "killall chrome"), "Navegador de Internet");
			printLines(processExec(r, "killall geckodriver"), "Firefox");			   
		}
		System.out.println("-- End --");
		System.exit(0);
	}
	// Executa os comandos em tempo de execução
	public static BufferedReader processExec(Runtime r, String execute) throws IOException {
		Process p = r.exec(execute);
		BufferedReader is = new BufferedReader(new InputStreamReader(p.getInputStream()));
		return is;
	}
	// Contagem e print de processos matados	
	public static void printLines(BufferedReader is, String browser) throws IOException {
		if	(is.readLine() == null) {
			System.out.println("0 " + browser + "driver processes to kill");
		} else {
			Integer i = 0;
			while (is.readLine() != null) {
				i++;
			}
			System.out.println(i+" " +browser+"driver " + "processes killed");
		}
	}
}
