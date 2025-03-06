//This is the service layer in your Spring Boot application, acting as an intermediary between the controller and repository layers

package io.moshhamedani.store.service;

import io.moshhamedani.store.model.Task;
import io.moshhamedani.store.repository.TaskRepository; // The repository interface for database operations on tasks.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // Marks this class as a service component.

import java.util.List;
import java.util.Optional;

@Service //Marks this class as a Spring service bean.
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Fetch all tasks (for the list)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Fetch a task by ID (for editing or retrieval)
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id); // Return Optional to match controller
    }

    // Save a task (for both adding and updating)
    public Task saveTask(Task task) {
        return taskRepository.save(task); // Handles both new and existing tasks
    }

    // Delete a task by ID
    public void deleteTask(Long id) {
        Task task = getTaskById(id)
            .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        taskRepository.delete(task);
    }

    // Optional: Explicit update method (if you want more control)
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = getTaskById(id)
            .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setCompleted(updatedTask.isCompleted());
        // createdAt remains unchanged
        return taskRepository.save(existingTask); // Updates the task with the same ID
    }
}