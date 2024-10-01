package com.demo.demoandroidimage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView hotelRecyclerView;
    private HotelAdapter hotelAdapter;
    private List<Hotel> hotelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hotelRecyclerView = findViewById(R.id.hotelRecyclerView);
        hotelList = new ArrayList<>();
        // ข้อมูลตัวอย่างโรงแรม
        String desc = null;

//        hotelList.add(new Hotel("Aster Hotel", "2,171 บาท",desc = getString(R.string.desc1),R.drawable.hotel1));
//        hotelList.add(new Hotel("Sirin Exclusive Hotel", "1,925 บาท", desc = getString(R.string.desc2), R.drawable.hotel2
//        ));
        hotelList.add(new Hotel("Aster Hotel", "2,171 บาท ต่อคืน",desc = getString(R.string.desc1),R.drawable.hotel1
                ,new int[]{
                R.drawable.hotel1_1,
                R.drawable.hotel1_2,
                R.drawable.hotel1_3,
                R.drawable.hotel1_4,
                R.drawable.hotel1_5,
                R.drawable.hotel1_6,

        }));
        hotelList.add(new Hotel("New Sun Hotel", "1,925 บาท ต่อคืน", desc = getString(R.string.desc2), R.drawable.hotel2
                ,new int[]{
                R.drawable.hotel2_1,
                R.drawable.hotel2_2,
                R.drawable.hotel2_3,
                R.drawable.hotel2_4,
                R.drawable.hotel2_5,
                R.drawable.hotel2_6,

        }));
        hotelList.add(new Hotel("White Swan Apartment","33,436 บาท ต่อคืน", desc =getString(R.string.desc3), R.drawable.hotel3
            ,new int[]{
                R.drawable.hotel3_1,
                R.drawable.hotel3_2,
                R.drawable.hotel3_3,
                R.drawable.hotel3_4,
                R.drawable.hotel3_5,

        }));

        hotelList.add(new Hotel("The Ocean Front Villa Nha Trang Abogo","27,187 บาท ต่อคืน", desc =getString(R.string.desc4), R.drawable.hotel4
                ,new int[]{
                R.drawable.hotel4_1,
                R.drawable.hotel4_2,
                R.drawable.hotel4_3,
                R.drawable.hotel4_4,
                R.drawable.hotel4_5,

        }));


        hotelAdapter = new HotelAdapter(hotelList, this);
        hotelRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        hotelRecyclerView.setAdapter(hotelAdapter);

    }



}