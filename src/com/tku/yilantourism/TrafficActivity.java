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
	String[] busArray = { "�����ȹB", "�������ȹB", "����ȹB" };
	String[] railArray = { "�۫�����", "�j������", "�~�D����", "�G�˨���", "�y������", "ù�F����",
			"�V�s����" };
	String sourR = "��ƨӷ�:�H�N��(akao_chen),����L��,�l�Ȩ� (�y���})(����̥��J )";
	String sourB = "��ƨӷ�:�U�a�ȹB";
	String[] busContentArray = { "�����ȹB", "�������ȹB", "����ȹB" };
	String[] railContentArray = { "�۫�����", "�j������", "�~�D����", "�G�˨���", "�y������",
			"ù�F����", "�V�s����" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_traffic);
		busContentArray[0] = "�@�B�����ȹB(������B������)\n1.�y����\n��}:�y�����ժٸ�190��\n�q��:03-937-3600\n���I:�X�̼s��\n2.ù�F��\n��}:ù�F��������T�q229��\n�q��:03-955-6585\n���I: ù�F�B�ʤ���B�y�A�����B�L������B�L����ü\n3.�G�˯�\n��}:�G�˶m�G�˸�5�q70��\n�q��:03-988-0700\n���I:���򷾤���";
		busContentArray[1] = "�G�B�������ȹB(�x�_��B���B�O����B���B��ޤj�ӯ��B�U�ب�  ���ҥi�{��)\n1.�y����B��\n�a�}�G�y�����ժٸ�190�� ��5��6��x\n�q��: 03-938-5655\n���I:�X�̼s��\n2.ù�F��B��\n�a�}:�y����ù�F��������T�q229��\n�q��:03-956-6198\n���I: ù�F�B�ʤ���B�y�A�����B�L������B�L����ü\n3.�G����B��\n�a�}:�y�����G�˶m�G�˸�5�q150��\n�q��: 03-987-6739\n���I:���򷾤���";
		busContentArray[2] = "�T�B����ȹB(�x�_�����B�򶩨���)\n1.�y������\n�a�}:�y�����ժٸ�190��\n�q��:03-936-5441\n���I:�X�̼s��\n2.ù�F����\n�a�}:�y����ù�F���F��23��2��\n�q��:03-954-2703�B03-956-7505�B03-954-2054\n���I: ù�F�B�ʤ���B�y�A�����B�L������B�L����ü";
		railContentArray[0] = "1.�۫����� (only �϶������a)\n�y���u�Ĥ@���A����Y���C\n�u���K�D���i�H��F�p�����C\n�i�H�����ˡB�Q���������˨��b�K�D�W�Y�୷���A�[��j���B�t�s�q�C\n�A�X���w�۵M���[�B��Ӫ��C�ȡC";
		railContentArray[1] = "2.�j������ (only �϶������a)\n�j���Ѥ��q�B�j���A���X�j�۵M�P�ǲΪ^��	�C\n�X����~���|���@�Ӭݱo������g�L��m�A�i�H�P������ӡC\n�A�X���w�۵M���[�B��Ӫ��C�ȡC";
		railContentArray[2] = "3.�~�D����  (only �϶������a)\n�X���ߨ�ݨ�ӥ��v���t�s�q�����C\n��V�خ�������A�i�����i�J�F�y�C��source\n�𶢬��ʦ��Į��B�����ʡC\n�A�X���w�۵M���[�B��E�B��Ӫ��C�ȡC";
		railContentArray[3] = "4.�G�˨���\n�Ŭu���]�B���J�E���a�C\n�G�˴��򷾤���B���p�X�����ϡB�]���j�D�B�G�˨�Ѽq�B�G�˲��h���S������C\n�A�X���w�w���B�۵M���[���C�ȡC";
		railContentArray[4] = "5.�y������\n���y�����A���@���j���C\n�®��d�@���](�G��ѡB�w���i��)�B�ʪG����j��(�@�ءB����F�s)�B�X�̼s���B�y���F���]���C\n�A�X���n����i�����C�ȡC";
		railContentArray[5] = "6.ù�F����\nù�F�L�~��ƶ�ϡBù�F�]���Bù�F�B�ʤ���B�ǲ����N���ߡC\n�A�X���w�����B��~���ʪ��C�ȡC";
		railContentArray[6] = "7.�V�s����\n���������N�O�@�Ӵ��I�A�O�y���̬��R���������A�K�D�ĥΰ��[���B�f�t�y�u�Ϊ������ا��Φ̥զ�B�סA���e�÷P�C\n�q��x�Y�i�ݨ�@�j�����_�СA�_�и��٦�ߵ۹s�P���_��H�A�����H�ۥ|�u�e�{���P�������C\n�A�X���w�ж鴺�[�B�S��ؿv���C�ȡC";

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
						.setTitle("�п��")
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
						.setTitle("�п��")
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
