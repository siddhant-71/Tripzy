import React, { useState } from 'react'
import "./Flights.css"
import { IoIosArrowDropup } from "react-icons/io";
import { IoIosArrowDropdown } from "react-icons/io";
import { FaLongArrowAltRight } from "react-icons/fa";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
const EachFlight = ({flight}) => {
    const [drop, setdrop] = useState(false)
    const navigate =useNavigate();
    let total=flight.totalDurationInMinutes;
    let hr=Math.floor(total/60);
    let min=total%60;
    const timeToFly=`${hr}h ${min}m`;

    const bookingRequestDTO={
        userId:localStorage.getItem("userId"),
        flightId:flight.flightNumber,
        departureTime:flight.departureTime,
        arrivalTime:flight.arrivalTime,
        source :flight.departureAirportName,
        destination :flight.arrivalAirportName,
        seatNumber:12,
        seatClass:'A',
        totalPrice:flight.price
    }

    async function bookFlight(){
        await axios.post("http://localhost:8080/api/bookings/initiate",bookingRequestDTO).then(response=>{navigate('/payment',{state:{bookingResponse:response.data}}); console.log(response.data)}).catch(err=>{console.log(err)});
    }

  return (
    <div className='FlightContainer'>
        <div className='FlightContainer1'>
            <div>
                <img src={flight.logo} alt="" />
            </div>
            <div className='FlightName'>
                <h3>{flight.airline}</h3>
                <h4>{flight.flightNumber}</h4>
            </div>
            <div className='DepTime'>
                <h3>{flight.departureTime.substring(11,16)}</h3>
                <h3>{flight.departureAirportCode}</h3>
            </div>
            <div className='TimeToFly'>
                <h3>{timeToFly}</h3>
                <hr style={{width:"80%"}}/>
                <p>Non-Stop</p>
            </div>
            <div className='ArrivalTime'>
                <h3>{flight.arrivalTime.substring(11,16)}</h3>
                <h3>{flight.arrivalAirportCode}</h3>
            </div>
            <div className='Price'>
                <h2>₹ {flight.price} </h2>
            </div>
            <div className='Btn'>
                <button onClick={bookFlight}>BOOK</button>
                <p onClick={()=>setdrop(!drop)}>Flight Details{!drop && <IoIosArrowDropdown size={20} />}{drop && <IoIosArrowDropup size={20}/>}</p>
            </div>
        </div>
        {drop && <div className='dropbox'>
            <div>
                <h1 style={{margin:"0"}}>{flight.departureAirportCode}<FaLongArrowAltRight/>{flight.arrivalAirportCode}</h1>
                <h3>SUN {flight.departureTime.substring(8,10)} JUL · NON STOP · {timeToFly} · Economy</h3>
            </div>
            <div  style={{paddingBottom:"20px"}}>
                <h2>{flight.airline} | {flight.flightNumber}</h2>
            </div>
            <div className='Details'>
                <div className='FromDetail'>
                    <p>Sun {flight.departureTime.substring(8,10)} Jul</p>
                    <h2>{flight.departureTime.substring(11,16)}</h2>
                    <h3>{flight.departureAirportCode}</h3>
                    <p>{flight.departureAirportName}</p>
                    <p>Terminal Yet to Decide</p>
                </div>
                <div className='TimeDetail'>
                    <h2>{timeToFly}</h2>
                    <hr width="80%"/>
                </div>
                <div className='ToDetail'>
                    <p>Sun {flight.arrivalTime.substring(8,10)} Jul</p>
                    <h2>{flight.arrivalTime.substring(11,16)}</h2>
                    <h3>{flight.arrivalAirportCode}</h3>
                    <p>{flight.arrivalAirportName}</p>
                    <p>Terminal Yet to Decide</p>
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