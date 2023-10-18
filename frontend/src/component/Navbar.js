import React from 'react'
import { Link } from 'react-router-dom'
import { useAuth } from '../component/misc/AuthContext';

const Navbar = () => {

  const { getUser, userIsAuthenticated, userLogout } = useAuth()

  const logout = () => {
    userLogout()
  }

  const logoutMenuStyle = () => {
    return userIsAuthenticated() ? { "display": "block" } : { "display": "none" }
  }
  return (
    <>
<nav class="navbar navbar-expand-lg navbar-dark bg-success">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Employe Mangement System</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <Link to='/home' class="nav-link active" aria-current="page" href="#">Home</Link>
        </li>
        <li class="nav-item">
          <Link to='addData' class="nav-link active" aria-current="page" href="#">Add Data</Link>
        </li>
        <li class="nav-item">
          <Link to='/' class="nav-link active" aria-current="page" href="#" style={logoutMenuStyle()} onClick={logout}>Logout</Link>
        </li>
        
      </ul>
      
    </div>
  </div>
</nav>
    </>
  )
}

export default Navbar