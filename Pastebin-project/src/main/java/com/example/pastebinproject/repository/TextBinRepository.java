package com.example.pastebinproject.repository;

import com.example.pastebinproject.model.TextBin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextBinRepository extends CrudRepository<TextBin, Long> {
}
