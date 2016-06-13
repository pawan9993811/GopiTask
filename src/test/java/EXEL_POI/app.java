package EXEL_POI;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

import com.thoughtworks.selenium.webdriven.commands.*;

import static org.openqa.selenium.OutputType.*;

public class app {
  public static  FirefoxDriver wd;
  public static String uploadfile = "C:\\Users\\Pawan\\Pictures\\avatar\\avatar1.png";
  
  
   @BeforeMethod
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void app() throws InterruptedException, AWTException {
        
    	Xls_Reader read= new Xls_Reader("D:\\project selenium\\FrameWork\\Mavensample\\src\\main\\java\\MavenSam\\Mavensample\\Datatest.xlsx");
        int rowCount =	read.getRowCount("Sheet1");
        System.out.println(">>>>> Row " +rowCount );
        
        
    	wd.get("http://house1.in/app/admin/");
    	loginapp();
    	
    	for (int i = 2; i <rowCount ; i++) {
    		 
    		//Enter Name
    		String Name = read.getCellData("Sheet1", "Name", i);
    		System.out.println(Name);
    		 wd.findElement(By.id("first-name")).click();
    	      wd.findElement(By.id("first-name")).clear();
    	      wd.findElement(By.id("first-name")).sendKeys(Name);
    	     
    	     
    	      //Import image
    	      wd.findElement(By.name("profile_image")).click();
    	     //wd.findElement(By.name("profile_image")).sendKeys(uploadfile);
    	    //put path to your image in a clipboard
    	      StringSelection ss = new StringSelection(uploadfile);
    	      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

    	      //imitate mouse events like ENTER, CTRL+C, CTRL+V
    	      Robot robot = new Robot();
    	      robot.delay(250);
    	      robot.keyPress(KeyEvent.VK_ENTER);
    	      robot.keyRelease(KeyEvent.VK_ENTER);
    	      robot.keyPress(KeyEvent.VK_CONTROL);
    	      robot.keyPress(KeyEvent.VK_V);
    	      robot.keyRelease(KeyEvent.VK_V);
    	      robot.keyRelease(KeyEvent.VK_CONTROL);
    	      robot.keyPress(KeyEvent.VK_ENTER);
    	      robot.delay(50);
    	      robot.keyRelease(KeyEvent.VK_ENTER);
    	      Thread.sleep(5000);
    	    // Enter Phone Number  
    	      String Number= read.getCellData("Sheet1", "Number", i);
    	        wd.findElement(By.id("number")).click();
    	      wd.findElement(By.id("number")).clear();
    	      wd.findElement(By.id("number")).sendKeys(Number);
    	      
    	      
    	      String Address= read.getCellData("Sheet1", "Address", i);
    	        wd.findElement(By.id("address")).click();
    	        wd.findElement(By.id("address")).clear();
    	        wd.findElement(By.id("address")).sendKeys(Address);
    	        
    	        String Sub_Category= read.getCellData("Sheet1", "Sub Category", i);
    	        JavascriptExecutor js = (JavascriptExecutor)wd;
    	        js.executeScript("document.getElementById('parent_cate').style.display='block';");
    	         
    	        //Then Select required value
    	        Select dropdown = new Select(wd.findElement(By.id("parent_cate")));
    	        dropdown.selectByVisibleText(Sub_Category);
    	     
    	      
    	        wd.findElement(By.name("submit")).click();
    	        Thread.sleep(2000);
    	        read.setCellData("Sheet1","Result", i, "Pass");
    	  	 
		}
    
    	
      
    }
    
    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    
    public static void loginapp(){
		  wd.findElement(By.name("username")).click();
	        wd.findElement(By.name("username")).clear();
	        wd.findElement(By.name("username")).sendKeys("Admin");
	        wd.findElement(By.name("password")).click();
	        wd.findElement(By.name("password")).clear();
	        wd.findElement(By.name("password")).sendKeys("Amar123*");
	        wd.findElement(By.name("submit")).click();
	        if (!wd.findElement(By.tagName("html")).getText().contains("Gentellela Alela!")) {
	            System.out.println("verifyTextPresent failed");
	        }
	        wd.findElement(By.linkText("Add Provider")).click();
	}
	
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
