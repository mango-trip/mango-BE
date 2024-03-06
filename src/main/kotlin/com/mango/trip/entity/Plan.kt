package com.mango.trip.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "plan")
class Plan(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "title")
    val title: String,

    @OneToMany(mappedBy = "plan", fetch = FetchType.LAZY)
    val planCityList: MutableList<PlanCity> = mutableListOf(),

    @Column(name = "trip_start_date")
    val tripStartDate: LocalDateTime,

    @Column(name = "trip_end_date")
    val tripEndDate: LocalDateTime,

    @Column(name = "total_amount")
    val totalAmount: Int,

    @OneToMany(mappedBy = "plan", fetch = FetchType.LAZY)
    val fileList: MutableList<File> = mutableListOf(),

    @Column(name = "content")
    val content: String? = null,

    @Column(name = "is_active")
    val isActive: Boolean,
)