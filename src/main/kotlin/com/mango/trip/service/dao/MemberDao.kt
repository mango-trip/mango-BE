package com.mango.trip.service.dao

import com.mango.trip.entity.Member
import com.mango.trip.enumuration.OAuthType
import com.mango.trip.exception.DataNotFoundException
import com.mango.trip.repository.MemberRepository
import com.mango.trip.security.oauth.OAuthAttributes
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberDao(
    private val repository: MemberRepository,
) {
    @Transactional(readOnly = true)
    fun getById(id: Long): Member {
        return repository.findByIdOrNull(id)
            ?: throw DataNotFoundException("회원 정보를 찾을 수 없습니다. -id:$id")
    }

    @Transactional(readOnly = true)
    fun getByLoginIdAndOauthTypeOrNull(loginId: String, oAuthType: OAuthType): Member? {
        return repository.findByLoginIdAndOauthType(loginId, oAuthType)
    }

    @Transactional
    fun create(authAttributes: OAuthAttributes): Member {
        val member = Member(authAttributes)
        return repository.save(member)
    }
}