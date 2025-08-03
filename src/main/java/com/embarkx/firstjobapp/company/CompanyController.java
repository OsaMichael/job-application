package com.embarkx.firstjobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAllCompanies() {
        return new ResponseEntity<>(companyService.findAll(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
       boolean result = companyService.updateCompany(company, id);
       if (result)
           return  new ResponseEntity<>("Company updated succesfully", HttpStatus.OK);
       else
           return  new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return  new ResponseEntity<>("Company created succesfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
       var res = companyService.deleteCompany(id);
        if(res)
            return new ResponseEntity<>("Company deleted succesfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/{id}")
public  ResponseEntity<Company> findCompanyById(@PathVariable Long id){
        Company res = companyService.getCompanyById(id);
        if(res != null)
            return new ResponseEntity<>(res,HttpStatus.OK);

        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
}
