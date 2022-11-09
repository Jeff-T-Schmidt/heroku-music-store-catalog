package com.example.catalogmicroservice.repository;

import com.example.catalogmicroservice.model.Album;
import com.example.catalogmicroservice.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository  extends JpaRepository<Label, Long> {
}
