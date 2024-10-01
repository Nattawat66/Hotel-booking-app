package com.demo.demoandroidimage;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.Dialog; // สำหรับ Dialog
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager; // สำหรับ RecyclerView
import androidx.recyclerview.widget.RecyclerView; // สำหรับ RecyclerView
import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {

    private EditText customerName, checkInDate, checkOutDate;
    private Button bookNowBtn, viewmore;
    private ImageView hotelImage;
    private TextView hotelDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking); // กำหนด layout

        // ผูกตัวแปรกับ View ใน layout
        customerName = findViewById(R.id.customerName);
        checkInDate = findViewById(R.id.checkInDate);
        checkOutDate = findViewById(R.id.checkOutDate);
        bookNowBtn = findViewById(R.id.bookNowBtn);
        hotelImage = findViewById(R.id.hotelImage); // ผูกรูปภาพโรงแรม
        hotelDescription = findViewById(R.id.hotelDescription); // ผูกคำอธิบายโรงแรม
        viewmore = findViewById(R.id.viewmore);


        // รับข้อมูลโรงแรมจากหน้า MainActivity
        Hotel hotel = (Hotel) getIntent().getSerializableExtra("hotel");

        // แสดงข้อมูลโรงแรม (รูปภาพและคำอธิบาย)
        hotelImage.setImageResource(hotel.getImageResource());
        hotelDescription.setText("โรงแรม " + hotel.getName() + "\nราคาต่อคืน: " + hotel.getPrice() + "\n\n" + hotel.getDesc());
        bookNowBtn.setText("จองโรงแรม");

        viewmore.setOnClickListener(v -> {
            showImagePopup(hotel.getImageIds());
        });

        // ตั้งค่า DatePicker สำหรับวันที่เข้าพัก
        checkInDate.setOnClickListener(v -> showDatePickerDialog(checkInDate));
        checkOutDate.setOnClickListener(v -> showDatePickerDialog(checkOutDate));
        bookNowBtn.setOnClickListener(v -> {
            if (validateInputs()) {
                showConfirmationDialog(hotel);
            }
        });
    }


    private void showImagePopup(int[] imageIds) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_image_view); // สร้าง layout สำหรับ Popup ที่มี RecyclerView
        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerView);
        ImageAdapter imageAdapter = new ImageAdapter(imageIds); // สร้าง Adapter สำหรับ RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(imageAdapter);

        dialog.show(); // แสดง Popup
    }


    private void showDatePickerDialog(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear; // รูปแบบวันที่
            editText.setText(date); // ตั้งค่าที่ EditText
        }, year, month, day);

        datePickerDialog.show(); // แสดง DatePicker
    }


    private boolean validateInputs() {
        // ตรวจสอบว่าชื่อ, วันที่เข้าพัก, วันที่ออกจากที่พัก ไม่ว่าง
        if (customerName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (checkInDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (checkOutDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "กรุณาเลือกวันที่ออกจากที่พัก", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true; // ถ้าข้อมูลครบถ้วน
    }

    private void showConfirmationDialog(Hotel hotel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ยืนยันการจอง");
        builder.setMessage("คุณต้องการจองโรงแรม " + hotel.getName() + " ราคา " + hotel.getPrice() + " หรือไม่?");

        builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // ถ้ากดยืนยัน จะไปยังหน้า ConfirmationActivity
                Intent intent = new Intent(BookingActivity.this, ConfirmationActivity.class);
                intent.putExtra("name", customerName.getText().toString()); // ส่งชื่อลูกค้า
                intent.putExtra("checkIn", checkInDate.getText().toString()); // ส่งวันที่เข้าพัก
                intent.putExtra("checkOut", checkOutDate.getText().toString()); // ส่งวันที่ออกจากที่พัก
                startActivity(intent); // เปิดหน้า ConfirmationActivity
            }
        });

        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // ปิด Dialog เมื่อกดยกเลิก
            }
        });

        // แสดง Dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
