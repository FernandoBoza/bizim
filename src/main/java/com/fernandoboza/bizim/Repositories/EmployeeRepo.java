package com.fernandoboza.bizim.Repositories;

import com.fernandoboza.bizim.Models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepo extends MongoRepository<Employee, String> {
    Employee findEmployeeById(String id);
    Employee findEmployeeByEmail(String email);
}
