package com.excilys.computerdatabase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Vincent Galloy
 *         The Class UserRole.
 */
@Entity
@Table(name = "authorities", uniqueConstraints = @UniqueConstraint(columnNames = {"role", "username"}))
public class UserRole implements Serializable {
    private static final long serialVersionUID = -2736938120189054963L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_role_id", unique = true, nullable = false)
    private Integer userRoleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;
    @Column(name = "role", nullable = false, length = 100)
    private String role;

    /**
     * Instantiates a new user role.
     */
    public UserRole() {
        super();
    }

    /**
     * Instantiates a new user role.
     *
     * @param user the user
     * @param role the role
     */
    public UserRole(User user, String role) {
        this.user = user;
        this.role = role;
    }
    public Integer getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole [userRoleId=" + userRoleId + ", role=" + role + "]";
    }

}
