package az.company.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenProvider {

    private final String APP_SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9eyJpZCI6IjVjOWYzYWI2NzY2Mjg2NDYyNDY0YTczNCIsIm5hbWUiOiJSYW5keSIsImF2YXRhciI6Ii8vd3d3LmdyYXZhdGFyLmNvbS9hdmF0YXIvMTNhN2MyYzd";// _ isdifade etmeye icaze vermir!
    private final long EXPIRES_IN = 900_000;

    public String generateJwtToken(Authentication authentication){
        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
        Date expireDate =new Date(new Date().getTime() + EXPIRES_IN);
        Map<String,String> authority = new HashMap<>();
        authority.put("role",userDetails.getAuthorities().toString());
        return Jwts.builder().
                setClaims(authority).
                setSubject(Integer.toString(userDetails.getId())).
                setIssuedAt(new Date()).
                setExpiration(expireDate).
                signWith(SignatureAlgorithm.HS256,APP_SECRET).compact();
    }

    public Integer getUserIdFromJwt(String jwtToken){
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken).getBody();
        return Integer.parseInt(claims.getSubject());
    }

    public boolean validateToken(String jwtToken){
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
            return !isTokenExpired(jwtToken);
        }catch (Exception exception){
            return false;
        }
    }

    private boolean isTokenExpired(String jwtToken){
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).
                parseClaimsJws(jwtToken).getBody().getExpiration();

        return expiration.before(new Date());
    }

}
