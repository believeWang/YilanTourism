package com.tku.yilantourism;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.util.Log;

import pl.mg6.android.maps.extensions.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import pl.mg6.android.maps.extensions.Marker;


public class MarkerGenerator {
	protected static SQLiteUtil db = null;
	public static List<Set<String>> ListViewData = null;
	public static Map<String,MutiData> markMap=new HashMap<String,MutiData>();
	// 親水公園
		final static LatLng waterPark = new LatLng(24.672622, 121.812176);
		// 香格里拉
		final static LatLng leisureFarm = new LatLng(24.632526, 121.728877);
		// 蘭陽博物館
		final static LatLng ecoMuseum = new LatLng(24.868761, 121.832088);
		// 烏石港
		final static LatLng whaleDock = new LatLng(24.874212, 121.838655);
		// 宜蘭幾米廣場
		final static LatLng jimmySquare = new LatLng(24.75447, 121.758188);
		// 傳統藝術中心
		final static LatLng artCenter = new LatLng(24.685783, 121.824033);
		// 白米木屐村
		final static LatLng baimiVillage = new LatLng(24.581471, 121.845973);
		// 大湖風景區
		final static LatLng giantLake = new LatLng(24.741609, 121.691607);
		// 棲蘭神木園區
		final static LatLng divineTree = new LatLng(24.580173, 121.492362);
		// 砲台山
		final static LatLng cannonMount = new LatLng(24.591422, 121.854812);

		// 宜蘭藏酒酒莊
		final static LatLng wineVillage = new LatLng(24.9023, 121.862086);
		// 礁溪湯圍溝公園
		final static LatLng tanPark = new LatLng(24.829293, 121.769861);
		// 羅東林業園區
		final static LatLng lotungTree = new LatLng(24.684652, 121.77147);
		// 清水地熱
		final static LatLng earthHot = new LatLng(24.613897, 121.636652);
		// 太平山
		final static LatLng veryMountain = new LatLng(24.549622, 121.502895);
		// 幸福20號農場
		final static LatLng happinessFarm = new LatLng(24.636875, 121.712179);
		// 寒溪吊橋
		final static LatLng streamBridge = new LatLng(24.614541, 121.687356);
		// 梅花湖
		final static LatLng blossomLake = new LatLng(24.648265, 121.731062);
		// 新寮瀑布
		final static LatLng waterFall = new LatLng(24.609254, 121.74634);
		// 羅東觀光夜市
		final static LatLng nightMarket = new LatLng(24.676424, 121.76881);

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// 羅東我行我素
		final static LatLng minsu01 = new LatLng(24.675044, 121.769228);
		// 宜蘭羅東尚閤屋民宿
		final static LatLng minsu02 = new LatLng(24.67465, 121.79749);
		// 羅東民宿魚兒居
		final static LatLng minsu03 = new LatLng(24.678649, 121.770127);
		// 羅東院子なかにわ
		final static LatLng minsu04 = new LatLng(24.677199, 121.7637);
		// 羅東玩全夜市民宿
		final static LatLng minsu05 = new LatLng(24.678656, 121.77012);
		// 宜蘭羅東民宿夜市玩家
		final static LatLng minsu06 = new LatLng(24.675722, 121.773037);
		// 羅東後站平價套房
		final static LatLng minsu07 = new LatLng(24.674528, 121.778431);
		// 新宿小棧
		final static LatLng minsu08 = new LatLng(24.685324, 121.799528);
		// 閣子趣
		final static LatLng minsu09 = new LatLng(24.671871, 121.796567);
		// 羅東夜市羅東棧
		final static LatLng minsu10 = new LatLng(24.676209, 121.7738);

		// ~~~~~~~~~~~~~~~~~~~~~礁溪
		// 礁溪松田民宿
		final static LatLng minsu20 = new LatLng(24.833616, 121.786427);
		// 礁溪320
		final static LatLng minsu21 = new LatLng(24.828884, 121.768757);
		// QQ的家民宿
		final static LatLng minsu22 = new LatLng(24.830598, 121.775141);
		// 礁溪湯泉之家
		final static LatLng minsu23 = new LatLng(24.827014, 121.770827);
		// 雲湘居渡假民宿
		final static LatLng minsu24 = new LatLng(24.838075, 121.784774);
		// 泉鄉雅舍
		final static LatLng minsu25 = new LatLng(24.826722, 121.770312);
		// 閃亮の泉
		final static LatLng minsu26 = new LatLng(24.83014, 121.774016);
		// 宜蘭民宿礁溪艾瑪溫泉渡假行館
		final static LatLng minsu27 = new LatLng(24.826859, 121.770051);
		// 礁溪伊豆民宿
		final static LatLng minsu28 = new LatLng(24.822153, 121.777578);
		// 山璞民宿
		final static LatLng minsu29 = new LatLng(24.826742, 121.770195);
		// ~~~~~~~~~~~冬山
		// 卡松安
		final static LatLng minsu30 = new LatLng(24.632543, 121.789785);
		// 宜和民宿
		final static LatLng minsu31 = new LatLng(24.670389, 121.80574);
		// 親水居
		final static LatLng minsu32 = new LatLng(24.671276, 121.809238);
		// 幸福在角落
		final static LatLng minsu33 = new LatLng(24.651561, 121.801931);
		// 蘭。童話冬山河民宿
		final static LatLng minsu34 = new LatLng(24.673363, 121.808304);
		// 第二個家
		final static LatLng minsu35 = new LatLng(24.661068, 121.770185);
		// 伊莎城堡
		final static LatLng minsu36 = new LatLng(24.681981, 121.734608);
		// 幸福柚子鄉村民宿
		final static LatLng minsu37 = new LatLng(24.628079, 121.735509);
		// 花間雅舍　蓮春園
		final static LatLng minsu38 = new LatLng(24.671118, 121.810599);
		// 宜蘭民宿水和風
		final static LatLng minsu39 = new LatLng(24.657548, 121.797738);
		// ~~~~~~~~~~~~
		// 蜜餞家族
		final static LatLng miFamily = new LatLng(24.661760, 121.621339);
		// 謝記鴨賞
		final static LatLng shieDuck = new LatLng(24.685139, 121.797348);
		// 諾貝爾奶凍捲(羅東)
		final static LatLng noButter1 = new LatLng(24.679544, 121.76823);
		// 諾貝爾奶凍捲(礁溪)
		final static LatLng noButter2 = new LatLng(24.819906, 121.769067);
		// 奕順軒 (宜蘭)
		final static LatLng yihSiiun1 = new LatLng(24.750963, 121.7507);
		// 奕順軒 (礁溪)
		final static LatLng yihSiiun2 = new LatLng(24.826956, 121.7719);
		// 奕順軒 (羅東)
		final static LatLng yihSiiun3 = new LatLng(24.676658, 121.765849);
		// 三合餅舖
		final static LatLng sunHe = new LatLng(24.753905, 121.744005);
		public static MarkerOptions makeSpotMark(LatLng la, String ti, String sn) {
			MarkerOptions markerOpt = new MarkerOptions()
					.position(la)
					.title(ti)
					.snippet(sn)
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.purplespot));
			return markerOpt;

		}

		public static MarkerOptions makeHotelMark(LatLng la, String ti, String sn) {
			MarkerOptions markerOpt = new MarkerOptions()
					.position(la)
					.title(ti)
					.snippet(sn)
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.purredspot));
			return markerOpt;

		}

		public static MarkerOptions makeFoodMark(LatLng la, String ti, String sn) {
			MarkerOptions markerOpt = new MarkerOptions()
					.position(la)
					.title(ti)
					.snippet(sn)
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.purplefood));
			return markerOpt;

		}

		public static MarkerOptions makeRedMark(LatLng la, String ti, String sn) {
			MarkerOptions markerOpt = new MarkerOptions().position(la).title(ti)
					.snippet(sn)
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.redspot));

			return markerOpt;

		}
		public static void sameDb(SQLiteUtil db2,List<Set<String>> ListViewData2){
			db=db2;
			ListViewData=ListViewData2;
		}
		public static MarkerOptions makeAllMark(LatLng la, String ti, String sn, int id) {
			boolean b = false;
			if(!(markMap.containsKey(ti))){
				MutiData md=new MutiData(ti,la);
				markMap.put(ti,md );
				
			}
			if (ListViewData.size() == 0) {
				if (id == 0) {

					return makeSpotMark(la, ti, sn);
				} else if (id == 1) {
					return makeHotelMark(la, ti, sn);
				} else {
					return makeFoodMark(la, ti, sn);
				}

			} else {
				//Set tpSet = new HashSet<String>();
				for (int i = 0; i < ListViewData.size(); i++) {
					Set	tpSet = ListViewData.get(i);
					if (tpSet.contains(ti)) {

						b = true;
						return makeRedMark(la, ti, sn);
					}

				}
				if (b == false) {
					if (id == 0) {

						return makeSpotMark(la, ti, sn);
					} else if (id == 1) {
						return makeHotelMark(la, ti, sn);
					} else {
						return makeFoodMark(la, ti, sn);
					}
				}
			}
			Log.e("ERROR!!!!", "SHIT!!!!!!!!!!!!!");
			return null;

			/*
			 * if (markMap.isEmpty() == false) { if (markMap.containsKey(ti)) {
			 * 
			 * return makeRedMark(la, ti, sn); } else {
			 * 
			 * 
			 * if (id == 0) {
			 * 
			 * return makeSpotMark(la, ti, sn); } else if (id == 1) { return
			 * makeHotelMark(la, ti, sn); } else { return makeFoodMark(la, ti, sn);
			 * } }
			 * 
			 * } else { /*Iterator it = tempSet.iterator(); while (it.hasNext()) {
			 * 
			 * String s = (String) it.next(); if (s.contains(ti)) { markMap.put(ti,
			 * mMap.addMarker(makeRedMark(la, ti, sn)));
			 * 
			 * //return(makeRedMark(la, ti, sn)); }
			 * 
			 * }//// if (id == 0) {
			 * 
			 * return makeSpotMark(la, ti, sn); } else if (id == 1) { return
			 * makeHotelMark(la, ti, sn); } else { return makeFoodMark(la, ti, sn);
			 * } }
			 */

		}


	public static void markHotel(GoogleMap mMap) {

		mMap.addMarker(makeAllMark(
				minsu01,
				"民宿:羅東我行我素 ",
				"我行我宿，每一夢鄉的著落\n電話：0977560562\n地址：宜蘭縣羅東鎮中山路三段羅東夜市旁\n網址：http://0977560562.yilantravel.net/",
				1));
		mMap.addMarker(makeAllMark(
				minsu02,
				"民宿:宜蘭羅東尚閤屋民宿 ",
				"電話：0975385255\n地址：宜蘭縣羅東鎮新群五路60巷3號\n網址：http://shanghewu.elandbnb.tw/index.htm",
				1));
		mMap.addMarker(makeAllMark(
				minsu03,
				"民宿:羅東民宿魚兒居 ",
				"喧囂城市中 難得的舒適安逸\n電話：0920362220\n地址：宜蘭縣羅東鎮公正路120號\n網址：http://fish.yilantravel.net/",
				1));
		mMap.addMarker(makeAllMark(
				minsu04,
				"民宿:羅東院子なかにわ ",
				"對望一整片的落地窗 就是另一種愜意的享受\n電話：0975620110\n地址：羅東鎮純精路二段\n網址：http://courtyard.yilantravel.net/index.html",
				1));
		mMap.addMarker(makeAllMark(
				minsu05,
				"民宿:羅東玩全夜市民宿 ",
				"電話：0920362220\n地址： 宜蘭縣羅東鎮公正路120號\n網址：http://www.playelan.com/play/",
				1));
		mMap.addMarker(makeAllMark(
				minsu06,
				"民宿:宜蘭羅東民宿夜市玩家 ",
				"\n電話：0920766517\n地址：宜蘭縣羅東鎮民生東路\n網址：http://nmplayer.eebnb.com/",
				1));
		mMap.addMarker(makeAllMark(
				minsu07,
				"民宿:羅東後站平價套房",
				"電話：0975206750\n地址：宜蘭縣羅東鎮中山路二段356號\n網址：http://house.ilantravel.com.tw/bnb/station.htm",
				1));
		mMap.addMarker(makeAllMark(
				minsu08,
				"民宿:新宿小棧 ",
				"背起簡單的行囊 戀上簡單的假期\n電話：0983169002\n地址：宜蘭縣五結鄉五結鄉五結路福興村11號\n網址：http://0983169002.goodoks.com/",
				1));
		mMap.addMarker(makeAllMark(
				minsu09,
				"民宿:閣子趣 ",
				"欣賞滿天星斗 與家人愜意的喝著咖啡\n電話：0920606770\n地址：宜蘭縣羅東鎮中山路一段137號B棟2F-3\n網址：http://www.playelan.com/geziqu/",
				1));
		mMap.addMarker(makeAllMark(
				minsu10,
				"民宿:羅東夜市羅東棧 ",
				"摩登典雅 優雅品味\n電話：0975757185\n地址：宜蘭縣羅東鎮站前路50號\n網址：http://ldst.goinn.tw/",
				1));
		mMap.addMarker(makeAllMark(
				minsu20,
				"民宿:礁溪松田民宿 ",
				"在自然氣息環繞下 洗去城市的喧囂\n電話：0936577738\n地址：宜蘭縣礁溪鄉份尾一路118號\n網址：http://house.ilantravel.com.tw/bnb/songtian.htm",
				1));
		mMap.addMarker(makeAllMark(
				minsu21,
				"民宿:礁溪320 ",
				"天然溫泉 頂級泡湯享受\n電話：0921547995\n地址：宜蘭縣礁溪鄉德陽路\n網址：http://320.yilantravel.net/",
				1));
		mMap.addMarker(makeAllMark(
				minsu22,
				"民宿:QQ的家民宿 ",
				"享受溫泉泡湯美景大自然\n電話：0913417088\n地址：宜蘭縣礁溪鄉健康路(緊臨長榮鳯凰酒店斜對面)\n網址：http://www.phoenixvillage.idv.tw/article/article.php",
				1));
		mMap.addMarker(makeAllMark(
				minsu23,
				"民宿:礁溪湯泉之家 ",
				"電話：0952263128\n地址：宜蘭縣礁溪鄉信義路34巷16號\n網址：http://tungchung.goinn.tw/",
				1));
		mMap.addMarker(makeAllMark(
				minsu24,
				"民宿:雲湘居渡假民宿 ",
				"朝望龜山日出金霞燦爛 夕看彩雲青山餘暉\n電話：0912262172\n地址：宜蘭縣礁溪鄉白雲三路72號\n網址：http://www.jiaoxibnb.tw/index.html  ",
				1));
		mMap.addMarker(makeAllMark(
				minsu25,
				"民宿:泉鄉雅舍 ",
				"溫泉的故鄉\n電話：0981031889\n地址：宜蘭縣礁溪鄉信義路40號\n網址：http://hsc.elandbnb.tw/",
				1));
		mMap.addMarker(makeAllMark(
				minsu26,
				"民宿:閃亮の泉 ",
				"電話：0983981556\n地址：宜蘭縣礁溪鄉健康路50號\n網址：http://shining.goinn.tw/p1.asp",
				1));
		mMap.addMarker(makeAllMark(
				minsu27,
				"民宿:宜蘭民宿礁溪艾瑪溫泉渡假行館 ",
				"純樸自在的鄉野情懷，舒適放鬆的樂活旅程\n電話：0980610668\n地址：宜蘭縣礁溪鄉健康路50號\n網址：http://shining.goinn.tw/p1.asp",
				1));
		mMap.addMarker(makeAllMark(
				minsu28,
				"民宿:礁溪伊豆民宿",
				"歡迎您光臨伊豆，來趟慢活溫泉之旅\n電話：0963328668\n地址：宜蘭縣礁溪鄉德陽村奇立丹路195巷33號\n網址：http://edo.yilantravel.net/",
				1));
		mMap.addMarker(makeAllMark(
				minsu29,
				"民宿:山璞民宿 ",
				"\n電話：0910359511\n地址：宜蘭縣礁溪鄉信義路42號\n網址：http://keli.yilanhomestay.tw/ ",
				1));
		mMap.addMarker(makeAllMark(
				minsu30,
				"民宿:卡松安",
				"微暖的氣息　成就愛與美的蔓延\n電話:0933103789\n地址:宜蘭縣冬山鄉安中路36號\n網址:http://ksa.oks.tw/",
				1));
		mMap.addMarker(makeAllMark(
				minsu31,
				"民宿:宜和民宿",
				"輕鬆享受休閒與寧靜的鄉村生活\n電話:0932193373\n地址:宜蘭縣冬山鄉武淵村武淵一路41號\n網址:http://yihe.yilantravel.net/index.html",
				1));
		mMap.addMarker(makeAllMark(
				minsu32,
				"民宿:親水居",
				"體驗不同於城市的輕鬆與愜意\n電話:0937964941 \n地址:宜蘭縣冬山鄉武淵二路55號\n網址:http://qinshuiju.yilantravel.net/",
				1));
		mMap.addMarker(makeAllMark(
				minsu33,
				"民宿:幸福在角落",
				"\n電話:0987954177\n地址:宜蘭縣冬山鄉武淵村三堵三路26號\n網址:http://antigue.ilanbnb.tw/ ",
				1));
		mMap.addMarker(makeAllMark(
				minsu34,
				"民宿:蘭。童話冬山河民宿",
				"童玩節步行約1分鐘！\n電話:0975061671\n地址:宜蘭縣五結鄉親河路二段141號\n網址:http://stories.iibnb.com/",
				1));
		mMap.addMarker(makeAllMark(
				minsu35,
				"民宿:第二個家",
				"平原的風　體驗這慢活的假期\n電話:0932191025\n地址:宜蘭縣冬山鄉鹿安路222號\n網址:http://secondhome.yilantravel.net/index.html",
				1));
		mMap.addMarker(makeAllMark(
				minsu36,
				"民宿:伊莎城堡",
				"異國風情的獨特美感\n電話:0918108528\n地址:宜蘭縣冬山鄉柯林一路383號\n網址:http://www.isabnb.tw/",
				1));
		mMap.addMarker(makeAllMark(
				minsu37,
				"民宿:幸福柚子鄉村民宿",
				"在鄉村果園體驗農村生活\n電話:0935629130\n地址:宜蘭縣冬山鄉中山六路96號\n網址:http://www.playelan.com/pomelo/",
				1));
		mMap.addMarker(makeAllMark(
				minsu38,
				"民宿:花間雅舍　蓮春園",
				"\n電話:0932224669\n地址:宜蘭縣冬山鄉東八路123號\n網址:http://www.springvilla.com.tw/",
				1));
		mMap.addMarker(makeAllMark(
				minsu39,
				"民宿:宜蘭民宿水和風",
				"樂活，慢活。是一種日式風格\n電話:0927969501\n地址:宜蘭縣冬山鄉富農路二段182號\n網址:http://www.waterwind.tw/",
				1));
	}

	public static void markFood(GoogleMap mMap) {
		mMap.addMarker(makeAllMark(
				miFamily,
				"手信:蜜餞家族",
				"店內的蜜餞都是採用新鮮台灣珍果製作，保證讓消費者能夠食在「安心與放心」\n電話：039895371\n地址:宜蘭縣三星鄉天山村三星路7段307號（味珍香卜肉店旁）\n網址:http://039895371.tranews.com/",
				2));
		mMap.addMarker(makeAllMark(
				shieDuck,
				"手信:謝記鴨賞",
				"採傳統口味〝甘蔗頭〞燻煙，配合現代人講求低鹽、低糖健康觀念\n電話:039504628\n地址:宜蘭縣五結鄉五結路二段367號\n網址:http://www.039504646.com/",
				2));
		mMap.addMarker(makeAllMark(
				noButter1,
				"手信:諾貝爾奶凍捲(羅東店)",
				"將蘭陽平原，藍天、綠地、紅橋、銀河及白山嵐的美景特色，設計於外包裝上，也具代表著奶凍達人在故鄉宜蘭的象徵。\n電話:039558389\n地址:宜蘭縣羅東鎮公正路188號\n網址:http://www.pieart.com.tw/",
				2));
		mMap.addMarker(makeAllMark(
				noButter2,
				"手信:諾貝爾奶凍捲(礁溪店)",
				"將蘭陽平原，藍天、綠地、紅橋、銀河及白山嵐的美景特色，設計於外包裝上，也具代表著奶凍達人在故鄉宜蘭的象徵。\n電話:039885919:\n地址:宜蘭縣礁溪鄉礁溪路四段68號\n網址:http://www.pieart.com.tw/",
				2));
		mMap.addMarker(makeAllMark(
				yihSiiun1,
				"手信:奕順軒(宜蘭)",
				"宜蘭餅，好餅盡在奕順軒\n電話:039334536\n地址:宜蘭縣宜蘭市神農路2段17號\n網址:http://www.pon.com.tw/homepage.html",
				2));
		mMap.addMarker(makeAllMark(
				yihSiiun2,
				"手信:奕順軒(礁溪)",
				"宜蘭餅，好餅盡在奕順軒\n電話:039334536\n地址:宜蘭縣礁溪鄉礁溪路5段96號\n網址:http://www.pon.com.tw/homepage.html",
				2));
		mMap.addMarker(makeAllMark(
				yihSiiun3,
				"手信:奕順軒(羅東)",
				"宜蘭餅，好餅盡在奕順軒\n電話:039334536\n地址:宜蘭縣羅東鎮民權路160號\n網址:http://www.pon.com.tw/homepage.html",
				2));
		mMap.addMarker(makeAllMark(
				sunHe,
				"手信:三合餅舖",
				"四十年巷子內小小招牌，秉持著用心，每一道程序都是熱誠與堅持，實在的餡料與原料保留那份濃郁古早味。\n電話:0922923266\n地址:宜蘭市泰山路95巷2-4號\n網址:http://sun-he.com.tw/shopping/news.php",
				2));
	}

	
	
	public static void markSpot(GoogleMap mMap) {

		mMap.addMarker(makeAllMark(
				waterPark,
				"景點:冬山河親水公園",
				"以「水」本身規劃成不同的利用方式，而達 到觀光、休閒、遊憩與教育的不同目的。\n地址:宜蘭縣五結鄉親河路二段2號\n網址:http://www.ctnet.com.tw/scenery/images/dongshan/index.htm",
				0));
		mMap.addMarker(makeAllMark(
				leisureFarm,
				"景點:香格里拉休閒農場",
				"以鄉土餐飲、品茗、住宿渡假全方位的休閒深得你心。\n地址：宜蘭縣冬山鄉大進村梅山路168號\n網址:http://www.shangrilas.com.tw/indexa.php",
				0));
		mMap.addMarker(makeAllMark(
				ecoMuseum,
				"景點:蘭陽博物館",
				"樓層主要分為四層樓設計─分為山、海、平原，以展現宜蘭的地理及人文環境。\n地址: 宜蘭縣頭城鎮青雲路三段750號 \n網址:http://www.lym.gov.tw/",
				0));
		mMap.addMarker(makeAllMark(
				whaleDock,
				"景點:烏石港",
				"烏石港漁會大樓旁設有漁貨直銷中心，各式產品攤位琳瑯滿目，不怕找不到好吃的料理！\n地址: 宜蘭縣頭城鎮港口里港口路\n網址:http://140.111.74.53/tcesdoc/WuShiPort/index.html",
				0));
		mMap.addMarker(makeAllMark(
				jimmySquare,
				"景點:宜蘭幾米廣場",
				"廣場以「記憶片刻風景」為主題，現場的裝置藝術全取自知名繪本作家「幾米」的作品。\n地址: 宜蘭縣宜蘭市和睦里光復路1號\n網址:http://www.lanyangnet.com.tw/ilpoint/il17/",
				0));
		mMap.addMarker(makeAllMark(
				artCenter,
				"景點:傳統藝術中心",
				"各地藝文傳統風貌建立台灣文化的代表精神\n地址: 宜蘭縣五結鄉季新村五濱路二段201號\n網址:http://www.ncfta.gov.tw/ncfta_ce/main/index.aspx",
				0));
		mMap.addMarker(makeAllMark(
				baimiVillage,
				"景點:白米木屐村",
				"將生活用品轉換成手工藝品，使木屐不僅能穿在腳上，還能拿來彩繪、裝飾、欣賞\n地址: 宜蘭縣蘇澳鎮永春路174號\n網址:http://www.baimi.org.tw",
				0));
		mMap.addMarker(makeAllMark(
				giantLake,
				"景點:大湖風景區",
				"湖泊形狀似翱翔的天鵝，因此又稱為天鵝湖，湖岸周圍景色清新秀麗\n地址: 宜蘭縣員山鄉湖北村湖前路185號\n網址:http://www.dah-hu.com.tw/index.asp",
				0));
		mMap.addMarker(makeAllMark(
				divineTree,
				"景點:棲蘭神木園區",
				"株株昂然盤結於大地，巨幹扶疏，濃蔭蔽地\n地址: 宜蘭縣大同鄉太平村泰雅路四段6號\n網址:http://www.yeze.com.tw/cilan/news/news_index.asp",
				0));
		mMap.addMarker(makeAllMark(
				cannonMount,
				"景點:砲台山",
				"登臨砲台山，蘇澳、北方澳、南方澳風光美景盡收眼底。\n地址: 宜蘭縣蘇澳鎮長安里(蘇花公路起點旁)\n網址:http://www.lanyangnet.com.tw/ilpoint/su06/index.asp?url=link1.htm",
				0));
		mMap.addMarker(makeAllMark(
				wineVillage,
				"景點:宜蘭藏酒酒莊",
				"可以賞景、品酒、嚐鮮又採果、釀酒還能夠DIY，是第一家以綠建築概念為主軸的酒莊，酒莊內有溪流貫穿其中，得天獨厚的自然湧泉水質甘美。\n地址：宜蘭縣頭城鎮更新路126-50號\n網址:http://www.cjwine.com/",
				0));
		mMap.addMarker(makeAllMark(
				tanPark,
				"景點:礁溪湯圍溝公園",
				"室內裸湯售票處，全票80元、半票40元(65歲以上民眾) 、優待票60元(在職軍公教及110公分以上學生)還有免錢的泡腳樂就在室外。\n地址：宜蘭縣礁溪鄉德陽路99-11號\n網址:http://scenery.e-land.gov.tw/releaseRedirect.do?unitID=123&pageID=6662",
				0));
		mMap.addMarker(makeAllMark(
				lotungTree,
				"景點:羅東林業園區",
				"規劃有自然生態池、水生植物池、水生植物展示區、運材蒸汽火車頭展示區、森林鐵路、臨水木棧道等設施。\n地址：宜蘭縣羅東鎮中正北路118號\n網址:http://luodong.forest.gov.tw/",
				0));
		mMap.addMarker(makeAllMark(
				earthHot,
				"景點:清水地熱",
				"上山之前可以先買好蛋、玉米、茭白筍、地瓜、芋頭、馬鈴薯、栗子等等和網子，不然會比平地買在貴一些。１７點就準時關水清洗蛋池。\n地址：宜蘭縣大同鄉三星路8段501巷139號\n網址:http://www.lanyangnet.com.tw/ilpoint/dt10/",
				0));
		mMap.addMarker(makeAllMark(
				veryMountain,
				"景點:太平山",
				"如果去的時候是冬季，那麼從太平山下來時，可以到鳩之澤溫泉來泡溫泉，非常的舒服和愜意歐~\n地址：宜蘭縣大同鄉太平村58-1號\n網址:http://tps.forest.gov.tw/",
				0));
		mMap.addMarker(makeAllMark(happinessFarm, "景點:幸福20號農場",
				"幸福從這裡開始\n地址: 宜蘭縣冬山鄉大進村大進路446巷20號\n網址:http://www.20happy.com/",
				0));
		mMap.addMarker(makeAllMark(streamBridge, "景點:寒溪吊橋",
				"繁星與燈火相呼應，更令人心曠神怡\n地址:宜蘭縣大同鄉寒溪村境內(寒溪巷與華興巷之間)\n網址:暫無網址", 0));
		mMap.addMarker(makeAllMark(
				blossomLake,
				"景點:梅花湖",
				"值得尋芳的世外桃源\n地址:宜蘭縣冬山鄉大埤路1號\n網址:http://scenery.e-land.gov.tw/releaseRedirect.do?unitID=123&pageID=6669",
				0));
		mMap.addMarker(makeAllMark(
				waterFall,
				"景點:新寮瀑布",
				"風景美、路程短，更親近大自然\n地址:宜蘭縣冬山鄉中山村新寮二路盡頭\n網址:http://www.lanyangnet.com.tw/ilpoint/ds07/",
				0));
		mMap.addMarker(makeAllMark(
				nightMarket,
				"景點:羅東觀光夜市",
				"來宜蘭不可不來的夜市\n地址:宜蘭縣羅東鎮\n網址:http://www.lotong.gov.tw/TW/default.asp?PageId=F_M05_01_04L",
				0));

	}
	public static class MutiData {

		public String ti;

		public LatLng lat;
		public MutiData(String ti,LatLng lat){
			this.ti=ti;
			this.lat=lat;
		}

		
	}

}
