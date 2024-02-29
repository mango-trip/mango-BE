package com.mango.trip.security.oauth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler

class OAuth2FailureHandler(
    private val cookieAuthorizationRequestRepository: CookieAuthorizationRequestRepository,
) : SimpleUrlAuthenticationFailureHandler() {
    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        super.onAuthenticationFailure(request, response, exception)

        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response)
        logger.error("Google Login Failure : " + exception.message)
    }
}
