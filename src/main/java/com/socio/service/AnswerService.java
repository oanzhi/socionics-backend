package com.socio.service;

import com.socio.model.User;
import com.socio.model.UserAnswer;
import com.socio.model.UserSociotype;
import com.socio.model.dto.UserAnswerRequest;
import com.socio.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.socio.common.Constants.COUNT;
import static net.logstash.logback.argument.StructuredArguments.keyValue;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerService {

  private final AnswerRepository answerRepository;

  public void saveUserAnswers(List<UserAnswerRequest> userAnswerRequests) {
    log.debug("Saving user answers to database, {}", keyValue(COUNT, userAnswerRequests.size()));
    var userAnswers = userAnswerRequests.stream()
        .map(answerRequest -> new UserAnswer(answerRequest, new User()))
        .collect(Collectors.toList());

    answerRepository.saveAll(userAnswers);
  }

  public List<UserSociotype> processUserAnswers(List<UserAnswerRequest> userAnswerRequests) {
    log.debug("Processing users answers, {}", keyValue(COUNT, userAnswerRequests.size()));
    return Collections.emptyList();
  }
}