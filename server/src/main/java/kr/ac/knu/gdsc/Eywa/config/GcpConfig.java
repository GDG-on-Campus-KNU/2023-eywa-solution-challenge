package kr.ac.knu.gdsc.Eywa.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("gcp")
@Setter
@Getter
public class GcpConfig {
  private String bucketName;
  private String keyPath;
}
