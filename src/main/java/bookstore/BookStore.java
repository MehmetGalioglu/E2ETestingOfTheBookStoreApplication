package bookstore;

import api_assured.ApiUtilities;
import api_assured.ServiceGenerator;
import bookstore.models.AddListOfBooks;
import bookstore.models.Book;
import bookstore.models.BookResponse;
import context.ContextStore;
import okhttp3.Headers;
import retrofit2.Call;

import java.util.ArrayList;
import java.util.List;

public class BookStore extends ApiUtilities {
    BookStoreServices.BookStore bookStore;

    public BookStore(String userToken){
        bookStore = new ServiceGenerator(
                new Headers.Builder().add("Authorization", "Bearer " + userToken).build()
        ).generate(BookStoreServices.BookStore.class);
    }

    public BookResponse getBooks(){
        log.info("Getting books");
        Call<BookResponse> bookResponseCall = bookStore.getBooks();
        return perform(bookResponseCall, false, true);
    }

    public BookResponse postBooks(String userToken, AddListOfBooks books){
        log.info("Posting books");
        Call<BookResponse> postBooksCall = bookStore.postBooks(books);
        return perform(postBooksCall, false, true);
    }

    public List<Book> getBooksFromList(String publisher){
        List<Book> books = ContextStore.get("bookList");
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books){
            if (book.getPublisher().equals(publisher))
                filteredBooks.add(book);
        }
        return filteredBooks;
    }

    /* Or we can get the same book list filtered with respect to publisher with the lambda expression

    public List<Book> getBooksFromList(String publisher){
        List<Book> books = ContextStore.get("bookList");
        List<Book> filteredBooks = books.stream().filter(book -> book.getPublisher().equals(publisher)).toList();
        return filteredBooks;
    } */

    public List<AddListOfBooks.Isbn> getIsbnsFromTheList(List<Book> filteredBooks){
        List<AddListOfBooks.Isbn> collectionOfIsbn = new ArrayList<>();

        for (Book book : filteredBooks){
            collectionOfIsbn.add(new AddListOfBooks.Isbn(book.getIsbn()));
        }
        return collectionOfIsbn;
    }


}





