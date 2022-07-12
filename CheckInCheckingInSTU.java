package com.veracross.magnus.smokeSuiteTests;

import com.veracross.magnus.logs.Log;
import com.veracross.magnus.pages.*;
import com.veracross.magnus.reusableMethods.WebPageOperations;
import com.veracross.magnus.tests.BaseTest;
import com.veracross.magnus.utilities.ConfigFileReader;
import com.veracross.magnus.utilities.ExcelUtility;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.testng.Assert.*;


public class CheckInCheckingInSTU extends BaseTest {
	CheckInWebApp checkInWebApp;

	String excelLoginSheetName;
	int SchoolAdminUserNameRowNum;
	int SchoolAdminUserNameColNum;
	int SchoolAdminPasswordRowNum;
	int SchoolAdminPasswordColNum;
	ExcelUtility excelUtility;

	@BeforeClass(description = "Navigate to check in Web app page")
	public void lauchURL() {

		new ConfigFileReader("checkin");
		checkInWebApp = new CheckInWebApp();
		checkInWebApp.navigatetoCheckInwebappPage(System.getProperty("checkinURL"));

	}
	@Test(priority = 0, description = "Verify that user is able to login to Check In Web app application" )
	public void loginAs_School_Admin_User() {
		new ConfigFileReader("login");
		excelUtility = new ExcelUtility(System.getProperty("ExcelFileName"), System.getProperty("excelLoginSheetName"));
		checkInWebApp = new CheckInWebApp();
		SchoolAdminUserNameRowNum = Integer.parseInt(System.getProperty("SchoolAdminUserNameRowNum"));
		SchoolAdminUserNameColNum = Integer.parseInt(System.getProperty("SchoolAdminUserNameColNum"));
		SchoolAdminPasswordRowNum = Integer.parseInt(System.getProperty("SchoolAdminPasswordRowNum"));
		SchoolAdminPasswordColNum = Integer.parseInt(System.getProperty("SchoolAdminPasswordColNum"));
		Log.info("Enter valid school admin User Name");
		checkInWebApp.enterUserName(excelUtility.getCellData(SchoolAdminUserNameRowNum, SchoolAdminUserNameColNum));
		Log.info("Enter valid School admin Password");
		checkInWebApp.enterPassword(excelUtility.getCellData(SchoolAdminPasswordRowNum, SchoolAdminPasswordColNum));
		Log.info("click on login password");
		checkInWebApp.clickOnLogin();
		Log.info("click on login button");
		assertTrue(checkInWebApp.schoolNameIsDisplayed());
		Log.info("Verify if school name is displayed at the right top corner");
		checkInWebApp.selectATemplate(System.getProperty("TemplateName"));
		Log.info("Select a template from the dorpdown");
		checkInWebApp.clickOnStartCheckInPage();
		Log.info("Click on Start checkin");
		checkInWebApp.enterStudentName(System.getProperty("StudentName"));
		Log.info("Enter the Student Name");
		checkInWebApp.clickOnSearchButton();
		Log.info("Click on Search Button");
		assertTrue(checkInWebApp.studentNameIsDisplayed());
		Log.info("verify if student name is displayed");
		checkInWebApp.clickOnStudentname();
		Log.info("Click on Student link");
		checkInWebApp.clickOnDone();
		Log.info("verify if success message is displayed");
		assertTrue(checkInWebApp.studentSearchTextBoxIsDisplayed(),"Search text box is visible");

	}
}