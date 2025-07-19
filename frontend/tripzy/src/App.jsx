import './App.css'
import {Route,Routes,BrowserRouter} from 'react-router-dom'
import Home from './assets/pages/Home.jsx'
import Login from './assets/pages/Login.jsx'
import ProtectedRoute from './assets/pages/ProtectedRoute.jsx'
import Ai from './assets/Components/AI/AI.jsx'
import MyTrips from './assets/Components/MyTrips/MyTrips.jsx'
import Flights from './assets/Components/Flights/Flights.jsx'
import TripContainer from './assets/Components/MyTrips/TripContainer.jsx'
function App() {
  return (
      <Routes>
          <Route path="/" element={<Login/>}/>
          <Route path="/Home" element={  <ProtectedRoute> <Home/> </ProtectedRoute>} >
            <Route index element={<Home/>}/> 
            <Route path='AskAI' element={<Ai/>}/>
            <Route path='History' element={<MyTrips/>}/>
            <Route path='flights' element={<Flights/>}/>
          </Route>
      </Routes>
  )
}

export default App
