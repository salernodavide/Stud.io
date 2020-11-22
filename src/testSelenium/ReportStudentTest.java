package testSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReportStudentTest {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\david\\Documents\\GitHub\\Stud.io\\Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/Stud.io/");
		driver.findElement(By.xpath("//*[@id=\"user\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"emailLogin\"]")).sendKeys("librarian@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"passwordLogin\"]")).sendKeys("1234");
		driver.findElement(By.xpath("//*[@id=\"loginBtn\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[4]/form/a/input")).click();
		driver.findElement(By.xpath("//*[@id=\"supervise\"]/button[2]")).click();
		driver.findElement(By.xpath("/html/body/form/div/div[7]/button/font")).click();
		driver.close();
	}

}
