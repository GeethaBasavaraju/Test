package com.veracross.magnus.smokeSuiteTests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import com.veracross.magnus.pages.*;
import org.testng.annotations.Test;

import com.veracross.magnus.logs.Log;
import com.veracross.magnus.reusableMethods.WebPageOperations;
import com.veracross.magnus.tests.BaseTest;
import com.veracross.magnus.utilities.ConfigFileReader;
import com.veracross.magnus.utilities.ExcelUtility;

public class AdminDashboardCasesCloseAdmindashboard extends BaseTest {
	LoginPage loginPage;
	MagnusHealthPartnerLoginPage mha;
	FrontDesk frontDesk;

	 AdminDashboardPage admindashboardpage;
	VerifySHTElementsAsReseller verifySHTElementsAsReseller;
StudentHealthTrackerPageOfStudent studentHealthTrackerPageOfStudent;
	 @Test(priority = 0, description = "Verify that user is able to login to Magnus Health Application as school admin user" )
	 public void loginAs_School_Admin_User() {

		 verifySHTElementsAsReseller = new VerifySHTElementsAsReseller();
		 verifySHTElementsAsReseller.loginAsMHA();
	 }
	@Test(priority = 1, description = "[Reseller] Close Admin Dashboard and log out from Secure [NON-PAR SCH]")
			public void AdminDashboardCases()
	{
		new ConfigFileReader("login");
		frontDesk = new FrontDesk();
		mha = new MagnusHealthPartnerLoginPage();
		loginPage = new LoginPage();
		admindashboardpage = new AdminDashboardPage();
	       assertEquals(loginPage.getCurrentPageURL(),System.getProperty("BaseURL")+System.getProperty("MHPLoginURL"),"Validate is user is in Magnus healthportal Page");
		   mha.clickOnaccountAdminbutton();
			 admindashboardpage.clickOnCloseDashboard();
	        Log.info("Click on Close dashboard");
	        Log.info("Verify user navigated to Magnus Health Partner Login");
	      assertEquals(loginPage.getCurrentPageURL(),System.getProperty("BaseURL")+System.getProperty("MHPLoginURL"),"Validate is user is in Magnus healthportal Page");
	        mha.clickOnlogoutlink();
	        Log.info("Click on Logout link");
	 }
}
