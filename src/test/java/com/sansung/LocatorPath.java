package com.sansung;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LocatorPath extends  Baseclass{
	

	@FindBy(xpath="(//*[@id=\"wrap\"]/div[2]/nav/div/div/div[2]/div/div[2]/ul[2]/li[1]/a") 
	private WebElement search; 

	@FindBy(xpath = "//input[@type='text']") 
	private WebElement searchBox; 

	public WebElement getSearch() {
		return search;
	}


	public WebElement getSearchBox() {
		return searchBox;
	}


	public WebElement getSearchButton() {
		return searchButton;
	}


	public WebElement getProduct() {
		return product;
	}


	public WebElement getBuyButton() {
		return buyButton;
	}


	public WebElement getAddToCart() {
		return addToCart;
	}


	@FindBy(xpath="//button[@type='submit']") 
	private WebElement searchButton; 

	@FindBy(xpath="(//a[@class='result-title__link'])[2]") 
	private WebElement product; 

	@FindBy(linkText="Buy now") 
	private WebElement buyButton; 

	@FindBy(linkText="ADD TO CART") 
	private WebElement addToCart; 


	public LocatorPath (WebDriver fdriver){ 
		this.driver = fdriver; 
		PageFactory.initElements(fdriver, this); 
}


	
	}
