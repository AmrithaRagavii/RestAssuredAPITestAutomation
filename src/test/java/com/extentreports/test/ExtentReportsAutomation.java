package com.extentreports.test;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsAutomation {

	@Test
	public void extentTest() throws IOException {
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
		//ExtentSparkReporter failedtest=new ExtentSparkReporter("failed tests-index.html").filter().statusFilter().as(new Status[] {Status.FAIL}).apply();
		//failedtest.config().setDocumentTitle("Failed test");

		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("MyAutomationProjectReport");
		spark.config().setReportName	("Extent Reports");

		//		final File CONF = new File("extentconfig.json");
		//		spark.loadJSONConfig(CONF);


		//		final File CONF = new File("config/spark-config.xml");
		//		spark.loadXMLConfig(CONF);


		extent.attachReporter(spark);

		ExtentTest extenttest= extent.createTest("Login Test").assignAuthor("Amritha").assignCategory("smoke").assignDevice("chrome 80");	
		extenttest.pass("Login Test Started");
		extenttest.info("URL id loaded");
		extenttest.info("Values entered");
		extenttest.pass("Login Test successfully completed");

		ExtentTest extenttest1= extent.createTest("Homepage Test").assignAuthor("Ragavi").assignCategory("regression").assignDevice("firefox 60");
		extenttest1.pass("Homepage Test Started");
		extenttest1.info("URL id loaded");
		extenttest1.info("Values entered");
		extenttest1.fail("Homepage Test failed");

		extent.flush();

		Desktop.getDesktop().browse(new File("index.html").toURI());
		Desktop.getDesktop().browse(new File("failed tests-index.html").toURI());

	}

}
