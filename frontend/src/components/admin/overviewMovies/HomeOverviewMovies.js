// HomeOverviewMovies.js

import React, { useEffect, useState } from "react";
import AppbarAdmin from "../AppbarAdmin";
import OverviewMovies from "./OverviewMovies";
import jwt_decode from 'jwt-decode';

function HomeOverviewMovies() {
  const [isLogged, setIsLogged] = useState(true); // Track whether the user is logged in
  const [isAdmin, setIsAdmin] = useState(false); // Track whether the user has admin role

  useEffect(() => {
    const checkLoginStatus = () => {
      const token = localStorage.getItem("access_token");

      // Check if the token is empty
      if (!token) {
        setIsLogged(false);
        return;
      }

      const decodedToken = jwt_decode(token);

      // Check if the user has the admin role
      setIsAdmin(decodedToken.role === 'ROLE_ADMIN');

      setIsLogged(true);
    };

    checkLoginStatus();
  }, []);

  return (
    <>
      {isLogged && (isAdmin && <AppbarAdmin />)}
      {isLogged ? (
        isAdmin ? (
          <OverviewMovies />
        ) : (
          <div style={{ textAlign: "center", margin: "20px" }}>
            <p>Nemate pristup. Kontaktirajte administratora.</p>
            <button  onClick={() => window.location.href = "/homeUser"}>Return to Homepage</button>
          </div>
        )
      ) : (
        <div style={{ textAlign: "center", margin: "20px" }}>
          <p>Morate biti prijavljeni da biste pristupili ovom sadržaju.</p>
          {/* Add a link or button to navigate to the login page */}
          <button onClick={() => window.location.href = "/login"}>Go to Login</button>
        </div>
      )}
    </>
  );
}

export default HomeOverviewMovies;
