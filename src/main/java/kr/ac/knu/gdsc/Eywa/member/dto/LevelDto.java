package kr.ac.knu.gdsc.Eywa.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LevelDto {
    @JsonProperty("min_exp")
    private String minExp;
    @JsonProperty("max_exp")
    private String maxExp;
}
