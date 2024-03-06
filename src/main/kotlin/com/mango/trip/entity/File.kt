package com.mango.trip.entity

import jakarta.persistence.*

@Entity
@Table(name = "file")
class File(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "save_url")
    val saveUrl: String,

    @Column(name = "file_name")
    val fileName: String,

    @ManyToOne(optional = true)
    @JoinColumn(name = "plan_id")
    val plan: Plan?,
) {
}