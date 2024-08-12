package emi.formation.tmbackend.controllers;
import emi.formation.tmbackend.entities.Task;
import emi.formation.tmbackend.repositories.TaskRepository;
import emi.formation.tmbackend.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addtask/{userId}")
    public Task createTask(@PathVariable Long userId,@RequestBody Task task) {
        return taskService.addTaskToUser(userId,task);
    }

    @DeleteMapping("/delete/{userId}/{taskId}")
    public void deleteTask(@PathVariable Long userId,@PathVariable Long taskId) {
        taskService.deleteTask(userId,taskId);
    }
    @DeleteMapping("/deleteAll")
    public void deleteAllTasks() {
        taskService.deleteAllTasks();
    }
    @PutMapping("/update/{userId}/{taskId}")
    public Task updateTask(@RequestBody Task task,@PathVariable Long userId,@PathVariable Long taskId) {
        return taskService.updateTask(task,userId,taskId);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        if (tasks != null) {
            return ResponseEntity.ok(tasks);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/ajouterTask")
    public  Long ajouterTask(@RequestBody Task task) {
        taskRepository.save(task);
        return task.getTaskid();
    }
//for the update i let the id as the key for change to see if it's gonna make it easier if it's not the case it fine i'll use taskid given by the frontend logic
//if i use the taskid field then there will be no need to use as parameter because the front logic will send its id
}
