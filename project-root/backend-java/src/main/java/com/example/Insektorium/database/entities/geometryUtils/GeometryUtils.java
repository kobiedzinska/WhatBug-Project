package com.example.Insektorium.database.entities.geometryUtils;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

// do obsługi POINT type z MariaDB, będzie wygodniej obsługiwać na mapie

public class GeometryUtils {
    private static final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    public static Point createPoint(double latitude, double longitude) {
        return geometryFactory.createPoint(new Coordinate(longitude, latitude));
    }

    public static double getLatitude(Point point) {
        return point.getY();
    }

    public static double getLongitude(Point point) {
        return point.getX();
    }

}
