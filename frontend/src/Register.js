// src/Register.js
import React, { useState } from "react";
import "./App.css";

export default function Register({ onRegister, onSwitchToLogin }) {
  const [form, setForm] = useState({ username: "", password: "" });

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const submit = (e) => {
    e.preventDefault();
    if (!form.username || !form.password) {
      alert("Please fill both fields");
      return;
    }
    onRegister && onRegister(form);
  };

  return (
    <div className="auth-box">
      <h1>Create Account</h1>
      <form onSubmit={submit} className="auth-form">
        <input
          className="auth-input"
          name="username"
          value={form.username}
          onChange={handleChange}
          placeholder="Choose a username"
        />
        <input
          className="auth-input"
          name="password"
          type="password"
          value={form.password}
          onChange={handleChange}
          placeholder="Choose a password"
        />
        <button className="auth-btn" type="submit">
          Register
        </button>
      </form>

      <p>
        Already have an account?{" "}
        <button className="link-btn" onClick={onSwitchToLogin}>
          Login here
        </button>
      </p>
    </div>
  );
}
