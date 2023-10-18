
import React, { useEffect, useState } from 'react'
import employeService from "../../service/employe.service"
import { Link } from 'react-router-dom';
import { useAuth } from '../misc/AuthContext';

const Home = () => {
  const Auth = useAuth()
  const user = Auth.getUser()

  const[employlist,setemploylist]=useState([]);

  useEffect(()=>{
     init();
  },[]);

  const init=()=>{
    employeService.getAllEmployee(user).then((res)=>{
      console.log(res.data)
      setemploylist(res.data);

    })
    .catch((error)=>{
     
      console.log(error);
    }); 
  }

  const[mesg,setmesg]=useState("");

 const deleteEmploye=(id)=>{
  employeService.deleteEmploye(user,id).then((res)=>{
    console.log(" Deleted successfully");
    setmesg(" Deleted successfully");
    init();
  }).catch((error)=>{
 console.log(error);
  })
 }

  return (
    <>
    <div className="container mt-3">
      <div className="row">
          <div className="col-md-12">
            <div className="card">
              <div className="card-header fs-3 text-center">
               All Employees list
               {
      mesg &&
      <p className="fs-4 text-center text-success">{mesg}</p>
    }
              </div>
              <div className="card-body">
              <table class="table">
  <thead> 
   
    <tr>
      <th scope="col">Sr. No.</th>
      <th scope="col">Names</th>
      <th scope="col">Address</th>
      <th scope="col">Adhar Number</th>
      <th scope="col">Mobile Number</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
  {
      employlist.map((p,num)=>(
        <tr><td>{num+1}</td>
        <td>{p.name}</td>
        <td>{p.address}</td>
        <td>{p.adharnumber}</td>
        <td>{p.mobileno}</td>
        <td>
          <Link to={'editData/'+ p.id} className="btn btn-sm btn-primary">Edit</Link>
          <button onClick={()=> deleteEmploye(p.id)} className="btn btn-sm btn-danger ms-1">Delete</button>
        </td>
        </tr>
      ))
    }
   
  </tbody>
</table>
              </div>
            </div>
          </div>
      </div>
    </div>
    </>
    
  );



}

export default Home