package org.generation.ecommercedb.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @ApiModelProperty(notes = "ID de usuario", example = "4",required = true )
    private Long id;
    @ApiModelProperty(notes = "Nombre de usuario", example = "daniel")
    private String username;
    @ApiModelProperty(notes = "Contraseña de usuario", example = "12345")
    private String password;

    /**
     *Constructo de usuario
     * @param id Id de usuario
     * @param username Nombre de usuario
     * @param password Contraseña de usuario
     */
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Constructo vario de usuario
     */
    public User() {
    }

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
