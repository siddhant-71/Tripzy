import React, { useState } from 'react'
import "./Pages.css"
import { Link, Navigate, useNavigate } from 'react-router-dom'
import logo from "../Images/Logo.png"
import axios from 'axios'
const Login = () => {
    const [Container, setContainer] = useState(1)
    const [loginInput, setloginInput] = useState("")
    const [loginPass, setloginPass] = useState("")
    const navigate=useNavigate();

    async function loginNow() {
        try{
            const response=await axios.post("http://localhost:8080/api/user/login",null,{
                params:{
                    email:loginInput,
                    password:loginPass
                }
            });
            console.log(response.data.jwtToken);
            localStorage.setItem("token",response.data.jwtToken);
            localStorage.setItem("userId",response.data.userId);
            navigate("/Home")
        }
        catch(e){
            console.log("Login Failed",e);
            alert("Invalid Username or Password")
        }
    }
  return (
    <div className='loginContainer'>
        {Container==1 && <div className='Container'>
            <h2>LOGIN</h2>
            <input type="text" placeholder='Enter Email Or Phone Number' name="loginInput" id="loginInput"  required value={loginInput} onChange={(e)=>setloginInput(e.target.value)}/>
            <input type="text" placeholder='Enter Password' name="loginPass" id="loginPass" required value={loginPass} onChange={(e)=>setloginPass(e.target.value)}/>
            <Link className='forgot' onClick={()=>setContainer(3)}>Forgot Password ?</Link>
            <button onClick={loginNow}>LOGIN</button>
            <p className='register'>ALREADY HAVE AN ACCOUNT ?  <Link onClick={()=>setContainer(2)} className='register1'>Register Here</Link></p>
        </div>}
        {Container==2 && <div className='Container2'>
            <h2>Register</h2>
            <hr style={{color:"white",width:"100%"}}/>
            <div className='Name'>
                <input placeholder='First Name' type="text" />
                <input placeholder='Last Name' type="text" />
            </div>
            <input placeholder='Enter Your Email' type="text" />
            <input placeholder='Enter Your Phone Number' type="text" />
            <input placeholder='Enter Your Pasword' type="password" />
            <button>Register</button>
            <hr style={{color:"white",width:"100%"}}/>
            <p className='register'>Already Have An Account <Link onClick={()=>setContainer(1)} className='register1'>Login Here</Link></p>
        </div>}
        {Container==3 && <div className='Container3'>
            <h2>Reset Password</h2>
            <input type="text" name="" id="" placeholder='ENTER EMAIL OR PHONE NO'/>
            <button className='otp'>GET OTP</button>
            <input type="number" name="" id="" placeholder='ENTER OTP'/>
            <button className='submit' onClick={()=>setContainer(4)}>SUBMIT</button>
            <div className='twoOptions'>
                <Link onClick={()=>setContainer(1)} className='register1'>Login Using Password</Link>
                <Link onClick={()=>setContainer(2)} className='register1'>Register Here</Link>
            </div>
        </div>}
        {Container==4 && <div className='Container4'>
            <h2>Whoaa ! OTP VERIFIED</h2>
            <input placeholder='Enter New Password' type="password" />
            <input placeholder='Confirm New Password' type="text" />
            <button onClick={()=>setContainer(1)}>RESET PASSWORD</button>
        </div>}
    </div>
  )
}

export default Login