package com.example.pastebinproject.repository;

import com.example.pastebinproject.model.TextBin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TextBinRepository extends CrudRepository<TextBin, Long> {
}
