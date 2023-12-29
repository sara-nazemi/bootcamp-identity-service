package com.example.bootcampisservice.repositories;

import com.example.bootcampisservice.models.documents.ExceptionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.*;

public interface ExceptionRepository extends MongoRepository<ExceptionDocument, Spring> {
}
