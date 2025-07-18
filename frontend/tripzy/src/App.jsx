import './App.css'
import {Route,Routes,BrowserRouter} from 'react-router-dom'
import Home from './assets/pages/Home.jsx'
function App() {

  return (
    // <BrowserRouter>
    //   <Routes>
    //       <Route path="/" element={<Login/>}/>
    //       <Route path="/register" element={<Register/>}/>
    //       <Route path="/dashboard" element={  <ProtectedRoute> <Dashboard/> </ProtectedRoute>} >
    //         <Route index element={<DashboardData/>}/> 
    //         <Route path='restaurant' element={<Restaurants/>}/>
    //         <Route path='track' element={<TrackOrder/>}/>
    //         <Route path='history' element={<OrderHistory/>}/>
    //         <Route path='cart' element={<Cart/>}/>
    //       </Route>
    //   </Routes>
    // </BrowserRouter>
    <Home/>
  )
}

export default App
