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
    Button btnQuayLai;
    Button btnThem;
    EditText editTextTenLop;
    LopAdapter lopAdapter;

    ArrayList<Lop> listlop = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaovien_quanlylop);
        addControls();
        addEvents();
        khoiTaoGiaTriBanDau();
    }

    private void khoiTaoGiaTriBanDau() {
        listlop = new ArrayList<>();
        lopAdapter = new LopAdapter(this,R.layout.giaovien_itemlop,listlop);
        listView.setAdapter(lopAdapter);
        SessionManager sessionManager = new SessionManager(this);
        Query danhsachlop = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                .child("DanhSachGiaoVien").child(sessionManager.getUsername()).child("DanhSachLop");
        danhsachlop.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                String de = "";
                int count=0;
                while (items.hasNext()){
                    DataSnapshot item = items.next();
                    if(!item.getKey().equals("De"))
                        count++;
                    else
                        de = item.getValue().toString();
                }
                listlop.add(new Lop(dataSnapshot.getKey(),count,de));
                lopAdapter.notifyDataSetChanged();
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
        final SessionManager sessionManager = new SessionManager(QuanLyLopActivity.this);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenlop = editTextTenLop.getText().toString();
                editTextTenLop.setText("");
                if(listlop.contains(new Lop(tenlop))){
                    Message.showDialog(QuanLyLopActivity.this,"Đã tồn tại tên lớp này, vui lòng chọn tên lớp khác.");
                }
                else {
                    FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                            .child("DanhSachGiaoVien").child(sessionManager.getUsername()).child("DanhSachLop").child(tenlop).setValue("-1");
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
        editTextTenLop = findViewById(R.id.editTextTenLop);
        btnQuayLai = findViewById(R.id.btnQuayLai);
        btnThem = findViewById(R.id.btnThem);
    }
}
