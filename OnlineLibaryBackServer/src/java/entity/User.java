package entity;
// Generated 26.04.2016 22:20:35 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Integer id;
     private String email;
     private String password;
     private String firstName;
     private String secondName;

    public User() {
    }

	
    public User(String email, String password, String firstName, String secondName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSecondName() {
        return this.secondName;
    }
    
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }




}

