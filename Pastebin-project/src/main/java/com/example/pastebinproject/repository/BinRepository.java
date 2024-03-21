package com.example.pastebinproject.repository;

import com.example.pastebinproject.model.Bin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinRepository extends CrudRepository<Bin, Long> {
    Bin findByHashOfBin(int hashOfBin);
}
