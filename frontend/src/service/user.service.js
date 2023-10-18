import axios from "axios";

const API_URL="http://localhost:8085";
class UserService{
 saveUser(user){
    return axios.post(API_URL+"/login/register",user);
 }

 authenticate(user){
   return axios.post(API_URL+"/login/authenticate",user);
}

 
 
}
export default new UserService;