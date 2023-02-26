package project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project.advice.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor interceptor;

    private final String[] EXCLUDE_PATHS = {"/api/error", "/signup", "/login", "/start"};

    public WebConfig(LoginInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATHS);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://mullaepro.com")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Access-Control-Allow-Origin",
                        "*",
                        "Access-Control-Allow-Methods",
                        "POST, GET, PUT, DELETE",
                        "Access-Control-Allow-Headers",
                        "Origin, X-Requested-With, Content-Type, Accept",
                        "X-Token")
                .allowCredentials(true)
                .exposedHeaders("token")
                .maxAge(3000);
    }

}