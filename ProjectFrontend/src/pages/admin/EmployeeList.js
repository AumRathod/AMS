import React, { useEffect, useState } from 'react'
import Sidebar from '../../components/Sidebar'
import axios from 'axios';
import LoadingBar from 'react-top-loading-bar';
import { fetchAdminAuth } from '../../components/Verify';
import { useNavigate } from 'react-router-dom';

function EmployeeList() {

  const [progress,setProgress]=useState(0);
  const navigate = useNavigate()
  const [employees, setEmployees] = useState([]);
  const token = localStorage.getItem("token");

  useEffect(() => {
    fetchAdminAuth(navigate);
    axios.get('/admin/getAllEmployees',{headers : {Authorization : `Bearer ${token}`}})
      .then(response => {
        //console.log(response.data);
        setProgress(50);
        setEmployees(response.data);
        setProgress(100);
      })
      .catch(error => {
        console.log(error);
      });
  }, []);

  return (
    <div>
      <LoadingBar
        color='#0000FF'
        progress={progress}
        height={5}
        onLoaderFinished={() => setProgress(0)}
      />
      <Sidebar></Sidebar>
      
        <div className="overflow-x-auto mt-20 sm:ml-72 lg:ml-72 md:ml-72">
          <table className="w-full table-auto">
            <thead>
              <tr className="bg-gray-200 text-gray-600 uppercase text-sm leading-normal">
                <th className="py-3 px-6 text-left">Employee ID</th>
                <th className="py-3 px-6 text-left">First Name</th>
                <th className="py-3 px-6 text-left">Last Name</th>
                <th className="py-3 px-6 text-left">Department</th>
                <th className="py-3 px-6 text-left">Contact No</th>
                <th className="py-3 px-6 text-left">Email</th>
                <th className="py-3 px-6 text-left">Date of Joining</th>
              </tr>
            </thead>
            <tbody className="text-gray-600 text-sm font-light">
              {employees.map((employee) => (
                <tr
                  key={employee.id}
                  className="border-b border-gray-200 hover:bg-gray-100"
                >
                  <td className="py-4 px-6 text-left font-medium">
                    {employee.employeeId}
                  </td>
                  <td className="py-4 px-6 text-left font-medium">
                    {employee.employeeFirstName}
                  </td>
                  <td className="py-4 px-6 text-left font-medium">
                    {employee.employeeLastName}
                  </td>
                  <td className="py-4 px-6 text-left font-medium">
                    {employee.department}
                  </td>
                  <td className="py-4 px-6 text-left font-medium">
                    {employee.contactNo}
                  </td>
                  <td className="py-4 px-6 text-left font-medium">
                    {employee.email}
                  </td>
                  <td className="py-4 px-6 text-left font-medium">
                    {new Date(employee.dateOfJoining).toLocaleDateString()}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      
    </div>
  )
}

export default EmployeeList
