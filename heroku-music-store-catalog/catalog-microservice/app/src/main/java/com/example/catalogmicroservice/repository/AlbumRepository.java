package com.example.catalogmicroservice.repository;

import com.example.catalogmicroservice.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
