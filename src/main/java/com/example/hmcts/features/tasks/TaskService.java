package com.example.hmcts.features.tasks;

import com.example.hmcts.shared.domain.Task;
import com.example.hmcts.shared.dto.TaskRequestDto;
import com.example.hmcts.shared.dto.TaskResponseDto;
import com.example.hmcts.shared.mapper.TaskMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Optional<TaskResponseDto> getTaskById(Long id){
        log.debug("Retrieving task with ID: {}", id);
        return taskRepository.findById(id)
                .map(taskMapper::toResponseDto);

    }


}
