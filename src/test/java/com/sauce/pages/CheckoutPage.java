package com.sauce.pages;

import com.sauce.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends  BasePage{

    @FindBy(id = "checkout")
    public WebElement checkout;

    @FindBy(id = "first-name")
    public WebElement inputFirstName;

    @FindBy(id = "last-name")
    public WebElement inputLastName;

    @FindBy(id = "postal-code")
    public WebElement inputPostcode;

    @FindBy(id = "continue")
    public WebElement continueBtn;

    @FindBy(xpath = "//div[text()='41.02']")
    public WebElement totalPrice;

    public void enterDetails(String firstname,String lastName,String postcode){
        inputFirstName.sendKeys(firstname);
        inputLastName.sendKeys(lastName);
        inputPostcode.sendKeys(postcode);
    }
public Float getTotalPrice2(){
    System.out.println("total price =" + ProductPage.totalPrice * 1.02f);
    return ProductPage.totalPrice * 1.08f;
}
    public String  getTotalPrice(String price){
        return    Driver.get().findElement(By.xpath("//div[text()='"+price+"']")).getText();
        //div[@class='summary_subtotal_label' and text()='37.98']
    }
}