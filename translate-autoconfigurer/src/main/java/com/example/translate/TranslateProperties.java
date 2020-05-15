package com.example.translate;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("translate")
public class TranslateProperties {

  private String projectId;

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }
}
