import React from 'react'
import './Register.css'
import axios from '../../api/axiosConfig';
import user_icon from '../icons/person.png'
import password_icon from '../icons/password.png'
import {useEffect, useState } from "react";

const Register = () => {

  const [username, setName] = useState("");
  const [password, setPassword] = useState("");

  async function save(event)
    {
        event.preventDefault();
    try
        {
         await axios.post("https://movie-review-latest.onrender.com/api/v1/users/save",
        {
        username: username,
        password: password,
        });
          alert("User Registered Succesfully !");
          setName("");
          setPassword("");
          
        }
    catch(err)
        {
          console.log(err);
          alert("User Registration Failed");
        }
   }

  return (
    <div className='container'>
      <div className="header">
        <div className="text">Sign Up</div>
        <div className="underline"></div>
      </div>

      <div className="inputs">
        <div className="input">
          <img src={user_icon} alt="" />
          <input type="text" placeholder="username" id="username" 
          value={username}
          onChange={(event) =>{
            setName(event.target.value);
          }}

          />
        </div>

        <div className="input">
          <img src={password_icon} alt="" />
          <input type="password"  placeholder="password" id="password"
          value={password}
          onChange={(event) =>{
            setPassword(event.target.value);
          }}

          />
        </div>
      </div>
      <div className="submit-container">
        <div className="submit" onClick={save}>Signup</div>
        <div className="submit">Login</div>
      </div>

    </div>
  )
}

export default Register