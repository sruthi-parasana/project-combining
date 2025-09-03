// src/Login.js
import React, { useState } from "react";
import "./App.css";

export default function Login({ onLogin, onSwitchToRegister }) {
  const [form, setForm] = useState({ username: "", password: "" });

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const submit = (e) => {
    e.preventDefault();
    if (!form.username || !form.password) {
      alert("Please enter username and password");
      return;
    }
    onLogin && onLogin(form);
  };

  return (
    <div className="auth-box">
      <h1>Login</h1>
      <form onSubmit={submit} className="auth-form">
        <input
          className="auth-input"
          name="username"
          value={form.username}
          onChange={handleChange}
          placeholder="Enter username"
        />
        <input
          className="auth-input"
          name="password"
          type="password"
          value={form.password}
          onChange={handleChange}
          placeholder="Enter password"
        />
        <button className="auth-btn" type="submit">
          Login
        </button>
      </form>

      <p>
        Don’t have an account?{" "}
        <button className="link-btn" onClick={onSwitchToRegister}>
          Register here
        </button>
      </p>
    </div>
  );
}
