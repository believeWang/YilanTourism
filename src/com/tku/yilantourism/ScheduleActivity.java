package com.tku.yilantourism;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import com.tku.yilantourism.MarkerGenerator.MutiData;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ScheduleActivity extends Activity {
	private PopupWindow popupWindow;
	private View view;
	String time = "", money = "", site = "";
	ListView lt_group;
	ArrayList<Process> list;
	ScheduleAdapter groupAdapter;
	Context context;
	View realView;
	int poss;	
	EditText editTime;
	EditText editMoney;
	EditText editSite;
	Button yes;
	protected SQLiteUtil db = null;
    private DragListView mShowAll;



    //private ArrayList<Process> mCodeData = new ArrayList<Process>();

  //  private ArrayList<Process> mNameData = new ArrayList<Process>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
		
		lt_group = (ListView) this.findViewById(R.id.listView1);
		list = new ArrayList<Process>();
	
		
		Button show = (Button) this.findViewById(R.id.button_actsch);
		LayoutInflater layoutInflater2 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		realView = layoutInflater2.inflate(R.layout.activity_schedule, null);
		db = new SQLiteUtil(ScheduleActivity.this);
		//list=db.SelectAll3(SQLiteUtil.TABLE_NAME3);
		if(getIntent().getStringExtra("title")!=null){
			Log.e("fromMap", getIntent().getStringExtra("title"));
			Process fromMap =new Process("","",getIntent().getStringExtra("title"));
			list.add(fromMap);
		}
		
		if(getIntent().getStringExtra("num")!=null){
			Log.e("fromMap", getIntent().getStringExtra("num"));
			int cnt=Integer.parseInt(getIntent().getStringExtra("num"));
			for(int i=0;i<cnt;i++){
				Log.e("fromMap2", getIntent().getStringExtra("title"+i));
				Process fromMap2 =new Process("","",getIntent().getStringExtra("title"+i));
				list.add(fromMap2);
			}
			
			//Log.e("fromMap", getIntent().getStringExtra("title"));
			
			
		}
		show.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				yes.setText("加入");
				showWindow1();
			}

		});
		Button cl = (Button) this.findViewById(R.id.button1_actsch);
		cl.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(ScheduleActivity.this)
						.setTitle("清空行程")
						.setMessage("你確定要清空？")
						.setPositiveButton("是",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										list.clear();
										lt_group.setAdapter(groupAdapter);
										/*
										 * Map<String, String> map =
										 * ListViewData.get(pos);
										 * db.Delete(SQLiteUtil.TABLE_NAME,
										 * "id="+String.valueOf(map.get("id")));
										 * ListViewData.remove(pos);
										 * gridview.setAdapter(adapter);
										 */
									}
								})
						.setNegativeButton("否",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
									}
								}).show();

			}

		});
		// LayoutInflater layoutInflater = (LayoutInflater)
		// getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		view = layoutInflater2.inflate(R.layout.schedulehelper, null);
		editTime = (EditText) view.findViewById(R.id.EditText1_helper);
		editMoney = (EditText) view.findViewById(R.id.editText2_helper);
		editSite = (EditText) view.findViewById(R.id.editText3_helper);
		yes = (Button) view.findViewById(R.id.button1_helper);
		context = this;
		groupAdapter = new ScheduleAdapter(this);
		groupAdapter.updateData(list);
		lt_group.setAdapter(groupAdapter);
		lt_group.setOnItemClickListener(linster);
		lt_group.setOnItemLongClickListener(longlinster);
	}

	OnItemClickListener linster = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			poss = position;
			Process pro2 = list.get(position);
			editTime.setText(pro2.time);
			editSite.setText(pro2.site);
			editMoney.setText(pro2.money);

			yes.setText("編輯");

			showWindow1();

			// list.set(position, pro2);
			// lt_group.setAdapter(groupAdapter);

		}
	};
	OnItemLongClickListener longlinster = new OnItemLongClickListener() {
		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			final int pos = position;

			new AlertDialog.Builder(ScheduleActivity.this)
					.setTitle("刪除行程")
					.setMessage("你確定要刪除？")
					.setPositiveButton("是",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									list.remove(pos);
									lt_group.setAdapter(groupAdapter);
									/*
									 * Map<String, String> map =
									 * ListViewData.get(pos);
									 * db.Delete(SQLiteUtil.TABLE_NAME,
									 * "id="+String.valueOf(map.get("id")));
									 * ListViewData.remove(pos);
									 * gridview.setAdapter(adapter);
									 */
								}
							})
					.setNegativeButton("否",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
								}
							}).show();
			return true;
		}
	};

	@SuppressWarnings("deprecation")
	private void showWindow1() {
		

		if (popupWindow == null) {

			// editTime.setText(inputTime);
			editTime.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2,
						int arg3) {
					// When user changed the Text

					

				}

				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1,
						int arg2, int arg3) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub
					time = String.valueOf(arg0);
				}
			});

			// editMoney.setText(inputMoney);
			editMoney.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2,
						int arg3) {
					// When user changed the Text

				}

				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1,
						int arg2, int arg3) {
					// TODO Auto-generated method stub

				}

				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub
					money = String.valueOf(arg0);
				}
			});

			// editSite.setText(inputSite);
			editSite.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2,
						int arg3) {
					// When user changed the Text

				}

				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1,
						int arg2, int arg3) {
					// TODO Auto-generated method stub

				}

				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub
					site = String.valueOf(arg0);

				}
			});

			/*
			 * TextView test = (TextView) view.findViewById(R.id.text_sch);
			 * test.setText("YOOOOOOOOOOoo");
			 */

			// 创建一个PopuWidow对象
			popupWindow = new PopupWindow(view, LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT);
			// Log.e("coder", "xPos:");
			Button cancel = (Button) view.findViewById(R.id.Button01_helper);
			cancel.setOnClickListener(new Button.OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					editTime.setText("");
					editSite.setText("");
					editMoney.setText("");
					popupWindow.dismiss();
				}

			});

			yes = (Button) view.findViewById(R.id.button1_helper);

			yes.setOnClickListener(new Button.OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub

					Process prowindow = new Process();
					prowindow.money = editMoney.getText().toString();
					prowindow.site = editSite.getText().toString();
					prowindow.time = editTime.getText().toString();

					
					if (yes.getText().equals("編輯")) {
						list.set(poss, prowindow);
						
					} else {
						
						list.add(prowindow);
					}

					lt_group.setAdapter(groupAdapter);
					editTime.setText("");
					editSite.setText("");
					editMoney.setText("");

					popupWindow.dismiss();

				}

			});

		}

		// 使其聚集
		popupWindow.setFocusable(true);
		// 设置允许在外点击消失
		popupWindow.setOutsideTouchable(true);

		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		// 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
		int xPos = windowManager.getDefaultDisplay().getWidth() / 2
				- popupWindow.getWidth() / 2;
		popupWindow
				.setHeight(windowManager.getDefaultDisplay().getHeight() / 2);
		// popupWindow.showAsDropDown(realView, xPos, 0);
		popupWindow.showAtLocation(realView, Gravity.CENTER, 0, 0);
		// mPopupWindow.showAtLocation(findViewById(R.id.main), Gravity.CENTER,
		// 0, 0);
		/*
		 * if (popupWindow != null) { popupWindow.dismiss(); }
		 */}

	
	@Override
	protected void onStop() {
		  db.reBuild("SQLiteUtil3");
			for(int i=0;i<list.size();i++){
				Process tsp =list.get(i);
				ContentValues cv = new ContentValues();
				cv.put(SQLiteUtil.COLUMN_NAMEs3[0], tsp.site);
				cv.put(SQLiteUtil.COLUMN_NAMEs3[1], tsp.time);
				cv.put(SQLiteUtil.COLUMN_NAMEs3[2], tsp.money);

				db.Insert(SQLiteUtil.TABLE_NAME3, cv);
				
			}
		

		super.onStop();

	}

}
/*
 * private void pop() { Context mContext = ScheduleActivity.this; LayoutInflater
 * mLayoutInflater = (LayoutInflater)
 * mContext.getSystemService(LAYOUT_INFLATER_SERVICE); View v_pop =
 * mLayoutInflater.inflate(R.layout.schedulehelper, null); PopupWindow popWindow
 * = new PopupWindow(v_pop, 400,600,this); popOlaWindow.setFocusable(true);
 * popOlaWindow.setBackgroundDrawable(new BitmapDrawable());
 * popOlaWindow.setSoftInputMode
 * (WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
 * popOlaWindow.setContentView(v_pop); popOlaWindow.update();
 * popOlaWindow.showAtLocation(findViewById(R.id.activityRoot),
 * Gravity.LEFT|Gravity.TOP, 10, 70); }
 */
