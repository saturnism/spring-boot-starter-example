package com.example.translate;

import com.google.cloud.translate.v3.TranslateTextRequest;
import com.google.cloud.translate.v3.TranslationServiceClient;

class TranslateTemplateImpl implements TranslateTemplate {
  private final TranslationServiceClient client;
  private final TranslateProperties properties;

  TranslateTemplateImpl(TranslationServiceClient client,
      TranslateProperties properties) {
    this.client = client;
    this.properties = properties;
  }

  @Override
  public String translate(String text, String sourceLangCode, String targetLangCode) {
    var response = client.translateText(TranslateTextRequest.newBuilder()
        .addContents(text)
        .setParent("projects/" + properties.getProjectId())
        .setSourceLanguageCode(sourceLangCode)
        .setTargetLanguageCode(targetLangCode)
        .build());

    return response.getTranslations(0).getTranslatedText();
  }
}
