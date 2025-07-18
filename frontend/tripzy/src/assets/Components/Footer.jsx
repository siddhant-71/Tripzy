import "./HomePage.css"
import logo from "../Images/logo.png"
import {Link} from 'react-router-dom'
const Footer = () => {
  return (
    <div className="footer">
        <div><img src={logo} alt="" /></div>
        <div>
            <ul>
                <li><Link className="link" to="/help">HELP</Link></li>
                <li><Link className="link" to="/about">ABOUT</Link></li>
                <li><Link className="link" to="/faq">FAQ</Link></li>
                <li><Link className="link" to="/customerService">CUSTOMER SERVICE</Link></li>
                <li><Link className="link" to="contactUs">CONTACT US</Link></li>
            </ul>
        </div>
    </div>
  )
}

export default Footer