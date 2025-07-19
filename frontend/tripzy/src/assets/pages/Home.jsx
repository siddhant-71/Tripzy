import React from 'react'
import Navbari from '../Components/Navbari.jsx'
import Hero from '../Components/Hero.jsx'
import Footer from '../Components/Footer.jsx'
import Input from '../Components/Input.jsx'
import Ai from '../Components/AI/AI.jsx'
import MyTrips from '../Components/MyTrips/MyTrips.jsx'
import Content from '../Components/Content.jsx'
const Home = () => {
  return (
    <>
    <Navbari/>
    <Hero/>
    <Input/>
    <Ai/>
    <MyTrips/>
    <Content/>
    <Footer/>
    </>
  )
}

export default Home