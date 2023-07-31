package test.spring.repository.song;

import java.util.ArrayList;
import java.util.List;

public class HaversineDAO {

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
    
    // �� ��ǥ�ݰ��� ���� ���
    public static List<Double> LatLon(double lat1, double lon1, double lat2, double lon2) {
       
       List<Double> list = new ArrayList<>();
       
       double centerLat = (lat1 + lat2) / 2;
        double centerLon = (lon1 + lon2) / 2;
       
       double lat_length = lat2 - lat1;
       double lon_length = lon2 - lon1;
       
       double squareMinLat = lat1;
        double squareMaxLat = lat2;
        double squareMinLon = lon1;
        double squareMaxLon = lon2;
       
       if(lat_length < lon_length) {
          if(lon_length > 0.3) {
             lon_length = 0.3;
          }
          squareMinLat = centerLat - lon_length/2;
            squareMaxLat = centerLat + lon_length/2;
            squareMinLon = lon1;
            squareMaxLon = lon2;
       }else if(lat_length > lon_length) {
          if(lat_length > 0.3) {
             lat_length = 0.3;
          }
          squareMinLat = lat1;
            squareMaxLat = lat2;
            squareMinLon = centerLon - lat_length/2;
            squareMaxLon = centerLon + lat_length/2;
       }else if(lat_length == lon_length) {
          squareMinLat = lat1;
            squareMaxLat = lat2;
            squareMinLon = lon1;
            squareMaxLon = lon2;
       }

        list.add(squareMinLat);
        list.add(squareMaxLat);
        list.add(squareMinLon);
        list.add(squareMaxLon);

        return list;
    }
    
    // ?km �ݰ� ���� �ּ� �� �ִ� ����, �浵 �� ���
    public static List<Double> radius(double lat1, double lon1, double length) {
       
       List<Double> list = new ArrayList<>();
       double radius = length; // �ݰ� ���� (����: km)

        // 5km �ݰ� ���� �ִ� ������ �ּ�, �ִ� �� ���
        double minLatitude = lat1 - (radius / 6371) * (180 / Math.PI);
        double maxLatitude = lat1 + (radius / 6371) * (180 / Math.PI);

        // 5km �ݰ� ���� �ִ� �浵�� �ּ�, �ִ� �� ���
        double minLongitude = lon1 - (radius / 6371) * (180 / Math.PI) / Math.cos(Math.toRadians(lat1));
        double maxLongitude = lon1 + (radius / 6371) * (180 / Math.PI) / Math.cos(Math.toRadians(lat1));
        
        list.add(minLatitude);
        list.add(maxLatitude);
        list.add(minLongitude);
        list.add(maxLongitude);

        
        return list;
    }
    
    // ?km �ݰ� ���� �ּ� �� �ִ� ����, �浵 �� ���
    public static List<Double> radius(double Lat, double Lon, double lat1, double lon1, double lat2, double lon2) {
       
       List<Double> list = new ArrayList<>();
       // �� ��ǥ ������ �Ÿ� ��� (Haversine ���� ���)
        HaversineDAO ha = new HaversineDAO();
        double radius = ha.haversineDistance(lat1, lon1, lat2, lon2);   // �ݰ� ���� (����: km)

        // 5km �ݰ� ���� �ִ� ������ �ּ�, �ִ� �� ���
        double minLatitude = Lat - (radius / 6371) * (180 / Math.PI);
        double maxLatitude = Lat + (radius / 6371) * (180 / Math.PI);

        // 5km �ݰ� ���� �ִ� �浵�� �ּ�, �ִ� �� ���
        double minLongitude = Lon - (radius / 6371) * (180 / Math.PI) / Math.cos(Math.toRadians(lat1));
        double maxLongitude = Lon + (radius / 6371) * (180 / Math.PI) / Math.cos(Math.toRadians(lat1));
        
        list.add(minLatitude);
        list.add(maxLatitude);
        list.add(minLongitude);
        list.add(maxLongitude);

        
        return list;
    }

}