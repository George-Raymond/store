document.addEventListener("DOMContentLoaded", () => {
    // Dark Mode Functionality
    const darkModeToggle = document.getElementById('darkModeToggle');
    const body = document.body;

    // Check local storage for user preference
    const isDarkMode = localStorage.getItem('darkMode') === 'true';
    if (isDarkMode) {
        body.classList.add('dark-mode');
        darkModeToggle.checked = true;
    }

    // Toggle dark mode when the checkbox changes
    darkModeToggle.addEventListener('change', () => {
        body.classList.toggle('dark-mode');
        // Save preference to local storage
        localStorage.setItem('darkMode', darkModeToggle.checked);
    });

    // Load tasks when page loads
    fetchTasks();
});

const API_URL = "/api/tasks";

// Load tasks
function fetchTasks() {
    fetch(API_URL)
        .then(response => response.json())
        .then(tasks => {
            const taskList = document.querySelector(".task-list");
            taskList.innerHTML = `
                <h2>Your Tasks</h2>
                <table class="task-table">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Status</th>
                            <th>Description</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${tasks.map((task, index) => `
                            <tr class="task-row ${index % 2 === 0 ? 'even-row' : 'odd-row'}">
                                <td class="task-title">${task.title}</td>
                                <td class="task-status ${task.completed ? 'completed' : ''}">
                                    ${task.completed ? "Completed" : "Pending"}
                                </td>
                                <td class="task-description">${task.description || 'No description'}</td>
                                <td>
                                    <button class="toggle-btn" onclick="toggleComplete(${task.id}, ${task.completed})">
                                        ${task.completed ? "Undo" : "Complete"}
                                    </button>
                                    <button class="edit-btn" onclick="editTask(${task.id})">Edit</button>
                                    <button class="delete-btn" onclick="deleteTask(${task.id})">Delete</button>
                                </td>
                            </tr>
                        `).join('')}
                    </tbody>
                </table>
            `;
        })
        .catch(error => console.error("Error fetching tasks:", error));
}

// Form submission handling
const form = document.querySelector(".add-task form");
let isEditMode = false;
let currentTaskId = null;

form.addEventListener("submit", function (e) {
    e.preventDefault();
    if (isEditMode) {
        updateTask(currentTaskId);
    } else {
        addTask();
    }
});

// Add new task
function addTask() {
    const taskTitle = document.getElementById("task-title").value;
    const taskCompleted = document.getElementById("task-completed").checked;
    const taskDescription = document.getElementById("task-description").value;

    if (!taskTitle) return;

    fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title: taskTitle, completed: taskCompleted, description: taskDescription || null })
    })
        .then(() => {
            resetForm();
            fetchTasks();
        })
        .catch(error => console.error("Error adding task:", error));
}

// Toggle task completion
function toggleComplete(id, completed) {
    fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .then(task => {
            fetch(`${API_URL}/${id}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ id, title: task.title, completed: !completed, description: task.description })
            })
                .then(() => fetchTasks())
                .catch(error => console.error("Error toggling task:", error));
        });
}

// Delete task
function deleteTask(id) {
    fetch(`${API_URL}/${id}`, { method: "DELETE" })
        .then(() => fetchTasks())
        .catch(error => console.error("Error deleting task:", error));
}

// Edit task
function editTask(id) {
    fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .then(task => {
            document.getElementById("task-title").value = task.title;
            document.getElementById("task-completed").checked = task.completed;
            document.getElementById("task-description").value = task.description || "";
            
            document.getElementById("form-title").textContent = "Edit Task";
            document.getElementById("submit-btn").textContent = "Save Changes";
            document.getElementById("cancel-btn").style.display = "inline";

            isEditMode = true;
            currentTaskId = id;
        })
        .catch(error => console.error("Error fetching task for edit:", error));
}

// Update task
function updateTask(id) {
    const taskTitle = document.getElementById("task-title").value;
    const taskCompleted = document.getElementById("task-completed").checked;
    const taskDescription = document.getElementById("task-description").value;

    if (!taskTitle) return;

    fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title: taskTitle, completed: taskCompleted, description: taskDescription || null })
    })
        .then(() => {
            resetForm();
            fetchTasks();
        })
        .catch(error => console.error("Error updating task:", error));
}

// Reset form
function resetForm() {
    document.getElementById("task-title").value = "";
    document.getElementById("task-completed").checked = false;
    document.getElementById("task-description").value = "";
    document.getElementById("form-title").textContent = "Add a New Task";
    document.getElementById("submit-btn").textContent = "Add Task";
    document.getElementById("cancel-btn").style.display = "none";
    isEditMode = false;
    currentTaskId = null;
}