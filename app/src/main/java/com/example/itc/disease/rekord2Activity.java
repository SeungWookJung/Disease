package com.example.itc.disease;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.itc.disease.db.DB2;

import java.util.ArrayList;

public class rekord2Activity extends AppCompatActivity {
    ImageButton Back_button;
    Button add_button;
    Button delete_button;
    ListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekord2);




        final DB2 dbHelper = new DB2(getApplicationContext(), "User.db", null, 1);
        final ListView listview;



        Back_button = (ImageButton) findViewById(R.id.back_button);
        Back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rekord2Activity.this, ChoiceActivity.class);
                startActivity(intent);


            }
        });
        add_button = (Button)findViewById(R.id.add);
        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(rekord2Activity.this,rekordActivity.class);

                startActivity(intent);

            }


        });




        // Adapter 생성
        adapter = new ListViewAdapter(this);

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listView2);
        listview.setAdapter(adapter);





        Cursor cursor3 = dbHelper.getResult3();
        try {
            if (cursor3.moveToFirst()) {
                do {
                    String idx = cursor3.getString(0);
                    String select_name = cursor3.getString(1);
                    String date = cursor3.getString(2);
                    adapter.addItem(select_name,idx, date);

                    Log.i("test",idx +  " : " + select_name + " : " + date);
                } while (cursor3.moveToNext());
            }
        } catch (Exception e) {
//            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor3 != null && !cursor3.isClosed()) {
                cursor3.close();
            }
        }
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int check_position = listview.getCheckedItemPosition();   //리스트뷰의 포지션을 가져옴.
                dbHelper.delete();
                adapter.listViewItemList2.remove(check_position);
                adapter.notifyDataSetChanged();

            }
        });

    }






    class ListViewAdapter  extends BaseAdapter {
        private Context context;
        private ArrayList<ListViewItem2> listViewItemList2 = new ArrayList<ListViewItem2>() ;





        public ListViewAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return listViewItemList2.size();
        }

        @Override
        public Object getItem(int position) {
            return listViewItemList2.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // "listview_item" Layout을 inflate하여 convertView 참조 획득.
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_item2, parent, false);
            }


            // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
            TextView titleTextView = (TextView) convertView.findViewById(R.id.textView2);
            TextView dateTextView = (TextView) convertView.findViewById(R.id.dateView);

            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            ListViewItem2 listViewItem = listViewItemList2.get(position);

            // 아이템 내 각 위젯에 데이터 반영
            titleTextView.setText(listViewItem.getTitle());
            dateTextView.setText(listViewItem.getDate());


            return convertView;
        }
        // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
        public void addItem(String title, String idx, String date) {
            ListViewItem2 item = new ListViewItem2();
            item.setTitle(title);
            item.setIdx(idx);
            item.setDate(date);
            listViewItemList2.add(item);
            }

         }

    }
