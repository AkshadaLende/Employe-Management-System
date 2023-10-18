import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './component/misc/AuthContext';
import './App.css';
import Navbar from './component/Navbar';
import Home from './component/home/Home';
import AddData from './component/AddData';
import EditData from './component/EditData';
import Login from './component/home/Login';
import Registraion from './component/home/Registraion';
import { NavLink, Navigate } from 'react-router-dom'

function App() {
  return (
    <AuthProvider>
      {/* <Router> */}
        <Navbar />
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/home" element={<Home />} />
          <Route path="/addData" element={<AddData />} />
          <Route path="/editData/:id" element={<EditData />} />
          <Route path="/newRegistration" element={<Registraion />} />
          <Route path="*" element={<Navigate to="/" />} />
         
        </Routes>
      {/* </Router> */}
    </AuthProvider>
  );
}

export default App;
