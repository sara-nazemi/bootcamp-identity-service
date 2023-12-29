package com.example.bootcampisservice.services;

import com.example.bootcampisservice.models.documents.ExceptionDocument;
import com.example.bootcampisservice.repositories.ExceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExceptionServiceImpl implements ExceptionService{
    private final ExceptionRepository exceptionRepository;

    @Autowired
    public ExceptionServiceImpl(ExceptionRepository exceptionRepository) {
        this.exceptionRepository = exceptionRepository;
    }

    @Override
    public void saveException(ExceptionDocument exceptionDocument) {
        exceptionRepository.save(exceptionDocument);
    }
}
