package detai.android.activity.hocsinh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import detai.android.Object.Constants;
import detai.android.Object.message.Message;
import detai.android.Object.session.SessionManager;
import detai.android.thitracnghiem.R;

public class KetQuaThiActivity extends AppCompatActivity {
    boolean nopbai = false;

    EditText etTenThiSinh;
    EditText etSoDiemDatDuoc;
    EditText etDe;
    Button btnNopBai;

    @Override
    protected void onPause() {
        super.onPause();
        if(!nopbai){
            InitDisplay();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hocsinh_ketquathi);

        addControls();
        addEvents();
        InitDisplay();
    }

    private void InitDisplay() {
        Intent intent = getIntent();

        String soCauDung = intent.getStringExtra(Constants.SO_CAU_DUNG);

        Message.showDialog(this,"Bạn đã làm đúng "+soCauDung+" câu.");

        final double soDiemDatDuoc = intent.getDoubleExtra(Constants.SO_DIEM_DAT_DUOC,0);
        etSoDiemDatDuoc.setText(soDiemDatDuoc + "");

        //Gui ket qua ve server
        final SessionManager sessionManager = new SessionManager(KetQuaThiActivity.this);
        Query fbdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/");
        fbdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> hocsinh = dataSnapshot.child("DanhSachHocSinh").child(sessionManager.getUsername()).getChildren().iterator();
                String lop = "",giaovien = "";
                while (hocsinh.hasNext()){
                    DataSnapshot hs = hocsinh.next();
                    if(hs.getKey().equals("Ten"))
                        etTenThiSinh.setText(hs.getValue().toString());
                    if(hs.getKey().equals("Lop"))
                        lop = hs.getValue().toString();
                    if(hs.getKey().equals("GiaoVien"))
                        giaovien = hs.getValue().toString();
                }
                final String de = dataSnapshot.child("DanhSachGiaoVien").child(giaovien).child("DanhSachLop").child(lop).child("De").getValue().toString();
                etDe.setText(de);
                btnNopBai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                                .child("DanhSachHocSinh").child(sessionManager.getUsername()).child("DanhSachDiem").child(de).setValue(soDiemDatDuoc+"");
                        nopbai = true;
                        finish();
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void addEvents() {

    }

    private void addControls() {

        etTenThiSinh = (EditText) findViewById(R.id.etTenThiSinh);
        etSoDiemDatDuoc = (EditText) findViewById(R.id.etSoDiemDatDuoc);
        etDe = (EditText) findViewById(R.id.etDe);

        btnNopBai = (Button) findViewById(R.id.btnBack);

    }
}
