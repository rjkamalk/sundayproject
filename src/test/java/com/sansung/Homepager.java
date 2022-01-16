package com.sansung;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Homepager extends Baseclass {
	Pagefacory pr =new Pagefacory(driver);
	@BeforeSuite
	public void user_enter_the_url() {
		browserlaunch("chrome");
		driver.get("https://www.samsung.com/in/");
	    
	}
	@Test
	public void homepage() {
	jsClick(pr.getLp().getSearch());
	 
	 jsGetAttribute(pr.getLp().getSearchBox(), "galaxy"); 
	 jsClick(pr.getLp().getSearchButton()); 
	 jsClick(pr.getLp().getProduct()); 
	 jsClick(pr.getLp().getBuyButton()); 
	 jsClick(pr.getLp().getAddToCart()); 
	} 
	 
	
	

}
