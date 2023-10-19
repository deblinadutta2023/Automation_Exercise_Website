package APW_details;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class APW_Products_Category
{	public WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions act;

public  APW_Products_Category (WebDriver wd)
	{ driver=wd;
	  PageFactory.initElements(driver, this);
	  wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	  js = (JavascriptExecutor) driver;
	  act=new Actions(driver);

	}

//Repository Of WebElements for TestCase 18//
	@FindBy(xpath = "//a[normalize-space()='Women']")WebElement categoryWomen;
	@FindBy(xpath = "//a[normalize-space()='Men']")WebElement categoryMen;
	@FindBy(xpath = "(//a[contains(text(),'Dress')])[1]")WebElement womenDress;
	@FindBy(xpath = "//h2[@class='title text-center']")WebElement searchedCategoryTitle;
	@FindBy(xpath = "//h2[normalize-space()='Category']")WebElement sideBarCategory;
	@FindBy(xpath = "//a[normalize-space()='Tshirts']")WebElement MenTshirts;
//Repository Of WebElements for TestCase 19//
	@FindBy(xpath = "//h2[normalize-space()='Brands']")WebElement sideBarBrand;
	@FindBy(xpath = "//a[@href='/brand_products/Biba']")WebElement brandBiba;
	@FindBy(xpath = "//a[@href='/brand_products/H&M']")WebElement hm;
	@FindBy(xpath = "//a[@href='/brand_products/Allen Solly Junior']")WebElement allenjunior;
	@FindBy(xpath = "//a[@href='/brand_products/Babyhug']")WebElement brandBabyHug;

//View Category Products
	public void view_Category() throws InterruptedException
	{ wait.until(ExpectedConditions.visibilityOf(sideBarCategory));
	  Assert.assertTrue(sideBarCategory.getText().equals("CATEGORY"));
	  js.executeScript("arguments[0].click()", categoryWomen);//Women category
	  wait.until(ExpectedConditions.visibilityOf(womenDress));
	  js.executeScript("arguments[0].click()", womenDress);
	  Thread.sleep(3000);
	  wait.until(ExpectedConditions.visibilityOf(searchedCategoryTitle));
	  Assert.assertTrue(searchedCategoryTitle.getText().equals("WOMEN - DRESS PRODUCTS"));
	  System.out.println(" Verified that Category Page is displayed With Title Of 'WOMEN - TOPS PRODUCTS'");
	}
//MenCategory
	public void view_MenCategory()
	{wait.until(ExpectedConditions.visibilityOf(sideBarCategory));
	 js.executeScript("arguments[0].click()", categoryMen);	
	 wait.until(ExpectedConditions.visibilityOf(MenTshirts));
	 js.executeScript("arguments[0].click()", MenTshirts);	
	 wait.until(ExpectedConditions.visibilityOf(searchedCategoryTitle));
	 Assert.assertTrue(searchedCategoryTitle.getText().equals("MEN - TSHIRTS PRODUCTS"));
	 System.out.println(" Verified that Category Page is displayed With Title Of 'MEN - TSHIRTS PRODUCTS'");
	}
	/*public void select_BrandHM()
	{wait.until(ExpectedConditions.visibilityOf(sideBarBrand));
	  System.out.print(sideBarBrand.getText());
	  Assert.assertTrue(sideBarBrand.getText().equals("BRANDS"));
	  js.executeScript("arguments[0].click()", hm);
	  wait.until(ExpectedConditions.visibilityOf(searchedCategoryTitle));
	  System.out.print(searchedCategoryTitle.getText());
	  Assert.assertTrue(searchedCategoryTitle.getText().equals("BRAND - H&M PRODUCTS"));
	  System.out.println(" Verified that Category Page is displayed With Title Of 'BRAND - H&M PRODUCTS'"); 
	}*/
	
	/*public void select_BrandAllenjunior()
	{ wait.until(ExpectedConditions.visibilityOf(sideBarBrand));
	  js.executeScript("arguments[0].click()", allenjunior);
	  wait.until(ExpectedConditions.visibilityOf(searchedCategoryTitle));
	  Assert.assertTrue(searchedCategoryTitle.getText().equals("BRAND - ALLEN SOLLY JUNIOR PRODUCTS"));
	  System.out.println(" Verified that Category Page is displayed With Title Of 'BRAND - BrandAllenjunior'"); 
	}*/
	public void select_BrandBIBA()
	{wait.until(ExpectedConditions.visibilityOf(sideBarBrand));
	  Assert.assertTrue(sideBarBrand.getText().equals("BRANDS"));
	  js.executeScript("arguments[0].click()", brandBiba);
	  wait.until(ExpectedConditions.visibilityOf(searchedCategoryTitle));
	  Assert.assertTrue(searchedCategoryTitle.getText().equals("BRAND - BIBA PRODUCTS"));
	  System.out.println(" Verified that Category Page is displayed With Title Of 'BRAND - BIBA PRODUCTS'"); 

	}

	public void select_BrandBabyHug()
	{ wait.until(ExpectedConditions.visibilityOf(sideBarBrand));
	  js.executeScript("arguments[0].click()", brandBabyHug);
	  wait.until(ExpectedConditions.visibilityOf(searchedCategoryTitle));
	  Assert.assertTrue(searchedCategoryTitle.getText().equals("BRAND - BABYHUG PRODUCTS"));
	  System.out.println(" Verified that Category Page is displayed With Title Of 'BRAND - BABYHUG PRODUCTS'");

}
}
