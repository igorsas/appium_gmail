package com.igor.listener;

import com.igor.utils.provider.DriverProvider;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestNGListener implements ITestListener {
	private static Logger LOGGER = LogManager.getLogger(TestNGListener.class);

	@Override
	public void onTestStart(ITestResult result) {
		LOGGER.info(String.format("Test : %s , Method : %s, Started", result.getName(), result.getMethod()));

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		LOGGER.info(String.format("Test : %s , Method : %s passed successful", result.getName(),
				result.getMethod()));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			LOGGER.warn(String.format("Test : %s , Method : %s failed in", result.getName(), result.getMethod()));
			File screenshot = ((TakesScreenshot) DriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);
			String screenshotName = getFileName();
			String path = getPath(screenshotName);
			FileUtils.copyFile(screenshot, new File(path));
			LOGGER.info("<br><img src=" + screenshotName + " height='355' width='200'/><br>");

		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}

	private String getFileName() {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
		Date date = new Date();
		return dateFormat.format(date) + "Thread" + Thread.currentThread() + "_" + "screenshot" + ".png";
	}

	private String getPath(String name) {
		return "target\\surefire-reports\\html\\" + name;
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		LOGGER.info(String.format("Test : %s , Method : %s was skipped ", result.getName(), result.getMethod()));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		LOGGER.warn(String.format("Test : %s , Method : %s failed  within success precentage: %s  ",
				result.getName(), result.getMethod(),result.getTestContext().getFailedButWithinSuccessPercentageTests().getAllResults().toString()));
	}

	@Override
	public void onStart(ITestContext context) {
		LOGGER.info(String.format("Starting tests : %s, Output directory : %s, Test Suite : %s",
				context.getName(), context.getOutputDirectory(), context.getSuite()));
	}

	@Override
	public void onFinish(ITestContext context) {
		LOGGER.info(String.format("Finish tests : %s, Output directory : %s, Test Suite : %s",
				context.getName(), context.getOutputDirectory(), context.getSuite()));
	}

}
