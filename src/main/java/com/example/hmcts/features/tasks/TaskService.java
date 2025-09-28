package com.example.hmcts.features.tasks;

import com.example.hmcts.shared.domain.Task;
import com.example.hmcts.shared.dto.TaskRequestDto;
import com.example.hmcts.shared.dto.TaskResponseDto;
import com.example.hmcts.shared.mapper.TaskMapper;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for task business logic
 */

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    /**
     * create a new task
     */
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto){
        log.info("Creating new task: {} ", taskRequestDto.getTitle());

        Task task = taskMapper.toEntity(taskRequestDto);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toResponseDto((savedTask));
    }

    /**
     * Retrieve a task by ID
     */
    @Transactional(readOnly = true)
    public Optional<TaskResponseDto> getTaskById(Long id){
        log.debug("Retrieving task with ID: {}", id);
        return taskRepository.findById(id)
                .map(taskMapper::toResponseDto);
    }

    /**
     * Retrieve all tasks
     */
    public List<TaskResponseDto> getAllTasks(){
        log.debug("Retrieving all tasks");
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    /**
     *  Update a task
     */
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto){
        log.info("Updating task: {}", id);
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id:" + id));

        taskMapper.updateEntityFromDto(existingTask, taskRequestDto);
        Task savedTask = taskRepository.save(existingTask);
        return taskMapper.toResponseDto(savedTask);



    }


}
