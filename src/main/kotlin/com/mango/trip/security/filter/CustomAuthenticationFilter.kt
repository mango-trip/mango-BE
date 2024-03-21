package com.mango.trip.security.filter

import com.mango.trip.exception.auth.OAuth2RequestException
import com.mango.trip.security.token.FrontJwtAuthenticationToken
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KotlinLogging
import org.apache.coyote.BadRequestException
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

@Order(4)
class CustomAuthenticationFilter(
    private val authenticationManager: AuthenticationManager
) : OncePerRequestFilter() {
    private val log = KotlinLogging.logger {}
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationToken = getToken(request)
        val token = when (request.requestURI.split("/")[1]) {
            "front" -> FrontJwtAuthenticationToken(authorizationToken, authorizationToken.length)
            "admin" -> {
                // FIXME : 나중에 어드민 유저 정리하고 고치기
                throw BadRequestException()
            }
            else -> {
                log.warn("${request.requestURI} is not supported.")
                throw BadRequestException()
            }
        }
        val authentication = authenticationManager.authenticate(token)
        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }

    private fun getToken(request: HttpServletRequest): String {
        val headerPrefix = "Bearer "
        val tokenPayload = request.getHeader("Authorization")
        if (tokenPayload.isNullOrEmpty() || tokenPayload.length <= headerPrefix.length) {
            throw OAuth2RequestException("Not Found Token")
        }
        return tokenPayload.substring(headerPrefix.length, tokenPayload.length)
    }
}