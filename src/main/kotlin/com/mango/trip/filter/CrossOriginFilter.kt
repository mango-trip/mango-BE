package com.mango.trip.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(0)
class CrossOriginFilter : Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpServletResponse = response as HttpServletResponse
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*")
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS, GET, DELETE, PUT, PATCH")
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600")
        httpServletResponse.setHeader(
            "Access-Control-Allow-Headers",
            "x-requested-with, origin, content-type, accept, Authorization"
        )

        val httpServletRequest = request as HttpServletRequest
        if ("OPTIONS" == httpServletRequest.method) {
            httpServletResponse.status = HttpServletResponse.SC_OK
        } else {
            chain?.doFilter(httpServletRequest, httpServletResponse)
        }
    }
}
