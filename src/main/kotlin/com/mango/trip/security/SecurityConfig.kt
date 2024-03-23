package com.mango.trip.security

import com.mango.trip.security.oauth.CookieAuthorizationRequestRepository
import com.mango.trip.repository.MemberRepository
import com.mango.trip.security.filter.CustomAuthenticationFilter
import com.mango.trip.security.jwt.JwtTokenProvider
import com.mango.trip.security.oauth.OAuth2FailureHandler
import com.mango.trip.security.oauth.OAuth2SuccessHandler
import com.mango.trip.security.oauth.OAuthUserService
import com.mango.trip.security.provider.FrontJwtTokenProvider
import com.mango.trip.service.dao.MemberDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.OrRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val memberDao: MemberDao,
    private val cookieAuthorizationRequestRepository: CookieAuthorizationRequestRepository,
) {

    val authPassList = listOf(
        AntPathRequestMatcher("/docs/**", HttpMethod.GET.name()),
        AntPathRequestMatcher("/v3/api-docs/**", HttpMethod.GET.name()),
        AntPathRequestMatcher("/oauth2/**", HttpMethod.GET.name()),
        AntPathRequestMatcher("/login/**", HttpMethod.GET.name()),
        // Front Public API
        AntPathRequestMatcher("/front/api/v1/country/**", HttpMethod.GET.name()),
    )

    @Bean
    fun oAuth2SuccessHandler(): OAuth2SuccessHandler {
        return OAuth2SuccessHandler(jwtTokenProvider)
    }

    @Bean
    fun oAuth2FailureHandler(): OAuth2FailureHandler {
        return OAuth2FailureHandler(cookieAuthorizationRequestRepository)
    }

    @Bean
    fun oAuthUserService(): OAuthUserService {
        return OAuthUserService(memberDao)
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().requestMatchers(OrRequestMatcher(authPassList))
        }
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .formLogin{ it.disable() }
            .logout { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers("/oauth2/**").permitAll()
                it.requestMatchers("/login/**").permitAll()
                it.anyRequest().authenticated()
            }
            .oauth2Login { loginConfigurer ->
                loginConfigurer.successHandler(oAuth2SuccessHandler())
                loginConfigurer.failureHandler(oAuth2FailureHandler())
                loginConfigurer.authorizationEndpoint {
                    it.authorizationRequestRepository(cookieAuthorizationRequestRepository)
                }
                loginConfigurer.userInfoEndpoint { it.userService(oAuthUserService()) }
            }
            .addFilterBefore(
                customAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter::class.java
            )

        return http.build()
    }

    fun customAuthenticationFilter(): CustomAuthenticationFilter {
        return CustomAuthenticationFilter(authenticationManager())
    }

    @Bean
    protected fun authenticationManager(): AuthenticationManager {
        return ProviderManager(
            FrontJwtTokenProvider(memberDao, jwtTokenProvider),
        )
    }
}