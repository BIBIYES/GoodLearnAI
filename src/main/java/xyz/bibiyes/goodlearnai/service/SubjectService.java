package xyz.bibiyes.goodlearnai.service;

import xyz.bibiyes.goodlearnai.entity.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> list();  // Method to get all subjects

    boolean save(Subject subject);  // Method to save a new subject

    boolean updateById(Subject subject);  // Method to update a subject by ID

    boolean removeById(Long id);  // Method to delete a subject by ID
}
