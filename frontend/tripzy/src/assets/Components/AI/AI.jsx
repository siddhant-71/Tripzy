import React from 'react'
import "./AI.css"
import { RiAiGenerate2 } from "react-icons/ri";

const AI = () => {
  return (
    <div className='border'>
        <div className='request'>
            <input className='destReq' placeholder='Enter the destinations you want to explore ...' type="text" />
            <input className='noOfDays' placeholder='No of Days...' type="text" />
            <button><RiAiGenerate2 size={40} color='#bbe4e9'/></button>
        </div>
        <div className='response'>
            I cannot create an itinerary without more information. To give you the best recommendations, I need to know:

Where do you want to go? (e.g., a specific city, country, region, or even a type of destination like "beach vacation" or "cultural trip")

When are you planning to travel? (specific dates or a general time of year)

How long will your trip be? (number of days or weeks)

What is your budget? (e.g., luxury, mid-range, budget-friendly)

Who are you traveling with? (solo, family with kids, couple, friends)

What are your interests? (e.g., history, food, adventure, relaxation, art, nature, nightlife)

What's your preferred pace? (fast-paced and packed, relaxed, a mix?)

Are there any specific activities or sights you definitely want to include?
        </div>
    </div>
  )
}

export default AI