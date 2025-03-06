/*
The REST controller layer handles HTTP requests related to CRUD operations from the frontend and returns responses.
It communicates with the service layer to perform actions.
It exposes REST API endpoints for CRUD operations.
*/

package io.moshhamedani.store.controller; // Defines the package where this class resides

import io.moshhamedani.store.model.Task;
import io.moshhamedani.store.service.TaskService; // The service layer class that contains business logic for task operations.
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // A Spring class to build HTTP responses with status codes and data
import org.springframework.web.bind.annotation.*; // Annotations for defining REST endpoints (e.g., @RestController, @GetMapping).

import java.util.List;//For returning multiple tasks.
import java.util.Optional;


@RestController // Marks this class as a REST controller. It tells Spring that this class handles HTTP requests and returns data directly as JSON (or other formats), not HTML views. Behind the scenes, it combines @Controller and @ResponseBody.
@RequestMapping("/api/tasks") // Sets a base URL path for all endpoints in this controller
@Tag(name = "Tasks", description = "Manage tasks in the Task Manager application")
public class TaskController { // This is the main class that will contain all the REST endpoint methods.

    @Autowired  //Annotation that automatically injects dependencies(i.e beans) into a class
    private TaskService taskService;

    // Get all tasks
    @GetMapping
    @Operation(summary = "Get all tasks", description = "Lists all tasks in the system")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // Get task by ID (for fetching data to populate edit form)
    @GetMapping("/{id}")
    @Operation(summary = "Get a task by ID", description = "Retrieves details of a specific task by ID")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Add new task
    @PostMapping
    @Operation(summary = "Add a new task", description = "Creates a new task with title, description, and completion status")
    public Task addTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    // Update existing task (for editing without creating new ID)
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing task", description = "Updates an existing task without changing its ID")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Optional<Task> existingTask = taskService.getTaskById(id);
        if (existingTask.isPresent()) {
            task.setId(id); // Ensure the ID remains the same
            Task updatedTask = taskService.saveTask(task);
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete task
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task", description = "Removes a task by its ID")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}