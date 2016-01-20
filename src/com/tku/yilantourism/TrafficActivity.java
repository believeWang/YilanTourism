package com.tku.yilantourism;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class TrafficActivity extends Activity {
	String[] busArray = { "首都客運", "葛瑪蘭客運", "國光客運" };
	String[] railArray = { "石城車站", "大里車站", "外澳車站", "礁溪車站", "宜蘭車站", "羅東車站",
			"冬山車站" };
	String sourR = "資料來源:隨意窩(akao_chen),紫色微笑,痞客邦 (慶媽咪)(花鹿米民宿 )";
	String sourB = "資料來源:各家客運";
	String[] busContentArray = { "首都客運", "葛瑪蘭客運", "國光客運" };
	String[] railContentArray = { "石城車站", "大里車站", "外澳車站", "礁溪車站", "宜蘭車站",
			"羅東車站", "冬山車站" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_traffic);
		busContentArray[0] = "一、首都客運(市府轉運站乘車)\n1.宜蘭站\n位址:宜蘭市校舍路190號\n電話:03-937-3600\n景點:幾米廣場\n2.羅東站\n位址:羅東鎮傳藝路三段229號\n電話:03-955-6585\n景點: 羅東運動公園、宜農牧場、林場公園、林場肉羹\n3.礁溪站\n位址:礁溪鄉礁溪路5段70號\n電話:03-988-0700\n景點:湯圍溝公園";
		busContentArray[1] = "二、葛瑪蘭客運(台北轉運站、板橋轉運站、科技大樓站、萬華車  站皆可程車)\n1.宜蘭轉運站\n地址：宜蘭市校舍路190號 第5及6月台\n電話: 03-938-5655\n景點:幾米廣場\n2.羅東轉運站\n地址:宜蘭縣羅東鎮傳藝路三段229號\n電話:03-956-6198\n景點: 羅東運動公園、宜農牧場、林場公園、林場肉羹\n3.礁溪轉運站\n地址:宜蘭縣礁溪鄉礁溪路5段150號\n電話: 03-987-6739\n景點:湯圍溝公園";
		busContentArray[2] = "三、國光客運(台北車站、基隆車站)\n1.宜蘭車站\n地址:宜蘭市校舍路190號\n電話:03-936-5441\n景點:幾米廣場\n2.羅東車站\n地址:宜蘭縣羅東鎮興東路23之2號\n電話:03-954-2703、03-956-7505、03-954-2054\n景點: 羅東運動公園、宜農牧場、林場公園、林場肉羹";
		railContentArray[0] = "1.石城車站 (only 區間車停靠)\n宜蘭線第一站，位於頭城。\n沿著鐵道走可以到達小村莊。\n可以像平溪、十分車站那樣走在鐵道上欣賞風景，觀賞大海、龜山島。\n適合喜歡自然景觀、拍照的遊客。";
		railContentArray[1] = "2.大里車站 (only 區間車停靠)\n大里天公廟、大海，結合大自然與傳統氛圍	。\n出站後途中會有一個看得到火車經過位置，可以與火車拍照。\n適合喜歡自然景觀、拍照的遊客。";
		railContentArray[2] = "3.外澳車站  (only 區間車停靠)\n出站立刻看到太平洋及龜山島美景。\n橫越濱海公路後，可直接進入沙灘遊憩source\n休閒活動有衝浪、降落傘。\n適合喜歡自然景觀、刺激、拍照的遊客。";
		railContentArray[3] = "4.礁溪車站\n溫泉旅館、民宿聚集地。\n礁溪湯圍溝公園、五峰旗風景區、跑馬古道、礁溪協天廟、礁溪眾多的特色美食。\n適合喜歡泡湯、自然景觀的遊客。";
		railContentArray[4] = "5.宜蘭車站\n位於宜蘭市，為一等大站。\n舊書櫃咖啡館(二手書、定期展覽)、百果樹紅磚屋(咖啡、藝文沙龍)、幾米廣場、宜蘭東門夜市。\n適合偏好藝文展覽的遊客。";
		railContentArray[5] = "6.羅東車站\n羅東林業文化園區、羅東夜市、羅東運動公園、傳統藝術中心。\n適合喜歡美食、戶外活動的遊客。";
		railContentArray[6] = "7.冬山車站\n車站本身就是一個景點，是宜蘭最美麗的火車站，鐵道採用高架式且搭配流線形的鋼骨建材及米白色雨棚，頗具前衛感。\n從月台即可看到一大片的稻田，稻田裡還佇立著零星的稻草人，景色隨著四季呈現不同的風貌。\n適合喜歡田園景觀、特色建築的遊客。";

		findView();
	}

	public void findView() {
		final TextView content = (TextView) this.findViewById(R.id.textView2);
		final TextView source = (TextView) this.findViewById(R.id.textView3);
		source.setTextSize(10);
		content.setMovementMethod(ScrollingMovementMethod.getInstance());
		ImageButton bus = (ImageButton) this.findViewById(R.id.ImageButton1_bus);
		bus.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(TrafficActivity.this)
						.setTitle("請選擇")
						.setItems(busArray,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										content.setText(busContentArray[which]);
										source.setText(sourB);

									}
								}).show();

			}

		});
		ImageButton rail = (ImageButton) this.findViewById(R.id.ImageButton1_rail);
		rail.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(TrafficActivity.this)
						.setTitle("請選擇")
						.setItems(railArray,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										content.setText(railContentArray[which]);
										source.setText(sourR);

									}
								}).show();
				source.setText(sourR);
			}

		});
		TextView size = (TextView) this.findViewById(R.id.textView1);

		content.setTextSize(22);
		size.setTextSize(22);
		final SeekBar sb = (SeekBar) this.findViewById(R.id.seekBar1);
		sb.setProgress(22);
		sb.setMax(60);

		sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				// seekBarValue.setText(String.valueOf(progress));

				content.setTextSize(sb.getProgress());
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
		});
	}

	
}
