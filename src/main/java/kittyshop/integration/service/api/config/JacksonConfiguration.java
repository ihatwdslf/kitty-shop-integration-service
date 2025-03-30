package kittyshop.integration.service.api.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import kittyshop.integration.service.api.utils.BigDecimalSerializer;
import kittyshop.integration.service.api.utils.ZonedDateTimeDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Configuration
public class JacksonConfiguration {

    @Bean
    public SimpleModule javaDateTimeModule() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());
        module.addSerializer(BigDecimal.class, new BigDecimalSerializer());
        return module;
    }
}
