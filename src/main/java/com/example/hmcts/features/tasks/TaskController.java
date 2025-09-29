package com.example.hmcts.features.tasks;

import com.example.hmcts.shared.dto.TaskRequestDto;
import com.example.hmcts.shared.dto.TaskResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * REST Controller for Task operations
 */
@Controller
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Tasks", description = "Task management API")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Create a new task with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully")
            @ApiResponse(responseCode = "400", description = "Invalid task data")
    })
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody TaskRequestDto taskRequestDto){
        log.info("Creating task via REST API: {}", taskRequestDto.getTitle());

        TaskResponseDto createdTask = taskService.createTask(taskRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);

    }
}


