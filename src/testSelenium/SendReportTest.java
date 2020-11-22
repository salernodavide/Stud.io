package testSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SendReportTest {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\matte\\eclipse-workspace\\Stud.io\\Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/Stud.io/");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"user\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"emailLogin\"]")).sendKeys("stu");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"passwordLogin\"]")).sendKeys("stu");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"loginBtn\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"searchField\"]")).sendKeys("bib");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"searchBtn\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"libbib\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"librPageButtons\"]/p/a[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"tableReports\"]/thead/tr/th[4]/button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"reportFormTitle\"]")).sendKeys("provaSeleniumApiTitolo");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"reportFormDescription\"]")).sendKeys("provaSeleniumApiDescrizione");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"btnSendReport\"]")).click();
	}
}
