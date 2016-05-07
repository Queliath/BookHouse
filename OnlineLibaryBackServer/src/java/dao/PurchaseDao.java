/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Purchase;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Владислав
 */
public class PurchaseDao {
    
    public static List<Purchase> getPurchasesByUser(Integer userId){
        List<Purchase> purchases = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction t = session.beginTransaction();
            Query q = session.createQuery("from Purchase where user_id = :user_id");
            q.setInteger("user_id", userId);
            purchases = (List<Purchase>) q.list();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return purchases;
    }
    
    public static boolean addPurchase(Purchase purchase){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            Transaction t = session.beginTransaction();
            session.save(purchase);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        
        return true;
    }
    
    public static List<Purchase> getPurchasesByBook(Integer bookId){
        List<Purchase> purchases = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction t = session.beginTransaction();
            Query q = session.createQuery("from Purchase where book_id = :book_id");
            q.setInteger("book_id", bookId);
            purchases = (List<Purchase>) q.list();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return purchases;
    }
}
