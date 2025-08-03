package com.embarkx.firstjobapp.review.Implement;

import com.embarkx.firstjobapp.company.Company;
import com.embarkx.firstjobapp.company.CompanyService;
import com.embarkx.firstjobapp.review.Review;
import com.embarkx.firstjobapp.review.ReviewRepository;
import com.embarkx.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository _reviewRepository;
    private final CompanyService  _companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        _reviewRepository = reviewRepository;
        _companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = _reviewRepository.findByCompanyId(companyId);

        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
      var company = _companyService.getCompanyById(companyId);
        if (company!=null){
            review.setCompany(company);//mapping
            _reviewRepository.save(review);
            return true;
        }
       return false;

    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {

        List<Review> reviews = _reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if(_companyService.getCompanyById(companyId) != null){
            updatedReview.setCompany(_companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            _reviewRepository.save(updatedReview);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(_companyService.getCompanyById(companyId) != null && _reviewRepository.existsById(reviewId)) {

            Review review = _reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            _companyService.updateCompany(company,companyId);

            _reviewRepository.deleteById(reviewId);
            return true;
        }
        else
           return false;
    }
}
