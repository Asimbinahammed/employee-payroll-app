package com.bridgelabz.employeepayroll.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Purpose : To configure the SWAGGER2 documentation for employee payroll service application
 *
 * @author ASIM AHAMMED
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-15
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * Purpose : This method is used to specify the swagger to which API(Application Programming Interface)
     * to show on Swagger UI(User Interface) console
     *
     * @return the docket link which has the information about API(Application Programming Interface)
     */
    @Bean
    public Docket postApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Employee Payroll")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bridgelabz"))
                .build();
    }

    /**
     * Purpose : This method is used to add extra datas which will give user a proper idea about
     * the API(Application Programming Interface) information in the Swagger UI(User Interface) console
     *
     * @return the swagger API information
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Employee Payroll Service Application")
                .description("Sample Documentation Generated Using SWAGGER2 for Employee Payroll Rest API")
                .termsOfServiceUrl("https://github.com/Asimbinahammed")
                .license("License")
                .licenseUrl("https://github.com/Asimbinahammed")
                .version("1.0")
                .title("Employee Payroll Rest API")
                .contact(new Contact("Asim", "https://www.facebook.com/", "asim242370@gmail.com"))
                .build();
    }
}