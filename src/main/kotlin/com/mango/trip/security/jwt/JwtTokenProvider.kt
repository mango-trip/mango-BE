package com.mango.trip.security.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtTokenProvider {
    @Value("\${spring.security.jwt.secret.key}")
    private lateinit var secretKey: String

    private lateinit var key: Key
    private val expiredTime = 3 * 60 * 60 * 1000 // 3시간

    @PostConstruct
    fun init() {
        key = Keys.hmacShaKeyFor(secretKey.toByteArray())
    }

    fun createToken(userId: String): String {
        val expired = Date(System.currentTimeMillis() + expiredTime)

        return Jwts.builder()
            .setSubject(userId)
            .setIssuedAt(Date())
            .setExpiration(expired)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun decodeToken(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }
}