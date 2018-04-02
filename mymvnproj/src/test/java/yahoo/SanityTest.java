package yahoo;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ATUReportsListener.class,ConfigurationListener.class,MethodListener.class})
public class SanityTest extends MainClass
{
	
	{
		System.setProperty("atu.reporter.config","e:\\6am_feb18\\atu.properties");
	}
		
	@Test
	@Parameters({"browser"})
	public void sanityTesting(String br) throws Exception
	{
		if(br.matches("firefox"))
		{
			driver=new FirefoxDriver();
		}
		
		FileInputStream fin=new FileInputStream("E:\\6AM_feb18\\testdata.xlsx");
		  XSSFWorkbook wb=new XSSFWorkbook(fin);
		  XSSFSheet ws=wb.getSheet("sanitytest");
		  Row row;
		  String classname,methodname;
		  
		  for(int r=1;r<=ws.getLastRowNum();r++)
		  {
			  row=ws.getRow(r);
			  if(row.getCell(4).getStringCellValue().matches("yes"))
			  {
				  classname=row.getCell(2).getStringCellValue();
				  methodname=row.getCell(3).getStringCellValue();
				  
				  Class c=Class.forName(classname); 
				  Method m=c.getMethod(methodname,null); 
				  Object obj=c.newInstance();  
				  m.invoke(obj, null);			  
			  }
		  }
		  
		  fin.close();
		/*Home h=new Home(driver);
		h.login();
		
		Compose c=new Compose(driver);
		c.sendmail();
		c.signout();*/
				
		
	}
}
