package steps.demoqa;

import api_assured.ApiUtilities;
import demoqapages.BookStorePage;
import demoqapages.components.ElementGroup;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BookStorePageSteps extends ApiUtilities {

    BookStorePage bookStorePage = new BookStorePage();

    @Given("Click {} button on the bookstore page")
    public void buttonClick(String buttonName){
        List<WebElement> elementGroupButtons = bookStorePage.elementGroupButtons();
        WebElement elementGroupButton = bookStorePage.getElementFromList(buttonName, elementGroupButtons);
        log.info("Clicking " + buttonName + " button on the bookstore page");
    }
}
