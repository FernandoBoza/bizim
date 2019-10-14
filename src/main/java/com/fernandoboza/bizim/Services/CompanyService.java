package com.fernandoboza.bizim.Services;

import java.util.*;

import com.fernandoboza.bizim.Models.Company;
import com.fernandoboza.bizim.Repositories.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class CompanyService {
    @Autowired
    private CompanyRepo companyRepo;

    public Company findCompanyById(String id){
        return companyRepo.findCompanyById(id);
    }

    public Company createCompany(Company company){
        return companyRepo.save(company);
    }

    public String deleteCompany(String id){
        companyRepo.deleteById(id);
        return "Company deleted";
    }

    public Company updateCompany(Company c){
        return companyRepo.save(companyRepo.findCompanyById(c.getId()));
    }

}
