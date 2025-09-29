package com.example.hmcts.features.tasks;

import com.example.hmcts.shared.dto.TaskRequestDto;
import com.example.hmcts.shared.dto.TaskResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
            @ApiResponse(responseCode = "201", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid task data")
    })
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody TaskRequestDto taskRequestDto){
        log.info("Creating task via REST API: {}", taskRequestDto.getTitle());
        TaskResponseDto createdTask = taskService.createTask(taskRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    /**
     * Get a task by ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get task by ID", description = "Retrieves a task by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskResponseDto> getTaskByID(@Parameter(description = "Task ID") @PathVariable Long id){
        log.info("Getting task by ID {}", id);
        Optional<TaskResponseDto> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all tasks
     */
    @GetMapping
    @Operation(summary = "Get all tasks", description = "Retrieves all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully")
    })
    public ResponseEntity<List<TaskResponseDto>> getAllTasks(){
        log.info("Getting all tasks");
        List<TaskResponseDto> tasks =taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Update task
     */
}


