import axios from "axios";

export const fetchAdminAuth = async (navigate) => {
  
    try{
      // const role = localStorage.getItem("role");
      // const eid = localStorage.getItem("eid");
      // const token = localStorage.getItem("token");
      // if(token === null || role === null || eid == null ){
      //   navigate("/");
      //   return;
      // }
      // if(role === "ADMIN"){
      //   const response = await axios.get("/admin/isAdmin",{ headers : {Authorization : `Bearer ${token}`}});
      //   if(response.data === "ADMIN"){}
      // }
      // else if(role === "USER"){
      //   console.log("user in local storage")
      //   const res = await axios.get("/user/isUser",{ headers : {Authorization : `Bearer ${token}`}});
      //   if(res.data === "USER"){
      //     navigate("/userdashboard");
      //     return;
      //   }
      //   else {
      //     navigate("/");
      //   }
      // }
      // else{
      //   navigate("/");
      // }
      const token = localStorage.getItem("token");
      const response = await axios.get("/user/isUser", {headers : {Authorization : `Bearer ${token}`}});
      // console.log("response.data => ",response.data);
      
      if(response.data === "USER"){
        navigate("/userdashboard")
      }
      else if(response.status === 403){
        navigate("/")
      }
  
    }catch(error) {
      console.log(error);
    }

  }     

  export const fetchUserAuth = async (navigate) => {
    try{
      const role = localStorage.getItem("role");
      const eid = localStorage.getItem("eid");
      const token = localStorage.getItem("token");
      if(token === null || role === null || eid == null ){
        navigate("/");
        return;
      }
      const response = await axios.get("/admin/isAdmin", {headers : {Authorization : `Bearer ${token}`}});
      // console.log("response.data => ",response.data);
      
      if(response.data === "ADMIN"){
        navigate("/admindashboard")
      }
      else if(response.status === 403){
        navigate("/")
      }
  
    }catch(error) {
      console.log(error);
    }
  }     
