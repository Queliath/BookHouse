/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Book;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Владислав
 */
public class BookDao {
    
    public static List<Book> getBooksBySection(Integer sectionId){
        List<Book> books = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction t = session.beginTransaction();
            Query q = session.createQuery("from Book where section_id = :section_id");
            q.setInteger("section_id", sectionId);
            books = (List<Book>) q.list();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return books;
    }
    
    public static Book getBookById(Integer id){
        Book book = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            Transaction t = session.beginTransaction();
            Query q = session.createQuery("from Book where id = :id");
            q.setInteger("id", id);
            book = (Book) q.uniqueResult();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return book;
    }
    
    public static List<Book> getAllBooks(){
        List<Book> books = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction t = session.beginTransaction();
            Query q = session.createQuery("from Book");
            books = (List<Book>) q.list();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return books;
    }
    
    public static List<Book> getBooksByKeyword(String keyword){
        List<Book> books = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction t = session.beginTransaction();
            Query q = session.createQuery("from Book where Title like :pattern");
            q.setString("pattern", "%" + keyword + "%");
            books = (List<Book>) q.list();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return books;
    }
    
    public static boolean addBook(Book book){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            Transaction t = session.beginTransaction();
            session.save(book);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        
        return true;
    }
    
    public static boolean updateBook(Book book){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            Transaction t = session.beginTransaction();
            session.update(book);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        
        return true;
    }
    
    public static boolean deleteBook(Book book){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            Transaction t = session.beginTransaction();
            session.delete(book);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        
        return true;
    }
    
    public static List<Book> getBooksByPrice(int minPrice, int maxPrice){
        List<Book> books = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction t = session.beginTransaction();
            Query q = session.createQuery("from Book where Price between :min_price and :max_price");
            q.setInteger("min_price", minPrice);
            q.setInteger("max_price", maxPrice);
            books = (List<Book>) q.list();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return books;
    }
    
}
