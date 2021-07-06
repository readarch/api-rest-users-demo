package apirestusers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(
                        RequestMethod.GET, newArrayList(
                                new ResponseMessageBuilder()
                                        .code(200).message("OK").build(),
                                new ResponseMessageBuilder()
                                        .code(204).message("No Content").build(),
                                new ResponseMessageBuilder()
                                        .code(400).message("Bad Request").build(),
                                new ResponseMessageBuilder()
                                        .code(401).message("Unauthorized").build(),
                                new ResponseMessageBuilder()
                                        .code(403).message("Forbidden").build(),
                                new ResponseMessageBuilder()
                                        .code(500).message("Internal Server Error").build()))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("apirestusers.controllers"))
                .paths(regex("/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().contact(new Contact("Renzo Araos", "", "readarch@gmail.com"))
                .title("API Rest Users")
                .version("1.0")
                .description("API RESTful de creación de usuarios.")
                .build();
    }
}