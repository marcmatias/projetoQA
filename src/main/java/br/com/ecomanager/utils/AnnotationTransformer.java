package br.com.ecomanager.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/**
 * Tranforma as anotações do sistema
 * 
 * As anotações setadas aqui vão estar presentes em todos
 * os @Test do sistema, em nosso caso utilizamos isso para
 * que todos os testes tenham o RetryAnalyzer que reexecuta
 * o teste caso o primeiro resultado seja erro.
 * 
 * o AnnotationTransformer deve ser chamado 
 * 
 */
public class AnnotationTransformer implements IAnnotationTransformer {

	 @Override
	    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
	        annotation.setRetryAnalyzer(Retry.class);
	    }
}
