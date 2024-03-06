package com.mango.trip.security.oauth

import com.mango.trip.entity.Member
import com.mango.trip.enumuration.OAuthType
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import java.util.*

class OAuthCustomUser(
    authorities: MutableCollection<out GrantedAuthority>,
    attributes: Map<String, Any>,
    nameAttributeKey: String,
    val oAuthType: OAuthType,
    val userId: Long,
) : DefaultOAuth2User(authorities, attributes, nameAttributeKey) {
    constructor(
        attributes: OAuthAttributes,
        usernameAttributeName: String,
        member: Member
    ): this(
        authorities = Collections.emptyList(),
        attributes = attributes.attributes,
        nameAttributeKey = usernameAttributeName,
        oAuthType = member.oauthType,
        userId = member.id!!,
    )
}