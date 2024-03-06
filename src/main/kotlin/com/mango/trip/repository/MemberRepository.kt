package com.mango.trip.repository

import com.mango.trip.entity.Member
import com.mango.trip.enumuration.OAuthType
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByLoginIdAndOauthType(loginId: String, oAuthType: OAuthType): Member?
}