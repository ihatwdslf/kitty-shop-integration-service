package kittyshop.integration.service.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "kittyshop.integration.service.api.config")
public class KittyShopIntegrationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KittyShopIntegrationServiceApplication.class, args);
    }

}
