package com.embarkx.firstjobapp.company.impliment;

import com.embarkx.firstjobapp.company.Company;
import com.embarkx.firstjobapp.company.CompanyRepository;
import com.embarkx.firstjobapp.company.CompanyService;
import com.embarkx.firstjobapp.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

   @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {

        Optional<Company> companyOption = companyRepository.findById(id);
        if(companyOption.isPresent())
        {
            Company comp = companyOption.get();
            comp.setDescription(company.getDescription());
            comp.setName(company.getName());
            comp.setJobs(company.getJobs());
            companyRepository.save(comp);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
