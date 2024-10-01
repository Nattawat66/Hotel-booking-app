package com.demo.demoandroidimage;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView customerInfo, datesInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        customerInfo = findViewById(R.id.customerInfo);
        datesInfo = findViewById(R.id.datesInfo);

        // ข้อมูลการจองที่ส่งมาจาก BookingActivity
        String name = getIntent().getStringExtra("name");
        String checkIn = getIntent().getStringExtra("checkIn");
        String checkOut = getIntent().getStringExtra("checkOut");

        customerInfo.setText("ชื่อผู้จอง: " + name);
        datesInfo.setText("วันที่เข้าพัก: " + checkIn + "\nวันที่ออก: " + checkOut);
    }
}