package com.github.pabloo99.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Data
@Table(name = "regions")
public class Region {

    @Id
    @Column(name = "region_id")
    private Integer id;

    @Column(name = "region_name")
    private String name;

}
