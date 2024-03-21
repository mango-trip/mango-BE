package com.mango.trip.security.provider

import com.mango.trip.security.dto.MemberInfoDto
import com.mango.trip.security.jwt.JwtTokenProvider
import com.mango.trip.security.token.FrontJwtAuthenticationToken
import com.mango.trip.service.dao.MemberDao
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

class FrontJwtTokenProvider(
    private val memberDao: MemberDao,
    private val jwtTokenProvider: JwtTokenProvider,
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val jwtToken = authentication.principal as String
        val memberId = jwtTokenProvider.decodeToken(jwtToken)

        val member = memberDao.getById(memberId.toLong())
        val memberInfo = MemberInfoDto(member)
        return UsernamePasswordAuthenticationToken(memberInfo, null, memberInfo.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication.isAssignableFrom(FrontJwtAuthenticationToken::class.java)
    }
}
