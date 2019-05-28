package br.com.ecomanager.utils;

import static br.com.ecomanager.commons.Properties.getCurrentLocalDateStamp;
import static br.com.ecomanager.commons.Properties.getCurrentLocalDateTimeStamp;
import static br.com.ecomanager.commons.Properties.getCurrentPlatform;

import java.io.File;

import org.openqa.selenium.Platform;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


/**
 * Configurações do ExtentReport
 * 
 * Aqui fica a classe responsável por toda configuração
 * relativa ao ExetentReport gerado ao final dos testes
 * executados.
 */
public class ExtentManager {

	private static ExtentReports extent;
	private static String reportFileNameWindows = "DigivoxLab_" + getCurrentLocalDateTimeStamp() + ".html";
	private static String windowsPath = System.getProperty("user.dir") + "\\TestReport\\ExtentReports\\"+ getCurrentLocalDateStamp() + "\\";
	private static String winReportFileLoc = windowsPath + "\\" + reportFileNameWindows;
	
	private static String reportFileNameLinux = "DigivoxLab_" + getCurrentLocalDateTimeStamp() + ".html";
	private static String linuxPath = System.getProperty("user.dir") + "/TestReport/ExtentReports/"+ getCurrentLocalDateStamp() + "/" ;
	private static String linReportFileLoc = linuxPath + "/" + reportFileNameLinux;

	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}
	/**
	 * Cria uma instância do ExtentReport
	 * @return Retorna a instância criada
	 */
	public static ExtentReports createInstance() {
		Platform platform = getCurrentPlatform();
		String fileName = getReportFileLocation(platform);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
//	        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
//	        htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("DigivoxLab");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		return extent;
	}
	/**
	 * Select the extent report file location based on platform
	 * @param platform Recebe qual a plataforma está executando os testes
	 * @return Retorna a localização em que o relatório será salvo
	 */
	private static String getReportFileLocation(Platform platform) {
		String reportFileLocation = null;
		switch (platform) {
		case WINDOWS:
			reportFileLocation = winReportFileLoc;
			createReportPath(windowsPath);
			System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
			break;
		case LINUX:
			reportFileLocation = linReportFileLoc;
			createReportPath(linuxPath);
			System.out.println("ExtentReport Path for LINUX: " + linuxPath + "\n");
			break;
		default:
			System.out.println("ExtentReport path has not been set! There is a problem!\n");
			break;
		}
		return reportFileLocation;
	}
	/**
	 * Cria o caminho para salvar o relatório caso ele não exista
	 * 
	 * @param path Recebe o caminho que o relatório será salvo
	 */
	private static void createReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				System.out.println("Directory: " + path + " is created!");
			} else {
				System.out.println("Failed to create directory: " + path);
			}
		} else {
			System.out.println("Directory already exists: " + path);
		}
	}
}
