import React, { useState } from 'react'
import "./MyTrips.css"
import { FaArrowRight } from "react-icons/fa";
import { IoIosArrowDropdownCircle } from "react-icons/io";
import { IoIosArrowDropup } from "react-icons/io";
import { RiEmotionHappyLine } from "react-icons/ri";
import PassengerDetail from './PassengerDetail';

const TripContainer = () => {
    const [drop, setdrop] = useState(true)
  return (
    <div className='tripContainer'>
        <div className='tripContainer1'>
            <div>
                <h2>GOA <FaArrowRight size={25}/> Nagpur</h2>
                <p>Thursday 13 March 2025 , 4 Traveller(s) , Non-Stop , 1 hr 35 min</p>
            </div>
            <div>
                Booking-Id : TRIPZYW12345678909876
            </div>
            <div>
                {drop && <IoIosArrowDropdownCircle size={40} onClick={()=>{setdrop(!drop)}} className='drop'/>}
                {!drop && <IoIosArrowDropup size={40} onClick={()=>{setdrop(!drop)}} className='drop'/>}
            </div>
        </div>
        <hr/>
        {!drop && <><div className='tripContainer2'>
            <div className='first'>
                <p>INDIGO</p>
                <p>PNR</p>
            </div>
            <div className='from'>
                <div><h2>GOA</h2></div>
                <div><h3>GOI 07:00</h3></div>
                <div>Thu, 13th March</div>
                <div>Goa Dabolim International Airport</div>
            </div>
            <div className='divider'>
                <p>1h 35m</p>
                <hr style={{width:"80%"}}/>
                <p>Non-Stop</p>
            </div>
            <div className='from'>
                <div><h2>Nagpur</h2></div>
                <div><h3>NAG 08:35</h3></div>
                <div>Thu, 13th March</div>
                <div>Dr. Babasaheb Ambedkar International Airport</div>
            </div>
            <div className='total' >
                <RiEmotionHappyLine className='icon' size={120}/>
                <h3>Completed</h3>
            </div>
        </div>
        <hr/>
        <div className='tripContainer3'>
            <h2>Passenger Details</h2>
            <ul>
                <li><PassengerDetail/></li>
            </ul>
        </div>
        <hr />
        <div className='tripContainer4'>
            <h2>Payment Details</h2>
        </div></>}
    </div>
  )
}

export default TripContainer