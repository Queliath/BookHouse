/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Rating;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Владислав
 */
public class RatingDao {
    
    public static double getAverageRatingByBook(Integer bookId){
        List<Rating> ratings = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction t = session.beginTransaction();
            Query q = session.createQuery("from Rating where rating_book_id = :rating_book_id");
            q.setInteger("rating_book_id", bookId);
            ratings = (List<Rating>) q.list();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        double summaryRating = 0.0;
        for(Rating rating : ratings){
            summaryRating += rating.getValue();
        }
        
        return summaryRating / ratings.size();
    }
    
    public static boolean addRating(Rating rating){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            Transaction t = session.beginTransaction();
            session.save(rating);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        
        return true;
    }
    
    public static List<Rating> getRatingsByUser(Integer userId){
        List<Rating> ratings = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction t = session.beginTransaction();
            Query q = session.createQuery("from Rating where rating_user_id = :rating_user_id");
            q.setInteger("rating_user_id", userId);
            ratings = (List<Rating>) q.list();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return ratings;
    }
}
