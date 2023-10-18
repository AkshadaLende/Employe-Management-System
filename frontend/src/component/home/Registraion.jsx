import React, { useState } from 'react'
import userService from '../../service/user.service';
import { NavLink, Navigate } from 'react-router-dom'



const Registraion = () => {

  const[user,setUser]=useState({
    username:"",
    email:"",
    password:"",
   })
   
   const handleChange=(e)=>{
    const value= e.target.value;
    setUser({...user,[e.target.name]:value})
   }
  
   const[mesg,setmesg]=useState("");
   const [isError, setIsError] = useState(false)
   const [errorMessage, setErrorMessage] = useState('')

   const UserRegister=(e)=>{
    e.preventDefault();
    console.log(user)
    userService.saveUser(user).then((res)=>{
      console.log("Data successfully saved.")
      setmesg("Registration completed. Please login");
      setUser({
        username:"",
    email:"",
    password:"",
       })
    }).catch((error)=>{
  console.log(error);
    });
   }
  
  return (
    <>
    <div className="container mt-3" >
<div className="row">
<div className="col-md-6 offset-md-3">
  <div className="card">
    <div className="card-header fs-3 text-center">
      New Registration
    </div>
    {
      mesg &&
      <p className="fs-4 text-center text-success">{mesg}</p>
    }
    <div className="card-body">
      <form onSubmit={(e)=> UserRegister(e)}>
        <div className="mb-3">
        <div class="form-outline">
                    <input type="text" id="form3Example1" name="username" class="form-control" value={user.username} onChange={(e)=>handleChange(e)} />
                    <label class="form-label" for="form3Example1">Username</label>
        </div>
        <div className="mb-3">
        <input type="email" id="form3Example3" name="email" class="form-control" value={user.email} onChange={(e)=>handleChange(e)} />
                <label class="form-label" for="form3Example3">Email address</label>
        </div>
        <div className="mb-3">
        <input type="password" id="form3Example4" name="password" class="form-control" value={user.password} onChange={(e)=>handleChange(e)} />
                <label class="form-label" for="form3Example4">Password</label>
        </div>
        
        <button className="btn btn-primary col-md-12">Submit</button>
        </div>
      </form>
    </div>
    </div>
   </div>
</div>

    </div>
    </>
  )
}

export default Registraion