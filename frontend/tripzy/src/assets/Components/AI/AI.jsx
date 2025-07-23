import React, { useState } from 'react'
import "./AI.css"
import { FaArrowLeft } from "react-icons/fa";
import { RiAiGenerate2 } from "react-icons/ri";
import { useNavigate } from 'react-router-dom';
import { DotLoader } from 'react-spinners';
import axios from 'axios';
import Markdown  from 'react-markdown';

const AI = () => {

  const [cities, setcities] = useState("")
  const [noDays, setnoDays] = useState(7)
  const [loading,setloading]=useState(false);
  const [breifloading, setbreifloading] = useState(false)
  const [aiResponse,setAiResponse]=useState("Enter the citites and get Your Itinerary");
  const [breifResponse, setbreifResponse] = useState("")
  const navigate=useNavigate();
  function goHome(){
    navigate('/Home');
  }

  async function handleAiClick(){
    if(cities==""){
      alert("enter the cities")
    }
    else{
          setloading(true);
          await axios.post("http://localhost:8080/api/ai/ask",null,{params:{details: `${cities} and for ${noDays}`}})
          .then(response=>{ console.log(response.data); setAiResponse(response.data)})
          .catch(error=>{console.log("Error is here", error)});
          setloading(false);
    }
  }
  async function getbreif(){
    if(aiResponse=="Enter the citites and get Your Itinerary"){
      alert("get the itinerary first")
    }
    else{
      setbreifloading(true);
      await axios.post("http://localhost:8080/api/ai/breif",{content:`${aiResponse}`})
      .then(response=>{setbreifResponse(response.data)})
      .catch(err=>{console.log(err)});
      setbreifloading(false);
    }
  }
  return (
    <>
    <div className='aiHome'>
      <FaArrowLeft size={35} className='icon' onClick={goHome}/>
      <p onClick={goHome} >HOME</p>
    </div>
    <div className='border'>
        <div className='request'>
            <input className='destReq' value={cities} onChange={(e)=>setcities(e.target.value)} placeholder='Enter the destinations you want to explore ...' type="text" />
            <input className='noOfDays' value={noDays} onChange={(e)=>setnoDays(e.target.value)} placeholder='No of Days...' type="number" />
            <button onClick={handleAiClick}><RiAiGenerate2 size={40} color='#bbe4e9'/></button>
        </div>
        <div className='response'>
          {!loading && <Markdown>{aiResponse}</Markdown>}
          {loading && <><DotLoader color='black'/> <p style={{color:"black",fontSize:"20px",fontWeight:"550"}}>Generating Itinerary</p></>}
        </div>
        <div>
          <button className='summary' onClick={getbreif}>Click Here for summary</button>
          <div className='summ'> 
            {!breifloading && <Markdown>{breifResponse}</Markdown>}
            {breifloading && <><DotLoader color='black'/><p>Generating summary</p></>}
          </div>
        </div>
    </div>
    </>
  )
}

export default AI