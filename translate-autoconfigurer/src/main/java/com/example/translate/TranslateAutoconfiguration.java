package com.example.translate;

import com.google.cloud.translate.v3.TranslationServiceClient;
import java.io.IOException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(TranslationServiceClient.class)
@ConditionalOnProperty(value = "translate.enabled", matchIfMissing = true, havingValue = "true")
@EnableConfigurationProperties(TranslateProperties.class)
public class TranslateAutoconfiguration {
  @Bean
  @ConditionalOnMissingBean
  TranslationServiceClient translationServiceClient() throws IOException {
    return TranslationServiceClient.create();
  }

  @Bean
  @ConditionalOnMissingBean
  TranslateTemplate translateTemplate(TranslateProperties properties, TranslationServiceClient client) {
    return new TranslateTemplateImpl(client, properties);
  }

}
