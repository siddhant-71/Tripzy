import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import "./Payments.css"

const Payment = () => {
    const {state} =useLocation();
    const navigate=useNavigate();
    const booking=state || {};
    const data=booking.bookingResponse;
    useEffect(() => {
        const completeBooking = async ()=>{
            console.log("hello")
            try{
                console.log("hello")
                await axios.post(`http://localhost:8080/api/bookings/${data.id}`).then(response=>{console.log(response); navigate('/Home')}).catch(err=>{console.log("Not Booked")});
            }
            catch(err){
                console.log(err);
            }
        }
        const verifyPayment = async (response, paymentReference) => {
            const payload = {
                razorpayPaymentId: response.razorpay_payment_id,
                razorpayOrderId: response.razorpay_order_id,
                razorpaySignature: response.razorpay_signature,
                paymentReference: paymentReference,
            };
            const token=localStorage.getItem("token");
            try {
                console.log("THis is the first",payload);
                await axios.post("http://localhost:8080/api/payments/verify", payload).then(response=>{completeBooking()}).catch(err=>{console.log(err)});
            } catch (err) {
                console.error("Verification error", err);
                navigate('/Home')
            }
        };
        const loadRazorpayScript = () => {
        return new Promise((resolve) => {
            const script = document.createElement("script");
            script.src = "https://checkout.razorpay.com/v1/checkout.js";
            script.onload = () => {
            resolve(true);
            };
            script.onerror = () => {
            resolve(false);
            };
            document.body.appendChild(script);
        });
        };
        const openRazorpayCheckout = (payment) => {
        const options = {
            key: "rzp_test_YmTwJC615FKYFA",
            amount: 100, 
            currency: "INR",
            name: "Tripzy",
            description: "Flight Booking Payment",
            order_id: payment.razorpayOrderID, 
            handler: function (response) {
            verifyPayment(response, payment.paymentReference);
            },
            prefill: {
            name: "Test User",
            email: "siddhantd711@gmail.com",
            contact: "9028673711"
            },
            theme: {
            color: "#3399cc"
            }
        };

        const rzp = new window.Razorpay(options);
        rzp.open();
        };
        async function handleClick(){

            const paymentRequest={
                bookingId:data.id,
                amount:data.totalPrice,
                currency:"INR"
            }
            const isScriptLoaded = await loadRazorpayScript();

            if (!isScriptLoaded) {
                alert("Failed to load Razorpay script. Please try again.");
                return;
            }

            await axios.post("http://localhost:8080/api/payments/initiate", paymentRequest)
            .then(response => openRazorpayCheckout(response.data)) 
            .catch(err => console.log(err));
        }
        handleClick();
    }, [])
    
  return (
    <div></div>
  )
}

export default Payment