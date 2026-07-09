package com.foodapp.dao;

import com.foodapp.model.Location;
import java.util.List;

/**
 * LocationDAO interface - defines WHAT operations are possible on the locations table.
 * LocationDAOImpl provides the actual JDBC code.
 */
public interface LocationDAO {

    boolean addLocation(Location location);

    Location getLocationById(int locationId);

    // Checkout page shows the CURRENT user's saved addresses only.
    List<Location> getLocationsByUserId(int userId);

    boolean updateLocation(Location location);

    boolean deleteLocation(int locationId);
}