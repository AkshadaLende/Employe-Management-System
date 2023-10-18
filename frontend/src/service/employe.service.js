import axios from "axios";

const API_URL='http://localhost:8085';
class employeService{   
 saveEmploye(user,employe){
   console.log('Bearer ' +user.accessToken)
    return axios.post(API_URL+'/api/AddData', employe,{
      headers : {'Authorization': 'Bearer ' + user.accessToken}})
 }

 getAllEmployee(user){
   console.log('Bearer ' +user.accessToken)
    return axios.post(API_URL+"/api/AllData",{
      headers : {'Authorization': 'Bearer ' + user.accessToken}})
 }

 updateEmploye(user,employe){
    return axios.post(API_URL+"/api/UpdateData",employe,{
      headers : {'Authorization': 'Bearer ' + user.accessToken}})
 }

 deleteEmploye(user,id){
   console.log('Bearer ' +user.accessToken)
    return axios.post(API_URL+"/api/DeleteData/"+ id,{
      headers : {'Authorization': 'Bearer ' + user.accessToken}})
 }

 getEmployeById(user,id){
   return axios.post(API_URL+"/api/"+ id,{
      headers : {'Authorization': 'Bearer ' + user.accessToken}})
 }


 
}

function bearerAuth(user) {
   return `Bearer ${user.accessToken}`
 }
export default new employeService;