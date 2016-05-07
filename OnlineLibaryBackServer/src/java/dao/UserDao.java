/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Владислав
 */
public class UserDao {
    
    public static User getUserById(Integer id){
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            Transaction t = session.beginTransaction();
            Query q = session.createQuery("from User where id = :id");
            q.setInteger("id", id);
            user = (User) q.uniqueResult();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return user;
    }
    
    public static List<User> getAllUsers(){
        List<User> users = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction t = session.beginTransaction();
            Query q = session.createQuery("from User");
            users = (List<User>) q.list();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return users;
    }
    
    public static boolean addUser(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            Transaction t = session.beginTransaction();
            session.save(user);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        
        return true;
    }
}
