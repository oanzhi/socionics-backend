package com.socio.service;

import com.socio.model.Question;
import com.socio.model.dto.QuestionRequest;
import com.socio.model.dto.QuestionResponse;
import com.socio.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static com.socio.common.Constants.QUESTION_COUNT;
import static com.socio.common.Constants.QUESTION_ID;
import static net.logstash.logback.argument.StructuredArguments.keyValue;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {

  private final QuestionRepository questionRepository;

  public List<QuestionResponse> getAllQuestionList() {
    log.debug("Getting all question list");
    return questionRepository.findAll().stream()
        .map(Question::toResponseDto)
        .collect(Collectors.toList());
  }

  public Page<QuestionResponse> getQuestionPage(Pageable pageable) {
    log.debug("Getting question page");
    return questionRepository.findAll(pageable).map(Question::toResponseDto);
  }

  public Optional<QuestionResponse> getQuestionById(int id) {
    log.debug("Getting question with {}", keyValue(QUESTION_ID, id));
    return questionRepository.findById(id).map(Question::toResponseDto);
  }

  public List<QuestionResponse> getRandomizedQuestionList(int count) {
    log.debug("Getting and randomizing question {}", keyValue(QUESTION_COUNT, count));

    var allQuestions = questionRepository.findAll();
    if (allQuestions.size() <= count) {
      return allQuestions.stream()
          .map(Question::toResponseDto)
          .collect(Collectors.toList());
    }

    var questionIds = allQuestions.stream()
        .map(Question::getId)
        .collect(Collectors.toList());

    var idsToSelect = getRandomizedIdsFromList(questionIds, count);
    return questionRepository.getAllByIdContaining(idsToSelect).stream()
        .map(Question::toResponseDto)
        .collect(Collectors.toList());
  }

  private Set<Integer> getRandomizedIdsFromList(List<Integer> idsList, int count) {
    Random random = new Random();
    var idsToSelect = new HashSet<Integer>();
    while (idsToSelect.size() < count) {
      idsToSelect.add(idsList.get(random.nextInt(idsList.size())));
    }

    return idsToSelect;
  }

  public QuestionResponse createQuestion(QuestionRequest questionRequest) {
    log.debug("Creating new question");
    var question = new Question(questionRequest);
    var savedQuestion = questionRepository.save(question);

    return savedQuestion.toResponseDto();
  }

  public Optional<QuestionResponse> updateQuestionById(int id, QuestionRequest questionRequest) {
    log.debug("Updating existing question with {}", keyValue(QUESTION_ID, id));
    var questionToUpdate = questionRepository.findById(id);
    questionToUpdate.ifPresent(question -> {
      question.setText(questionRequest.getText());
      questionRepository.save(question);
    });

    return questionToUpdate.map(Question::toResponseDto);
  }

  public Optional<QuestionResponse> deleteById(int id) {
    log.debug("Removing existing question with {}", keyValue(QUESTION_ID, id));
    var questionToDelete = questionRepository.findById(id);
    questionToDelete.ifPresent(question -> questionRepository.deleteById(question.getId()));

    return questionToDelete.map(Question::toResponseDto);
  }
}