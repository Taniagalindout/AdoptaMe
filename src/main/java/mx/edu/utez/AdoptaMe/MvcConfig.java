package mx.edu.utez.AdoptaMe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import mx.edu.utez.AdoptaMe.handler.CustomLoginSuccessHandler;
import mx.edu.utez.AdoptaMe.handler.CustomLogoutSuccessHandler;
import mx.edu.utez.AdoptaMe.util.AppProperties;
import mx.edu.utez.AdoptaMe.util.GeneralInfoApp;
import mx.edu.utez.AdoptaMe.util.InfoMovement;

import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final AppProperties appProperties;

    public MvcConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public static CustomLoginSuccessHandler loginSuccessHandler() {return new CustomLoginSuccessHandler();}

    @Bean
    public static CustomLogoutSuccessHandler customLogoutSuccessHandler() { return new CustomLogoutSuccessHandler();}

    @Bean(name = "AppProperties")
    public static AppProperties getAppProperties() {
        return new AppProperties();
    }

    @Bean
    public static InfoMovement getInfoMovement() {
        return new InfoMovement();
    }

    @Bean
    public static GeneralInfoApp getGeneralInfoApp() { return new GeneralInfoApp();}

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error_403").setViewName("error/403");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        String pathImages = appProperties.getImageSavePath() != null ?
                appProperties.getImageSavePath() :
                "";

        String resourcePath = Paths.get(pathImages).toAbsolutePath().toUri().toString();

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(resourcePath);
    }
}
