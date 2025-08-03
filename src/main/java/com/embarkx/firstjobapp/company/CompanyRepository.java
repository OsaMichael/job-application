package com.embarkx.firstjobapp.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    //extend means inheriting a class and it properties.
    //CompanyRepository inherite JpaRepository
}
