import React from "react";
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const navigate = useNavigate();
  const [user, setUser] = useState({
    phone: "",
    password: "",
  });

  const handleInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleOnSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("/messagehub/rest/users/login", user);
      if (response.status === 200) {
        const id = response.data.id;
        navigate("/messagehub/contacts", { state: { id } });
      }
    } catch (error) {
      console.error(error);
    }
  };
  return (
    <form
      style={{ padding: 20 + "px", color: "white" }}
      onSubmit={handleOnSubmit}
    >
      <div className="form-group">
        <label>Phone</label>
        <input
          type="text"
          name="phone"
          pattern="[0-9]{10}"
          title="Enter 10 digit phone number"
          className="form-control"
          placeholder="Phone"
          value={user.phone}
          onChange={handleInputChange}
          required
        />
      </div>
      <div className="form-group">
        <label>Password</label>
        <input
          type="password"
          name="password"
          className="form-control"
          placeholder="Password"
          pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$"
          title="Password must contain at least 8 characters, including letters and numbers"
          value={user.password}
          onChange={handleInputChange}
          required
        />
      </div>
      <br></br>
      <button
        type="submit"
        className="btn"
        style={{ backgroundColor: "#007bff" }}
      >
        Submit
      </button>
    </form>
  );
};

export default Login;
