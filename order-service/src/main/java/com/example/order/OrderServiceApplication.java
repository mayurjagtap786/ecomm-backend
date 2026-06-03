package com.example.order;

import com.example.order.dto.OrderServiceContactInfo;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value={OrderServiceContactInfo.class})
@OpenAPIDefinition(
		info = @Info(
				title = "OrderService REST-API Documentation",
				description=" Ecommerce Order Service REST API Documentation",
				version = "v1",
				contact = @Contact(
						name= "TechExamples",
						email = "mayurjagtap789@gmail.com",
						url = "https://www.techexamples.com"
				),
				license = @License(
						name = "Apache 2.O",
						url = "https://www.techexamples.com"
				)
		), externalDocs = @ExternalDocumentation(
			description=" Ecommerce Order Microservice REST API External Document",
			url = "https://www.techexamples.com"
		)
)
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
