package com.example.springBootSecurityJWTMySql.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "user_tbl")
public class User {

    @Id
    @GeneratedValue
    Integer id;
    String name;
    String userName;
    String password;

    @ElementCollection
     @CollectionTable(
             name = "roles_tbl",
             joinColumns=@JoinColumn(name = "id"))
            @Column(name = "role")
    Set<String > roles;
}
