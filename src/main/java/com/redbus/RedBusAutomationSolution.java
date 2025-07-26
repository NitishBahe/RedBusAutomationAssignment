package com.redbus;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBusAutomationSolution{

	public static void main(String[] args) {

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		WebDriver wd = new ChromeDriver(chromeOptions);
		WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(30));

		wd.get("https://redbus.in");

		By srcButtonLocator = By.xpath("//div[contains(@class,'srcDestWrapper') and @role='button']");
		WebElement sourceButton = wait.until(ExpectedConditions.visibilityOfElementLocated(srcButtonLocator));
		sourceButton.click();

		By searchSuggestionSelectionLocator = By.xpath("//div[contains(@class,'searchSuggestionWrapper')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestionSelectionLocator));

		selectLocation(wd, wait, "Mumbai");
		selectLocation(wd, wait, "Pune");

		By searchButtonLocator = By.xpath("//button[contains(@class,'searchButtonWrapper')]");
		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
		searchButton.click();

		By primoButtonLocator = By.xpath("//*[contains(text(),'Primo Bus ')]");
		WebElement primoButton = wait.until(ExpectedConditions.elementToBeClickable(primoButtonLocator));
		primoButton.click();

		By tupleWrapperLocator = By.xpath("//li[contains(@class,'tupleWrapper')]");// find the row locator
		By busesNameLocator = By.xpath(".//div[contains(@class,'travelsName')]");// bus name locator
		By endOfListLocator = By.xpath("//span[contains(text(),'End of list')]");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tupleWrapperLocator));

		By eveningButtonLocator = By.xpath("//*[contains(text(),'18:00-24:00 ')]");
		WebElement eveningButton = wait.until(ExpectedConditions.elementToBeClickable(eveningButtonLocator));
		eveningButton.click();

		Actions action = new Actions(wd);
		action.sendKeys(Keys.PAGE_UP).build().perform();

		By subTitleLocator = By.xpath("//span[contains(@class,'subtitle')]");
		WebElement subTitle = null;
		if (wait.until(ExpectedConditions.textToBePresentInElementLocated(subTitleLocator, "buses"))) {
			subTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(subTitleLocator));
		}
		System.out.println(subTitle.getText());

		JavascriptExecutor js = (JavascriptExecutor) wd;

		// Handling Lazy Loading
		while (true) {
			List<WebElement> rowList = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tupleWrapperLocator));
			List<WebElement> endOfListList = wd.findElements(endOfListLocator);
			if (!endOfListList.isEmpty()) {

				break;
			}

			js.executeScript("arguments[0].scrollIntoView({behaviour:'smooth'})", rowList.get(rowList.size() - 3));
		}

		List<WebElement> rowList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tupleWrapperLocator));
		System.out.println("Total Number of Buses Loaded with Primo and Evening Filter:" + rowList.size());
		for (WebElement row : rowList) {
			System.out.println(row.findElement(busesNameLocator).getText());
		}

	}

	public static void selectLocation(WebDriver wd, WebDriverWait wait, String locationData) {
		WebElement searchTextBoxElement = wd.switchTo().activeElement();
		searchTextBoxElement.sendKeys(locationData);

		By searchCategoryLocator = By.xpath("//div[contains(@class,'searchCategory')]");
		List<WebElement> searchList = wait
				.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchCategoryLocator, 1));
		System.out.println(searchList.size());

		WebElement locationSearchResult = searchList.get(0);
		By locationLocator = By.xpath(".//div[contains(@class,'listHeader')]");
		List<WebElement> locationList = locationSearchResult.findElements(locationLocator);
		System.out.println(locationList);

		for (WebElement location : locationList) {
			String lName = location.getText();
			if (lName.equalsIgnoreCase(locationData)) {
				location.click();
				break;
			}
		}
	}

}
