import React from 'react'
import Navbari from '../Navbari.jsx'
import Dates from './Dates.jsx'
import EachFlight from './EachFlight.jsx'
const Flights = () => {
  return (
    <div>
        <Navbari/>
        <Dates/>
        <h4>FLIGHTS FILTER DROPBOX</h4>
        <EachFlight/>
    </div>
  )
}

export default Flights