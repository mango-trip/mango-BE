package com.mango.trip.security.token

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class FrontJwtAuthenticationToken(
    principal: String,
    credentials: Int
) : UsernamePasswordAuthenticationToken(principal, credentials)