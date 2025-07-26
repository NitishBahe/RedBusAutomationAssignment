package com.redbus;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBusAutomationAssignment {

	public static void main(String[] args) throws InterruptedException {

		// I want to launch chrome browser
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		WebDriver wd = new ChromeDriver(chromeOptions);
		WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(30));

		wd.get("https://redbus.in");

		By srcButtonLocator = By.xpath("//div[contains(@class,'srcDestWrapper') and @role='button']");
		WebElement sourceButton = wait.until(ExpectedConditions.visibilityOfElementLocated(srcButtonLocator));
		// WebElement sourceButton = wd.findElement(srcButtonLocator);
		sourceButton.click();

		/*
		 * By fromInputBoxLocator = By.xpath("//input[@id='srcDest']"); WebDriverWait
		 * wait = new WebDriverWait(wd, Duration.ofSeconds(10)); WebElement fromInputBox
		 * =
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(fromInputBoxLocator)
		 * ); fromInputBox.sendKeys("Pune");
		 */

		// Thread.sleep(4000);

		By searchSuggestionSelectionLocator = By.xpath("//div[contains(@class,'searchSuggestionWrapper')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestionSelectionLocator));
		WebElement searchTextBoxElement = wd.switchTo().activeElement();
		searchTextBoxElement.sendKeys("Mumbai");

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
			if (lName.equalsIgnoreCase("Mumbai")) {
				location.click();
				break;
			}
		}
		
		
		//focus on the to section
		
		WebElement toTextBox=wd.switchTo().activeElement();
		
		toTextBox.sendKeys("Pune");
		
		By tosearchCategoryLocator = By.xpath("//div[contains(@class,'searchCategory')]");
		List<WebElement> toSearchList = wait
				.until(ExpectedConditions.numberOfElementsToBeMoreThan(tosearchCategoryLocator , 1));
		System.out.println(toSearchList.size());
		
		
		WebElement toLocationCategory = toSearchList.get(0);
		By toLocationNameLocator = By.xpath(".//div[contains(@class,'listHeader')]");
		
		List<WebElement> toLocationList = toLocationCategory.findElements(toLocationNameLocator);
		System.out.println(toLocationList);

		for (WebElement toLocation : toLocationList) {
			String tolocationName = toLocation.getText();
			if (tolocationName.equalsIgnoreCase("Pune")) {
				toLocation.click();
				break;
			}
		}

	}

}
