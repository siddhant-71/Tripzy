import React from 'react'
import "./MyTrips.css"
import TripContainer from './TripContainer'
import { FaArrowLeft } from "react-icons/fa";
import { useNavigate } from 'react-router-dom';

const MyTrips = () => {
  const navigate =useNavigate();
  function goBack(){
    navigate('/Home')
  }
  return (
    <div className='mytrips'>
      <div className='homeMyTrips'>
                <FaArrowLeft size={40} color='black' className='arrow' onClick={goBack}/>
                <p onClick={goBack}>HOME</p>
              </div>
        <div>
            <h2>Hello, hasta la vista</h2>
            <div className='myTrips-navbar'>
                <div><p>UpComing Trips</p></div>
                <div><p>Cancelled Trips</p></div>
                <div><p>Completed Trips</p></div>
            </div>
        </div>
        <div className='trips'>
            <TripContainer/>
        </div>
    </div>
  )
}

export default MyTrips