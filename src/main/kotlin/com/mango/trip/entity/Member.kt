package com.mango.trip.entity

import com.mango.trip.enumuration.OAuthType
import com.mango.trip.security.oauth.OAuthAttributes
import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "login_id")
    val loginId: String,

    @Column(name = "email")
    val email: String?,

    @Column(name = "oauth_type")
    @Enumerated(EnumType.STRING)
    val oauthType: OAuthType,

    @Column(name = "nickname")
    val nickname: String,
) {
    constructor(
        authAttributes: OAuthAttributes
    ): this(
        loginId = authAttributes.id!!,
        email = authAttributes.email!!,
        oauthType = authAttributes.oAuthType!!,
        nickname = authAttributes.name!!,
    )
}