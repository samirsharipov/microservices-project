package uz.example.gatewayService.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.security.Key;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Value("${jwt.secret}")
    private String secretKey;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();


            if (isExcluded(request.getURI().getPath(), config.getExcludedUrls())) {
                System.out.println("Path is PUBLIC (excluded from auth): " + request.getURI().getPath()); // Debug uchun
                return chain.filter(exchange);
            }

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                System.out.println("Authorization header topilmadi for secured path: " + request.getURI().getPath()); // Debug uchun
                return onError(exchange, "Authorization header topilmadi", HttpStatus.UNAUTHORIZED);
            }

            String authHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                try {
                    validateToken(token);
                    exchange.getRequest().mutate().header("X-Auth-User", extractUsername(token)).build();
                    System.out.println("Token valid, user: " + extractUsername(token) + " for path: " + request.getURI().getPath()); // Debug uchun
                } catch (Exception e) {
                    System.out.println("Token xatosi: " + e.getMessage() + " for path: " + request.getURI().getPath()); // Debug uchun
                    return onError(exchange, "Token muddati o'tgan yoki noto'g'ri: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
                }
            } else {
                System.out.println("Authorization header formati noto'g'ri for path: " + request.getURI().getPath()); // Debug uchun
                return onError(exchange, "Authorization header formati noto'g'ri", HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);
        });
    }

    private Mono<Void> onError(org.springframework.web.server.ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private void validateToken(String token) {
        Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isExcluded(String path, List<String> excludedUrls) {
        if (excludedUrls == null || excludedUrls.isEmpty()) {
            return false;
        }
        return excludedUrls.stream().anyMatch(excludedPath -> path.equals(excludedPath));
    }

    public static class Config {
        private List<String> excludedUrls;

        public void setExcludedUrls(String urls) {
            this.excludedUrls = Arrays.asList(urls.split(","));
        }

        public List<String> getExcludedUrls() {
            return excludedUrls;
        }
    }
}