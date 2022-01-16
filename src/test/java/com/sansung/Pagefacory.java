package com.sansung;

import org.openqa.selenium.WebDriver;

public class Pagefacory extends Baseclass{
	private LocatorPath lp;




	public LocatorPath getLp() {
		lp = new LocatorPath(driver);
		return lp;
	}



	public Pagefacory(WebDriver fdriver) {
	this.driver=fdriver;
	}


}
