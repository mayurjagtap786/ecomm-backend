package org.shopping.kart.inventory;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.shopping.kart.inventory.dto.InventoryContactInfoDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(value = {InventoryContactInfoDTO.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Inventory Service REST-API Documentation",
				description ="Ecommerce Inventory Service REST API Documentation",
				version ="v1",
				contact = @Contact(
						name = "TechExamples",
						email = "mayurjagtap786@gmail.com",
						url = "https://www.techexamples.com"
				),
				license = @License(
						name = "Apache 2.O",
						url = "https://www.techexamples.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Inventory Service API Document",
				url = "https://www.techexamples.com"
		)
)
public class InventoryServcieApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServcieApplication.class, args);
	}
}