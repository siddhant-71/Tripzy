import React from 'react'
import "./HomePage.css"
import { FaArrowCircleRight } from "react-icons/fa";
import { FaArrowAltCircleRight } from "react-icons/fa";
const Input = () => {
  return (
    <div className='input-form'>
        <div><input type="text" name="" id="" placeholder='Delhi'/>FROM</div>
        <div><input type="text" name="" id="" placeholder='Mumbai'/>TO</div>
        <div><input type="date" name="" id="" placeholder='departure'/>DEPARTURE</div>
        <div><input type="date" name="" id="" placeholder='arrival'/>ARRIVAL</div>
        <button><FaArrowAltCircleRight color='lightgreen' size={60}/></button>
    </div>
  )
}

export default Input