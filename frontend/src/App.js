// src/App.js
import React, { useState } from "react";
import "./App.css";
import TaskManager from "./TaskManager";
import Login from "./Login";
import Register from "./Register";

function App() {
  const [user, setUser] = useState(localStorage.getItem("user") || null);
  const [isRegister, setIsRegister] = useState(false);

  const handleRegister = ({ username, password }) => {
    if (!username || !password) return alert("Fill all fields");
    localStorage.setItem("registeredUser", JSON.stringify({ username, password }));
    alert("Registered successfully — please login");
    setIsRegister(false);
  };

  const handleLogin = ({ username, password }) => {
    const stored = JSON.parse(localStorage.getItem("registeredUser"));
    if (stored && stored.username === username && stored.password === password) {
      localStorage.setItem("user", username);
      setUser(username);
    } else {
      alert("Invalid username or password");
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("user");
    setUser(null);
  };

  if (user) {
    return (
      <div className="App">
        <div
          style={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            maxWidth: 800,
            margin: "12px auto",
          }}
        >
          <h2>Welcome, {user}</h2>
          <button onClick={handleLogout}>Logout</button>
        </div>
        <TaskManager />
      </div>
    );
  }

  return isRegister ? (
    <Register onRegister={handleRegister} onSwitchToLogin={() => setIsRegister(false)} />
  ) : (
    <Login onLogin={handleLogin} onSwitchToRegister={() => setIsRegister(true)} />
  );
}

export default App;
