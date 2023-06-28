import React from "react";
import App from "./App";
import { Routes, Route } from "react-router-dom";
import Messages from "./components/DashboardComponents/Messages";

const Main = () => {
  return (
    <Routes>
      <Route path="/messagehub" element={<App />} />
      <Route path="/messagehub/contacts" element={<Messages />} />
    </Routes>
  );
};

export default Main;
