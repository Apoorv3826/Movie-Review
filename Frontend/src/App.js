import './App.css';
import api from './api/axiosConfig';
import { useState, useEffect } from 'react';
import Layout from './components/Layout';
import { Route, Routes } from 'react-router-dom';
import Home from './components/home/Home';
import Header from './components/header/Header';
import Trailer from './components/trailer/Trailer';
import Reviews from './components/reviews/Reviews';
import Register from './components/user/Register';
import Login from './components/user/Login';
import Watchlist from './components/user/Watchlist';
import { useNavigate } from 'react-router-dom';


function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [movies, setmovies] = useState();
    const [movie, setMovie] = useState();
    const [reviews,setReviews] = useState([]);
    const [userId, setUserId] = useState(null);

    const handleLogout = () => {
        setIsLoggedIn(false);
        setUserId(null);
      };

    const getMovies = async () =>{
        try {

        const response = await api.get("/api/v1/movies");
        setmovies(response.data);
            
        } catch (error) {
            console.log(error);
        }
        
    }

    const getMovieData = async (movieId) => {
     
        try 
        {
            const response = await api.get(`/api/v1/movies/${movieId}`);
    
            const singleMovie = response.data;
    
            setMovie(singleMovie);
    
            setReviews(singleMovie.reviews);
            
    
        } 
        catch (error) 
        {
          console.error(error);
        }
    
      }

    useEffect(() => {
        getMovies();
      },[])

    return (
        <div className="App">
            <Header isLoggedIn={isLoggedIn} handleLogout={handleLogout} />
            <Routes>
                <Route path="/" element={<Layout/>}>
                <Route path="/" element={<Home movies={movies}/>}> </Route>
                <Route path="/Trailer/:ytTrailerId" element={<Trailer/>}></Route>
                <Route path="/Reviews/:movieId" element ={<Reviews isLoggedIn={isLoggedIn} getMovieData = {getMovieData} movie={movie} reviews ={reviews} setReviews = {setReviews} />}></Route>
                <Route path= "/Register" Component={Register}></Route>
                <Route path="/Login" element={<Login setIsLoggedIn={setIsLoggedIn} setUserId={setUserId} />} />
                <Route path="/watchList" element={ <Watchlist userId={userId} />}/>
                </Route>
            </Routes>

        </div>
    );
}

export default App;