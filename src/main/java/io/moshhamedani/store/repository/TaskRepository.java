/* The repository layer is responsible for communicating with the database.
It allows us to save, retrieve, update, and delete tasks without writing SQL queries.
It uses Spring Data JPA, which makes database operations simple 

JpaRepository provides CRUD methods automatically:
findAll() → Get all tasks
findById(Long id) → Get a task by ID
save(Task task) → Add or update a task
deleteById(Long id) → Delete a task
*/
package io.moshhamedani.store.repository;

import io.moshhamedani.store.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}