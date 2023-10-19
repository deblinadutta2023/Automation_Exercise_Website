package APW_details;


	import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

	public class APW_Home_page {
					public WebDriver driver;
			WebDriverWait wait;
			Actions act;
			JavascriptExecutor js;
			public APW_Home_page(WebDriver driver) 
				 {
					this.driver=driver;
					PageFactory.initElements(driver,this);
					wait=new WebDriverWait(driver,Duration.ofSeconds(10));
					js = (JavascriptExecutor) driver;
					act=new Actions(driver);
				}
			
			//Repository Of Home Page WebElements//
			@FindBy(linkText= "Contact us")WebElement contactUsLink;
			@FindBy(xpath = "//div[@class='col-sm-12']")WebElement contactUs;
			@FindBy(xpath = "//input[@name='name']")WebElement name;
			@FindBy(xpath = "//input[@name='email']")WebElement email;
			@FindBy(xpath = "//input[@name='subject']")WebElement subject;
			@FindBy(xpath = "//textarea[@id='message']")WebElement message;
			@FindBy(xpath = "//input[@name='upload_file']")WebElement uploadfile;
			@FindBy(xpath = "//input[@name='submit']")WebElement submit;
			@FindBy(xpath = "//div[@class='status alert alert-success']")WebElement successMsg;
			@FindBy(linkText = "Home")WebElement homeBtn;
			@FindBy(linkText = "Test Cases")WebElement testCasesLink;
			@FindBy(xpath = "//div[@class='container']/descendant::b")WebElement testCases;
			
			//Repository Of Home Page/ Recommended Items (TestCases 22) WebElements//
			@FindBy(xpath = "//div[@class='recommended_items']")WebElement recommndedItems;
			@FindBy(xpath = "//h2[text()='recommended items']//following::a[text()='Add to cart'][3]")WebElement recDressCartBtn1;
			@FindBy(xpath = "//u[normalize-space()='View Cart']")WebElement viewCartLink;
			@FindBy(xpath = "//div[@class='modal-content']")WebElement confirmAlert;
			@FindBy(xpath = "//table[@id='cart_info_table']/child::tbody[1]/child::tr")List<WebElement>tableRows;
			@FindBy(xpath = "//li[@class='active']")WebElement shoppingCart;
			@FindBy(xpath = "//table[@id='cart_info_table']/child::tbody[1]/child::tr[1]/child::td[2]")WebElement prodDescription;
			
			public void contactus() throws IOException {
				wait.until(ExpectedConditions.visibilityOf(contactUsLink)); 
				Assert.assertTrue(contactUsLink.isDisplayed());
				
				contactUsLink.click();
				wait.until(ExpectedConditions.visibilityOf(contactUs));
				Assert.assertTrue(contactUs.isDisplayed());
				//excel sheet path---- registration form
				String filePath="C:\\Deblina_study_personal_doc\\TESTING\\PROJECT_AUTOMATION\\2nd_project\\Automation2nd_project.xlsx";
				  FileInputStream fis=new FileInputStream(filePath);
					XSSFWorkbook workBook=new XSSFWorkbook(fis);
					XSSFSheet sheet=workBook.getSheet("cont_us");
					name.sendKeys(sheet.getRow(1).getCell(0).toString());
					email.sendKeys(sheet.getRow(1).getCell(1).toString());
					subject.sendKeys(sheet.getRow(1).getCell(2).toString());
					message.sendKeys(sheet.getRow(1).getCell(3).toString());
					uploadfile.sendKeys("C:\\Users\\souro\\Desktop\\calEnder.JPG");
					submit.click();
					Alert alt=driver.switchTo().alert();
					alt.accept();
					wait.until(ExpectedConditions.visibilityOf(successMsg));
					Assert.assertTrue(successMsg.isDisplayed());  // verifying Success! Your details have been submitted successfully.' element is visible
					System.out.println("Success! Your details have been submitted successfully!");
					homeBtn.click();
			}
					
					public void testCases_Page() throws InterruptedException
					{  
						wait.until(ExpectedConditions.visibilityOf(testCasesLink));
						Assert.assertTrue(testCasesLink.isDisplayed());  // verifying TestCases element is visible
						testCasesLink.click();	
						Thread.sleep(5000);
						
				        // verifying TestCases page is visible
						Assert.assertTrue(testCases.getText().equals("TEST CASES"));  
						System.out.println("Verified! User is navigated to test cases page successfully");
					}						
					public void recommendeditems_AddtoCart() throws InterruptedException
					{  		
						wait.until(ExpectedConditions.visibilityOf(contactUsLink));
						  act.scrollToElement(recommndedItems).build().perform();
						  Assert.assertTrue(recommndedItems.isDisplayed());
						  wait.until(ExpectedConditions.visibilityOf(recDressCartBtn1));
						  js.executeScript("arguments[0].click()",recDressCartBtn1);
						  wait.until(ExpectedConditions.visibilityOf(confirmAlert));
						  viewCartLink.click();
						  wait.until(ExpectedConditions.visibilityOf(shoppingCart));
							System.out.println("No. Of Products in the Cart: "+tableRows.size());
							System.out.println("No. Of Products in the Cart: "+prodDescription.getText());	
					}
	}
		

