package com.tcs.hotelMgmt.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.tcs.hotelMgmt.entity.UserEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    // ✅ Use a proper Base64-encoded 256-bit key (at least 32 bytes after decoding)
	private final String secretKey = "u8G8wA3YbI6yQbsZjY1BQj7HxhBG27kRXuXByXZ3zFo=";

    public String GenerateToken(UserEntity user) {
        Map<String, Object> claim = new HashMap<>();
        return Jwts.builder()
                .claims(claim)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 *1000*10)) // 10 hours
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
