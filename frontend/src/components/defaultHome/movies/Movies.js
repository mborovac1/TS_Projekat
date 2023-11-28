import React, { useEffect, useState } from "react";
import TextField from "@mui/material/TextField";
import SingleContent from "../SingleContent/SingleContent";
import "./Movies.css";
import Genres from "./Genres";
import { Button } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";

const Movies = () => {
  const [filmovi, setFilmovi] = useState([]);
  const [selectedGenres, setSelectedGenres] = useState([]);
  const [genres, setGenres] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredMovies, setFilteredMovies] = useState([]);
  const [kolicinaKarata, setKolicinaKarata] = useState(0);

  useEffect(() => {
    //fetch("http://localhost:8080/rezervacija-karata/films")
    fetch("http://localhost:8081/filmovi")
      .then((res) => res.json())
      .then((result) => {
        setFilmovi(result);
        console.log("RES", result);
      });

    filterMovies();
  }, [searchTerm, selectedGenres]);

  const handleSearchInputChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleGenreSelect = (genre) => {
    setSelectedGenres([...selectedGenres, genre]);
  };

  const handleGenreDeselect = (genre) => {
    setSelectedGenres(selectedGenres.filter((selected) => selected.id !== genre.id));
  };

  const filterMovies = () => {
    const filteredMovies = filmovi.filter((movie) => {
      /*// Filter based on selected genres
      if (selectedGenres.length > 0 && !selectedGenres.every((selectedGenre) => movie.zanr.some((genre) => genre.nazivZanra === selectedGenre.nazivZanra))) {
        return false;
      }*/
      let onlyGenres = selectedGenres.map((el) => el.nazivZanra);
      let allGenresCurr = movie.zanrovi.map((el) => el.nazivZanra);
      const hasCommonElement = onlyGenres.every((element) => allGenresCurr.includes(element));

      if (selectedGenres.length > 0 && !hasCommonElement) {
        return false;
      }

      // Filter based on search term
      if (searchTerm !== "" && !movie.nazivFilma.toLowerCase().includes(searchTerm.toLowerCase())) {
        return false;
      }

      return true;
    });

    setFilteredMovies(filteredMovies);
  };

  const filteredContent = searchTerm !== "" || selectedGenres.length > 0 ? filteredMovies : filmovi;

  return (
    <div>
      <div style={{ display: "flex", margin: "15px" }}>
        <TextField
          style={{ flex: 1 }}
          className="searchBox"
          label="Pretraga filmova"
          variant="filled"
          InputProps={{
            style: { color: "white" },
          }}
          InputLabelProps={{
            style: { color: "white" },
          }}
          value={searchTerm}
          onChange={handleSearchInputChange}
        />
        <Button variant="contained" style={{ backgroundColor: "white", marginLeft: 10 }}>
          <SearchIcon style={{ color: "black" }} />
        </Button>
      </div>

      <Genres selectedGenres={selectedGenres} setSelectedGenres={setSelectedGenres} genres={genres} setGenres={setGenres} onGenreSelect={handleGenreSelect} onGenreDeselect={handleGenreDeselect} />
      <div className="movies">
        {filteredContent.length > 0 ? (
          filteredContent.map((c) => (
            <div key={c.id}>
              <SingleContent id={c.id} naziv={c.nazivFilma} trajanje={c.trajanje} opis={c.opis} poster={c.posterPath} />
            </div>
          ))
        ) : (
          <p>
            <b>Ne postoje filmovi sa odabranim zanrovima.</b>
          </p>
        )}
      </div>
    </div>
  );
};

export default Movies;
