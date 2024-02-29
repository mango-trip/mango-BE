package com.mango.trip.security.oauth

import com.mango.trip.security.oauth.CookieAuthorizationRequestRepository.Companion.REDIRECT_URI_PARAM_COOKIE_NAME
import com.mango.trip.exception.auth.OAuth2RequestException
import com.mango.trip.security.jwt.JwtTokenProvider
import com.mango.trip.util.CookieUtils
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler

class OAuth2SuccessHandler(
    private val jwtTokenProvider: JwtTokenProvider,
) : SimpleUrlAuthenticationSuccessHandler() {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val user = authentication.principal as OAuthCustomUser
        val jwtToken = jwtTokenProvider.createToken(user.userId.toString())
        response.addHeader("Authorization", "Bearer $jwtToken")

        redirect(request, response)
    }

    private fun redirect(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ) {
        val redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)?.value
        if (redirectUri.isNullOrBlank()) {
            throw OAuth2RequestException("Not Found Redirect URI")
        }
        redirectStrategy.sendRedirect(request, response, redirectUri)
    }
}