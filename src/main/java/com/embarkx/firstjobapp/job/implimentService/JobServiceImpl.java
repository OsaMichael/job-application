package com.embarkx.firstjobapp.job.implimentService;

import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.JobRepository;
import com.embarkx.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        //job.setId(nextId++);
      jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deletedJobById(Long id) {
      try{
          jobRepository.deleteById(id);
          return true;
      }
      catch (Exception e){
          return false;
      }

        /*Iterator<Job> iterator = jobs.iterator();
        while (iterator.hasNext()) {
            Job job = iterator.next();
            if (job.getId().equalsid)) {
                iterator.remove();
                return true;
            }
        }
        return false;*/

    }

    @Override
    public boolean updateJob(Long id, Job job) {
         Optional<Job> optionalJob = jobRepository.findById(id);
         if(optionalJob.isPresent())
        {
            Job jb = optionalJob.get();
                jb.setDescription(job.getDescription());
                jb.setTitle(job.getTitle());
                jb.setLocation(job.getLocation());
                jb.setMinSalary(job.getMinSalary());
                jb.setMaxSalary(job.getMaxSalary());
                jobRepository.save(jb);
                return true;
            }
        return false;
    }


}
