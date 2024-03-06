package com.mango.trip.security.oauth

import com.mango.trip.enumuration.OAuthType
import java.util.*

data class OAuthAttributes(
    val attributes: Map<String, Any>,
    var nameAttributesKey: String? = null,
    var id: String? = null,
    var name: String? = null,
    var email: String? = null,
    var gender: String? = null,
    var ageRange: String? = null,
    var profileImageUrl: String? = null,
    var oAuthType: OAuthType? = null,
) {
    constructor(
        socialName: String,
        attributes: Map<String, Any>,
    ) : this(
        attributes = attributes,
    ) {
        when (socialName) {
            OAuthType.KAKAO.registrationId -> {
                val kakaoAccount = attributes["kakao_account"] as Map<*, *>
                val kakaoProfile = kakaoAccount["profile"] as Map<*, *>

                this.oAuthType = OAuthType.KAKAO
                this.id = attributes["id"].toString()
                this.name = kakaoProfile["nickname"]?.toString()
                this.email = kakaoAccount["email"]?.toString()
                this.gender = kakaoAccount["gender"]?.toString()
                this.ageRange = kakaoAccount["ageRange"]?.toString()
                this.profileImageUrl = kakaoProfile["profile_image_url"]?.toString()
                this.nameAttributesKey = "id"
            }
            OAuthType.NAVER.registrationId -> {
                val naverAccount = attributes["response"] as Map<*, *>

                this.oAuthType = OAuthType.NAVER
                this.id = naverAccount["id"].toString()
                this.name = naverAccount["nickname"].toString()
                this.email = naverAccount["email"].toString()
                this.gender = naverAccount["gender"].toString()
                this.ageRange = naverAccount["age"].toString()
                this.profileImageUrl = naverAccount["profile_image"].toString()
                this.nameAttributesKey = "id"
            }
            else -> throw Exception("지원하지 않는 로그인 방식입니다.")
        }
    }
}