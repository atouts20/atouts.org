package   com.aatout.configuration.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.aatout"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .securitySchemes(this.securitySchemes())
                .securityContexts(Arrays.asList(this.securityContext()));
    }


    public SecurityContext securityContext() {
        AuthorizationScope[] scopes = {new AuthorizationScope("read", "for read operation"),
                new AuthorizationScope("write", "for write operation")};
        List<SecurityReference> securityReferences = Arrays.asList(new SecurityReference("basicAuth", scopes),
                new SecurityReference("Key", scopes), new SecurityReference("User Authentification Token", scopes));
        return SecurityContext.builder().securityReferences(securityReferences)
                .forPaths(PathSelectors.any())
                .build();
    }

    public List<SecurityScheme> securitySchemes() {
        // SecurityScheme basicAuth = new BasicAuth("basicAuth");
        SecurityScheme userAuthToken = new ApiKey("User Authentification Token", "Authorization", "header");
        //  SecurityScheme keyAuth = new ApiKey("Key", "Key", "header");
        return Arrays.asList(
                // keyAuth,
                userAuthToken
                //, basicAuth
        );
    }


    private ApiInfo getApiInfo() {
        Contact contact = new Contact("Direction Générale AATOUTS", "https://www.atoutsafrique.com", "aatout@gmail.com");
        return new ApiInfoBuilder()
                .title("PLATEFORME D'ECHANGE DES VALEURS AFRIQUE ATOUT")
                .description("Tous les services pour les applications la consommation des webservices")
                .version("1.0")
                .license("Aatout Benin 1.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(contact)
                .build();
    }


}