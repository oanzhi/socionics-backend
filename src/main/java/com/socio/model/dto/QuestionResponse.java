package com.socio.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.socio.model.Question;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionResponse {

  @Schema(description = "Question id")
  private Integer id;

  @Schema(description = "Question text")
  private String text;

  @Schema(description = "Local date time of question modifying", example = "2022-02-20T14:30:07.5053601")
  private LocalDateTime modifiedDate;

  @Schema(description = "Local date time of question creating", example = "2022-02-20T14:30:07.5053601")
  private LocalDateTime creationDate;

  public QuestionResponse(Question question) {
    BeanUtils.copyProperties(question, this);
  }
}