import React, { useEffect, useState } from 'react'
import Navbari from '../Navbari.jsx'
import Dates from './Dates.jsx'
import axios from 'axios'
import EachFlight from './EachFlight.jsx'
import RenderFlights from './RenderFlights.jsx'
import { useLocation, useNavigate } from 'react-router-dom'
import { FaArrowLeft } from "react-icons/fa";
const Flights = () => {
  const navigate=useNavigate();
  const {state} =useLocation();
  const {source,destination,deptDate,arriDate}=state || {};
  const detail=[source,destination,deptDate,arriDate];
  function goBack(){
    navigate('/Home')
  }
  return (
    <>
        <Navbari/>
        <div className='home'>
          <FaArrowLeft size={40} color='black' className='arrow' onClick={goBack}/>
          <p onClick={goBack}>HOME</p>
        </div>
        <Dates/>
        <h4>FLIGHTS FILTER DROPBOX</h4>
        <RenderFlights detail={detail}/>
    </>
  )
}

export default Flights