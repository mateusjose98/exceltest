package com.example.exceltest;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "mockdata")
public class Entidade {

    @Id
    private Integer id;
    private String nome;

}
