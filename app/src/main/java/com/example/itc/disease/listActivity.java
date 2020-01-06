package com.example.itc.disease;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.itc.disease.db.DB2;

import java.util.ArrayList;
public class listActivity extends AppCompatActivity {

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final ListView listview;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter(this);

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(adapter);

        DB2 dbHelper = new DB2(getApplicationContext(), "disease.db", null, 1);
        Cursor cursor = dbHelper.getResult();

        try {
            if (cursor.moveToFirst()) {
                do {
                    String idx = cursor.getString(0);
                    String name = cursor.getString(1);
                    String e_name = cursor.getString(2);
                    String info = cursor.getString(3);
                    adapter.addItem(ContextCompat.getDrawable(this, R.drawable.h), name, idx);
                    Log.i("test", idx + " : " + name + " : " + e_name + " : " + info);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
//            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int check_position = listview.getCheckedItemPosition();   //리스트뷰의 포지션을 가져옴.
                ListViewItem item = (ListViewItem) parent.getAdapter().getItem(position); //리스트뷰의 포지션 내용을 가져옴.
                Intent intent = new Intent(listActivity.this, ExplanationActivity.class);
                intent.putExtra("idx", item.getIdx());
                startActivity(intent);
            }
        });
    }
    class ListViewAdapter  extends BaseAdapter{
        private Context context;
        private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;

        public ListViewAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return listViewItemList.size();
        }

        @Override
        public Object getItem(int position) {
            return listViewItemList.get(position);
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
                convertView = inflater.inflate(R.layout.activity_item, parent, false);
            }

            // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
            ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageView1) ;
            TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1) ;

            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            ListViewItem listViewItem = listViewItemList.get(position);

            // 아이템 내 각 위젯에 데이터 반영
            iconImageView.setImageDrawable(listViewItem.getIcon());
            titleTextView.setText(listViewItem.getTitle());

            return convertView;
        }
        // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
        public void addItem(Drawable icon, String title, String idx) {
            ListViewItem item = new ListViewItem();
            item.setIcon(icon);
            item.setTitle(title);
            item.setIdx(idx);
            listViewItemList.add(item);
        }
    }

}
