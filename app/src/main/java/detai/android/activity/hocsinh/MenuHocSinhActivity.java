package detai.android.activity.hocsinh;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import detai.android.Test;
import detai.android.Object.session.SessionManager;
import detai.android.thitracnghiem.R;

/**
 * Created by hoang on 05-May-18.
 */

public class MenuHocSinhActivity extends AppCompatActivity {
    Button btnStart;
    Button btnRule;
    Button btnCore;
    Button btnExit;

    TextView tenDangNhap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hocsinh_menu);
        Test test = new Test(MenuHocSinhActivity.this);
        try {
            addControls();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addEvents();
        khoiTaoGiaTriBanDau();
    }

    private void addEvents() {

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SessionManager sessionManager = new SessionManager(MenuHocSinhActivity.this);
                Query fbdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/");

                fbdb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String lop = "";
                        String giaovien = "";
                        Iterator<DataSnapshot> hocsinhitems = dataSnapshot.child("DanhSachHocSinh").child(sessionManager.getUsername()).getChildren().iterator();
                        ArrayList<String> listde = new ArrayList<>();
                        while (hocsinhitems.hasNext()) {
                            DataSnapshot item = hocsinhitems.next();
                            if (item.getKey().equals("Lop"))
                                lop = item.getValue().toString();
                            else if (item.getKey().equals("GiaoVien"))
                                giaovien = item.getValue().toString();
                            else if (item.getKey().equals("DanhSachDiem")){
                                Iterator<DataSnapshot> diem = item.getChildren().iterator();
                                while (diem.hasNext()){
                                    listde.add(diem.next().getKey());
                                }
                            }
                        }
                        Iterator<DataSnapshot> lopitems = dataSnapshot.child("DanhSachGiaoVien").child(giaovien).child("DanhSachLop").child(lop).getChildren().iterator();
                        String de = "";
                        while (lopitems.hasNext()) {
                            DataSnapshot item = lopitems.next();
                            if (item.getKey().equals("De")) {
                                de = item.getValue().toString();
                                if (listde.contains(de)){
                                    AlertDialog.Builder alertbuild = new AlertDialog.Builder(MenuHocSinhActivity.this);
                                    alertbuild.setMessage("Bạn đã hoàn thành bài thi này trước đó.");
                                    alertbuild.setCancelable(true);
                                    alertbuild.setPositiveButton(
                                            "OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    return;
                                                }
                                            });
                                    AlertDialog alert11 = alertbuild.create();
                                    alert11.show();
                                } else {
                                    Intent intent = new Intent(MenuHocSinhActivity.this, ThiTracNghiemActivity.class);
                                    startActivity(intent);

                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        btnRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuHocSinhActivity.this, LuatLeActivity.class);
                startActivity(intent);
            }
        });

        btnCore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuHocSinhActivity.this, BangDiemActivity.class);
                startActivity(intent);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager sessionManager = new SessionManager(MenuHocSinhActivity.this);
                sessionManager.logout();
                finish();
            }
        });
    }

    private void addControls() throws IOException {

        btnStart = (Button) findViewById(R.id.btnStart);
        btnRule = (Button) findViewById(R.id.btnRule);
        btnCore = (Button) findViewById(R.id.btnCore);
        btnExit = (Button) findViewById(R.id.btnExit);

        tenDangNhap = findViewById(R.id.tvHelloUser);

    }

    private void khoiTaoGiaTriBanDau() {
        SessionManager sessionManager = new SessionManager(MenuHocSinhActivity.this);
        Query ten = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/").child("DanhSachHocSinh").child(sessionManager.getUsername()).child("Ten");
        ten.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tenDangNhap.setText("Xin chào học sinh " + dataSnapshot.getValue() + "!");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
