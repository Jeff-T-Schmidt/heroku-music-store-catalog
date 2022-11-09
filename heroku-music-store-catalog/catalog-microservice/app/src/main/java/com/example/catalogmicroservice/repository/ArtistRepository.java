package com.example.catalogmicroservice.repository;

import com.example.catalogmicroservice.model.Album;
import com.example.catalogmicroservice.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository  extends JpaRepository<Artist, Long> {
}
