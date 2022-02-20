package com.socio.controller;

import com.socio.model.dto.QuestionRequest;
import com.socio.model.dto.QuestionResponse;
import com.socio.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.socio.common.Constants.QUESTION_ID;
import static net.logstash.logback.argument.StructuredArguments.keyValue;

@Slf4j
@Controller
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    @Operation(summary = "Get question response list")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Question list was successfully fetched"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<List<QuestionResponse>> getAllQuestionsList() {
        log.info("Getting all question list");
        return ResponseEntity.ok(questionService.getAllQuestionList());
    }

    @GetMapping("/page")
    @Operation(summary = "Get question response page")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Question page was successfully fetched"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<Page<QuestionResponse>> getQuestionPage(
            @PageableDefault(sort = {"modifiedDate", "creationDate"}) Pageable pageable) {
        log.info("Getting question page");
        return ResponseEntity.ok(questionService.getQuestionPage(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get question response by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Question was successfully fetched"),
            @ApiResponse(responseCode = "204", description = "Question was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<QuestionResponse> getQuestionById(@PathVariable Integer id) {
        log.info("Getting question with {}", keyValue(QUESTION_ID, id));
        var questionResponse = questionService.getQuestionById(id);

        return questionResponse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    @Operation(summary = "Create new question")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Question was successfully created"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<QuestionResponse> createQuestion(@RequestBody QuestionRequest questionRequest) {
        log.info("Creating new question");
        var questionResponse = questionService.createQuestion(questionRequest);

        return ResponseEntity.ok(questionResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing question")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Question was successfully updated"),
            @ApiResponse(responseCode = "204", description = "Question was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<QuestionResponse> updateQuestion(@PathVariable Integer id, @RequestBody QuestionRequest questionRequest) {
        log.info("Updating existing question with {}", keyValue(QUESTION_ID, id));
        var questionResponse = questionService.updateQuestionById(id, questionRequest);

        return questionResponse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove existing question if present")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Question was successfully removed"),
            @ApiResponse(responseCode = "204", description = "Question was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<QuestionResponse> deleteQuestion(@PathVariable Integer id) {
        log.info("Removing existing question with {}", keyValue(QUESTION_ID, id));
        var questionResponse = questionService.deleteById(id);

        return questionResponse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}