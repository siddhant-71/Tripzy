import React from 'react'
import { Link, Outlet, useNavigate } from 'react-router-dom';
import "./HomePage.css"
import logo from "../Images/logo.png"
const Navbari = () => {
  return (
    <div className='navbar'>
      <div>
        <img className='logo' src={logo} alt="" />
      </div>
      <div className='navbar-right'>
        <Link className='link' to="/">Help</Link>
        <Link className='link' to="/History">My Trips</Link>
        <button className='AI'>AI</button>
        <button className='Logout'>LOGOUT</button>
      </div>
    </div>
  )
}

export default Navbari