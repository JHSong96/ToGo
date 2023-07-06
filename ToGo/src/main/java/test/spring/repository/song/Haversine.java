package test.spring.repository.song;

import java.util.ArrayList;
import java.util.List;

public class Haversine {

    // Haversine ������ ����Ͽ� �� ��ǥ ������ �Ÿ� ���
    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6371; // ���� ������ (����: km)

        // ������ �������� ��ȯ
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // Haversine ���� ���
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        return distance;
    }
    
    public static List<Double> LatLon(double lat1, double lon1, double lat2, double lon2) {
        List<Double> list = new ArrayList<>();

        // ���� �߽� ��ǥ ���
        double centerLat = (lat1 + lat2) / 2;
        double centerLon = (lon1 + lon2) / 2;

        // �� ��ǥ ������ �Ÿ� ��� (Haversine ���� ���)
        Haversine ha = new Haversine();
        double radius = ha.haversineDistance(lat1, lon1, lat2, lon2) / 2;

        // ���� ���δ� ���簢���� ���� ���� ���
        double squareMinLat = centerLat - radius;
        double squareMaxLat = centerLat + radius;

        // ���� ���δ� ���簢���� �浵 ���� ���
        double squareMinLon = centerLon - radius;
        double squareMaxLon = centerLon + radius;

        list.add(squareMinLat);
        list.add(squareMaxLat);
        list.add(squareMinLon);
        list.add(squareMaxLon);

        return list;
    }
}
