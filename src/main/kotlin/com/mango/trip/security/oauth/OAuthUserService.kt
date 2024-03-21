package com.mango.trip.security.oauth

import com.mango.trip.entity.Member
import com.mango.trip.repository.MemberRepository
import com.mango.trip.service.dao.MemberDao
import jakarta.transaction.Transactional
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User


open class OAuthUserService(
    private val memberDao: MemberDao,
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val service = DefaultOAuth2UserService()
        val oAuthUser = service.loadUser(userRequest)
        val oAuthUserAttributes = oAuthUser.attributes

        // OAuth2 서비스 id (google, kakao, naver)
        val registrationId = userRequest.clientRegistration.registrationId

        // OAuthAttributes: OAuth2User의 attribute를 서비스 유형(KAKAO, NAVER)에 맞게 담아줄 클래스
        val usernameAttributeName =
            userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName
        val attributes = OAuthAttributes(registrationId, oAuthUserAttributes)
        println(attributes)
        val user = saveOrUpdate(attributes)

        return OAuthCustomUser(attributes, usernameAttributeName, user)
    }

    @Transactional
    open fun saveOrUpdate(authAttributes: OAuthAttributes): Member {

        return memberDao.getByLoginIdAndOauthTypeOrNull(
            authAttributes.id!!, authAttributes.oAuthType!!
        ) ?: memberDao.create(authAttributes)
    }
}