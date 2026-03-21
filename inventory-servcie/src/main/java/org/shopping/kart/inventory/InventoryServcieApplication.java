package org.shopping.kart.inventory;

import org.shopping.kart.inventory.dto.InventoryContactInfoDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(value = {InventoryContactInfoDTO.class})
public class InventoryServcieApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServcieApplication.class, args);
	}
}