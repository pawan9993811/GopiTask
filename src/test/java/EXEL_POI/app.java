package EXEL_POI;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
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
import java.io.IOException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

import com.thoughtworks.selenium.webdriven.commands.*;

import static org.openqa.selenium.OutputType.*;

public class app {
	public static  FirefoxDriver wd;
	public static String uploadfile = "D:\\project selenium\\FrameWork\\Mavensample\\images\\Avatar\\Logo.png";
	public static String fileLocation = "D:/project selenium/FrameWork/Mavensample/src/main/java/MavenSam/Mavensample/Main file 22.06.2016.xls";

	@BeforeMethod
	public void setUp() throws Exception {
		wd = new FirefoxDriver();
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wd.manage().window().maximize();

	}

	@Test
	public void app() throws InterruptedException, RowsExceededException, WriteException  {

		/*Xls_Reader read= new Xls_Reader("D:\\project selenium\\FrameWork\\Mavensample\\src\\main\\java\\MavenSam\\Mavensample\\Datatest.xlsx");
    	int rowCount =	read.getRowCount("Sheet1");
        System.out.println(">>>>> Row " +rowCount );
		 */  

		


		wd.get("http://house1.in/app/admin/");
		loginapp();


		try {
			File src=new File("D:/project selenium/FrameWork/Mavensample/src/main/java/MavenSam"
					+ "/Mavensample/Main file 22.06.2016.xls");
			
			Workbook wb=Workbook.getWorkbook(src);
			Sheet sh1=    wb.getSheet(0);
			int rowCount1 =sh1.getRows();

			for (int i = 1; i <10; i++) {

				Cell  c1=sh1.getCell(0,i);
				String Name=c1.getContents();



				wd.findElement(By.id("first-name")).click();
				wd.findElement(By.id("first-name")).clear();
				wd.findElement(By.id("first-name")).sendKeys(Name);

				//Import image
				wd.findElement(By.name("profile_image")).click();

				try {

					uploadfilewithskili(uploadfile);
				} catch (FindFailed e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Name ===> " +Name); 
				Cell  c2=sh1.getCell(3,i);
				String Number=c2.getContents();

				wd.findElement(By.id("number")).click();
				wd.findElement(By.id("number")).clear();
				wd.findElement(By.id("number")).sendKeys(Number);
				System.out.println("Number ===> "+ Number);

				Cell  c3=sh1.getCell(1,i);
				String Address1=c3.getContents();
				System.out.println("Address1 ===> "+Address1);

				Cell  c4=sh1.getCell(2,i);
				String Address2=c4.getContents();
				System.out.println("Address1 ===>"+Address2);

				wd.findElement(By.id("address")).click();
				wd.findElement(By.id("address")).clear();
				wd.findElement(By.id("address")).sendKeys(Address1+","+Address2);
				System.out.println("Address===> " +Address1+","+Address2);

				Cell  c5=sh1.getCell(4,i);
				String Sub_Category=c5.getContents();
				System.out.println("Sub_Category===> " +Sub_Category);

				JavascriptExecutor js = (JavascriptExecutor)wd;
				js.executeScript("document.getElementById('parent_cate').style.display='block';");

				//Then Select required value
				Select dropdown = new Select(wd.findElement(By.id("parent_cate")));
				dropdown.selectByVisibleText(Sub_Category);
				Thread.sleep(2000);

				wd.findElement(By.name("submit")).click();
				Thread.sleep(5000);

				WritableWorkbook writeBook = Workbook.createWorkbook(src);
				writeBook.createSheet("Sheet1", 0);
				WritableSheet writesheet = writeBook.getSheet(0);

				Label label = new Label(6, i, "Pass");
				writesheet.addCell(label);
				writeBook.write();


			}


		} catch (BiffException e1) {
			// TODO Auto-generated catch block
			
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}






























		/*




    	for (int i = 2; i <rowCount-1 ; i++) {

    		//Enter Name
    		String Name = read.getCellData("Sheet1", "Name", i);
    		System.out.println(Name);
    		 wd.findElement(By.id("first-name")).click();
    	      wd.findElement(By.id("first-name")).clear();
    	      wd.findElement(By.id("first-name")).sendKeys(Name);


    	      //Import image
    	      wd.findElement(By.name("profile_image")).click();

    	      try {

				uploadfilewithskili(uploadfile);
			} catch (FindFailed e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
    	        Thread.sleep(5000);

    	        read.setCellData("Sheet1","Result", i, "Pass");

		}*/



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

	public static void uploadfilewithskili(String locationOfuploadfile) throws FindFailed{

		Screen sc = new Screen();


		Pattern find = new Pattern("D:\\project selenium\\FrameWork\\Mavensample\\images\\Find.png");
		Pattern open = new Pattern("D:\\project selenium\\FrameWork\\Mavensample\\images\\open.PNG");

		sc.type(find, locationOfuploadfile);
		sc.click(open);

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
