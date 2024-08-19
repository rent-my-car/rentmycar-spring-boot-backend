package com.rentmycar.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {

	@Value("${SECRET_KEY}") // Injects the secret key for signing JWT tokens from application properties
	private String jwtSecret;

	@Value("${EXP_TIMEOUT}") // Injects the expiration time for the JWT from application properties
	private int jwtExpirationMs;

	private Key key;

	@PostConstruct
	public void init() {
		// Initializes the Key object used for signing and verifying JWTs
		key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}

	/**
	 * Generates a JWT token for the authenticated user.
	 * 
	 * @param authentication The authentication object containing user details.
	 * @return The generated JWT token as a string.
	 */
	// will be invoked by Authentication controller) , upon successful
	// authentication
	public String generateJwtToken(Authentication authentication) {
		log.info("generate jwt token " + authentication);
		// Cast the principal object to CustomUserDetails to access user-specific
		// details
		CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
		// JWT : userName,issued at ,exp date,digital signature(does not typically
		// contain password , can contain authorities
		// Build the JWT token with the required claims
		return Jwts.builder() // JWTs : a Factory class , used to create JWT tokens
				.setSubject((userPrincipal.getUsername()))
				// Set the subject of the token (typically the username or email)

				.setIssuedAt(new Date())// Sets the JWT Claims iat (issued at) value of current date
				// Set the issued date to the current date
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))// Sets the JWT Claims exp
				// Set the expiration date based on configured timeout
				// (expiration) value.
				// setting custom claims

				.claim("user_id", userPrincipal.getId())
				// Add a custom claim for user_id

				// .claim("email", userPrincipal.getEmail())
				// Add a custom claim for email

				.claim("authorities", getAuthoritiesInString(userPrincipal.getAuthorities()))
				// Add the authorities as a custom claim

				.signWith(key, SignatureAlgorithm.HS512)
				// Signs the constructed JWT using the specified
				// algorithm with the specified key, producing a
				// JWS(Json web signature=signed JWT)
				// Using token signing algo : HMAC using SHA-512

				.compact();
		// Actually builds the JWT and serializes it to a compact, URL-safe string
	}

	// this method will be invoked by our custom JWT filter
	public String getUserNameFromJwtToken(Claims claims) {
		return claims.getSubject();
	}

	// this method will be invoked by our custom filter
	public Claims validateJwtToken(String jwtToken) {
		// try {
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().
		// Sets the signing key used to verify JWT digital signature.
				parseClaimsJws(jwtToken).getBody();// Parses the signed JWT returns the resulting Jws<Claims> instance
		// throws exc in case of failures in verification
		return claims;
	}
	// Accepts Collection<GrantedAuthority> n rets comma separated list of it's
	// string form

	private String getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
		String authorityString = authorities.stream().map(authority -> authority.getAuthority())
				.collect(Collectors.joining(","));
		System.out.println(authorityString);
		return authorityString;
	}

	public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
		String authString = (String) claims.get("authorities");
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
		authorities.forEach(System.out::println);
		return authorities;
	}

	/**
	 * Retrieves the user ID from the JWT token.
	 * 
	 * @param token The JWT token as a String.
	 * @return The user ID as a String.
	 */
	public Long getUserIdFromJwtToken(String token) {
		Claims claims = validateJwtToken(token); // Validate the token and get claims
		return claims.get("user_id", Long.class); // Extract and return the user ID from the claims
	}

}
