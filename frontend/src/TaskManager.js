// src/TaskManager.js
import React, { useState } from "react";
import "./App.css";

export default function TaskManager({ onLogout }) {
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState("");

  const addTask = (e) => {
    e.preventDefault();
    if (!newTask.trim()) return;
    const task = { id: Date.now(), text: newTask, completed: false };
    setTasks([...tasks, task]);
    setNewTask("");
  };

  const toggleTask = (id) => {
    setTasks(
      tasks.map((t) =>
        t.id === id ? { ...t, completed: !t.completed } : t
      )
    );
  };

  const deleteTask = (id) => {
    setTasks(tasks.filter((t) => t.id !== id));
  };

  return (
    <div className="dashboard-container">
      {/* Header */}
      <header className="dashboard-header">
        <h1>📋 Task Manager</h1>
        <button className="logout-btn" onClick={onLogout}>
          Logout
        </button>
      </header>

      {/* Navbar */}
      <nav className="dashboard-navbar">
        <ul>
          <li>🏠 Home</li>
          <li>✅ My Tasks</li>
          <li>📊 Reports</li>
          <li>⚙️ Settings</li>
        </ul>
      </nav>

      {/* Main Content */}
      <main className="dashboard-main">
        <form onSubmit={addTask} className="task-form">
          <input
            className="task-input"
            value={newTask}
            onChange={(e) => setNewTask(e.target.value)}
            placeholder="Add a new task..."
          />
          <button className="task-add-btn" type="submit">
            + Add
          </button>
        </form>

        <ul className="task-list">
          {tasks.length === 0 && <p className="empty">No tasks yet ✅</p>}
          {tasks.map((t) => (
            <li
              key={t.id}
              className={`task-item ${t.completed ? "completed" : ""}`}
            >
              <span onClick={() => toggleTask(t.id)}>{t.text}</span>
              <button
                className="delete-btn"
                onClick={() => deleteTask(t.id)}
              >
                ✖
              </button>
            </li>
          ))}
        </ul>
      </main>

      {/* Footer */}
      <footer className="dashboard-footer">
        <p>© {new Date().getFullYear()} Task Manager. All rights reserved.</p>
      </footer>
    </div>
  );
}
