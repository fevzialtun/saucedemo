package com.sauce.stepDefinitions;

import com.sauce.pages.CheckoutPage;
import com.sauce.pages.LoginPage;
import com.sauce.pages.ProductPage;
import com.sauce.utilities.BrowserUtils;
import com.sauce.utilities.ConfigurationReader;
import com.sauce.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

public class LoginStepDefs {
    LoginPage loginpage = new LoginPage();
    ProductPage productPage = new ProductPage();
    CheckoutPage checkoutPage = new CheckoutPage();

    @Given("The user is on the login page")
    public void the_user_is_on_the_login_page() {
        String url = ConfigurationReader.get("url");
        Driver.get().get(url);
    }

    @When("The user is enters {string} and {string}")
    public void the_user_is_enters_and(String userName, String password) {
        loginpage.login(userName, password);
    }

    @Then("The user should be able to login and see {string} header")
    public void the_user_should_be_able_to_login_and_see_header(String expectedHeader) {
        Assert.assertEquals("verify that the header is:", expectedHeader, productPage.headerText.getText());

    }

    @Then("The user should be able to sort products high to low")
    public void the_user_should_be_able_to_sort_products_high_to_low() {
        productPage.sortByHilo.click();
    }




    @And("The user adds two cheapest products in to the basket")
    public void theUserAddsTwoCheapestProductsInToTheBasket() {
        BrowserUtils.waitFor(2);
        //Driver.get().findElement(By.xpath("//div[@class='inventory_item_price' and text()='"+ productPage.getCheapest()+"']/../button")).click();
        //Driver.get().findElement(By.xpath("//div[@class='inventory_item_price' and text()='"+ productPage.getSecondCostliest()+"']/../button")).click();
        productPage.addProduct(productPage.getCheapest());
        productPage.addProduct(productPage.getSecondCostliest());
    }

    @And("The user go to the basket")
    public void theUserGoToTheBasket() {

        BrowserUtils.waitForPageToLoad(5);
        BrowserUtils.clickWithJS(productPage.basket);
        productPage.basket.click();
    }

    @And("The user clicks checkout")
    public void theUserClicksCheckout() {
        BrowserUtils.waitFor(5);
        checkoutPage.checkout.click();
    }

    @And("The user enters details {string} {string} {string}  and finish the purchase")
    public void theUserEntersDetailsAndFinishThePurchase(String firstname, String lastname, String postcode) {
        checkoutPage.enterDetails(firstname, lastname, postcode);
        checkoutPage.continueBtn.click();
    }

    @Then("The user verify the total cost")
    public void theUserVerifyTheTotalCost() {
        String expectedTotalPrice = checkoutPage.getTotalPrice2().toString();
        String actualTotalPrice = checkoutPage.getTotalPrice(expectedTotalPrice);
        Assert.assertEquals(expectedTotalPrice,actualTotalPrice);
    }
}