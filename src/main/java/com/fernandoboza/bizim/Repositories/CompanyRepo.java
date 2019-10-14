package com.fernandoboza.bizim.Repositories;

import com.fernandoboza.bizim.Models.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepo extends MongoRepository<Company, String> {
    Company findCompanyById(String id);
}
