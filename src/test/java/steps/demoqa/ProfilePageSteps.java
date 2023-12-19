package steps.demoqa;

import api_assured.ApiUtilities;
import bookstore.models.Book;
import context.ContextStore;
import demoqa.pages.ProfilePage;
import io.cucumber.java.en.Given;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class ProfilePageSteps extends ApiUtilities {

    ProfilePage profilePage = new ProfilePage();

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

            for (int i = 0; i < userBooks.size(); i++) {

                if (!profilePage.inventoryRows.get(i).getGridCells().get(2).getText().equals(" ")){
                    profilePage.elementClick(profilePage.inventoryRows.get(i).bookLink, true);
                    List<String> labels = profilePage.getLabels();

                    List<String> actualValues = new ArrayList<>();
                    for (int j = 0; j < 7; j++) {
                        actualValues.add(profilePage.getBookDescriptionRows(labels.get(j)).getValue());
                    }

                    actualValues.remove(6);

                    List<String> expectedValues = List.of(

                            userBooks.get(i).getIsbn(),
                            userBooks.get(i).getTitle(),
                            userBooks.get(i).getSubTitle(),
                            userBooks.get(i).getAuthor(),
                            userBooks.get(i).getPublisher(),
                            String.valueOf(userBooks.get(i).getPages()),
                            userBooks.get(i).getWebsite()

                            );

                    for (int j = 0; j < 6; j++) {
                        Assert.assertTrue("Book descriptions do not match",
                                actualValues.contains(expectedValues.get(j)));
                        log.success("Book description match");
                    }
                    
                    profilePage.elementClick(profilePage.backToBookStoreButton, true);
                }

            }

    }

}


