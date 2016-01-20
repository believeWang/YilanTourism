package com.tku.yilantourism;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.text.Collator;

import pl.mg6.android.maps.extensions.ClusteringSettings;
import pl.mg6.android.maps.extensions.GoogleMap;
import pl.mg6.android.maps.extensions.SupportMapFragment;
import android.support.v4.app.FragmentManager;

import pl.mg6.android.maps.extensions.GoogleMap;
import pl.mg6.android.maps.extensions.GoogleMap.InfoWindowAdapter;
import pl.mg6.android.maps.extensions.GoogleMap.OnInfoWindowClickListener;
import pl.mg6.android.maps.extensions.GoogleMap.OnMapClickListener;

import com.google.android.gms.maps.model.LatLngBounds.Builder;

import pl.mg6.android.maps.extensions.Marker;
import pl.mg6.android.maps.extensions.MarkerOptions;
import pl.mg6.android.maps.extensions.SupportMapFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
//import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tku.yilantourism.MarkerGenerator.MutiData;

public class TourMapActivity extends FragmentActivity {

	private GoogleMap mMap;
	private TextView txtOutput;
	private Marker markerMe;
	private final String TAG = "=== Map Demo ===>";
	private String phoneNumber;
	private String name;
	private String website;
	private String address;
	protected SQLiteUtil db = null;
	private static List<Set<String>> ListViewData = null;
	private static Map<String, String> blMapData = null;
	ListView listView;
	protected iconAdapter adapter = null;
	static Map<String, MutiData> tpMap = null;
	// private static Set<String> tempSet = null;
	// private static Map<String, Marker> markMap = null;
	static Object[] temp;
	private static final double[] CLUSTER_SIZES = new double[] { 180, 160, 144,
			120, 96 };

	private final int[] foodsIcon = { R.drawable.mifamily, R.drawable.shieduck,
			R.drawable.nobutter, R.drawable.yihsiiun, R.drawable.sune };
	private final int[] spotsIcon = { R.drawable.waterpark,
			R.drawable.leisurefarm, R.drawable.ecomuseum, R.drawable.whaledock,
			R.drawable.jimmysquare, R.drawable.artcenter,
			R.drawable.baimivillage, R.drawable.giantlake,
			R.drawable.divinetree, R.drawable.cannonmount,
			R.drawable.winevillage, R.drawable.tanpark, R.drawable.lotungtree,
			R.drawable.earthhot, R.drawable.verymountain,
			R.drawable.happinessfarm, R.drawable.streambridge,
			R.drawable.blossomlake, R.drawable.waterfall,
			R.drawable.nightmarket };
	private final int[] hotelsIcon = { R.drawable.minsu01, R.drawable.minsu02,
			R.drawable.minsu03, R.drawable.minsu04, R.drawable.minsu05,
			R.drawable.minsu06, R.drawable.minsu07, R.drawable.minsu08,
			R.drawable.minsu09, R.drawable.minsu10, R.drawable.minsu20,
			R.drawable.minsu21, R.drawable.minsu22, R.drawable.minsu23,
			R.drawable.minsu24, R.drawable.minsu25, R.drawable.minsu26,
			R.drawable.minsu27, R.drawable.minsu28, R.drawable.minsu29,
			R.drawable.minsu30, R.drawable.minsu31, R.drawable.minsu32,
			R.drawable.minsu33, R.drawable.minsu34, R.drawable.minsu35,
			R.drawable.minsu36, R.drawable.minsu37, R.drawable.minsu38,
			R.drawable.minsu39 };
	private final String[] foodsTitle = { "蜜餞", "鴨賞", "諾貝爾", "奕順軒", "三合餅" };
	private final String[] spotsTitle = { "親水公園", "香格", "蘭陽博", "烏石", "幾米",
			"傳統藝術", "白米", "大湖", "棲蘭", "砲台", "藏酒", "湯圍", "林業園", "清水地", "太平山",
			"號農場", "寒溪", "梅花", "新寮", "觀光夜市" };
	private final String[] hotelsTitle = { "我行", "尚閣", "魚兒", "院子", "玩全", "玩家",
			"後站", "小棧", "閣子", "羅東棧", "松田", "320", "家民宿", "湯泉", "雲湘", "泉鄉",
			"閃亮", "艾瑪", "伊豆", "山璞", "卡松", "宜和", "親水居", "在角落", "童話", "第二個",
			"伊莎", "柚子", "花間", "水和風" };
	boolean[] bl = { true, true, true, true, false, true };
	String[] locationArray = { "現在的位置", "礁溪火車站", "宜蘭火車站", "羅東火車站", "礁溪轉運站",
			"宜蘭轉運站" };
	LatLng[] LatArray = { new LatLng(24.827297, 121.775388),
			new LatLng(24.754762, 121.758308),
			new LatLng(24.677642, 121.774626),
			new LatLng(24.828533, 121.773511),
			new LatLng(24.751128, 121.759316) };
	MenuItem sea;

	private MutableData[] dataArray = { new MutableData(6, new LatLng(-50, 0)),
			new MutableData(28, new LatLng(-52, 1)),
			new MutableData(496, new LatLng(-51, -2)), };
	private Handler handler = new Handler();
	private Runnable dataUpdater = new Runnable() {

		@Override
		public void run() {
			for (MutableData data : dataArray) {
				data.value = 7 + 3 * data.value;
			}
			onDataUpdate();
			handler.postDelayed(this, 1000);
		}
	};

	/** 記錄軌跡 */
	private ArrayList<LatLng> traceOfMe;
	EditText txtSearch;

	/** GPS */
	private LocationManager locationMgr;
	private String provider;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_google_map);

		ImageButton b1 = (ImageButton) this.findViewById(R.id.imageButton1);
		b1.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				new AlertDialog.Builder(TourMapActivity.this)
						.setTitle("請選擇定位")
						.setItems(locationArray,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										if (which == 0) {
											ConnectivityManager CM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
											NetworkInfo info = CM
													.getActiveNetworkInfo();
											if (info != null) {
												initLocationProvider(2);

												whereAmI();
											} else {
												if (initLocationProvider(1)) {
													whereAmI();
												} else {

													Toast.makeText(
															TourMapActivity.this,
															"請開啟定位！",
															Toast.LENGTH_SHORT)
															.show();
												}

											}

										} else {
											checkFocus(LatArray[which - 1]);
										}
									}
								}).show();

			}

		});
		ImageButton b2 = (ImageButton) this.findViewById(R.id.imageButton2);
		b2.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				mMap.animateCamera(CameraUpdateFactory.zoomIn());

			}

		});
		ImageButton b3 = (ImageButton) this.findViewById(R.id.imageButton3);
		b3.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				mMap.animateCamera(CameraUpdateFactory.zoomOut());

			}

		});
		ImageButton b4 = (ImageButton) this.findViewById(R.id.imageButton4);
		b4.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(TourMapActivity.this)
						.setTitle("請選擇")
						.setMultiChoiceItems(
								new String[] { "地標叢集", "顯示景點", "顯示民宿", "顯示伴手禮",
										"只顯示已標記的", "顯示標記資訊" },
								bl,
								new DialogInterface.OnMultiChoiceClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which, boolean isChecked) {
										switch (which) {
										case 0:
											bl[0] = isChecked;
											break;
										case 1:
											bl[1] = isChecked;
											break;
										case 2:
											bl[2] = isChecked;
											break;
										case 3:
											bl[3] = isChecked;
											break;
										case 4:
											bl[4] = isChecked;
											break;
										case 5:
											bl[5] = isChecked;
											break;

										default:
											break;
										}

									}
								})
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										checkView();

									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
									}
								}).show();

			}

		});

	}

	public void checkFocus(LatLng lat) {
		CameraPosition camPosition = new CameraPosition.Builder().target(lat)
				.zoom(17).build();
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camPosition));
	}

	public void checkView() {
		db.reBuild("SQLiteUtil2");
		for (int i = 0; i < bl.length; i++) {
			ContentValues cv = new ContentValues();
			cv.put(SQLiteUtil.COLUMN_NAMEs2[0], String.valueOf(i));
			cv.put(SQLiteUtil.COLUMN_NAMEs2[1], String.valueOf(bl[i]));

			db.Insert(SQLiteUtil.TABLE_NAME2, cv);
		}

		mMap.clear();
		MarkerGenerator.sameDb(db, ListViewData);

		updateClustering(3, bl[0]);
		if (bl[1] == true) {
			MarkerGenerator.markSpot(mMap);
		}
		if (bl[2] == true) {
			MarkerGenerator.markHotel(mMap);

		}
		if (bl[3] == true) {
			MarkerGenerator.markFood(mMap);

		}
		if (bl[4] == true) {
			if (ListViewData.size() <= 0) {

			} else {

				// Set tpSet = new
				// HashSet<String>();
				double ln = 0;
				double lo = 0;
				String ti = "";
				String sn = "";
				String tps = "";
				for (int i = 0; i < ListViewData.size(); i++) {

					Set tpSet = ListViewData.get(i);
					Object tpa[] = tpSet.toArray();
					for (int j = 0; j < tpSet.size(); j++) {

						if (String.valueOf(tpa[j]).length() > 25) {
							sn = String.valueOf(tpa[j]);
						} else if (String.valueOf(tpa[j]).contains(":")) {
							ti = String.valueOf(tpa[j]);
						} else if (Double.parseDouble(String.valueOf(tpa[j])) > 100) {
							lo = Double.parseDouble(String.valueOf(tpa[j]));
						} else {
							ln = Double.parseDouble(String.valueOf(tpa[j]));
						}

					}

					LatLng lat = new LatLng(ln, lo);
					mMap.addMarker(MarkerGenerator.makeRedMark(lat, ti, sn));
				}
			}
		}
		if (bl[5] == true)
			txtOutput.setVisibility(View.VISIBLE);
		if (bl[5] == false)
			txtOutput.setVisibility(View.INVISIBLE);

	}

	void updateClustering(int clusterSizeIndex, boolean enabled) {
		ClusteringSettings clusteringSettings = new ClusteringSettings();
		clusteringSettings.addMarkersDynamically(true);

		if (enabled) {
			clusteringSettings.clusterOptionsProvider(new markerCluster(
					getResources()));

			double clusterSize = CLUSTER_SIZES[clusterSizeIndex];
			clusteringSettings.clusterSize(clusterSize);
		} else {
			clusteringSettings.enabled(false);
		}
		mMap.setClustering(clusteringSettings);
	}

	class CustomizeInfoWindowAdapter implements InfoWindowAdapter {
		LayoutInflater inflater = null;
		private Collator collator = Collator.getInstance();
		private Comparator<Marker> comparator = new Comparator<Marker>() {
			public int compare(Marker lhs, Marker rhs) {
				String leftTitle = lhs.getTitle();
				String rightTitle = rhs.getTitle();
				if (leftTitle == null && rightTitle == null) {
					return 0;
				}
				if (leftTitle == null) {
					return 1;
				}
				if (rightTitle == null) {
					return -1;
				}
				return collator.compare(leftTitle, rightTitle);
			}
		};

		CustomizeInfoWindowAdapter(LayoutInflater inflater) {
			this.inflater = inflater;
		}

		// 未點選前
		public View getInfoWindow(Marker marker) {
			// 可以沒有 InfoWindow
			return (null);
		}

		// 點選後
		public View getInfoContents(Marker marker) {
			// 自訂 InfoWindow layout
			View popWindow = inflater
					.inflate(R.layout.info_window_layout, null);
			ImageView photo = (ImageView) popWindow
					.findViewById(R.id.imageView1);

			TextView title = (TextView) popWindow.findViewById(R.id.title);
			TextView snippet = (TextView) popWindow.findViewById(R.id.snippet);

			// RelativeLayout mainlayout = (RelativeLayout)
			// popWindow.findViewById(R.id.);
			// mainlayout.setBackgroundColor(Color.RED);
			// bababa...

			// photo.setImageResource(R.drawable.winevillage);
			title.setText(marker.getTitle());
			if (marker.isCluster()) {
				List<Marker> markers = marker.getMarkers();
				int i = 0;
				String text = "";
				while (i < 3 && markers.size() > 0) {
					Marker m = Collections.min(markers, comparator);
					String title1 = m.getTitle();
					if (title1 == null) {
						break;
					}
					text += title1 + "\n";
					markers.remove(m);
					i++;
				}
				if (text.length() == 0) {
					text = "Markers with mutable data";
				} else if (markers.size() > 0) {
					text += "and " + markers.size() + " more...";
				} else {
					text = text.substring(0, text.length() - 1);
				}
				title.setText(text);
				return (popWindow);

			} else {
				int temp = 0;
				if (marker.getTitle().contains("景點")) {
					for (int i = 0; i < 20; i++) {
						if (marker.getTitle().contains(spotsTitle[i])) {
							temp = i;
						}
					}
					photo.setImageResource(spotsIcon[temp]);
				} else if (marker.getTitle().contains("民宿")) {
					for (int i = 0; i < 30; i++) {
						if (marker.getTitle().contains(hotelsTitle[i])) {
							temp = i;
						}

					}
					photo.setImageResource(hotelsIcon[temp]);
				} else if (marker.getTitle().contains("手信")){
					for (int i = 0; i < 3; i++) {
						if (marker.getTitle().contains(foodsTitle[i])) {
							temp = i;
						}

					}
					photo.setImageResource(foodsIcon[temp]);
				}

				snippet.setText(marker.getSnippet());
				return (popWindow);
			}
		}
	}

	@SuppressLint("NewApi")
	private void initMap() {

		if (mMap == null) {
			FragmentManager fm = getSupportFragmentManager();
			SupportMapFragment f = (SupportMapFragment) fm
					.findFragmentById(R.id.map);
			mMap = f.getExtendedMap();

			/*
			 * mMap = ((MapFragment) getFragmentManager().findFragmentById(
			 * R.id.map)).getMap();
			 */

			if (mMap != null) {
				mMap.setClustering(new ClusteringSettings()
						.clusterOptionsProvider(
								new markerCluster(getResources()))
						.addMarkersDynamically(true));
				db = new SQLiteUtil(TourMapActivity.this);
				ListViewData = db.SelectAll(SQLiteUtil.TABLE_NAME);
				blMapData = db.SelectAll2(SQLiteUtil.TABLE_NAME2);

				for (int i = 0; i < blMapData.size(); i++) {
					Boolean tempB = new Boolean(
							blMapData.get(String.valueOf(i)));
					boolean tempb = tempB.booleanValue();
					bl[i] = tempb;

				}
				checkView();
				txtOutput
						.setText("標記的位置:請點擊位置資訊視窗標記 \n地址:未設定\n網站:未設定\n電話: 未設定");
				mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				phoneNumber = "尚未設定";
				website = "尚未設定";
				/*
				 * MarkerGenerator.sameDb(db, ListViewData);
				 * /*MarkerGenerator.markSpot(mMap);
				 * MarkerGenerator.markHotel(mMap);
				 * MarkerGenerator.markFood(mMap);
				 */
				adapter = new iconAdapter(this);

				mMap.setInfoWindowAdapter(new CustomizeInfoWindowAdapter(
						getLayoutInflater()));
				mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

					@Override
					public void onInfoWindowClick(Marker marker) {
						// TODO Auto-generated method stub
						if (marker.isCluster()) {
							List<Marker> markers = marker.getMarkers();
							Builder builder = LatLngBounds.builder();
							for (Marker m : markers) {
								builder.include(m.getPosition());
							}
							LatLngBounds bounds = builder.build();
							mMap.animateCamera(CameraUpdateFactory
									.newLatLngBounds(
											bounds,
											getResources()
													.getDimensionPixelSize(
															R.dimen.padding)));
						} else {

							boolean b = false;
							if (ListViewData.size() == 0) {
								// addIntoMap(marker);
							} else {
								// Set tpSet = new HashSet<String>();
								for (int i = 0; i < ListViewData.size(); i++) {
									Set tpSet = ListViewData.get(i);
									if (tpSet.contains(marker.getTitle())) {
										db.Delete(SQLiteUtil.TABLE_NAME,
												"_title=\'" + marker.getTitle()
														+ "\'");
										ListViewData.remove(i);
										b = true;
									}
								}
							}
							// ~~~~~~~~~~~~
							if (b) {
								if (marker.getTitle().substring(0, 2)
										.equals("民宿")) {

									marker.setIcon(BitmapDescriptorFactory
											.fromResource(R.drawable.purredspot));

								} else if (marker.getTitle().substring(0, 2)
										.equals("景點")) {

									marker.setIcon(BitmapDescriptorFactory
											.fromResource(R.drawable.purplespot));
								} else if (marker.getTitle().substring(0, 2)
										.equals("手信")) {

									marker.setIcon(BitmapDescriptorFactory
											.fromResource(R.drawable.purplefood));
								}
								Toast.makeText(
										TourMapActivity.this,
										marker.getTitle().substring(3)
												+ "已移除標記", Toast.LENGTH_SHORT)
										.show();

							} else {

								addIntoMap(marker);

							}

						}
					}
				});

			}
		}

	}

	@SuppressWarnings("unchecked")
	private void addIntoMap(Marker marker) {

		Set tpSet = new HashSet<String>();
		tpSet.add(marker.getTitle());
		tpSet.add(marker.getSnippet());
		tpSet.add(marker.getPosition().latitude);
		tpSet.add(marker.getPosition().longitude);
		ListViewData.add(tpSet);

		marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.redspot));
		Toast.makeText(TourMapActivity.this,
				marker.getTitle().substring(3) + "已標記", Toast.LENGTH_SHORT)
				.show();
		ContentValues cv = new ContentValues();
		cv.put(SQLiteUtil.COLUMN_NAMEs[0], marker.getTitle());
		cv.put(SQLiteUtil.COLUMN_NAMEs[1], marker.getSnippet());
		cv.put(SQLiteUtil.COLUMN_NAMEs[2], marker.getPosition().latitude);
		cv.put(SQLiteUtil.COLUMN_NAMEs[3], marker.getPosition().longitude);
		// cv.put(SQLiteUtil.COLUMN_NAMEs[1], dts);
		db.Insert(SQLiteUtil.TABLE_NAME, cv);
		// ListViewData = db.SelectAll(SQLiteUtil.TABLE_NAME);
		updateLikeMinsu(marker);

	}

	private void updateLikeMinsu(Marker marker) {

		// 顯示資訊
		if (marker.getSnippet().contains("電話")) {
			phoneNumber = (marker.getSnippet().substring(marker.getSnippet()
					.indexOf("電話") + 3, marker.getSnippet().indexOf("電話") + 5));
			if (phoneNumber.equals("03")) {
				phoneNumber = (marker.getSnippet().substring(marker
						.getSnippet().indexOf("電話") + 3, marker.getSnippet()
						.indexOf("電話") + 12));
			} else {
				phoneNumber = (marker.getSnippet().substring(marker
						.getSnippet().indexOf("電話") + 3, marker.getSnippet()
						.indexOf("電話") + 13));
			}

		} else {
			phoneNumber = "暫無電話";
		}
		if (marker.getSnippet().contains("地址")) {
			address = (marker.getSnippet().substring(marker.getSnippet()
					.indexOf("地址") + 3, marker.getSnippet().indexOf("網址") - 1));
		} else {
			address = "暫無地址";
		}
		if (marker.getSnippet().contains("網址")) {
			website = (marker.getSnippet().substring(marker.getSnippet()
					.indexOf("網址") + 3, marker.getSnippet().length() ));
		} else {
			website = "暫無網址";
		}
		name = marker.getTitle().substring(3);
		txtOutput.setText("標記的位置: " + marker.getTitle().substring(3) + "\n地址: "
				+ address + "\n網址: " + website + "\n電話: " + phoneNumber);
	}

	GpsStatus.Listener gpsListener = new GpsStatus.Listener() {
		@Override
		public void onGpsStatusChanged(int event) {
			switch (event) {
			case GpsStatus.GPS_EVENT_STARTED:
				Log.d(TAG, "GPS_EVENT_STARTED");
				Toast.makeText(TourMapActivity.this, "GPS_EVENT_STARTED",
						Toast.LENGTH_SHORT).show();
				break;
			case GpsStatus.GPS_EVENT_STOPPED:
				Log.d(TAG, "GPS_EVENT_STOPPED");
				Toast.makeText(TourMapActivity.this, "GPS_EVENT_STOPPED",
						Toast.LENGTH_SHORT).show();
				break;
			case GpsStatus.GPS_EVENT_FIRST_FIX:
				Log.d(TAG, "GPS_EVENT_FIRST_FIX");
				Toast.makeText(TourMapActivity.this, "GPS_EVENT_FIRST_FIX",
						Toast.LENGTH_SHORT).show();
				break;
			case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
				Log.d(TAG, "GPS_EVENT_SATELLITE_STATUS");
				break;
			}
		}
	};
	LocationListener locationListener = new LocationListener() {
		@Override
		public void onLocationChanged(Location location) {
			updateWithNewLocation(location);
		}

		@Override
		public void onProviderDisabled(String provider) {
			updateWithNewLocation(null);
		}

		@Override
		public void onProviderEnabled(String provider) {

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			switch (status) {
			case LocationProvider.OUT_OF_SERVICE:
				Log.v(TAG, "Status Changed: Out of Service");
				Toast.makeText(TourMapActivity.this,
						"Status Changed: Out of Service", Toast.LENGTH_SHORT)
						.show();
				break;
			case LocationProvider.TEMPORARILY_UNAVAILABLE:
				Log.v(TAG, "Status Changed: Temporarily Unavailable");
				Toast.makeText(TourMapActivity.this,
						"Status Changed: Temporarily Unavailable",
						Toast.LENGTH_SHORT).show();
				break;
			case LocationProvider.AVAILABLE:
				Log.v(TAG, "Status Changed: Available");
				Toast.makeText(TourMapActivity.this,
						"Status Changed: Available", Toast.LENGTH_SHORT).show();
				break;
			}
		}

	};

	/**
	 * 顯示"我"在哪裡
	 * 
	 * @param lat
	 * @param lng
	 */
	private void showMarkerMe(double lat, double lng) {
		if (markerMe != null) {
			markerMe.remove();
		}

		MarkerOptions markerOpt = new MarkerOptions();
		markerOpt.position(new LatLng(lat, lng));
		markerOpt.title("我在這裡");
		markerOpt.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.whereami));
		markerMe = mMap.addMarker(markerOpt);

		Toast.makeText(this, "lat:" + lat + ",lng:" + lng, Toast.LENGTH_SHORT)
				.show();
	}

	/**
	 * 執行"我"在哪裡 1.建立位置改變偵聽器 2.預先顯示上次的已知位置
	 */
	private void whereAmI() {
		// 取得上次已知的位置
		Location location = locationMgr.getLastKnownLocation(provider);
		Toast.makeText(TourMapActivity.this, provider, Toast.LENGTH_SHORT)
				.show();
		updateWithNewLocation(location);

		// GPS Listener
		locationMgr.addGpsStatusListener(gpsListener);

		// Location Listener
		int minTime = 5000;// ms
		int minDist = 5;// meter
		locationMgr.requestLocationUpdates(provider, minTime, minDist,
				locationListener);
		/*
		 * if (location != null) {
		 * 
		 * //showMarkerMe(location.getLatitude(), location.getLongitude());
		 * cameraFocusOnMe(location.getLatitude(), location.getLongitude());
		 * }else{ Toast.makeText(TourMapActivity.this, "No location found.",
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 */
	}

	private void cameraFocusOnMe(double lat, double lng) {
		CameraPosition camPosition = new CameraPosition.Builder()
				.target(new LatLng(lat, lng)).zoom(16).build();
		// 16
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camPosition));
	}

	/**
	 * 更新並顯示新位置
	 * 
	 * @param location
	 */
	private void updateWithNewLocation(Location location) {

		if (location != null) {

			double lng = location.getLongitude();
			double lat = location.getLatitude(); // 速度 float speed =
													// location.getSpeed();
			// 時間 long time = location.getTime(); String timeString =

			showMarkerMe(lat, lng);
			cameraFocusOnMe(lat, lng);

			// "我" showMarkerMe(lat, lng); cameraFocusOnMe(lat, lng);

		} else {
			Toast.makeText(TourMapActivity.this, "找不到", Toast.LENGTH_SHORT)
					.show();
		}

		// 顯示資訊
		// txtOutput.setText(where);
	}

	@Override
	protected void onStart() {
		super.onStart();

		initView();
		initMap();

		CameraPosition camPosition = new CameraPosition.Builder()
				.target(new LatLng(24.728122, 121.764107)).zoom(10).build();
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camPosition));

	}

	@Override
	protected void onStop() {
		if (locationMgr != null)
			locationMgr.removeUpdates(locationListener);

		super.onStop();

	}

	private void initView() {
		txtOutput = (TextView) findViewById(R.id.txtOutput);
	}

	/**
	 * GPS初始化，取得可用的位置提供器
	 * 
	 * @return
	 */
	private boolean initLocationProvider(int id) {
		locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// 1.選擇最佳提供器
		// Criteria criteria = new Criteria();
		// criteria.setAccuracy(Criteria.ACCURACY_FINE);
		// criteria.setAltitudeRequired(false);
		// criteria.setBearingRequired(false);
		// criteria.setCostAllowed(true);
		// criteria.setPowerRequirement(Criteria.POWER_LOW);
		//
		// provider = locationMgr.getBestProvider(criteria, true);
		//
		// if (provider != null) {
		// return true;
		// }

		// 2.選擇使用GPS提供器
		// if (locationMgr.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
		// provider = LocationManager.GPS_PROVIDER;
		// return true;
		// }
		switch (id) {
		case 1:
			if (locationMgr.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				provider = LocationManager.GPS_PROVIDER;
				return true;
			}
			break;
		case 2:
			if (locationMgr.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
				provider = LocationManager.NETWORK_PROVIDER;
				return true;
			}
			break;
		default:

		}
		// 3.選擇使用網路提供器
		// if (locationMgr.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
		// {
		// provider = LocationManager.NETWORK_PROVIDER;
		// return true;

		// }
		if (locationMgr.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			provider = LocationManager.GPS_PROVIDER;
			return true;
		}

		return false;
	}

	/*
	 * private void markAll() { markSpot(); markHotel(); markFood(); }
	 */

	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.google_map, menu);
		// 參數1:群組id, 參數2:itemId, 參數3:item順序, 參數4:item名稱
		menu.add(0, 0, 0, "電話聯絡");
		menu.add(0, 1, 1, "瀏覽網站");
		menu.add(0, 2, 2, "匯出標記行程規劃");
		menu.add(0, 3, 3, "全部標記匯出");
		MenuItem clear = menu.add(0, 4, 4, "清除已標記點");
		
		View v = (View) menu.findItem(R.id.search).getActionView();

		listView = (ListView) this.findViewById(R.id.listView1);

		listView.setOnItemClickListener(linster);

		sea = menu.getItem(4);

		/** Get the edit text from the action view */
		txtSearch = (EditText) v.findViewById(R.id.txt_search);

		txtSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				listView.setVisibility(View.VISIBLE);
				txtOutput.setVisibility(View.INVISIBLE);
				tpMap = new HashMap<String, MutiData>();
				Iterator it = MarkerGenerator.markMap.values().iterator();
				while (it.hasNext()) {
					MutiData md = (MutiData) it.next();
					if (md.ti.contains(cs)) {
						tpMap.put(md.ti, md);

					}
				}
				if (tpMap.size() > 0) {
					listView.setAdapter(adapter);
				}
				if (cs.length() == 0) {
					listView.setVisibility(View.INVISIBLE);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtSearch.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					listView.setVisibility(View.INVISIBLE);
					txtSearch.setText("");
				}
			}
		});

		/** Setting an action listener */

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 依據itemId來判斷使用者點選哪一個item
		switch (item.getItemId()) {
		case 0:
			if (phoneNumber.length() == 10 || phoneNumber.length() == 9) {
				Uri uri = Uri.parse("tel:" + phoneNumber);
				Intent intent = new Intent(Intent.ACTION_DIAL, uri);
				startActivity(intent);
			} else {
				Toast.makeText(TourMapActivity.this, "未提供電話",
						Toast.LENGTH_SHORT).show();
			}

			break;
		case 1:
			if (website.length() > 15) {
				ConnectivityManager CM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo info = CM.getActiveNetworkInfo();
				if (info != null) {
					Uri uri = Uri.parse(website);
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);

				} else {
					Toast.makeText(TourMapActivity.this, "網際網路沒有連線",
							Toast.LENGTH_SHORT).show();
				}

			} else {
				Toast.makeText(TourMapActivity.this, "未提供網址",
						Toast.LENGTH_SHORT).show();
			}

			break;
		case 2:
			
			/*if(name.contains("請點擊")||name.length()<2){
				Toast.makeText(TourMapActivity.this, "請先標記",
						Toast.LENGTH_SHORT).show();
			}else{
				
				Intent intent = new Intent();
				  
				  intent.setClass(TourMapActivity.this, ScheduleActivity.class);
				   // new一個Bundle物件，並將要傳遞的資料傳入 
				 	intent.putExtra("title",name);
				  // 將Bundle物件傳給intent intent.putExtras(bundle);
				  startActivity(intent);
			}*/
			Intent intent2 = new Intent();
			  
			  intent2.setClass(TourMapActivity.this, ScheduleActivity.class);
			if(ListViewData.size()>0){
				Set tpSet = ListViewData.get(ListViewData.size()-1);
				Object tpa[] = tpSet.toArray();
				String send="";
				for (int j = 0; j < tpSet.size(); j++) {

					
					 if (String.valueOf(tpa[j]).length() < 25&&String.valueOf(tpa[j]).contains(":")) {
						 
						 send = String.valueOf(tpa[j]);
						 Log.e("title", send);
							 intent2.putExtra("title", send.substring(3));
							
					} 
					

				}
			}
			 startActivity(intent2);
			
			 
			break;
		case 3:
			Intent intent = new Intent();
			  
			  intent.setClass(TourMapActivity.this, ScheduleActivity.class);
			   // new一個Bundle物件，並將要傳遞的資料傳入 
			 
			for (int i = 0; i < ListViewData.size(); i++) {

				Set tpSet = ListViewData.get(i);
				Object tpa[] = tpSet.toArray();
				String send="";
				for (int j = 0; j < tpSet.size(); j++) {

					
					 if (String.valueOf(tpa[j]).length() < 25&&String.valueOf(tpa[j]).contains(":")) {
						 
						 send = String.valueOf(tpa[j]);
						 Log.e("title"+i, send);
							 intent.putExtra("title"+i, send.substring(3));
							
					} 
					

				}
				
			}
		
			intent.putExtra("num",String.valueOf(ListViewData.size()));
			
			  // 將Bundle物件傳給intent intent.putExtras(bundle);
			  startActivity(intent);
			
			 
			break;
		case 4:
			db.reBuild("SQLiteUtil");
			ListViewData.clear();
			mMap.clear();

			if (bl[1] == true) {
				MarkerGenerator.markSpot(mMap);
			}
			if (bl[2] == true) {
				MarkerGenerator.markHotel(mMap);

			}
			if (bl[3] == true) {
				MarkerGenerator.markFood(mMap);

			}
			if (bl[1] == bl[2] && bl[2] == bl[3] && bl[3] == false) {
				bl[1] = true;
				MarkerGenerator.markSpot(mMap);
				db.reBuild("SQLiteUtil2");
				for (int i = 0; i < bl.length; i++) {
					ContentValues cv = new ContentValues();
					cv.put(SQLiteUtil.COLUMN_NAMEs2[0], String.valueOf(i));
					cv.put(SQLiteUtil.COLUMN_NAMEs2[1], String.valueOf(bl[i]));

					db.Insert(SQLiteUtil.TABLE_NAME2, cv);
				}

			}

			break;
		case R.id.search:

			txtSearch.requestFocus();
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {

				public void run() {
					InputMethodManager inputManager = (InputMethodManager) txtSearch
							.getContext().getSystemService(
									Context.INPUT_METHOD_SERVICE);
					inputManager.showSoftInput(txtSearch, 0);
				}

			}, 50);

		default:
		}
		return super.onOptionsItemSelected(item);
	}

	OnItemClickListener linster = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			txtSearch.setText("");
			txtSearch.clearFocus();
			listView.setVisibility(View.INVISIBLE);
			sea.setChecked(false);
			if (bl[5] == true)
				txtOutput.setVisibility(View.VISIBLE);
			InputMethodManager imm = (InputMethodManager) TourMapActivity.this
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(txtSearch.getWindowToken(), 0);
			MutiData m = (MutiData) temp[position];
			CameraPosition camPosition = new CameraPosition.Builder()
					.target(m.lat).zoom(17).build();
			mMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(camPosition));
		}
	};

	private static class iconAdapter extends BaseAdapter {
		private LayoutInflater mInflater;

		public iconAdapter(Context context) {
			mInflater = LayoutInflater.from(context);

		}

		@Override
		public int getCount() {

			if (null != tpMap) {
				return tpMap.size();
			} else {
				return 0;
			}

		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			convertView = this.mInflater.inflate(R.layout.searchelper, null);

			TextView tv = (TextView) convertView.findViewById(R.id.list_text);

			convertView.setTag(tv);
			temp = tpMap.values().toArray();
			MutiData m = (MutiData) temp[position];

			tv.setText(m.ti);
			if (position % 2 == 0) {
				tv.setBackgroundColor(Color.rgb(255, 255, 255));
			} else {
				tv.setBackgroundColor(Color.rgb(245, 245, 220));
			}

			return convertView;
		}

	}

	private void onDataUpdate() {
		Marker m = mMap.getMarkerShowingInfoWindow();
		if (m != null && !m.isCluster() && m.getData() instanceof MutableData) {
			m.showInfoWindow();
		}
	}

	private static class MutableData {

		private int value;

		private LatLng position;

		public MutableData(int value, LatLng position) {
			this.value = value;
			this.position = position;
		}
	}

	@SuppressLint("NewApi")
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_UP) {
			
			if(listView.getVisibility()==View.VISIBLE){
				txtSearch.setText("");
				txtSearch.clearFocus();
				listView.setVisibility(View.INVISIBLE);
				sea.collapseActionView();
			}else{
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("提示");
				builder.setMessage("是否回到主畫面?");
				builder.setPositiveButton("是",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// 退出程序
								finish();
							}
						});
				builder.setNegativeButton("否", null);
				builder.show();
				
			}
			
			return true;
		}
		return super.dispatchKeyEvent(event);
	}
	/*
	 * @SuppressLint("NewApi")
	 * 
	 * @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
	 * if(keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键 // processExit();
	 * Toast.makeText(TourMapActivity.this,
	 * String.valueOf(sea.collapseActionView()), Toast.LENGTH_SHORT).show();
	 * return true; } return super.onKeyDown(keyCode, event); }
	 */
}
