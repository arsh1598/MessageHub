import "./App.css";
import Register from "./components/RegisterComponent/Register";
import Title from "./components/TitleComponent/Title";
import { useState } from "react";
import Login from "./components/LoginComponent/Login";

function App() {
  const [showRegister, setShowRegister] = useState(false);
  const [showLogin, setShowLogin] = useState(false);

  function showRegisterForm() {
    setShowLogin(false);
    setShowRegister(true);
  }

  function showLoginForm() {
    setShowRegister(false);
    setShowLogin(true);
  }
  return (
    <div className="appContainer">
      <Title />
      <div className="button-group">
        <button className="button" onClick={() => showRegisterForm()}>
          Register
        </button>
        <button className="button blue" onClick={() => showLoginForm()}>
          Login
        </button>
      </div>
      {(showRegister && <Register />) || (showLogin && <Login />)}
    </div>
  );
}

export default App;
