import './App.css'
import {Route,Routes,BrowserRouter} from 'react-router-dom'
import Home from './assets/pages/Home.jsx'
import Login from './assets/pages/Login.jsx'
import ProtectedRoute from './assets/pages/ProtectedRoute.jsx'
import Ai from './assets/Components/AI/AI.jsx'
import MyTrips from './assets/Components/MyTrips/MyTrips.jsx'
import Flights from './assets/Components/Flights/Flights.jsx'
import TripContainer from './assets/Components/MyTrips/TripContainer.jsx'
import Input from './assets/Components/Input.jsx'
import Navbari from './assets/Components/Navbari.jsx'
import Hero from './assets/Components/Hero.jsx'
import Footer from './assets/Components/Footer.jsx'
import Payment from './assets/Components/Payments/Payment.jsx'
function App() {
  return (
      <Routes>
          <Route path="/" element={<Login/>}/>
          <Route path="/Home" element={  <ProtectedRoute> <Home/> </ProtectedRoute>} >
            <Route index element={<><Hero/><Input/></>}/> 
            <Route path='AskAI' element={<Ai/>}/>
            <Route path='flights' element={<Flights/>}/>
            <Route path='History' element={<MyTrips/>}/>
            <Route path='flights' element={<Flights/>}/>
          </Route>
          <Route path='/payment' element={<Payment/>}/>
      </Routes>
  )
}

export default App
