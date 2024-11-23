import React, { useEffect, useState } from 'react';
import api from '../../api/axiosConfig';

const Watchlist = ({ userId }) => {
  const [watchlist, setWatchlist] = useState([]);

  useEffect(() => {
    const fetchWatchlist = async () => {
      try {
        console.log("Fetching watchlist for userId:", userId);
        const response = await api.get(`https://movie-review-latest.onrender.com/api/v1/users/getWatchlist/${userId}`);
        console.log("Watchlist response:", response.data);
        setWatchlist(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchWatchlist();
  }, [userId]);

  return (
    <div>
      <h3>Watchlist</h3>
      <ul>
        {watchlist?.map((movie) => (
          <li key={movie.id}>{movie.title}</li>
        ))}
      </ul>
    </div>
  );
};

export default Watchlist;
