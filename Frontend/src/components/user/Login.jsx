import React from 'react'
import './Register.css'
import axios from '../../api/axiosConfig';
import user_icon from '../icons/person.png'
import password_icon from '../icons/password.png'
import { useNavigate, useLocation } from 'react-router-dom';
import {useEffect, useState } from "react";

const Login = ({ setIsLoggedIn, setUserId }) => {

  const [username, setName] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const location = useLocation();
  const { state } = location;

  async function login(event)
    {
        event.preventDefault();
    try
        {
         await axios.post("https://movie-review-latest.onrender.com/api/v1/users/login",
        {
        username: username,
        password: password,
        }).then((res) =>
        {
            console.log(res.data);

            if(res.data.message == "Username not exists")
            {
                alert("Email not exists");
            }
            else if(res.data.message == "Login Success")
            {
                setIsLoggedIn(true);
                setUserId(res.data.id);
                console.log(res.data.id);
                navigate(state?.from || '/');
            }
            else
            {
                alert("Incorrect email and password");
            }
        }, fail =>{
            console.error(fail);
        });
          
        }
    catch(err)
        {
          console.log(err);
          alert(err);
        }
   }

  return (
    <div className='container'>
      <div className="header">
        <div className="text">Login</div>
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
        <div className="submit">Signup</div>
        <div className="submit" onClick={login}>Login</div>
      </div>

    </div>
  )
}

export default Login;