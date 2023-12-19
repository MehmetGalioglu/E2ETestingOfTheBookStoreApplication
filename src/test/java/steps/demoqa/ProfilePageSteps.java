package steps.demoqa;

import api_assured.ApiUtilities;
import bookstore.models.Book;
import context.ContextStore;
import demoqa.pages.ProfilePage;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import java.util.List;

public class ProfilePageSteps extends ApiUtilities {

    ProfilePage profilePage = new ProfilePage();

    @Given("Verify that the number of books in the user account and added to user account are the same")
    public void verifyBookNumbers(){

        List<String> filteredBooks = ContextStore.get("filteredBooks");
        int numberOfSelectedBooks = filteredBooks.size();

        List<String> authorNames = profilePage.getAuthorNames();
        int numberOfBooksInTheProfile = authorNames.size();

        System.out.println(numberOfSelectedBooks);
        System.out.println(numberOfBooksInTheProfile);

        Assert.assertEquals("Number of books do not match!\nExpected: " + numberOfSelectedBooks + "\nActual: " + numberOfBooksInTheProfile,
                numberOfSelectedBooks, numberOfBooksInTheProfile);
        log.success("Number of selected book and the number of books in the user account are the same");

    }

    @Given("Verify that book descriptions in the user account and of the selected books are the same")
    public void compareBookDescriptions(){
        List<Book> userBooks = ContextStore.get("userBooks");

            for (int i = 0; i < userBooks.size(); i++) {

                if (!profilePage.inventoryRows.get(i).getGridCells().get(2).getText().equals(" ")){
                    profilePage.elementClick(profilePage.inventoryRows.get(i).bookLink, true);
                    List<String> labels = profilePage.getLabels();

                    String actualISBN = profilePage.getBookDescriptionRows(labels.get(0)).getValue();
                    String actualTitle = profilePage.getBookDescriptionRows(labels.get(1)).getValue();
                    String actualSubTitle = profilePage.getBookDescriptionRows(labels.get(2)).getValue();
                    String actualAuthor = profilePage.getBookDescriptionRows(labels.get(3)).getValue();
                    String actualPublisher = profilePage.getBookDescriptionRows(labels.get(4)).getValue();
                    String actualTotalPage = profilePage.getBookDescriptionRows(labels.get(5)).getValue();
                    //String actualDescription = profilePage.getBookDescriptionRows(labels.get(6)).getValue();
                    String actualWebSite = profilePage.getBookDescriptionRows(labels.get(7)).getValue();

                    Assert.assertEquals("Isbn numbers do not match!\nExpected: " + userBooks.get(i).getIsbn() + "\nActual: " + actualISBN,
                            userBooks.get(i).getIsbn(), actualISBN);
                    log.success("Isbn numbers match");

                    Assert.assertEquals("Titles do not match!\nExpected: " + userBooks.get(i).getTitle() + "\nActual: " + actualTitle,
                            userBooks.get(i).getTitle(), actualTitle);
                    log.success("Titles match");

                    Assert.assertEquals("Sub titles do not match!\nExpected: " + userBooks.get(i).getSubTitle() + "\nActual: " + actualSubTitle,
                            userBooks.get(i).getSubTitle(), actualSubTitle);
                    log.success("Sub titles match");

                    Assert.assertEquals("Authors do not match!\nExpected: " + userBooks.get(i).getAuthor() + "\nActual: " + actualAuthor,
                            userBooks.get(i).getAuthor(), actualAuthor);
                    log.success("Authors match");

                    Assert.assertEquals("Publishers do not match!\nExpected: " + userBooks.get(i).getPublisher() + "\nActual: " + actualPublisher,
                            userBooks.get(i).getPublisher(), actualPublisher);
                    log.success("Publishers match");

                    Assert.assertEquals("Total pages do not match!\nExpected: " + Integer.toString(userBooks.get(i).getPages()) + "\nActual: " + actualTotalPage,
                            Integer.toString(userBooks.get(i).getPages()), actualTotalPage);
                    log.success("Total pages match");

                    /* Assert.assertEquals("Descriptions do not match!\nExpected: " + book.getDescription() + "\nActual: " + actualDescription,
                               book.getDescription(), actualDescription);
                           log.success("Descriptions match"); */

                    Assert.assertEquals("Websites do not match!\nExpected: " + userBooks.get(i).getWebsite() + "\nActual: " + actualWebSite,
                            userBooks.get(i).getWebsite(), actualWebSite);
                    log.success("Websites match");

                    profilePage.elementClick(profilePage.backToBookStoreButton, true);
                }

            }

    }

}


