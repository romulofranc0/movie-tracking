# Movie Tracker 🎬

A personal movie tracking/journal application built with **Java Spring Boot**. This project helps you keep track of the movies you've watched, including details like the watch date, your rating, and comments.

---

## Features
- **Movie Management:**
  - Search for movies by title using the OMDB API.
  - Fetch detailed information about a movie by its IMDb ID.
- **Review System:**
  - Log reviews for movies, including:
    - Rating (e.g., 8.5/10)
    - Comment (e.g., "Great storyline and acting!")
    - Watch date (e.g., when you watched the movie)
    - Review date (e.g., when you logged the review)
  - Update or delete existing reviews.
  - Fetch all reviews or a specific review by its ID.
- **User Authentication:**
  - Secure user registration and login using **Spring Security** and **JWT**.
  - Role-based access control (e.g., `USER` and `ADMIN` roles).

---

## Technologies Used
- **Backend:**
  - Java 21
  - Spring Boot 3.4.1
  - Spring Data JPA
  - Spring Security with JWT
  - MySQL (for database)
  - Feign Client (for OMDB API integration)
- **Tools:**
  - Maven (for dependency management)
  - Lombok (for reducing boilerplate code)
- **APIs:**
  - RESTful API design
  - OMDB API (for fetching movie data)
