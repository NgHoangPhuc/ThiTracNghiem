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

import detai.android.Adapter.LopAdapter;
import detai.android.Object.Lop;
import detai.android.Object.message.Message;
import detai.android.Object.session.SessionManager;
import detai.android.thitracnghiem.R;

/**
 * Created by hoang on 06-May-18.
 */

public class QuanLyLopActivity extends AppCompatActivity{

    ListView listView;
    Button btnLuu;
    Button btnHuy;
    Button btnThem;
    EditText editTextTenLop;
    LopAdapter lopAdapter;

    final ArrayList<Lop> listlop = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaovien_quanlylop);
        addControls();
        addEvents();
        khoiTaoGiaTriBanDau();
    }

    private void khoiTaoGiaTriBanDau() {
        lopAdapter = new LopAdapter(this,R.layout.giaovien_itemlop,listlop);
        listView.setAdapter(lopAdapter);
        SessionManager sessionManager = new SessionManager(this);
        Query danhsachlop = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                .child("DanhSachGiaoVien").child(sessionManager.getUsername()).child("DanhSachLop");
        danhsachlop.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                while (items.hasNext()){
                    DataSnapshot item = items.next();
                    listlop.add(new Lop(item.getKey(),item.getChildrenCount()-1,item.child("De").getValue().toString()));
                }
                lopAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addEvents() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SessionManager sessionManager = new SessionManager(QuanLyLopActivity.this);
                for(Lop x: listlop){
                    FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                            .child("DanhSachGiaoVien").child(sessionManager.getUsername()).child("DanhSachLop").child(x.getName()).child("De").setValue(x.getDe());
                }
                final Query danhsachlop = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                        .child("DanhSachGiaoVien").child(sessionManager.getUsername()).child("DanhSachLop");
                danhsachlop.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                        while (items.hasNext()){
                            DataSnapshot item = items.next();
                            if(!listlop.contains(new Lop(item.getKey())))
                                FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                                        .child("DanhSachGiaoVien").child(sessionManager.getUsername()).child("DanhSachLop").child(item.getKey()).removeValue();
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
                String tenlop = editTextTenLop.getText().toString();
                editTextTenLop.setText("");
                if(listlop.contains(new Lop(tenlop))){
                    Message.showDialog(QuanLyLopActivity.this,"Đã tồn tại tên lớp này, vui lòng chọn tên lớp khác.");
                }
                else {
                    Lop lopmoi = new Lop(tenlop,0,"-1");
                    listlop.add(lopmoi);
//                    listView.removeAllViews();
                    lopAdapter.notifyDataSetChanged();
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
        editTextTenLop = findViewById(R.id.editTextTenLop);
        btnLuu = findViewById(R.id.btnBack);
        btnHuy = findViewById(R.id.btnHuy);
        btnThem = findViewById(R.id.btnThem);
    }
}
