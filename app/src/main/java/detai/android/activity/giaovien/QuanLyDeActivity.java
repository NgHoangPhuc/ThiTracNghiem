package detai.android.activity.giaovien;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import detai.android.Adapter.DeAdapter;
import detai.android.Object.message.Message;
import detai.android.Object.session.SessionManager;
import detai.android.thitracnghiem.R;

/**
 * Created by hoang on 06-May-18.
 */

public class QuanLyDeActivity extends AppCompatActivity {

    Button btnQuayLai,btnThem;
    ArrayList<String> listde;

    ListView listView;
    EditText editTextTenDe;
    DeAdapter deAdapter;

    @Override
    protected void onRestart() {
        super.onRestart();
        khoiTaoGiaTriBanDau();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaovien_quanlyde);

        addControls();
        addEvents();
        khoiTaoGiaTriBanDau();
    }

    private void khoiTaoGiaTriBanDau() {
        listde = new ArrayList<>();
        deAdapter = new DeAdapter(this,R.layout.giaovien_itemde, listde);
        listView.setAdapter(deAdapter);

        SessionManager sessionManager = new SessionManager(this);
        Query danhsachde = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                .child("DanhSachGiaoVien").child(sessionManager.getUsername()).child("DanhSachDe");
        danhsachde.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot != null) {
                    listde.add(dataSnapshot.getKey());
                    deAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addEvents() {
        final SessionManager sessionManager = new SessionManager(QuanLyDeActivity.this);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tende = editTextTenDe.getText().toString();
                editTextTenDe.setText("");
                if (listde.contains(tende)) {
                    Message.showDialog(QuanLyDeActivity.this, "Đã tồn tại tên lớp này, vui lòng chọn tên lớp khác.");
                } else {
                    FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                            .child("DanhSachGiaoVien").child(sessionManager.getUsername()).child("DanhSachDe").child(tende).setValue("-1");
                }
            }
        });

        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        listView = findViewById(R.id.listView);
        editTextTenDe = findViewById(R.id.editTextTenDe);
        btnQuayLai = findViewById(R.id.btnQuayLai);
        btnThem = findViewById(R.id.btnThem);

        listde = new ArrayList<>();
    }
}
