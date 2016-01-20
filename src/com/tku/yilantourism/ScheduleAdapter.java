package com.tku.yilantourism;
    import java.util.ArrayList;
import java.util.List;  
import android.content.Context;  
import android.graphics.Color;  
import android.util.Log;
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;  
import android.widget.ImageView;
import android.widget.TextView;  
      
    public class ScheduleAdapter extends BaseAdapter {  
      
        private Context context;  
      
        //private List<Process> list;  
        private ArrayList<Process> mShowCodeData = new ArrayList<Process>();

    //    private ArrayList<Process> mShowNameData = new ArrayList<Process>();

        private Context mContext;

       // private LayoutInflater mInflater;
       // public ScheduleAdapter(Context context, List<Process> list) {  
        public ScheduleAdapter(Context context) {  
      
            this.context = context;  
           // this.list = list;  
         //   mInflater = LayoutInflater.from(mContext);
        }  
        

      
      
        @Override  
        public int getCount() {  
            ///return list.size();
        	 return mShowCodeData.size();
        }  
        @Override
        public Process getItem(int position) {
            // TODO Auto-generated method stub
            return new Process( mShowCodeData.get(position));
        }
        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }
       /* @Override  
        public Object getItem(int position) {  
      
            return list.get(position);  
        }  
      
        @Override  
        public long getItemId(int position) {  
            return position;  
        }  */
      
        @Override  
        public View getView(int position, View convertView, ViewGroup viewGroup) {  
        	final int pos = position;
              
            ViewHolder holder;  
            if (convertView==null) {  
            	//convertView = mInflater.inflate(R.layout.dzh_delete_win_item, null);
            	 convertView=LayoutInflater.from(context).inflate(R.layout.dzh_delete_win_item, null);  
                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.dzh_delete_item_code);
     
                holder.move = (ImageView) convertView.findViewById(R.id.move_item);
       
                convertView.setTag(holder);
            	/*
                convertView=LayoutInflater.from(context).inflate(R.layout.schehelper, null);  
                holder=new ViewHolder();  
                  
                convertView.setTag(holder);  
                  
                holder.groupItem=(TextView) convertView.findViewById(R.id.list_sctext);  
                  */
            }  
            else{  
                holder=(ViewHolder) convertView.getTag();  
            }  
            Process itemCodeName = mShowCodeData.get(position);
        //    Process itemSymbolName = mShowNameData.get(position);
            if (itemCodeName.equals("")) {
               // holder.move.setBackgroundColor(ScheduleActivty.this.getResources().getColor(R.color.touming_color));
              //  holder.delete.setBackgroundColor(EditMyStockView.this.getResources().getColor(R.color.touming_color));
            }else{
                holder.move.setBackgroundResource(R.drawable.edit_move);
                
            }
            if (itemCodeName != null) {
               
                String tps="";
                tps="地點:"+itemCodeName.site+"\n";
                tps=tps+"時間:"+itemCodeName.time+"\n";
                tps=tps+"預算:"+itemCodeName.money;
                
                holder.code.setText(tps);
            
            }
            /*holder.groupItem.setTextColor(Color.BLACK); 
            String tps="";
            tps="地點:"+list.get(position).site+"\n";
            tps=tps+"時間:"+list.get(position).time+"\n";
            tps=tps+"預算:"+list.get(position).money;
            
            
            holder.groupItem.setText(tps);  */
              
            return convertView;  
        }  
      
       /* static class ViewHolder {  
            TextView groupItem;  
        }  */
        public class ViewHolder {
            TextView code;

            TextView name;

            ImageView move;

            ImageView delete;

        }
        public void up(int position) {
            if (position == 0)
                return;
            Process codeUpData = mShowCodeData.get(position);
            Process codeDownData = mShowCodeData.get(position - 1);
            mShowCodeData.set(position, codeDownData);
            mShowCodeData.set(position - 1, codeUpData);
     /*   /    Process nameUpData = mShowNameData.get(position);
          /  Process nameDownData = mShowNameData.get(position - 1);
            mShowNameData.set(position, nameDownData);
            mShowNameData.set(position - 1, nameUpData);*/
            this.notifyDataSetChanged();
        }

        public void down(int position) {
            if (position == mShowCodeData.size() - 1)
                return;
            Process codeDownData = mShowCodeData.get(position);
            Process codeUpData = mShowCodeData.get(position + 1);
            mShowCodeData.set(position, codeUpData);
            mShowCodeData.set(position + 1, codeDownData);
      /*      Process nameDownData = mShowNameData.get(position);
            Process nameUpData = mShowNameData.get(position + 1);
            mShowNameData.set(position, nameUpData);
            mShowNameData.set(position + 1, nameDownData);*/
            this.notifyDataSetChanged();
        }

        public void deleteItem(int position) {
            if (position > mShowCodeData.size() - 1)
                return;
            mShowCodeData.remove(position);
          //  mShowNameData.remove(position);
            this.notifyDataSetChanged();
        }

        public void deleteAll() {
            mShowCodeData.clear();
          //  mShowNameData.clear();
            this.notifyDataSetChanged();
        }

        public void updateData(ArrayList<Process> codeData) {
            mShowCodeData = codeData;
          //  mShowNameData = nameData;
            this.notifyDataSetChanged();
        }

        public void remove(Process moveitem) {
            mShowCodeData.remove(moveitem);
         //   mShowNameData.remove(dragItem[1]);
        
            Log.e("remove",moveitem.site);
            this.notifyDataSetChanged();
        }

        public void insert(Process moveitem, int index) {
            mShowCodeData.add(index, moveitem);
            Log.e("insert",String.valueOf(index));
            Log.e("insert",moveitem.site);
         //   mShowNameData.add(index, mReplaceString[1]);
            this.notifyDataSetChanged();
        }
  

      
    } 
    
    class Process{
    	String time,money,site;
    	public Process(){};
    	
    	public Process(String time,String money,String site){
    		this.money=money;
    		this.time=time;
    		this.site=site;
    	};
    	public Process(Process pro){
    		this.money=pro.money;
    		this.time=pro.time;
    		this.site=pro.site;
    	};
    }