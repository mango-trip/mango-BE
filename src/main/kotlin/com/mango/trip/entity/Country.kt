package com.mango.trip.entity

import jakarta.persistence.*

@Entity
@Table(name = "country")
class Country(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name")
    val name: String,

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    val cityList: MutableList<City> = mutableListOf(),
) {

}