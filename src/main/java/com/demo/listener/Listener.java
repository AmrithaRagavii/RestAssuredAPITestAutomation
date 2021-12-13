package com.demo.listener;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.demo.report.ReportManager;

public class Listener implements ITestListener,ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		ReportManager.configReport();	
	}

	@Override
	public void onFinish(ISuite suite) {
		ReportManager.flushReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		ReportManager.createTest(result.getMethod().getDescription(),"Regression");
		ReportManager.test.pass(result.getMethod().getDescription() + " is started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ReportManager.test.pass(result.getMethod().getDescription() + " is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ReportManager.test.fail(result.getMethod().getDescription() + " is failed");
		ReportManager.test.fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ReportManager.test.skip(result.getMethod().getDescription() + "is skipped");
	}

}
