import React, { useState } from 'react'
import "./HomePage.css"
import { FaArrowCircleRight } from "react-icons/fa";
import { FaArrowAltCircleRight } from "react-icons/fa";
import { useNavigate } from 'react-router-dom';
const Input = () => {

  const [from, setfrom] = useState("")
  const [to, setto] = useState("")
  const [arriDate, setarriDate] = useState("")
  const [deptDate, setdeptDate] = useState("")
  const navigate=useNavigate();  
  const handleSearch=()=>{
    navigate('flights',{
      state:{
        source:from,
        destination:to,
        deptDate:arriDate,
        arriDate:deptDate
      }
    });
  }
  return (
    <div className='input-form'>
        <div><input type="text" name="" id="" onChange={(e)=>setfrom(e.target.value)} required value={from} placeholder='Delhi'/>FROM</div>
        <div><input type="text" name="" id="" onChange={(e)=>setto(e.target.value)} required value={to} placeholder='Mumbai'/>TO</div>
        <div><input type="date" name="" id="" onChange={(e)=>setarriDate(e.target.value)} required value={arriDate} placeholder='departure'/>DEPARTURE</div>
        <div><input type="date" name="" id="" onChange={(e)=>setdeptDate(e.target.value)} required value={deptDate} placeholder='arrival'/>ARRIVAL</div>
        <button><FaArrowAltCircleRight color='lightgreen' size={60} onClick={handleSearch}/></button>
    </div>
  )
}

export default Input