package detai.android.activity.giaovien;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import detai.android.Object.session.SessionManager;
import detai.android.thitracnghiem.R;

/**
 * Created by hoang on 05-May-18.
 */

public class MenuGiaoVienActivity extends AppCompatActivity {
    Button btnQuanLyLop;
    Button btnXemDiem;
    Button btnQuanLyDe;
    Button btnExit;

    TextView tenDangNhap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaovien_menu);
        addControls();
        addEvents();
        khoiTaoGiaTriBanDau();
    }

    private void addEvents() {

        btnQuanLyLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuGiaoVienActivity.this, QuanLyLopActivity.class);
                startActivity(intent);
            }
        });

        btnQuanLyDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuGiaoVienActivity.this, QuanLyDeActivity.class);
                startActivity(intent);
            }
        });

        btnXemDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuGiaoVienActivity.this, XemDiemActivity.class);
                startActivity(intent);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager sessionManager = new SessionManager(MenuGiaoVienActivity.this);
                sessionManager.logout();
                finish();
            }
        });
    }

    private void addControls() {

        btnQuanLyLop = (Button) findViewById(R.id.btnQuanLyLop);
        btnQuanLyDe = (Button) findViewById(R.id.btnQuanLyDe);
        btnXemDiem = (Button) findViewById(R.id.btnXemDiem);
        btnExit = (Button) findViewById(R.id.btnExit);

        tenDangNhap = findViewById(R.id.tvHelloUser);
    }

    private void khoiTaoGiaTriBanDau() {
        SessionManager sessionManager = new SessionManager(MenuGiaoVienActivity.this);
        Query ten = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/").child("DanhSachGiaoVien").child(sessionManager.getUsername()).child("Ten");
        ten.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tenDangNhap.setText("Xin chào giáo viên " + dataSnapshot.getValue() + "!");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
