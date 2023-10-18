import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';
import employeService from "../service/employe.service"
import { useAuth } from '../component/misc/AuthContext';

function EditData() {

  const Auth = useAuth()
  const user = Auth.getUser()

    const[employe,setEmploye]=useState({
        name:"",
        address:"",
        adharnumber:"",
        mobileno:"",
       })
      const { id} =useParams();
      //console.log(id);

       const[mesg,setmesg]=useState("");

       useEffect(()=>{
        employeService.getEmployeById(user,id).then((res)=>{
            setEmploye(res.data);
            console.log("getEmployeById"+res.data);
        }).catch((error)=>{
            console.log(error);
        })
       },[])
      
       const handleChange=(e)=>{
        const value=e.target.value;
        setEmploye({...employe,[e.target.name]:value})
       }
      
       const EmployRegister=(e)=>{
        e.preventDefault();
        console.log(employe);
      
        employeService.updateEmploye(user,employe).then((res)=>{
      console.log("Employe updated successfully");
      setmesg("Employe updated successfully");
      setEmploye({
        name:"",
        address:"",
        adharnumber:"",
        mobileno:"",
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
            Edit Employee
          </div>
          {
            mesg &&
            <p className="fs-4 text-center text-success">{mesg}</p>
          }
          <div className="card-body">
            <form onSubmit={(e)=> EmployRegister(e)}>
              <div className="mb-3">
               <label>Enter Employee Name</label>
                <input type="text" name="name" className="form-control" value={employe.name} onChange={(e)=>handleChange(e)}/>
              </div>
              <div className="mb-3">
               <label>Enter Address</label>
                <input type="text" name="address" className="form-control" value={employe.address} onChange={(e)=>handleChange(e)}/>
              </div>
              <div className="mb-3">
               <label>Enter AdharNumber</label>
                <input type="text" name="adharnumber" className="form-control" value={employe.adharnumber} onChange={(e)=>handleChange(e)}/>
              </div>
              <div className="mb-3">
               <label>Enter Mobile Number</label>
                <input type="text" name="mobileno" className="form-control" value={employe.mobileno} onChange={(e)=>handleChange(e)}/>
              </div>
              <button className="btn btn-primary col-md-12">Edit</button>
            </form>
          </div>
          </div>
         </div>
      </div>
      
          </div>
          </>
        )
      }

export default EditData