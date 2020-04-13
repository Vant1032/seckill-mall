package cc.vant.seckillmall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({"dev","test"})
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket = docket
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cc.vant.seckillmall"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("秒杀商城")
                .contact(new Contact("Vant", "https://www.vant.cc", "forvant@qq.com"))
                .version("1.0.0")
                .description("秒杀商城文档")
                .build();
    }
}
