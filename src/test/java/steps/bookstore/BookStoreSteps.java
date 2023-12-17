package steps.bookstore;

import api_assured.ApiUtilities;
import bookstore.BookStore;
import bookstore.models.AddListOfBooks;
import bookstore.models.Book;
import context.ContextStore;
import io.cucumber.java.en.Given;
import java.util.List;


public class BookStoreSteps extends ApiUtilities {

    String userToken = ContextStore.get("userToken");
    BookStore bookStore = new BookStore(userToken);

    @Given("Authorize user created with token generated")
    public void authorizeUser(){
        String userToken = ContextStore.get("userToken");
        new BookStore(userToken);

        log.success("User has been successfully authorized");
    }

    @Given("Send a get request to get a book list from the system")
    public void getBooks(){
        List<Book> books =  bookStore.getBooks().getBooks();
        ContextStore.put("bookList", books);

        log.success("Book list has been successfully received from the system");
    }

    @Given("Get the books published by {} from the list")
    public void getBooksFromTheList(String publisher){

        List<Book> filteredBooks =  bookStore.getBooksFromList(publisher);
        ContextStore.put("filteredBooks", filteredBooks);

        log.success("Books published by " + publisher + " have been successfully selected");

    }

    @Given("Send a post request to add selected books to user account")
    public void postBooks(){

        String userID = ContextStore.get("userID");
        String userToken = ContextStore.get("userToken");
        List<Book> filteredBooks = ContextStore.get("filteredBooks");

        List<AddListOfBooks.Isbn> collectionOfIsbn = bookStore.getIsbnsFromTheList(filteredBooks);

        AddListOfBooks addListOfBooks = new AddListOfBooks(userID, collectionOfIsbn);
        bookStore.postBooks(userToken, addListOfBooks);
        log.success("Selected books have been successfully added to the user account");

        List<Book> userBooks = bookStore.postBooks(userToken, addListOfBooks).getBooks();
        ContextStore.put("userBooks",userBooks);

    }

}
