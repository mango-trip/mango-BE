package com.mango.trip.entity

import jakarta.persistence.*

@Entity
@Table(name = "plan_city")
class PlanCity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "plan_id")
    val plan: Plan,

    @ManyToOne
    @JoinColumn(name = "city_id")
    val city: City,
) {

}