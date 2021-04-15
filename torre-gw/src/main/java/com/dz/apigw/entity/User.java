/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dz.apigw.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tbl_users")
//@NamedQueries({
//    @NamedQuery(
//        name = "User.findByUsername",
//        query = "select u from tbl_users u where u.username = :username"
//        ),
//})
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
//    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "username", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @Column(name = "password", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    public User(){
        super();
    }
    
    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }    
    
    public User(Long id, String username, String password) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
    }    

    @Override
    public String toString() {
        return String.format("User{id=%d, username='%s', password=%s}", id, username, password);
    }

//    @Column(name = "FIRSTNAME", length = 50)
//    @NotNull
//    @Size(min = 4, max = 50)
//    private String firstname;
//
//    @Column(name = "LASTNAME", length = 50)
//    @NotNull
//    @Size(min = 4, max = 50)
//    private String lastname;
//
//    @Column(name = "EMAIL", length = 50)
//    @NotNull
//    @Size(min = 4, max = 50)
//    private String email;
//
//    @Column(name = "ENABLED")
//    @NotNull
//    private Boolean enabled;
//
//    @Column(name = "LASTPASSWORDRESETDATE")
//    @Temporal(TemporalType.TIMESTAMP)
//    @NotNull
//    private Date lastPasswordResetDate;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "USER_AUTHORITY",
//            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
//            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
//    private List<Authority> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
//
//    public String getFirstname() {
//        return firstname;
//    }
//
//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Boolean getEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(Boolean enabled) {
//        this.enabled = enabled;
//    }
//
//    public List<Authority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(List<Authority> authorities) {
//        this.authorities = authorities;
//    }
//
//    public Date getLastPasswordResetDate() {
//        return lastPasswordResetDate;
//    }
//
//    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
//        this.lastPasswordResetDate = lastPasswordResetDate;
//    }
}