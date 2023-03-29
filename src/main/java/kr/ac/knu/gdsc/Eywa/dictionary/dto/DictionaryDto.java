package kr.ac.knu.gdsc.Eywa.dictionary.dto;

import kr.ac.knu.gdsc.Eywa.dictionary.domain.Dictionary;
import kr.ac.knu.gdsc.Eywa.register.dto.RegisterResponseDto;
import lombok.Getter;

@Getter
public class DictionaryDto {
  private final Dictionary data;
  private final RegisterResponseDto register;

  public DictionaryDto(Dictionary dictionary, RegisterResponseDto registerResponseDto) {
    this.data = dictionary;
    this.register = registerResponseDto;
  }
}
