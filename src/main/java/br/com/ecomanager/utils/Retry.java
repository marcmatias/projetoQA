package br.com.ecomanager.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Reexecução de teste.
 * 
 * Caso a primeira tentativa de teste resulte em erro o 
 * teste será executado novamente. A finalidade é ter
 * certeza de que o erro não é  intermitente.
 */
public class Retry implements IRetryAnalyzer{

	private int retryCount = 0;
    
	public boolean retry(ITestResult result) {
        int maxRetryCount = 1;
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retry #" + retryCount + " for test: " + result.getMethod().getMethodName() + ", on thread: " + Thread.currentThread().getName());
            return true;
        }
        return false;
    }

}
