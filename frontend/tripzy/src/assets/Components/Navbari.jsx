import React from 'react'
import { Link, Outlet, useNavigate } from 'react-router-dom';
import "./HomePage.css"
import logo from "../Images/logo.png"
const Navbari = () => {
  const navigate=useNavigate();
  function handlelogout(){
    localStorage.clear();
    navigate("/")
  }
  function goToAI(){
    navigate("/Home/AskAI")
  }
  return (
    <div className='navbar'>
      <div>
        <img className='logo' src={logo} alt="" />
      </div>
      <div className='navbar-right'>
        <Link className='link' to="/Home">Help</Link>
        <Link className='link' to="/Home/History">My Trips</Link>
        <button className='AI' onClick={goToAI}>AI</button>
        <button className='Logout' onClick={handlelogout}>LOGOUT</button>
      </div>
    </div>
  )
}

export default Navbari