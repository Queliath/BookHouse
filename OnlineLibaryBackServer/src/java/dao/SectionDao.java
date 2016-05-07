/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Section;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Владислав
 */
public class SectionDao {
    
    public static List<Section> getAllSections(){
        List<Section> sections = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction t = session.beginTransaction();
            Query q = session.createQuery("from Section");
            sections = (List<Section>) q.list();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return sections;
    }
    
    public static Section getSectionById(Integer id){
        Section section = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            Transaction t = session.beginTransaction();
            Query q = session.createQuery("from Section where id = :id");
            q.setInteger("id", id);
            section = (Section) q.uniqueResult();
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return section;
    }
    
    public static boolean addSection(Section section){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            Transaction t = session.beginTransaction();
            session.save(section);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        
        return true;
    }
    
    public static boolean updateSection(Section section){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            Transaction t = session.beginTransaction();
            session.update(section);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        
        return true;
    }
    
    public static boolean deleteSection(Section section){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            Transaction t = session.beginTransaction();
            session.delete(section);
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
