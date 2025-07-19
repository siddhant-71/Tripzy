import React from 'react'
import "./MyTrips.css"
import TripContainer from './TripContainer'

const MyTrips = () => {
  return (
    <div className='mytrips'>
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