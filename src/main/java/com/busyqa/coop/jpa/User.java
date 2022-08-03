package com.busyqa.coop.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="busyqacrmusers")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    
 
    @Column(name="ID_USER", unique=true, nullable=false)
    private int idUser;

    @Column(name="FIRST_NAME", nullable=false, length=45)
    private String firstName;

    @Column(name="LAST_NAME", nullable=false, length=45)
    private String lastName;

    /*Username will be used as the ID to find a user from database and hence should be unique*/
    @Id
    @Column(name="USERNAME", unique=true, nullable=false, length=45)
    private String username;

    @Column(name="PASSWORD",nullable=false, length=100)
    private String password;

    @Column(name="EMAIL", unique=true, nullable=false, length=45)
    private String email;
    
    @Column(name="PHONE_NUMBER", nullable=false)
    private String phone;
    
    @Column(name="ROLE",nullable=false, length=45)
    private String role;


    public User() {
    }

	public User(int idUser, String firstName, String lastName,
               String username, String password, String email, String phone, String role) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }


    public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
    public String toString() {
        return "User [idUser=" + idUser + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
                + username + ", password=" + password + ", email=" + email + ", phone=" + phone + ",role= " + role + "]";
    }
}
