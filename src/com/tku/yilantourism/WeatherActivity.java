package com.tku.yilantourism;




import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



public class WeatherActivity extends Activity {
	public class XMLData {
		Map<String, String> data = new LinkedHashMap<String, String>();
	}

	private static final List<XMLData> items_weather = new ArrayList<XMLData>();
	ListView  list;
	String[] locationArray = { "宜蘭", "台北市", "新北市", "台中市", "台南市",
	"高雄市","桃園縣","新竹縣","苗栗縣","彰化縣","南投縣","雲林縣","嘉義縣","屏東縣","花蓮縣","台東縣","基隆市" };
	static RelativeLayout layout ;
	String yi="http://www.cwb.gov.tw/rss/forecast/36_17.xml";
	String pei="http://www.cwb.gov.tw/rss/forecast/36_01.xml";
	String ne="http://www.cwb.gov.tw/rss/forecast/36_04.xml";
	String cen="http://www.cwb.gov.tw/rss/forecast/36_08.xml";
	String sou="http://www.cwb.gov.tw/rss/forecast/36_13.xml";
	String hi="http://www.cwb.gov.tw/rss/forecast/36_02.xml";
	String cir="http://www.cwb.gov.tw/rss/forecast/36_05.xml";
	String ew="http://www.cwb.gov.tw/rss/forecast/36_06.xml";
	String cat="http://www.cwb.gov.tw/rss/forecast/36_07.xml";
	String dirty="http://www.cwb.gov.tw/rss/forecast/36_09.xml";
	String head="http://www.cwb.gov.tw/rss/forecast/36_10.xml";
	String clou="http://www.cwb.gov.tw/rss/forecast/36_11.xml";
	String home="http://www.cwb.gov.tw/rss/forecast/36_12.xml";
	String east="http://www.cwb.gov.tw/rss/forecast/36_15.xml";
	String flow="http://www.cwb.gov.tw/rss/forecast/36_18.xml";
	String ti="http://www.cwb.gov.tw/rss/forecast/36_19.xml";
	String chick="http://www.cwb.gov.tw/rss/forecast/36_03.xml";
	String[] urlArray={yi,pei,ne,cen,sou,hi,cir,ew,cat,dirty,head,clou,home,east,flow,ti,chick};
	String finUrl;
	private XMLData parseItem(Element item) {
		XMLData xml = new XMLData();
		for (int i = 0; i < item.childNodes().size(); i++) {
			org.jsoup.nodes.Node node = item.childNode(i);
			if (node.getClass() != Element.class) {
				continue;
			}
			Element el = (Element) node;
			if (el.tagName().compareTo("") == 0)
				continue;

			String key = el.tagName();

			String value = "";
			if (el.text().compareTo("") == 0) {
				if ((i + 1) < item.childNodes().size()) {
					org.jsoup.nodes.Node no = item.childNode(i + 1);
					value = no.toString();
					i++;
				}
			} else {
				value = el.text();
			}

			if (value.contains("<![CDATA["))
				value = value.replace("<![CDATA[", " ");
			if (value.contains("]]>"))
				value = value.replace("]]>", " ");
			if (value.compareTo("") == 0)
				continue;
			xml.data.put(key, value);
		}

		return xml;
	}

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		 layout = (RelativeLayout) findViewById(R.id.weatherlayout);
		 list = (ListView) findViewById(R.id.listView_weather);

		
		Button b2 = (Button) this.findViewById(R.id.button2);
		b2.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(WeatherActivity.this).setTitle("請選擇城市")
				.setItems(locationArray,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								finUrl=urlArray[which];
								setList();
							}
						}).show();
				
				
			}

		});
		

		

	}
	@SuppressLint("NewApi")
	public void setList(){
		if (android.os.Build.VERSION.SDK_INT >= 4) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
					.build());
		}

		//if (items_weather.isEmpty()) {
			try {
				items_weather.clear();
				ConnectivityManager CM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo info = CM.getActiveNetworkInfo();
				if (info != null) {

					Document doc = Jsoup.connect(
							finUrl)
							.get();

					// Element body = doc.body();
					// Element channel =
					// body.getElementsByTag("channel").get(0);
					// Elements items = channel.getElementsByTag("item");
					Elements items = doc.select("item");
					for (int i = 0; i < items.size(); i++) {
						XMLData xml = parseItem(items.get(i));
						// System.out.println(xml);

						// String text = parseItem(items.get(i));

						items_weather.add(xml);
					}
				} else {

					Toast.makeText(WeatherActivity.this, "網際網路沒有連線", Toast.LENGTH_LONG)
							.show();
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
		
		list.setAdapter(new iconAdapter(this));

		AdapterView.OnItemClickListener listener2 = new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ViewHolder holder = (ViewHolder) view.getTag();
				Toast.makeText(view.getContext(),
						"本日天氣: " + holder.textView.getText(),
						Toast.LENGTH_SHORT).show();
			}
		};
		list.setOnItemClickListener(listener2);
		
	}



	public static class ViewHolder {
		TextView textView;
		ImageView imageView_icon;
	}

	public static class iconAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		public Bitmap mIcon1, mIcon2, mIcon3;
		private int temp;

		public iconAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
			temp = -2;
			mIcon1 = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.sun_144);
			mIcon2 = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.cloud_144);
			mIcon3 = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.rain_144);
		}

		public int getCount() {
			// TODO Auto-generated method stub
			// return m_ItemData.length;
			return items_weather.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder;
			if (convertView == null) {
				convertView = this.mInflater.inflate(R.layout.listhelper, null);
				holder = new ViewHolder();
				holder.textView = (TextView) convertView
						.findViewById(R.id.list_text);
				holder.imageView_icon = (ImageView) convertView
						.findViewById(R.id.image_item);
				// holder.textView.setBackgroundColor(Color.YELLOW);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			int iconStandard = -1;
			XMLData xml = items_weather.get(position);
			holder.textView.setText(xml.data.get("title"));
			// Log.i("HERE", xml.data.get("title"));
			String value = xml.data.get("title");
			if (value.contains("%")) {
				String num = value.substring(value.indexOf("率") + 2,
						value.indexOf("%"));
				num = num.trim();

				iconStandard = Integer.parseInt(num);
			}
			if (iconStandard < 0) {
				iconStandard = temp;
			} else {
				temp = iconStandard;
			}
			if (iconStandard < 30) {
				holder.imageView_icon.setImageBitmap(mIcon1);
				
				layout.setBackgroundResource(R.drawable.sunnyday);
			
			} else if (iconStandard < 60) {
				holder.imageView_icon.setImageBitmap(mIcon2);
				holder.textView.setTextColor(Color.BLACK);
				layout.setBackgroundResource(R.drawable.cloudday);
			} else {
				holder.imageView_icon.setImageBitmap(mIcon3);
				layout.setBackgroundResource(R.drawable.rainnyday);
			}

			
			return convertView;
		}
	}
}
    
    
   
    
    
  
	
