package emi.formation.tmbackend.services;


import emi.formation.tmbackend.entities.Task;
import emi.formation.tmbackend.entities.User;
import emi.formation.tmbackend.repositories.TaskRepository;
import emi.formation.tmbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> getTasksByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user.getTasks();
        }
        return null;
    }

    public Task addTaskToUser(Long userId, Task task) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            getTasksByUserId(userId).add(task);//this allows to associate between user and his task
            taskRepository.save(task);
            return task;
        }
        else {
            return null;
        }

    }


    public void deleteTask(Long userId,Long taskId) {
        User user = userRepository.findById(userId).orElse(null);

        taskRepository.deleteById(taskId);
    }
    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }
    public Task updateTask(Task newTask, Long userId, Long taskId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        List<Task> tasks = getTasksByUserId(userId);
        for (Task existingTask : tasks) {
            if (existingTask.getId().equals(taskId)) {
                // Update the task's content and datetime
                existingTask.setContent(newTask.getContent());
                existingTask.setDatetime(newTask.getDatetime());

                return taskRepository.save(existingTask);
            }
        }

        return null;
    }

}
