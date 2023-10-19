package APW_details;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class APW_SignUpLogin {
	
		public WebDriver driver;
		WebDriverWait wait;
		public APW_SignUpLogin(WebDriver driver) 
			 {
				this.driver=driver;
				PageFactory.initElements(driver,this);
				wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			}
		//Repository Of Home Page WebElements//

		@FindBy(xpath="//ul[@class='nav navbar-nav']/child::li/a")List<WebElement> autoExcercise;
		@FindBy(xpath="//a[normalize-space()='Signup / Login']")WebElement signLog;

		//Repository Of SignUp Page WebElements//
		@FindBy(xpath="//div[@class='signup-form']")WebElement newUserForm;
		@FindBy(xpath="//input[@name='name']")WebElement regName;
		@FindBy(xpath="(//input[@name='email'])[2]")WebElement regEmail;
		@FindBy(xpath="(//button[@type='submit'])[2]")WebElement signupBtn;
		@FindBy(xpath = "//div[@class='col-sm-4']/descendant::p")WebElement signUpErrMsg;

		//Repository Of Account Information Page WebElements//

		@FindBy(xpath = "//div[@class='login-form']")WebElement loginAccForm;
	    @FindBy(xpath="//input[@type='text']")List<WebElement> inforList;
		@FindBy(xpath = "//input[@id='id_gender2']")WebElement titleMrs;
		@FindBy(xpath = "//input[@id='name']")WebElement name;
		@FindBy(xpath = "//input[@id='email']")WebElement email;
		@FindBy(xpath = "//input[@id='password']")WebElement password;
		@FindBy(xpath = "//select[@id='days']")WebElement birthDays;
		@FindBy(xpath = "//select[@id='months']")WebElement birthMonths;
		@FindBy(xpath = "//select[@id='years']")WebElement birthYear;
		@FindBy(xpath = "//input[@id='newsletter']")WebElement newsCheckBox;
		@FindBy(xpath = "//input[@id='optin']")WebElement offerCheckBox;
		@FindBy(xpath = "//input[@id='first_name']")WebElement firstName;
		@FindBy(xpath = "//input[@id='last_name']")WebElement lastName;
		@FindBy(xpath = "//input[@id='company']")WebElement company;
		@FindBy(xpath = "//input[@id='address1']")WebElement address1;
		@FindBy(xpath = "//select[@id='country']")WebElement country;
		@FindBy(xpath = "//input[@id='state']")WebElement state;
		@FindBy(xpath = "//input[@id='city']")WebElement city;
		@FindBy(xpath = "//input[@id='zipcode']")WebElement zipCode;
		@FindBy(xpath = "//input[@id='mobile_number']")WebElement mobileNum;
		@FindBy(xpath = "//button[contains(text(),'Create Account')]")WebElement createAccBtn;

		//Home page WebElements
		@FindBy(xpath = "//h2[@data-qa='account-created']")WebElement confirmMsg;
		@FindBy(xpath = "//a[@data-qa='continue-button']")WebElement continueBtn;
		@FindBy(xpath = "//div[@id='dismiss-button']")WebElement dismissBtn;
		@FindBy(linkText = "Delete Account")WebElement delAcc;
		@FindBy(xpath = "//div[@class='container']/descendant::b")WebElement delMsg;

		//Repository Of Login Page WebElements//
		@FindBy(xpath = "//div[@class='login-form']")WebElement loginForm;
		@FindBy(xpath="(//input[@name='email'])[1]")WebElement loginEmail;
		@FindBy(xpath="//input[@type='password']")WebElement passWord;
		@FindBy(xpath="(//button[@type='submit'])[1]")WebElement loginBtn;
		@FindBy(xpath = "//div[@class='col-sm-4 col-sm-offset-1']/descendant::p")WebElement loginErrMsg;
		@FindBy(xpath = "//div[@class='col-sm-8']/descendant::b")WebElement loginIdMsg;
		@FindBy(linkText = "Logout")WebElement logOutBtn;
		
		public void launchUrl() {
			driver.manage().window().maximize();
			wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			driver.get("https://automationexercise.com/");
			wait.until(ExpectedConditions.visibilityOfAllElements(autoExcercise));
			//AdBlocker window handling//
			Set<String>handles=driver.getWindowHandles();
			Iterator<String> itr=handles.iterator();
			String parentWindow=itr.next();
			String window1=itr.next();
	        driver.switchTo().window(parentWindow);
		}
		
		public void signup_loginLink() {
			Assert.assertTrue(signLog.isDisplayed());
			wait.until(ExpectedConditions.visibilityOf(signLog)); 
			signLog.click();
		}
			
	       
		public void reg_newUser() throws IOException {
			wait.until(ExpectedConditions.visibilityOf(newUserForm));
			Assert.assertTrue(newUserForm.isDisplayed());//Verification of a new user sign up/login link click
			//excel sheet path----new user sign up
			String filePath="C:\\Deblina_study_personal_doc\\TESTING\\PROJECT_AUTOMATION\\2nd_project\\Automation2nd_project.xlsx";
			  FileInputStream fis=new FileInputStream(filePath);
				XSSFWorkbook workBook=new XSSFWorkbook(fis);
				XSSFSheet sheet=workBook.getSheet("Signup");
				regName.sendKeys(sheet.getRow(1).getCell(0).toString());
				regEmail.sendKeys(sheet.getRow(1).getCell(1).toString());
				signupBtn.click();
				fis.close();
				workBook.close();
				
						
		}
		
		public void accountInfo() throws IOException, InterruptedException {
			wait.until(ExpectedConditions.visibilityOf(loginAccForm));
			Assert.assertTrue(loginAccForm.isDisplayed());//Verification of new user sign up form
			//excel sheet path---- registration form
			String filePath="C:\\Deblina_study_personal_doc\\TESTING\\PROJECT_AUTOMATION\\2nd_project\\Automation2nd_project.xlsx";
			  FileInputStream fis=new FileInputStream(filePath);
				XSSFWorkbook workBook=new XSSFWorkbook(fis);
				XSSFSheet sheet=workBook.getSheet("Registration_form");
				password.sendKeys(sheet.getRow(1).getCell(2).getRawValue());
				birthDays.sendKeys(sheet.getRow(1).getCell(3).getRawValue());
				birthMonths.sendKeys(sheet.getRow(1).getCell(4).toString());
				birthYear.sendKeys(sheet.getRow(1).getCell(5).getRawValue());
				newsCheckBox.click();
				offerCheckBox.click();
				
				firstName.sendKeys(sheet.getRow(1).getCell(6).toString());
				lastName.sendKeys(sheet.getRow(1).getCell(7).toString());
				company.sendKeys(sheet.getRow(1).getCell(8).toString());
				address1.sendKeys(sheet.getRow(1).getCell(9).toString());
				country.sendKeys(sheet.getRow(1).getCell(10).toString());
				state.sendKeys(sheet.getRow(1).getCell(11).toString());
				city.sendKeys(sheet.getRow(1).getCell(12).toString());
				zipCode.sendKeys(sheet.getRow(1).getCell(13).getRawValue());
				mobileNum.sendKeys(sheet.getRow(1).getCell(14).getRawValue());
				createAccBtn.click();
				fis.close();
				workBook.close();
				///////////////////////////////
				
				//After Account creation- verify confirmation message
				wait.until(ExpectedConditions.visibilityOf(confirmMsg));
				Assert.assertTrue(confirmMsg.isDisplayed());
				continueBtn.click();
				Thread.sleep(3000);

				//AdBlocker window handling
				/*Set<String>handles=driver.getWindowHandles();
				Iterator<String> itr=handles.iterator();
				String parentWindow=itr.next();
				String window1=itr.next();
				driver.switchTo().window(parentWindow);*/

				//Verifying User Logged in as Username//
				wait.until(ExpectedConditions.visibilityOf(loginIdMsg));
				if(loginIdMsg.getText().equals("Deblina"))
				{   assertEquals(loginIdMsg.getText(), sheet.getRow(1).getCell(0).toString());
				System.out.println("Logged in as username");
				}
		}
		public void del_account()throws IOException
		{  wait.until(ExpectedConditions.visibilityOf(delAcc));
				delAcc.click();
				wait.until(ExpectedConditions.visibilityOf(delMsg));
				Assert.assertTrue(delMsg.getText().equals("ACCOUNT DELETED!"));
				continueBtn.click();
				}


				public void login_ValidCredentials() throws IOException
				{  wait.until(ExpectedConditions.visibilityOf(loginForm));
				Assert.assertTrue(loginForm.isDisplayed());
				String filePath="C:\\Deblina_study_personal_doc\\TESTING\\PROJECT_AUTOMATION\\2nd_project\\Automation2nd_project.xlsx";
				FileInputStream fis=new FileInputStream(filePath);
				XSSFWorkbook workBook=new XSSFWorkbook(fis);
				XSSFSheet sheet=workBook.getSheet("Valid_Login");
				loginEmail.sendKeys(sheet.getRow(1).getCell(0).toString());
				passWord.sendKeys(sheet.getRow(1).getCell(1).getRawValue());
				loginBtn.click();

				wait.until(ExpectedConditions.visibilityOf(loginIdMsg));
				if(loginIdMsg.getText().equals("StutiDutta"))
				{   assertEquals(loginIdMsg.getText(), sheet.getRow(1).getCell(2).toString());
				System.out.println("Logged in as username");
				}
				fis.close();
				workBook.close();
				}

				public void login_InvalidCredentials() throws IOException
				{  wait.until(ExpectedConditions.visibilityOf(loginForm));
				Assert.assertTrue(loginForm.isDisplayed());
				String filePath="C:\\Deblina_study_personal_doc\\TESTING\\PROJECT_AUTOMATION\\2nd_project\\Automation2nd_project.xlsx";
				FileInputStream fis=new FileInputStream(filePath);
				XSSFWorkbook workBook=new XSSFWorkbook(fis);
				XSSFSheet sheet=workBook.getSheet("Invalid_login");
				loginEmail.sendKeys(sheet.getRow(1).getCell(0).toString());
				passWord.sendKeys(sheet.getRow(1).getCell(1).getRawValue());
				loginBtn.click();
				wait.until(ExpectedConditions.visibilityOf(loginErrMsg));
				if(loginErrMsg.getText().equals("Your email or password is incorrect!"))
				{   assertEquals(loginErrMsg.getText(), sheet.getRow(1).getCell(2).toString());
				System.out.println("User is getting Error Message");
				}
				}

				public void logout_User() throws IOException
				{ wait.until(ExpectedConditions.visibilityOf(loginIdMsg));
				logOutBtn.click();
				wait.until(ExpectedConditions.visibilityOf(loginForm));
				Assert.assertTrue(loginForm.isDisplayed());
				System.out.println("User is successfully Logged out");
				}

				public void reg_ExistCredentials() throws IOException
				{wait.until(ExpectedConditions.visibilityOf(newUserForm));
				Assert.assertTrue(newUserForm.isDisplayed()); //Verification of 'New User Signup!' is visible
				String filePath="C:\\Deblina_study_personal_doc\\TESTING\\PROJECT_AUTOMATION\\2nd_project\\Automation2nd_project.xlsx";
				FileInputStream fis=new FileInputStream(filePath);
				XSSFWorkbook workBook=new XSSFWorkbook(fis);
				XSSFSheet sheet=workBook.getSheet("existing_email");
				System.out.println("Sign up succesfully");
				regName.sendKeys(sheet.getRow(1).getCell(0).toString());
				regEmail.sendKeys(sheet.getRow(1).getCell(1).toString());
				signupBtn.click();

				wait.until(ExpectedConditions.visibilityOf(signUpErrMsg));
				if(signUpErrMsg.getText().equals("Email Address already exist!"))
				{   assertEquals(signUpErrMsg.getText(), sheet.getRow(1).getCell(2).toString());
				System.out.println("Email Address already exist!");
				}

				fis.close();
				workBook.close();

		
}
		
}	