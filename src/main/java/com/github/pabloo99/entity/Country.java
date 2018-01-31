package com.github.pabloo99.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "countries")
public class Country {

    @Id
    @Column(name = "country_id")
    private String id;

    @Column(name = "country_name", length = 40)
    private String name;

    /*@Column(name = "region_id")
    private Integer regionId;*/

    //@ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

}
