package steps.demoqa;

import api_assured.ApiUtilities;
import bookstore.models.Book;
import context.ContextStore;
import demoqa.pages.ProfilePage;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProfilePageSteps extends ApiUtilities {

    ProfilePage profilePage = new ProfilePage();



    @Given("Expand profile inventory rows to the {}")
    public void expandInventoryRows(String tenRowsString){

        profilePage.centerElement(profilePage.rowNumberContainer);
        profilePage.elementClick(profilePage.rowNumberContainer, true);
        WebElement tenRows = profilePage.getElementFromList(tenRowsString, profilePage.rowNumbers);
        profilePage.elementClick(tenRows, true);

        log.info("Expanding profile inventory rows to the 10");

    }

    @Given("Verify that the number of books in the user account and added to user account are the same")
    public void verifyBookNumbers(){

        List<String> userBooks = ContextStore.get("userBooks");
        int numberOfSelectedBooks = userBooks.size();

        List<String> authorNames = profilePage.getAuthorNames();
        int numberOfBooksInTheProfile = authorNames.size();

        Assert.assertEquals("Number of books do not match!\nExpected: " + numberOfSelectedBooks + "\nActual: " + numberOfBooksInTheProfile,
                numberOfSelectedBooks, numberOfBooksInTheProfile);
        log.success("Number of selected book and the number of books in the user account are the same");

    }

    @Given("Verify that book descriptions in the user account and of the selected books are the same")
    public void compareBookDescriptions(){

        List<Book> userBooks = ContextStore.get("userBooks");

        for (Book book : userBooks) profilePage.bookVerification(book);
    }

}


