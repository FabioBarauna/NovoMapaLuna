package com.novomapa;

import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class MainActivity extends Activity implements LocationListener {

	private MapView osm;
	private MapController mc;
	private LocationManager lm;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		osm = (MapView) findViewById(R.id.mapView1);
		osm.setTileSource(TileSourceFactory.MAPNIK);
		osm.setBuiltInZoomControls(true);
		osm.setMultiTouchControls(true);
		
		mc = (MapController)osm.getController();
		mc.setZoom(16);
		
		GeoPoint center = new GeoPoint(-22.7894041,-43.3053775);
		mc.setCenter(center);
		addMarker(center);
		
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
 		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	}
	public void addMarker(GeoPoint center){
		Marker marker = new Marker(osm);
		marker.setPosition(center);
		marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
		marker.setIcon(getResources().getDrawable(R.drawable.ic_launcher));
		
		osm.getOverlays().add(marker);
		osm.invalidate();
		
		
	}
	@Override
	public void onLocationChanged(Location location) {
		GeoPoint center = new GeoPoint(location.getLatitude(),location.getLongitude());
		mc.animateTo(center);
		addMarker(center);
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	
}
	