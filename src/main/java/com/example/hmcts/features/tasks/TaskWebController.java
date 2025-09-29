package com.example.hmcts.features.tasks;

import com.example.hmcts.shared.domain.Task;
import com.example.hmcts.shared.domain.TaskStatus;
import com.example.hmcts.shared.dto.TaskRequestDto;
import com.example.hmcts.shared.dto.TaskResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    /**
     * create a new task
     */
    @PostMapping
    public String createTask(TaskRequestDto taskRequestDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Model model){
        log.info("Creating new task: {}", taskRequestDto.getTitle());

        // validation for status field
        if(taskRequestDto.getStatus() == null){
            bindingResult.rejectValue("status", "NotNull", "Status is required");
        }

        if(bindingResult.hasErrors()){
            log.warn("Validation errors in task creation: {} ", bindingResult.getAllErrors());
            model.addAttribute("taskStatuses", TaskStatus.values());
            return "task/form";
        }

        try{
            taskService.createTask(taskRequestDto);
            redirectAttributes.addFlashAttribute("successMessage", "Task created successfully");
            return "redirect:/tasks";
        } catch (Exception e) {
            log.error("Error creating task", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating task: " + e.getMessage());
            return "redirect:/tasks/new";
        }
    }

    /**
     * Display task edit form
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        log.info("Showing edit form for task: {}", id);

        try{
            TaskResponseDto taskResponseDto = taskService.getTaskById(id)
                    .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
            TaskRequestDto taskRequestDto = convertResponseDtoToRequestDto(taskResponseDto);
            model.addAttribute("taskRequestDto", taskRequestDto);
            model.addAttribute("taskId", id);
            model.addAttribute("taskStatuses", TaskStatus.values());
            return "task/form";
        } catch (TaskNotFoundException e) {
            log.warn("Task not found: {} ", id);
            redirectAttributes.addFlashAttribute("errorMessage", "Task not found");
            return "redirect:/tasks";
        }
    }

    /**
     * Update a task
     */
    @PostMapping("/{id}")
    public String updateTask(@PathVariable Long id,
                             @Valid @ModelAttribute("taskRequestDto") TaskRequestDto taskRequestDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Model model){
        log.info("Updating task: {}", id);

        // validation for status field
        if(taskRequestDto.getStatus() == null){
            bindingResult.rejectValue("status", "NotNull", "Status is required");
        }

        if(bindingResult.hasErrors()){
            log.warn("Validation errors in task update: {} ", bindingResult.getAllErrors());
            model.addAttribute("taskId", id);
            model.addAttribute("taskStatuses", TaskStatus.values());
            return "tasks/form";
        }

        try{
            taskService.updateTask(id, taskRequestDto);
            redirectAttributes.addFlashAttribute("successMessage", "Task updated successfully");
            return "redirect:/tasks";
        } catch (TaskNotFoundException e) {
            log.warn("Task not found for update: {}", id);
            redirectAttributes.addFlashAttribute("errorMessage", "Task not found");
            return "redirect:/tasks";
        } catch (Exception e) {
            log.error("Error updating task", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating task: " + e.getMessage());
            return "redirect:/tasks/" + id + "/edit";
        }
    }

    /**
     * Delete a task.
     */
    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes){
        log.info("Deleting task: {}", id);

        try{
            taskService.deleteTask(id);
            redirectAttributes.addFlashAttribute("successMessage", "Task deleted successfully");
        } catch (TaskNotFoundException e) {
            log.warn("Task not found for deletion: {}", id);
            redirectAttributes.addFlashAttribute("errorMessage", "Task not found");
        } catch (Exception e) {
            log.error("Error deleting task", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting task:" + e.getMessage());
        }

        return "redirect:/tasks";
    }













    /**
     * Convert TaskResponseDto to TaskRequestDto for form editing
     */
    private TaskRequestDto convertResponseDtoToRequestDto(TaskResponseDto dto){
        if(dto == null){
            return null;
        }

        return TaskRequestDto.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .dueDate(dto.getDueDate())
                .build();
    }



}
