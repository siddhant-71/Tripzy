import axios from 'axios'
import React, { useEffect, useState } from 'react'
import Flights from './Flights'
import EachFlight from './EachFlight'
import { DotLoader } from 'react-spinners'

const RenderFlights = ({detail}) => {
    const [data, setdata] = useState([])
  const [loading, setloading] = useState(false)
  const [err, seterr] = useState(null)
  useEffect(() => {
    async function fetchFlights() {
      setloading(true);
      try{
        const response=await axios.get("http://localhost:8080/api/flights/",{
          params:{
            source:detail[0],
            destination:detail[1],
            returnDate:detail[3],
            deptDate:detail[2]
          }
        });
      setloading(false);
      setdata(response.data.flights);
    }
    catch(e){
      setloading(false);
      seterr(e);
      console.log(err);
    }
    }
  fetchFlights();
  }, [])
  


  if(loading) return <div style={{justifyItems:"center"}}><DotLoader color='black'/> <p style={{color:"black",fontSize:"20px",fontWeight:"550"}}>Searching Flights</p></div>
  if(err) return <h1>ERROR</h1>
  if(!loading && data.length==0)return <h1>NO FLIGHTS FOUND</h1>
  return (
    <>
        {data.map((flight)=>(
          <EachFlight key={flight} flight={flight}/>
        ))}
    </>
  )
}

export default RenderFlights