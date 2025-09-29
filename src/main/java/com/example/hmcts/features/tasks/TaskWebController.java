package com.example.hmcts.features.tasks;

import com.example.hmcts.shared.domain.Task;
import com.example.hmcts.shared.domain.TaskStatus;
import com.example.hmcts.shared.dto.TaskRequestDto;
import com.example.hmcts.shared.dto.TaskResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Web Controller for Task operations using Thymeleaf
 */
@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Slf4j
public class TaskWebController {
    private final TaskService taskService;

    /**
     * Display all tasks
     */
    @GetMapping
    public String getAllTasks(Model model){
        log.info("Displaying all tasks");
        List<TaskResponseDto> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskStatuses", TaskStatus.values());
        return "tasks/list";
    }

    /**
     * Display task creation form
     */
    @GetMapping("/new")
    public String showCreateForm(Model model){
        log.info("Showing task creation form");
        TaskRequestDto taskRequestDto = TaskRequestDto.builder().status(TaskStatus.PENDING).build();
        model.addAttribute("taskRequestDto", taskRequestDto);
        model.addAttribute("taskStatuses", TaskStatus.values());
        return "task/form";
    }



}
