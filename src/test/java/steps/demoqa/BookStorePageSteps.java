package steps.demoqa;

import api_assured.ApiUtilities;
import demoqa.pages.BookStorePage;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;

public class BookStorePageSteps extends ApiUtilities {

    BookStorePage bookStorePage = new BookStorePage();

    @Given("Click {} button on the bookstore page")
    public void buttonClick(String buttonName){

        WebElement elementGroupButton = bookStorePage.getElementFromList(buttonName, bookStorePage.elementGroupButtons);
        bookStorePage.elementClick(elementGroupButton, true);

        log.info("Clicking " + buttonName + " button on the bookstore page");

    }
}
