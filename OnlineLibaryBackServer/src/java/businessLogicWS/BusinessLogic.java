/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogicWS;

import dao.AdminDao;
import dao.BookDao;
import dao.CommentDao;
import dao.PurchaseDao;
import dao.RatingDao;
import dao.SectionDao;
import dao.UserDao;
import entity.Admin;
import entity.Book;
import entity.Comment;
import entity.Purchase;
import entity.Rating;
import entity.Section;
import entity.User;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeSet;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Владислав
 */
@WebService(serviceName = "BusinessLogic")
public class BusinessLogic {


    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getAllSections")
    public List<Section> getAllSections() {
        return SectionDao.getAllSections();
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getSectionById")
    public Section getSectionById(@WebParam(name = "id") Integer id) {
        return SectionDao.getSectionById(id);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getBooksBySection")
    public List<Book> getBooksBySection(@WebParam(name = "sectionId") Integer sectionId) {
        return BookDao.getBooksBySection(sectionId);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getBookById")
    public Book getBookById(@WebParam(name = "id") Integer id) {
        return BookDao.getBookById(id);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getCommentsByBook")
    public List<Comment> getCommentsByBook(@WebParam(name = "bookId") Integer bookId) {
        return CommentDao.getCommentsByBook(bookId);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getAverageRatingByBook")
    public Double getAverageRatingByBook(@WebParam(name = "bookId") Integer bookId) {
        return RatingDao.getAverageRatingByBook(bookId);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getUserById")
    public User getUserById(@WebParam(name = "id") Integer id) {
        return UserDao.getUserById(id);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "addComment")
    public Boolean addComment(@WebParam(name = "bookId") Integer bookId, @WebParam(name = "userId") Integer userId, @WebParam(name = "content") String content, @WebParam(name = "date") Date date) {
        Comment comment = new Comment(bookId, userId, content, date);
        return CommentDao.addComment(comment);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "login")
    public User login(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        List<User> users = UserDao.getAllUsers();
        
        for(User user : users)
            if(email.equals(user.getEmail()) && password.equals(user.getPassword()))
                return user;
        
        List<Admin> admins = AdminDao.getAllAdmins();
        
        for(Admin admin : admins)
            if(email.equals(admin.getEmail()) && password.equals(admin.getPassword()))
                return new User(email, password, "admin", "admin");
        
        return null;
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "registry")
    public Boolean registry(@WebParam(name = "email") String email, @WebParam(name = "password") String password, @WebParam(name = "firstName") String firstName, @WebParam(name = "secondName") String secondName) {
        User user = new User(email, password, firstName, secondName);
        return UserDao.addUser(user);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getCommentsByUser")
    public List<Comment> getCommentsByUser(@WebParam(name = "userId") Integer userId) {
        return CommentDao.getCommentsByUser(userId);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getPurchasesByUser")
    public List<Purchase> getPurchasesByUser(@WebParam(name = "userId") Integer userId) {
        return PurchaseDao.getPurchasesByUser(userId);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "addPurchase")
    public Boolean addPurchase(@WebParam(name = "cardNumber") String cardNumber, @WebParam(name = "securityNumber") String securityNumber, @WebParam(name = "bookId") Integer bookId, @WebParam(name = "userId") Integer userId) {
        Purchase purchase = new Purchase(bookId, userId, cardNumber, securityNumber, "test", "test", "test");
        return PurchaseDao.addPurchase(purchase);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "addRating")
    public Boolean addRating(@WebParam(name = "value") Integer value, @WebParam(name = "userId") Integer userId, @WebParam(name = "bookId") Integer bookId) {
        Rating rating = new Rating(bookId, userId, value);
        return RatingDao.addRating(rating);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getAllBooks")
    public List<Book> getAllBooks() {
        return BookDao.getAllBooks();
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getBooksByKeyword")
    public List<Book> getBooksByKeyword(@WebParam(name = "keyword") String keyword) {
        return BookDao.getBooksByKeyword(keyword);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getRatingsByUser")
    public List<Rating> getRatingsByUser(@WebParam(name = "userId") Integer userId) {
        return RatingDao.getRatingsByUser(userId);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "addSection")
    public Boolean addSection(@WebParam(name = "name") String name, @WebParam(name = "description") String description, @WebParam(name = "image") String image) {
        Section section = new Section(name, description, image);
        return SectionDao.addSection(section);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "updateSection")
    public Boolean updateSection(@WebParam(name = "name") String name, @WebParam(name = "description") String description, @WebParam(name = "image") String image, @WebParam(name = "id") Integer id) {
        Section section = getSectionById(id);
        section.setName(name);
        section.setDescription(description);
        section.setImage(image);
        return SectionDao.updateSection(section);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "deleteSection")
    public Boolean deleteSection(@WebParam(name = "id") Integer id) {
        Section section = getSectionById(id);
        return SectionDao.deleteSection(section);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "addBook")
    public Boolean addBook(@WebParam(name = "title") String title, @WebParam(name = "description") String description, @WebParam(name = "preview") String preview, @WebParam(name = "numberOfPages") int numberOfPages, @WebParam(name = "price") int price, @WebParam(name = "year") int year, @WebParam(name = "image") String image, @WebParam(name = "fileName") String fileName, @WebParam(name = "dateOfPublication") Date dateOfPublication, @WebParam(name = "section") Integer section, @WebParam(name = "author") String author) {
        Book book = new Book(section, title, description, preview, numberOfPages, price, fileName, author, year, dateOfPublication, image);
        return BookDao.addBook(book);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "updateBook")
    public Boolean updateBook(@WebParam(name = "id") Integer id, @WebParam(name = "title") String title, @WebParam(name = "description") String description, @WebParam(name = "preview") String preview, @WebParam(name = "numberOfPages") int numberOfPages, @WebParam(name = "price") int price, @WebParam(name = "year") int year, @WebParam(name = "image") String image, @WebParam(name = "fileName") String fileName, @WebParam(name = "dateOfPublication") Date dateOfPublication, @WebParam(name = "section") Integer section, @WebParam(name = "author") String author) {
        Book book = getBookById(id);
        book.setAuthor(author);
        book.setDateOfPublicaion(dateOfPublication);
        book.setDescription(description);
        book.setFileName(fileName);
        book.setImage(image);
        book.setNumberOfPages(numberOfPages);
        book.setPreview(preview);
        book.setPrice(price);
        book.setSection(section);
        book.setTitle(title);
        book.setYear(year);
        return BookDao.updateBook(book);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "deleteBook")
    public Boolean deleteBook(@WebParam(name = "id") Integer id) {
        Book book = getBookById(id);
        return BookDao.deleteBook(book);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getTopBooksByRating")
    public List<Book> getTopBooksByRating() {
        List<Book> allBooks = getAllBooks();
        
        Comparator<Book> comp = (book1, book2) -> {
            Double rating1 = RatingDao.getAverageRatingByBook(book1.getId()), rating2 = RatingDao.getAverageRatingByBook(book2.getId());
            return rating2.compareTo(rating1);
        };
        
        TreeSet<Book> sortedBooks = new TreeSet<>(comp);
        for(Book book: allBooks)
            sortedBooks.add(book);
        
        return new ArrayList(sortedBooks);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getTopBooksByComment")
    public List<Book> getTopBooksByComment() {
        List<Book> allBooks = getAllBooks();
        
        Comparator<Book> comp = (book1, book2) -> {
            Integer numberOfComments1 = CommentDao.getCommentsByBook(book1.getId()).size(), numberOfComments2 = CommentDao.getCommentsByBook(book2.getId()).size();
            return numberOfComments2.compareTo(numberOfComments1);
        };
        
        TreeSet<Book> sortedBooks = new TreeSet<>(comp);
        for(Book book: allBooks)
            sortedBooks.add(book);
        
        return new ArrayList(sortedBooks);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getTopBooksByPurchases")
    public List<Book> getTopBooksByPurchases() {
        List<Book> allBooks = getAllBooks();
        
        Comparator<Book> comp = (book1, book2) -> {
            Integer numberOfPurchases1 = PurchaseDao.getPurchasesByBook(book1.getId()).size(), numberOfPurchases2 = PurchaseDao.getPurchasesByBook(book2.getId()).size();
            return numberOfPurchases2.compareTo(numberOfPurchases1);
        };
        
        TreeSet<Book> sortedBooks = new TreeSet<>(comp);
        for(Book book: allBooks)
            sortedBooks.add(book);
        
        return new ArrayList(sortedBooks);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getPurchasesByBook")
    public List<Purchase> getPurchasesByBook(@WebParam(name = "bookId") Integer bookId) {
        return PurchaseDao.getPurchasesByBook(bookId);
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getPurchasesBySectionChart")
    public List<Integer> getPurchasesBySectionChart() {
        List<Section> sections = SectionDao.getAllSections();
        List<Integer> purchasesBySection = new ArrayList<>();
        for(Section section : sections){
            Integer purchases = 0;
            List<Book> books = BookDao.getBooksBySection(section.getId());
            for(Book book : books){
                purchases += PurchaseDao.getPurchasesByBook(book.getId()).size();
            }
            purchasesBySection.add(purchases);
        }
        return purchasesBySection;
    }

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "getPurchasesByPriceChart")
    public List<Integer> getPurchasesByPriceChart() {
        List<Integer> purchasesByPrice = new ArrayList<>();
        
        for(int minPrice = 0, maxPrice = 50000; maxPrice <= 300000; minPrice += 50000, maxPrice += 50000){
            List<Book> books = BookDao.getBooksByPrice(minPrice, maxPrice);
            Integer purchases = 0;
            for(Book book : books)
                purchases += PurchaseDao.getPurchasesByBook(book.getId()).size();
            purchasesByPrice.add(purchases);
        }
        
        return purchasesByPrice;
    }
}
