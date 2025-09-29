package com.example.hmcts.features.home;

import com.example.hmcts.features.tasks.TaskService;
import com.example.hmcts.shared.dto.TaskResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final TaskService taskService;

    /**
     * Display the home page with task information
     */

    @GetMapping("/")
    public String home(Model model){
        log.info("Displaying home page");

        List<TaskResponseDto> allTasks = taskService.getAllTasks();
        model.addAttribute("totalTasks", allTasks.size());
        model.addAttribute("recentTasks", allTasks.stream().limit(5).toList());

        return "home/index";
    }
}
