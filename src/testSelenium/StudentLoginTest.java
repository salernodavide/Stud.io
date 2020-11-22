package testSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StudentLoginTest {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\matte\\Documents\\GitHub\\Stud.io\\Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/Stud.io/");
		driver.findElement(By.xpath("//*[@id=\"user\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"emailLogin\"]")).sendKeys("stu@live.it");
		driver.findElement(By.xpath("//*[@id=\"passwordLogin\"]")).sendKeys("1234");
	}

}
