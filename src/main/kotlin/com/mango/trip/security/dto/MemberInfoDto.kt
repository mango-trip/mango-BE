package com.mango.trip.security.dto

import com.mango.trip.entity.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MemberInfoDto(
    var id: Long,
): UserDetails {
    constructor(member: Member): this(
        id = member.id!!,
    )

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return ArrayList()
    }
    override fun getPassword(): String = ""
    override fun getUsername(): String = ""
    override fun isAccountNonExpired(): Boolean  = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}
