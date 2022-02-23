package com.socio.controller;

import com.socio.model.UserSociotype;
import com.socio.model.dto.UserAnswerRequest;
import com.socio.service.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.socio.common.Constants.COUNT;
import static net.logstash.logback.argument.StructuredArguments.keyValue;

@Slf4j
@Controller
@RequestMapping("/api/answer")
@RequiredArgsConstructor
public class AnswerController {

  private final AnswerService answerService;

  @PostMapping
  public ResponseEntity<List<UserSociotype>> processAnswers(List<UserAnswerRequest> userAnswerRequests) {
    log.info("Processing and saving answers, {}", keyValue(COUNT, userAnswerRequests.size()));
    var sociotypesProbability = answerService.processUserAnswers(userAnswerRequests);
    answerService.saveUserAnswers(userAnswerRequests);

    return ResponseEntity.ok(sociotypesProbability);
  }
}