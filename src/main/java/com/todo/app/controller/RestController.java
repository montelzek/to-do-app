package com.todo.app.controller;

import com.todo.app.entity.Task;
import com.todo.app.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class RestController {

    private final TaskService taskService;

    public RestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String renderTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks/list";
    }

    @GetMapping("/create")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "tasks/create";
    }

    @PostMapping
    public String saveTask(@ModelAttribute Task task) {
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable Integer id, Model model) {
        model.addAttribute("task", taskService.findById(id));
        return "/tasks/edit";
    }

    @PostMapping("/{id}")
    public String updateTask(@PathVariable Integer id, @ModelAttribute Task task) {
        task.setId(id);
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Integer id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }
}
