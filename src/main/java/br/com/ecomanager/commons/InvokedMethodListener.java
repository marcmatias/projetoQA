package br.com.ecomanager.commons;

import static br.com.ecomanager.commons.Properties.getCurrentPlatform;
import static br.com.ecomanager.core.TLDriverFactory.getDriver;
import static br.com.ecomanager.core.TLDriverFactory.resetDriver;
import static br.com.ecomanager.core.TLDriverFactory.setDriver;
import static br.com.ecomanager.core.TLDriverFactory.setSizeAndPosition;
import static br.com.ecomanager.core.TLDriverFactory.startUrl;
import static br.com.ecomanager.utils.ExtentManager.createInstance;
import static org.apache.commons.io.FileUtils.copyFile;
import static org.openqa.selenium.Platform.LINUX;
import static org.openqa.selenium.Platform.MAC;
import static org.openqa.selenium.Platform.WINDOWS;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.IRetryAnalyzer;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

/**
* <h1>Ouve as etapas do teste e toma ações baseadas em sua situação</h1>
* 
* Classe que invoca os métodos que ouvem o teste e a suite
* durante sua execução. Aqui estão as capturas em cada fase
* do teste(início, execução e finalização).
* 
*/
public class InvokedMethodListener implements IInvokedMethodListener, ITestListener, ISuiteListener {

	private static ExtentReports extent = createInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			System.out.println("Test Method BeforeInvocation is started. " + Thread.currentThread().getId());
			String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browser");
			setDriver(browserName);
			setSizeAndPosition();
			if(!method.getTestMethod().getMethodName().endsWith("ProperURL")) startUrl("http://marcmatias.pythonanywhere.com/login/");
		}
	}
	
	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		IRetryAnalyzer retry = testResult.getMethod().getRetryAnalyzer();
		if (retry == null) {
			return;
		} 
		testResult.getTestContext().getFailedTests().removeResult(testResult.getMethod());
		testResult.getTestContext().getSkippedTests().removeResult(testResult.getMethod());

		if (!testResult.isSuccess()) {
			File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
			try {
				if(getCurrentPlatform() == WINDOWS) {
					copyFile(scrFile, new File(System.getProperty("user.dir") + "//tmp//"+
							method.getTestMethod().getMethodName()+".png"));
					test.get().addScreenCaptureFromPath(System.getProperty("user.dir") + "//tmp//"+
							method.getTestMethod().getMethodName()+".png");
				}
				else if(getCurrentPlatform() == LINUX || getCurrentPlatform() == MAC) {
					copyFile(scrFile, new File(System.getProperty("user.dir") + "/tmp/"+
							method.getTestMethod().getMethodName()+".png"));
					test.get().addScreenCaptureFromPath(( System.getProperty("user.dir") + "/tmp/"+
							method.getTestMethod().getMethodName()+".png"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (method.isTestMethod()) {
			System.out.println("Test Method AfterInvocation is started. " + Thread.currentThread().getId());
			resetDriver();
		}
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " started!"));
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());
		test.set(extentTest);
		test.get().assignCategory(result.getTestClass().getName().substring(result.getTestClass().getName().lastIndexOf(".") + 1) + "_" +
				result.getMethod().getXmlTest().getLocalParameters().get("browser"));
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " passed!"));
		test.get().pass("Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " failed!"));
		test.get().fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		test.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("Extent Reports Version 3 Test Suite started!");	
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println(("Extent Reports Version 3  Test Suite is ending!"));
		extent.flush();
	}

	@Override
	public void onStart(ISuite suite) {
//				  String nameProject = suite.getXmlSuite().getName();
//				  KlovReporter klovReporter = new KlovReporter();
//				  klovReporter.initMongoDbConnection("localhost", 27017);
//				//klovReporter.initMongoDbConnection("10.10.32.123", 27017);
//				  klovReporter.setProjectName(nameProject);
//				  klovReporter.setReportName("1.5");
//				  klovReporter.initKlovServerConnection("http://localhost");
//				  extent.attachReporter(klovReporter);
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Finish Suite" + suite.getName());
	}
	
	/**
	 * Adiciona logs setados pelos testadores ao relatório
	 * 
	 * @param message Mensagem a ser salva no log
	 * @param type Status de mensagem
	 */
	public static void reportLog(String message, Status type) {    
	      test.get().log(type, message);
	      extent.flush();
	}
}
