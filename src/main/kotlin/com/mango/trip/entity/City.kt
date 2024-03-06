package com.mango.trip.entity

import jakarta.persistence.*

@Entity
@Table(name = "city")
class City(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name")
    val name: String,

    @ManyToOne
    @JoinColumn(name = "country_id")
    val country: Country,
) {

}