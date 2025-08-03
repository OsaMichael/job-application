package com.embarkx.firstjobapp.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService _reviewService;

    public ReviewController(ReviewService reviewService) {
        _reviewService = reviewService;
    }

    @GetMapping("/reviews")
   public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {

        var res = _reviewService.getAllReviews(companyId);
        if (!res.isEmpty())
                return  new ResponseEntity<>(res, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   @PostMapping("/reviews")
   public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review) {
     boolean res =  _reviewService.addReview(companyId, review);
        if(res)
            return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review not added", HttpStatus.NOT_FOUND);
   }

   @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {

        var res = _reviewService.getReview(companyId, reviewId);
        if(res != null)
              return new ResponseEntity<>(_reviewService.getReview(companyId, reviewId), HttpStatus.OK);
        else
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   @PutMapping("/reviews/{reviewId}")
   public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {

        boolean isReviewUpdated = _reviewService.updateReview(companyId, reviewId, review);
        if(isReviewUpdated)
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        else
           return  new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
   }

   @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {

        boolean isReviewDeleted = _reviewService.deleteReview(companyId,reviewId);
       if(isReviewDeleted)
           return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
       else
           return  new ResponseEntity<>("Review not deleted", HttpStatus.NOT_FOUND);
   }
}
