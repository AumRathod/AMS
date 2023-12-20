import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Sidebar from '../../components/Sidebar';
import { fetchAdminAuth } from '../../components/Verify';
import { useNavigate } from 'react-router-dom';

const Request = () => {
  const [requests, setRequests] = useState([]);
  const [requestStatus, setRequestStatus] = useState('');
  const navigate = useNavigate();
  const token = localStorage.getItem("token")
  useEffect(() => {
    fetchAdminAuth(navigate);
    axios.get('/admin/getAllRequests',{headers : {Authorization : `Bearer ${token}`}})
      .then(response => {
        setRequests(response.data);
      })
      .catch(error => {
        console.log(error);
      });
  }, []);

  const handleAccept = async (requestId) => {
    const confirmed = window.confirm("Are you sure you want to accept the request?");

    if (confirmed) {
      // Perform the action to accept the request
      // console.log("in confirmed......")
      await axios.post(`/admin/acceptRequest/${requestId}`,{}, { withCredentials : true ,headers : {Authorization : `Bearer ${token}`}})
        .then(res => {
          console.log(res.data);
          if(res.data.status === "Success"){
            alert("Request has been accepted.\n Mail has been sent to the employee.")
          }
          else{
            alert("Request cannot be accepted.\nReason:"+res.data.status)
          }
          setRequests(res.data.requests);
        })
        .catch(error => {
          console.log(error);
        })
      // Update the status of the request to "accepted"
      setRequestStatus('accepted');
    }
  }



    const handleReject = (requestId) => {
      // Update the status of the request to "rejected"
      
      setRequestStatus('rejected');
    };

    return (
      <div>
        <Sidebar></Sidebar>
        <div className="flex flex-col items-center lg:ml-72 sm:ml-72 mt-20">
          <h1 className="text-2xl font-bold mb-4">Requests</h1>
          <div className="w-full border-b-2 mb-4 drop-shadow-sm"></div>
          {requests.map((request) => (
            <div key={request.requestId} className="flex flex-col items-start w-full px-4 py-2 my-2 border rounded-lg">
              <div className="flex items-center w-full justify-between mb-2">
                <p className="text-sm text-gray-500">{`Employee ID: ${request.employeeId}`}</p>
                <div className="flex">
                  <button
                    onClick={() => handleAccept(request.requestId)}
                    className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded mr-2"
                  >
                    Accept
                  </button>
                  <button
                    onClick={() => handleReject(request.requestId)}
                    className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
                  >
                    Reject
                  </button>
                </div>
              </div>
              <h2 className="text-lg font-bold mb-2">{request.name}</h2>
              {Object.keys(request.attributes).map((key) => (
                <p key={key} className="text-sm">{`${key}: ${request.attributes[key]}`}</p>
              ))}
              <p className="text-xs text-gray-500 mt-2">{`Date of Request: ${new Date(request.dateOfRequest).toLocaleDateString()}`}</p>
            </div>
          ))}
          {requestStatus && <p className="text-lg mt-4">{`Request has been ${requestStatus}`}</p>}
        </div>
      </div>
    );
  };

  export default Request;
