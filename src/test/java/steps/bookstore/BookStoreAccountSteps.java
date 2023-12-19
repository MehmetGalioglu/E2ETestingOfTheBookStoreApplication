package steps.bookstore;

import api_assured.ApiUtilities;
import bookstore.BookStoreAccount;
import bookstore.models.LoginView;
import bookstore.models.TokenResponse;
import bookstore.models.User;
import context.ContextStore;
import io.cucumber.java.en.Given;
import utils.StringUtilities;

public class BookStoreAccountSteps extends ApiUtilities {

    StringUtilities strUtilities = new StringUtilities();

    BookStoreAccount bookStoreAccount = new BookStoreAccount();

    @Given("Send a post request to create a user in bookstore")
    public void postUser(){

        String userName = strUtilities.generateRandomString("fuzzy", 5, false, true);

        LoginView loginView = new LoginView(userName, "Password123!");

        User user = bookStoreAccount.postUser(loginView);
        String userID = user.getUserID();

        ContextStore.put("userID", userID);
        ContextStore.put("username", user.getUsername());

        log.success("User has been created successfully with username: " + user.getUsername() +
                " and with id: " + userID);

    }

    @Given("Send a post request to generate a token")
    public void generateToken(){

        String userName = ContextStore.get("username");

        LoginView loginView = new LoginView(userName, "Password123!");
        ContextStore.put("password", loginView.getPassword());

        TokenResponse tokenResponse = bookStoreAccount.generateToken(loginView);
        String userToken = tokenResponse.getToken();
        ContextStore.put("userToken", userToken);

        log.success("Token has been successfully generated");

    }

    @Given("Send a get request to get the user by Id")
    public void getUserById(){
        String userToken = ContextStore.get("userToken");
        String userID = ContextStore.get("userID");
        User user = bookStoreAccount.getUserById(userToken, userID);
        ContextStore.put("userBooks", user.getBooks());
    }
}
