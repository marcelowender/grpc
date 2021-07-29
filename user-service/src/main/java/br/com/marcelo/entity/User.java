package br.com.marcelo.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
@Data
public class User {

    @Id
    private String login;
    private String name;
    private String betType;
}
