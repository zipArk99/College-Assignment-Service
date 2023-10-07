package com.example.classassignment.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtServiceInterface {
    private static final String SECRET = "2Z+X3tU87NaIf5OuYbMVtgi7zAtbwfCjzKitG0iw2CCJn/PiGyh6t5xKiJD0kaIx";


    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
    return  createToken(claims,userDetails);
    }

    private String createToken(Map<String, Object> claims, UserDetails userDetails) {
        String token= Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignedKey(),SignatureAlgorithm.HS256)
                .compact();
        System.out.println("Thia is token "+token);
        return token;
    }

    private Claims getAllClaims(String jwtToken) {
        return Jwts.parser().setSigningKey(getSignedKey()).parseClaimsJws(jwtToken).getBody();
    }

    private <T> T getSingleClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    public String getUsername(String jwtToken) {
        return getSingleClaim(jwtToken, Claims::getSubject);
    }
    public Date getExpiration(String jwtToken){
        return getSingleClaim(jwtToken,Claims::getExpiration);
    }

    private Key getSignedKey() {
        byte[] keyByte = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyByte);
    }

    private boolean isTokenExpired(String jwtToken) {
        return getExpiration(jwtToken).before(new Date());
    }

    public Boolean validateToken(String jwtToken,UserDetails userDetails){
        final String username= getUsername(jwtToken);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }


}
