package com.example.hmcts.shared.exception;

import com.example.hmcts.features.tasks.TaskNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Global exception handler for web controllers.
 */
@ControllerAdvice
@Slf4j
public class WebExceptionHandler {

    /**
     * Handle TaskNotFoundException for web views.
     */
    @ExceptionHandler(TaskNotFoundException.class)
    public String handleTaskNotFoundException(TaskNotFoundException ex, RedirectAttributes redirectAttributes) {
        log.warn("Task not found: {}", ex.getMessage());
        redirectAttributes.addFlashAttribute("errorMessage", "Task not found: " + ex.getMessage());
        return "redirect:/tasks";
    }

    /**
     * Handle generic exceptions for web views.
     */
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, RedirectAttributes redirectAttributes) {
        log.error("Unexpected error occurred", ex);
        redirectAttributes.addFlashAttribute("errorMessage", "An unexpected error occurred. Please try again.");
        return "redirect:/tasks";
    }
}

