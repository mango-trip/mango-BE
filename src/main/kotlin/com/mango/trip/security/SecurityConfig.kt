package com.mango.trip.security

import CookieAuthorizationRequestRepository
import com.mango.trip.repository.MemberRepository
import com.mango.trip.security.jwt.JwtTokenProvider
import com.mango.trip.security.oauth.OAuth2SuccessHandler
import com.mango.trip.security.oauth.OAuthUserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val memberRepository: MemberRepository,
) {

    @Bean
    fun cookieAuthorizationRequestRepository(): CookieAuthorizationRequestRepository {
        return CookieAuthorizationRequestRepository()
    }

    @Bean
    fun oAuth2SuccessHandler(): OAuth2SuccessHandler {
        return OAuth2SuccessHandler(jwtTokenProvider)
    }

    @Bean
    fun oAuthUserService(): OAuthUserService {
        return OAuthUserService(memberRepository)
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .formLogin{ it.disable() }
            .logout { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers("/oauth2/**").permitAll()
                it.anyRequest().authenticated()
            }
            .oauth2Login { loginConfigurer ->
                loginConfigurer.successHandler(oAuth2SuccessHandler())
                loginConfigurer.authorizationEndpoint {
                    it.authorizationRequestRepository(cookieAuthorizationRequestRepository())
                }
                loginConfigurer.userInfoEndpoint { it.userService(oAuthUserService()) }
            }

        return http.build()
    }
}