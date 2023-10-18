import React, { useState } from 'react'
import userService from '../../service/user.service';
import { Link } from 'react-router-dom';
import {  parseJwt } from '../home/Helper';
import { useAuth } from '../misc/AuthContext';
import { NavLink, Navigate } from 'react-router-dom'

const Login = () => {
  const Auth = useAuth()
  const isLoggedIn = Auth.userIsAuthenticated()
  
 const[user,setUser]=useState({
  username:"",
  password:"",
 })
 
 const handleChange=(e)=>{
   const value= e.target.value;
  setUser({...user,[e.target.name]:value})
 }

 const[mesg,setmesg]=useState("");
 const[isError,setIsError]=useState(false);

 const userLogin=(e)=>{
  e.preventDefault();
  console.log(user)

  userService.authenticate(user).then((response)=>{
    console.log(response.data)
  //  const {accessToken} =res.data
   // setAuthHeader(accessToken);
  //   const data= parseJwt(accessToken)
  //   const authenticaticatedUser= {data,accessToken}
  // userLogin(authenticaticatedUser)
  const accessToken  = response.data
  const data = parseJwt(accessToken)
  const authenticatedUser = { data, accessToken }

  Auth.userLoginIn(authenticatedUser)
  setUser({
    username:"",
    password:""
   })
  
  }).catch((error)=>{
    console.log(error)
   // setIsError(true)
  })
  
 }

 if (isLoggedIn) {
  return <Navigate to={'/home'} />
}

 
  return (
    <>
    <section class="background-radial-gradient overflow-hidden">
    <style>
    {`.background-radial-gradient {
      background-color: hsl(218, 41%, 15%);
      background-image: radial-gradient(650px circle at 0% 0%,
          hsl(218, 41%, 35%) 15%,
          hsl(218, 41%, 30%) 35%,
          hsl(218, 41%, 20%) 75%,
          hsl(218, 41%, 19%) 80%,
          transparent 100%),    
        radial-gradient(1250px circle at 100% 100%,
          hsl(218, 41%, 45%) 15%,
          hsl(218, 41%, 30%) 35%,
          hsl(218, 41%, 20%) 75%,
          hsl(218, 41%, 19%) 80%,
          transparent 100%);
    }

    #radius-shape-1 {
      height: 220px;
      width: 220px;
      top: -60px;
      left: -130px;
      background: radial-gradient(#44006b, #ad1fff);
      overflow: hidden;
    }

    #radius-shape-2 {
      border-radius: 38% 62% 63% 37% / 70% 33% 67% 30%;
      bottom: -60px;
      right: -110px;
      width: 300px;
      height: 300px;
      background: radial-gradient(#44006b, #ad1fff);
      overflow: hidden;
    }

    .bg-glass {
      background-color: hsla(0, 0%, 100%, 0.9) !important;
      backdrop-filter: saturate(200%) blur(25px);
    }`}
  </style>
 


  <div class="container px-4 py-5 px-md-5 text-center text-lg-start my-5">
    <div class="row gx-lg-5 align-items-center mb-5">
      <div class="col-lg-6 mb-5 mb-lg-0" style={{ zIndex: 10 }}>
        <h1 class="my-5 display-5 fw-bold ls-tight" style={{ color: 'hsl(218, 81%, 95%)' }}>
          Employe Management <br />
          <span style={{ color: 'hsl(218, 81%, 75%)' }}>Admin login</span>
        </h1>
        <p class="mb-4 opacity-70" style={{ color: 'hsl(218, 81%, 85%)' }}>
        Good management is the art of making problems so interesting and their solutions so constructive that everyone wants to get to work and deal with them.
        </p>
      </div>

      <div class="col-lg-6 mb-5 mb-lg-0 position-relative">
        <div id="radius-shape-1" class="position-absolute rounded-circle shadow-5-strong"></div>
        <div id="radius-shape-2" class="position-absolute shadow-5-strong"></div>

        <div class="card bg-glass">
        {
      mesg &&
      <p className="fs-4 text-center text-success">{mesg}</p>
    }
          <div class="card-body px-4 py-5 px-md-5">
            <form onSubmit={(e)=> userLogin(e)}>
             
             
                <div class="form-outline mb-4">
                  <div class="form-outline">
                    <input type="text" id="form3Example1" name="username" class="form-control" value={user.username} onChange={(e)=>handleChange(e)} />
                    <label class="form-label" for="form3Example1">Username</label>
                  </div>
               
               
              </div>

              
              {/* <div class="form-outline mb-4">
                <input type="email" id="form3Example3" name="email" class="form-control" value={user.email} onChange={(e)=>handleChange(e)} />
                <label class="form-label" for="form3Example3">Email address</label>
              </div> */}

            
              <div class="form-outline mb-4">
                <input type="password" id="form3Example4" name="password" class="form-control" value={user.password} onChange={(e)=>handleChange(e)} />
                <label class="form-label" for="form3Example4">Password</label>
              </div>

              <Link to="newRegistration/" className="btn btn-primary btn-block me-2">Register</Link>
              <button type="submit" class="btn btn-primary btn-block me-2">
                Login
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  </section>

    </>
  )
}

export default Login