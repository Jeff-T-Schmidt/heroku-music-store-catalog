package com.example.catalogmicroservice.repository;

import com.example.catalogmicroservice.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
}
