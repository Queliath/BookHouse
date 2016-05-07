/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Admin;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Владислав
 */
public class AdminDao {
    
    public static List<Admin> getAllAdmins(){
        List<Admin> admins = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction t = session.beginTransaction();
            Query q = session.createQuery("from Admin");
            admins = (List<Admin>) q.list();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return admins;
    }
    
}
