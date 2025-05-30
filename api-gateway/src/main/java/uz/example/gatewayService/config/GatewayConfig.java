package uz.example.gatewayService.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Configuration
@RestController
public class GatewayConfig {

    @GetMapping("/fallback/classificator")
    public Mono<String> classificatorServiceFallback() {
        return Mono.just("Tizimda hatolik. Iltimos, keyinroq urinib ko'ring.");
    }
}
