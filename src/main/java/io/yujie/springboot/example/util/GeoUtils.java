package io.yujie.springboot.example.util;

import io.yujie.springboot.example.entity.dto.GeoPoint;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public interface GeoUtils {

    /**
     *
     * @param centerPoint 圆心
     * @param radius 半径,单位: km
     * @param numSides 多边形的边数
     * @return
     */
    static List<GeoPoint> calculatePolygon(GeoPoint centerPoint, double radius, int numSides) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(6);
        nf.setRoundingMode(RoundingMode.UP);

        List<GeoPoint> result = new ArrayList<>();
        for (int i = 0; i < numSides; i++) {
            double angle = 2 * Math.PI * i / numSides;
            double vertexLat = centerPoint.getLatitude() + (radius / 111.319 * Math.cos(angle));
            double vertexLng = centerPoint.getLongitude() + (radius / (111.319 * Math.cos(centerPoint.getLatitude() * Math.PI / 180)) * Math.sin(angle));
            GeoPoint vertex = new GeoPoint();
            vertex.setLatitude(vertexLat);
            vertex.setLongitude(vertexLng);
            result.add(vertex);
        }
        return result;
    }

    static void main(String[] args) {
        GeoPoint vertex = new GeoPoint();
        vertex.setLatitude(40.030613);
        vertex.setLongitude(116.354787);
        List<GeoPoint> geoPoints = calculatePolygon(vertex, 5, 12);
        System.out.println("geoPoints = " + geoPoints);
    }
}
