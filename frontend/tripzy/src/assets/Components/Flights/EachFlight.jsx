import React, { useState } from 'react'
import "./Flights.css"
import { IoIosArrowDropup } from "react-icons/io";
import { IoIosArrowDropdown } from "react-icons/io";
import { FaLongArrowAltRight } from "react-icons/fa";
const EachFlight = () => {
    const [drop, setdrop] = useState(false)
  return (
    <div className='FlightContainer'>
        <div className='FlightContainer1'>
            <div className='FlightName'>
                <h3>SPICE JET</h3>
                <h4>SG9965</h4>
            </div>
            <div className='DepTime'>
                <h3>20:00</h3>
                <h3>DEL</h3>
            </div>
            <div className='TimeToFly'>
                <h3>2h 35m</h3>
                <hr style={{width:"80%"}}/>
                <p>Non-Stop</p>
            </div>
            <div className='ArrivalTime'>
                <h3>04:30</h3>
                <h3>BOM</h3>
            </div>
            <div className='Price'>
                <h2>₹ 4330 </h2>
            </div>
            <div className='Btn'>
                <button>BOOK</button>
                <p>Flight Details{!drop && <IoIosArrowDropdown onClick={()=>setdrop(!drop)} size={20} />}{drop && <IoIosArrowDropup onClick={()=>setdrop(!drop)} size={20}/>}</p>
            </div>
        </div>
        {drop && <div className='dropbox'>
            <div>
                <h1 style={{margin:"0"}}>NEW DELHI <FaLongArrowAltRight/> MUMBAI</h1>
                <h3>SUN 20 JUL · NON STOP · 2h 15m · Economy</h3>
            </div>
            <div  style={{paddingBottom:"20px"}}>
                <h2>Spice Jet | SG 9965</h2>
            </div>
            <div className='Details'>
                <div className='FromDetail'>
                    <p>Sun 20 Jul</p>
                    <h2>02:15</h2>
                    <h3>DEL - New Delhi</h3>
                    <p>Indira Gandhi International Airport</p>
                    <p>Terminal 1D</p>
                </div>
                <div className='TimeDetail'>
                    <h2>2h 15m</h2>
                    <hr width="80%"/>
                </div>
                <div className='ToDetail'>
                    <p>Sun 20 Jul</p>
                    <h2>04:30</h2>
                    <h3>BOM - MUMBAI</h3>
                    <p>Chhatrapati Shivaji Maharaj International Airport</p>
                    <p>Terminal 1</p>
                </div>
                <div className='Baggage'>
                    <h2>Baggage</h2>
                    <p>Cabin : 7 kg per Adult</p>
                    <p>Check-in : 15 kg per Adult</p>
                </div>
            </div>
        </div>}
    </div>
  )
}

export default EachFlight