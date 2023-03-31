package kr.ac.knu.gdsc.Eywa.member.domain;

import java.util.Map;

public class GoogleUserInfo {

  private final Map<String, Object> attributes;

  public GoogleUserInfo(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  public String getSub() {
    return (String) attributes.get("sub");
  }

  public String getName() {
    return (String) attributes.get("name");
  }

  public String getEmail() {
    return (String) attributes.get("email");
  }

  public String getPicture() {
    return (String) attributes.get("picture");
  }
}
