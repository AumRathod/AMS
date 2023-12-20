import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import UserSidebar from '../../components/UserSidebar'
import { fetchUserAuth } from '../../components/Verify';

function UserDashboard() {
  const navigate = useNavigate();
  useEffect(() => {
    fetchUserAuth(navigate);
  }, [])
  
  return (
    <div>
      <UserSidebar/>
    </div>
  )
}

export default UserDashboard
