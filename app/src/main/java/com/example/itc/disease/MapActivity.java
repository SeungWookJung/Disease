package com.example.itc.disease;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.example.itc.disease.db.DB2;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import static net.daum.mf.map.api.MapPOIItem.MarkerType.BluePin;
import static net.daum.mf.map.api.MapPOIItem.MarkerType.RedPin;


public  class MapActivity extends AppCompatActivity {
    MapView mapView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        DB2 dbHelper = new DB2(getApplicationContext(), "Health_center.db", null, 1);
        Cursor cursor2 = dbHelper.getResult2();

        mapView = new MapView(this);
        mapView.setDaumMapApiKey("b450432c643219c9697214b846a1e5fa");
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(35.180688, 129.074787), true);
        mapView.setZoomLevel(7, true);//줌 배율


        try {
            if (cursor2.moveToFirst()) {

                do {
                    String idx = cursor2.getString(0);
                    String H_name = cursor2.getString(1);
                    String address = cursor2.getString(2);
                    String p_name = cursor2.getString(3);
                    Double X = cursor2.getDouble(4);
                    Double Y = cursor2.getDouble(5);

                    MapPOIItem marker = new MapPOIItem();
                    marker.setItemName(H_name);
                    marker.setTag(0);
                    marker.setMapPoint(MapPoint.mapPointWithGeoCoord(X, Y));
                    marker.setMarkerType(BluePin); // 기본으로 제공하는 BluePin 마커 모양.
                    marker.setSelectedMarkerType(RedPin);// 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
                    mapView.addPOIItem(marker);


                } while (cursor2.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor2 != null && !cursor2.isClosed()) {
                cursor2.close();
            }
        }


    }
}