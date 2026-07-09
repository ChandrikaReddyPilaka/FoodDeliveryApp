package com.foodapp.dao;

import com.foodapp.model.Rating;
import java.util.List;

/**
 * RatingDAO interface - defines WHAT operations are possible on the ratings table.
 * RatingDAOImpl provides the actual JDBC code.
 */
public interface RatingDAO {

    boolean addRating(Rating rating);

    Rating getRatingById(int ratingId);

    List<Rating> getAllRatings();

    // A restaurant's page shows ITS reviews, not every review in the app.
    List<Rating> getRatingsByRestaurantId(int restaurantId);

    boolean deleteRating(int ratingId);
}