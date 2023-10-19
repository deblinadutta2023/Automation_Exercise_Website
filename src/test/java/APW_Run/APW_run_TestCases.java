package APW_Run;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import APW_details.APW_Home_page;
import APW_details.APW_PaymentProcedure;
import APW_details.APW_ProductPage;
import APW_details.APW_Products_Category;
import APW_details.APW_SignUpLogin;
import io.github.bonigarcia.wdm.WebDriverManager;

public class APW_run_TestCases {

	WebDriver driver;
	APW_SignUpLogin signlog;
	APW_Home_page homepg;
	APW_ProductPage prodpg;
	APW_PaymentProcedure paymentProd;
	APW_Products_Category prodCategory;
	
	@BeforeMethod 
	public void launchBrowser() {
		ChromeOptions opt=new ChromeOptions();
		opt.addExtensions(new File("./Extensions/AdBlocker_chrome.crx"));
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver(opt);
		signlog=new APW_SignUpLogin(driver);
		homepg=new APW_Home_page(driver);
		prodpg=new APW_ProductPage(driver);
		paymentProd=new APW_PaymentProcedure(driver);
		prodCategory=new APW_Products_Category(driver);
		
	}
	@Test (priority=1)
	  public void registration_NewUser() throws InterruptedException, IOException {
		signlog.launchUrl();
		signlog.signup_loginLink();
		signlog.reg_newUser();
		signlog.accountInfo();
		
		}
	@Test(priority=2)
	  public void login_valid_cred() throws InterruptedException, IOException {
		signlog.launchUrl();
		signlog.signup_loginLink();
		signlog.login_ValidCredentials();
			}
	@Test (priority=3)
	  public void login_invalid_cred() throws InterruptedException, IOException {
		signlog.launchUrl();
		signlog.signup_loginLink();
		signlog.login_InvalidCredentials();
	}
	@Test (priority=4)
	  public void logout_user() throws InterruptedException, IOException {
		signlog.launchUrl();
		signlog.signup_loginLink();
		signlog.login_ValidCredentials();
		signlog.logout_User();
	}
	@Test (priority=5)
	  public void reg_existing_email() throws InterruptedException, IOException {
		signlog.launchUrl();
		signlog.signup_loginLink();
		signlog.reg_ExistCredentials();
	}
	@Test (priority=6)
	  public void homepg_contactus() throws InterruptedException, IOException {
		signlog.launchUrl();
		homepg.contactus();
		
	}
	@Test (priority=7)
	  public void homepg_testcases() throws InterruptedException, IOException {
		signlog.launchUrl();
		homepg.testCases_Page();
	}
	@Test (priority=8)
	  public void viewProduct_productDetails() throws InterruptedException, IOException {
		signlog.launchUrl();
		prodpg.productlink_Click();
		prodpg.prod_Details();
	}
	@Test (priority=9)
	  public void product_Search() throws InterruptedException, IOException {
		signlog.launchUrl();
		prodpg.productlink_Click();
		prodpg.search_Prod();
		
	}
	@Test (priority=10)
	  public void subscription_Homepage() throws InterruptedException, IOException {
		signlog.launchUrl();
		prodpg.subscription_HomePG();
	}
	
	@Test (priority=11)
	  public void subscription_Cartpage() throws InterruptedException, IOException {
		signlog.launchUrl();
		prodpg.subscription_CartPG();
	}
	@Test (priority=12)
	  public void addproduct_Cart() throws InterruptedException, IOException {
		signlog.launchUrl();
		prodpg.productlink_Click();
		prodpg.addProducts_Cart();
	}
	@Test (priority=13)
	  public void quantityprod_cart() throws InterruptedException, IOException {
		signlog.launchUrl();
		prodpg.productlink_Click();
		prodpg.prod_Details();
		prodpg.quantityProd_Cart();
	}
	@Test (priority=14)
	  public void placeOrder_Register_timeofCheckOut() throws InterruptedException, IOException {
		signlog.launchUrl();
		prodpg.productlink_Click();
		prodpg.addProducts_Cart();
		paymentProd.click_ProceedCheckOut();
		paymentProd.click_RegisterLoginBtn();
		signlog.reg_newUser();
		signlog.accountInfo();
		  paymentProd.click_CartLink();
		  String[] cartdetails=paymentProd.review_Order();
		  paymentProd.click_ProceedCheckOut();
		  paymentProd.verify_Address();
		  String[] reviewOrderDetails=paymentProd.review_Order();
		  for(int i=0; i<cartdetails.length; i++)
		  {assertEquals(cartdetails[i], reviewOrderDetails[i]);}
		  paymentProd.payment_Method();
		  signlog.del_account();
		}
	
	@Test (priority=15)
	  public void placeOrder_Register_BeforeCheckOut() throws InterruptedException, IOException {
		signlog.launchUrl();
		signlog.signup_loginLink();
		signlog.reg_newUser();
		signlog.accountInfo();
		prodpg.productlink_Click();
		prodpg.addProducts_Cart();
		  String[] cartdetails=paymentProd.review_Order();
		  System.out.println("-Product details Of Cart Page-");
		  for(int a=1;a<cartdetails.length;a++)
		  {System.out.print(cartdetails[a]+",  ");}
		  System.out.println();
		  paymentProd.click_ProceedCheckOut();
		  paymentProd.verify_Address();
		  String[] reviewOrderDetails=paymentProd.review_Order();
		  System.out.println("-Product details Of Review Your Order Page-");
		  for(int i=0; i<cartdetails.length; i++)
		  {assertEquals(cartdetails[i], reviewOrderDetails[i]);
		   System.out.print(reviewOrderDetails[i]+"-  ");
		  }
		  System.out.println();
		  paymentProd.payment_Method();
		 signlog.del_account();
		}
	
	@Test(priority=16)

	public void placeOrder_Login_BeforeCheckOut() throws InterruptedException, IOException
	{ signlog.launchUrl();
	signlog.signup_loginLink();
	signlog.login_ValidCredentials();
	prodpg.productlink_Click();
	prodpg.addProducts_Cart();
	 String[] cartdetails=paymentProd.review_Order();
	 paymentProd.click_ProceedCheckOut();
	 paymentProd.verify_Address();
	  String[] reviewOrderDetails=paymentProd.review_Order();
	  for(int i=0; i<cartdetails.length; i++)
	  {assertEquals(cartdetails[i], reviewOrderDetails[i]);}
	  paymentProd.payment_Method();
	  signlog.logout_User();
	}
	@Test(priority=17)

	public void remove_ProductFromCart() throws InterruptedException
	{signlog.launchUrl();
	paymentProd.remove_Products();
	}

	@Test(priority=18)

	public void verify_CatogoryOfProducts() throws InterruptedException
	{	signlog.launchUrl();
	prodCategory.view_Category();
	prodCategory.view_MenCategory();
	}
	
	@Test(priority=19)

	public void verify_ProductsBrands() throws InterruptedException
	{signlog.launchUrl();
	prodpg.productlink_Click();
	//prodCategory.select_BrandHM();
	//prodCategory.select_BrandAllenjunior();
	prodCategory.select_BrandBIBA();
	prodCategory.select_BrandBabyHug();
		}
	
	@Test(priority=20)

	public void searchProduct_verifyCart() throws InterruptedException, IOException
	{	signlog.launchUrl();
	prodpg.productlink_Click();
	prodpg.search_Prod();
	prodpg.addProducts_Cart();
	String[] beforeLoginCartItems=paymentProd.review_Order();
	  System.out.println("----------------Product details In Cart Before Login-------------------");
	  for(int a=1;a<beforeLoginCartItems.length;a++)
	  {System.out.print(beforeLoginCartItems[a]+",  ");}
	  System.out.println();
	signlog.signup_loginLink();
	signlog.login_ValidCredentials();
	paymentProd.click_CartLink();
	 String[] afterLoginCartItems=paymentProd.review_Order();
	  System.out.println("----------------Product details In Cart After Login-------------------");
	  for(int i=0; i<afterLoginCartItems.length; i++)
	  {assertEquals(beforeLoginCartItems[i], afterLoginCartItems[i]);
	  System.out.print(afterLoginCartItems[i]+",  ");
	  }
	  System.out.println();
	  signlog.logout_User();
	}
	
	@Test(priority=21)

	public void add_ProductReview() throws InterruptedException, IOException
	{	signlog.launchUrl();
	prodpg.productlink_Click();
	prodpg.prod_Details();
	prodpg.add_Review();
	}
	@Test(priority=22)

	public void recommandedProduct_Cart() throws InterruptedException, IOException
	{	signlog.launchUrl();
	homepg.recommendeditems_AddtoCart();
	}
	
}
	
	


