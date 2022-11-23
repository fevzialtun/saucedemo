package com.sauce.pages;

import com.sauce.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPage extends BasePage {
    static Float totalPrice = 0.0f;
    @FindBy(xpath = "//span[@class='title']")
    public WebElement headerText;

    @FindBy(css = ".product_sort_container")
    public WebElement dropDownSort;

    ////div[@class='inventory_item_price' and text()='$49.99')]

    @FindBy(css = ".shopping_cart_link")
    public WebElement basket;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    public List<WebElement> priceList;

    @FindBy(xpath = "//option[@value='hilo']")
    public WebElement sortByHilo;


    public Float getCheapest() {
        List<Float> priceNums = new ArrayList<Float>();
        for (int i = 0; i < priceList.size(); i++) {
            String price = priceList.get(i).getText();
            price = price.replace("$", "");
            float priceNum = Float.parseFloat(price);
            priceNums.add(i, priceNum);
        }
        Collections.sort(priceNums);

        System.out.println(priceNums.get(0));
        totalPrice +=priceNums.get(0);
        System.out.println("totalPrice = " + totalPrice);
        return priceNums.get(0);
    }

    public Float getSecondCostliest() {
        List<Float> priceNums = new ArrayList<Float>();
        for (int i = 0; i < priceList.size(); i++) {
            String price = priceList.get(i).getText();
            price = price.replace("$", "");
            float priceNum = Float.parseFloat(price);
            priceNums.add(i, priceNum);
        }
        Collections.sort(priceNums);

        System.out.println(priceNums.get(priceNums.size()-2));
        totalPrice +=priceNums.get(priceNums.size()-2);
        System.out.println("totalPrice = " + totalPrice);
        return priceNums.get(priceNums.size()-2);
    }

    public void sortProduct(String sortType) {
        Select select = new Select(dropDownSort);
        select.selectByVisibleText(sortType);
    }
    public void addProduct(Float price){
        Driver.get().findElement(By.xpath("//*[text()='"+price+"']/../button")).click();


    }

}