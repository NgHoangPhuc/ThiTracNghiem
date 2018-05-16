package detai.android.activity.giaovien;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import detai.android.Adapter.DeAdapter;
import detai.android.Object.message.Message;
import detai.android.Object.session.SessionManager;
import detai.android.thitracnghiem.R;

/**
 * Created by hoang on 06-May-18.
 */

public class QuanLyDeActivity extends AppCompatActivity {

    Button btnLuu,btnHuy,btnThem;
    ArrayList<String> listde;

    ListView listView;
    EditText editTextTenDe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaovien_quanlyde);

        addControls();
        addEvents();
        khoiTaoGiaTriBanDau();
    }

    private void khoiTaoGiaTriBanDau() {
        SessionManager sessionManager = new SessionManager(this);
        Query danhsachde = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                .child("DanhSachGiaoVien").child(sessionManager.getUsername()).child("DanhSachDe");
        danhsachde.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                while (items.hasNext()) {
                    DataSnapshot item = items.next();
                    listde.add(item.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView.setAdapter(new DeAdapter(this,R.layout.giaovien_itemde, listde));
    }

    private void addEvents() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SessionManager sessionManager = new SessionManager(QuanLyDeActivity.this);
                for (String x : listde) {
                    FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                            .child("DanhSachGiaoVien").child(sessionManager.getUsername()).child("DanhSachDe").child(x).setValue("-1");
                }
                final Query danhsachde = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                        .child("DanhSachGiaoVien").child(sessionManager.getUsername()).child("DanhSachDe");
                danhsachde.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                        while (items.hasNext()) {
                            DataSnapshot item = items.next();
                            if (!listde.contains(item.getKey()))
                                FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                                        .child("DanhSachGiaoVien").child(sessionManager.getUsername()).child("DanhSachDe").child(item.getKey()).removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                finish();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tende = editTextTenDe.getText().toString();
                editTextTenDe.setText("");
                if (listde.contains(tende)) {
                    Message.showDialog(QuanLyDeActivity.this, "Đã tồn tại tên lớp này, vui lòng chọn tên lớp khác.");
                } else {
                    listde.add(tende);
//                    listView.removeAllViews();
                    listView.setAdapter(new DeAdapter(QuanLyDeActivity.this, R.layout.giaovien_itemde, listde));
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        listView = findViewById(R.id.listView);
        editTextTenDe = findViewById(R.id.editTextTenDe);
        btnLuu = findViewById(R.id.btnLuu);
        btnHuy = findViewById(R.id.btnHuy);
        btnThem = findViewById(R.id.btnThem);

        listde = new ArrayList<>();
    }
}
