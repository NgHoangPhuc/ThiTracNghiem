package detai.android.activity.giaovien;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import detai.android.Adapter.UserInfoAdapter;
import detai.android.Object.User;
import detai.android.Object.session.SessionManager;
import detai.android.thitracnghiem.R;

/**
 * Created by hoang on 06-May-18.
 */

public class XemDiemActivity extends AppCompatActivity {
    ListView listView;
    Spinner spinnerDanhSachLop;
    Spinner spinnerDanhSachDe;

    ArrayList<String> listde;
    ArrayList<String> listlop;

    Button btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaovien_xemdiem);

        addControls();
        addEvents();
        khoiTaoGiaTriBanDau();
    }

    private void khoiTaoGiaTriBanDau() {
        final SessionManager sessionManager = new SessionManager(XemDiemActivity.this);
        final Query giaovien = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                .child("DanhSachGiaoVien").child(sessionManager.getUsername());
        giaovien.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items = dataSnapshot.child("DanhSachLop").getChildren().iterator();
                Iterator<DataSnapshot> items2 = dataSnapshot.child("DanhSachDe").getChildren().iterator();
                while (items.hasNext()) {
                    DataSnapshot item = items.next();
                    listlop.add(item.getKey());
                    spinnerDanhSachLop.setAdapter(new ArrayAdapter<String>(XemDiemActivity.this, R.layout.support_simple_spinner_dropdown_item, listlop));
                }
                while (items2.hasNext()) {
                    DataSnapshot item = items2.next();
                    listde.add(item.getKey());
                    spinnerDanhSachDe.setAdapter(new ArrayAdapter<String>(XemDiemActivity.this, R.layout.support_simple_spinner_dropdown_item, listde));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        spinnerDanhSachLop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listde.removeAll(listde);
                spinnerDanhSachDe.setAdapter(new ArrayAdapter<String>(XemDiemActivity.this, R.layout.support_simple_spinner_dropdown_item, listde));
                giaovien.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> items2 = dataSnapshot.child("DanhSachDe").getChildren().iterator();
                        while (items2.hasNext()) {
                            DataSnapshot item = items2.next();
                            listde.add(item.getKey());
                            spinnerDanhSachDe.setAdapter(new ArrayAdapter<String>(XemDiemActivity.this, R.layout.support_simple_spinner_dropdown_item, listde));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerDanhSachDe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Query database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/");
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> hstronglop = dataSnapshot.child("DanhSachGiaoVien").child(sessionManager.getUsername())
                                .child("DanhSachLop").child(spinnerDanhSachLop.getSelectedItem().toString()).getChildren().iterator();
                        ArrayList<User> lisths = new ArrayList<>();
                        while (hstronglop.hasNext()) {
                            DataSnapshot hs = hstronglop.next();
                            if (hs.getKey().equals("De"))
                                continue;
//                            Message.showDialog(XemDiemActivity.this, "Hoc sinh trong lop: " + hs.toString());

                            Iterator<DataSnapshot> diems = dataSnapshot.child("DanhSachHocSinh").child(hs.getKey())
                                    .child("DanhSachDiem").getChildren().iterator();
                            Iterator<DataSnapshot> hss = dataSnapshot.child("DanhSachHocSinh").child(hs.getKey()).getChildren().iterator();
                            String tenhs = "";
                            boolean hadten = false;
                            while (hss.hasNext()){
                                DataSnapshot hsitem = hss.next();
                                if(hsitem.getKey().equals("Ten")){
                                    tenhs = hsitem.getValue().toString();
                                    hadten = true;
                                }
                            }
                            if(!hadten)
                                continue;
                            boolean haddiem = false;
                            while (diems.hasNext()) {
                                DataSnapshot diem = diems.next();
                                if (diem.getKey().equals(spinnerDanhSachDe.getSelectedItem().toString())) {
                                    lisths.add(new User(tenhs, diem.getValue().toString()));
                                    haddiem = true;
                                    break;
                                }
                            }
                            if (haddiem == false) {
                                lisths.add(new User(tenhs, "Chưa làm bài."));
                            }
                        }
//                        Message.showDialog(XemDiemActivity.this, lisths.size() + "");
                        listView.setAdapter(new UserInfoAdapter(XemDiemActivity.this, R.layout.giaovien_itemdiem, lisths));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        listView = findViewById(R.id.listView);
        spinnerDanhSachLop = findViewById(R.id.spinnerDanhSachLop);
        spinnerDanhSachDe = findViewById(R.id.spinnerDanhSachDe);
        btnBack = findViewById(R.id.btnBack);

        listde = new ArrayList<>();
        listlop = new ArrayList<>();
    }
}
