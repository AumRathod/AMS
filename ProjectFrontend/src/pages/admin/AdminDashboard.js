import React,{useEffect} from 'react'
import Sidebar from '../../components/Sidebar'
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { fetchAdminAuth } from '../../components/Verify';

function AdminDashboard() {

  const navigate = useNavigate();
  
  useEffect( () => {              //aa navu naikhu 6
    // console.log("before call");
    fetchAdminAuth(navigate);
    // console.log("after call");
  }, []) 

  return (
    <div>
      <Sidebar/>
    </div>
  )
}

export default AdminDashboard
