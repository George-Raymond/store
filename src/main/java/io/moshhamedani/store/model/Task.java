/*
 * This file represents a Task in our application. It's mapped to a database table 
 * using JPA (Java Persistence API).
 */

package io.moshhamedani.store.model;

import io.swagger.v3.oas.annotations.media.Schema; // OpenAPI annotation for schema documentation
import jakarta.persistence.*; // JPA annotations (e.g., @Entity, @Id) for database mapping
import lombok.*; // Lombok annotations (e.g., @Getter, @Setter) to reduce boilerplate code like getters and setters.

import java.time.LocalDateTime; // A Java class for representing date and time, used here to track task creation time

@Entity // Annotation: Marks this class as a JPA entity, meaning it represents a table in the database.
@Table(name = "tasks")
@Getter // Automatically generates getter methods for all fields (e.g., getId(), getTitle())
@Setter // Generates setter methods for all fields (e.g., setId(), setTitle()).
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields as parameters
@ToString // Generates a toString() method that returns a string representation of the object
@Schema(description = "Represents a task in the Task Manager application")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the task", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Title of the task", example = "Learn Spring Boot")
    private String title;

    @Schema(description = "Description of the task", example = "Understand controllers and services")
    private String description;

    @Column(nullable = false)
    @Schema(description = "Completion status of the task", example = "false")
    private boolean completed;

    @Schema(description = "Timestamp when the task was created", example = "2025-03-05T12:34:56.789")
    private LocalDateTime createdAt = LocalDateTime.now();
}

// JPA: Maps Java objects to database tables.
// Lombok: Reduces boilerplate (no need to write getters/setters manually).
// Annotations: Control how fields map to the database (e.g., @Id, @Column).