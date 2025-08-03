package com.embarkx.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
private  JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        try {
            jobService.createJob(job);
            return new ResponseEntity<>("Job created successfully", HttpStatus.CREATED);
        } catch (Exception ex) {
            // Optionally log the error
            return new ResponseEntity<>("Failed to create job: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //jobService.createJob(job);
        //return new ResponseEntity<>("Job created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
     Job job = jobService.getJobById(id);
     if (job != null)
         return new ResponseEntity<>(job, HttpStatus.OK);
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
  public ResponseEntity<String>deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deletedJobById(id);
        if (deleted)
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);

        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
  }

  //
    //@RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
  @PutMapping("/{id}")
  public  ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updateJob){
      try {
        boolean updated = jobService.updateJob(id, updateJob);
        if (updated)
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } catch (Exception ex) {
          // Optionally log the error
          return new ResponseEntity<>("Failed to update job: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

}
