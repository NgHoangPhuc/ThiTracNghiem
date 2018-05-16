package detai.android.activity.giaovien;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import detai.android.Adapter.CauHoiAdapter;
import detai.android.Object.Question;
import detai.android.Object.session.SessionManager;
import detai.android.thitracnghiem.R;

/**
 * Created by hoang on 08-May-18.
 */

public class QuanLyCauHoiActivity extends AppCompatActivity{
    public String de;
    TextView tvDe;
    ListView listView;
    Button btnXong;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaovien_danhsachcauhoi);

        addControls();
        addEvents();
        khoiTaoGiaTriBanDau();
    }

    private void addControls() {
        tvDe = findViewById(R.id.tvDe);
        listView = findViewById(R.id.listView);
        btnXong = findViewById(R.id.btnXong);
    }

    private void addEvents() {
        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void khoiTaoGiaTriBanDau() {
        de = getIntent().getStringExtra("De");
        tvDe.setText("Đề "+de);
        final SessionManager sessionManager = new SessionManager(QuanLyCauHoiActivity.this);
        Query fbdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/");
        fbdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Question> listcauhoi = new ArrayList<>();
                Iterator<DataSnapshot> deitems = dataSnapshot.child("DanhSachGiaoVien").child(sessionManager.getUsername())
                                                                .child("DanhSachDe").child(de).getChildren().iterator();
                while (deitems.hasNext()) {
                    String noidung = "", dapana = "", dapanb = "", dapanc = "", dapand = "";
                    int id = 0, dapandung = 0;

                    DataSnapshot item = deitems.next();
                    id = Integer.parseInt(item.getKey());

                    Iterator<DataSnapshot> cauhoiitems = item.getChildren().iterator();
                    while (cauhoiitems.hasNext()) {
                        DataSnapshot item2 = cauhoiitems.next();
                        if (Integer.parseInt(item2.getKey()) % 10 == 1)
                            noidung = item2.getValue().toString();
                        else if (Integer.parseInt(item2.getKey()) % 10 == 2)
                            dapana = item2.getValue().toString();
                        else if (Integer.parseInt(item2.getKey()) % 10 == 3)
                            dapanb = item2.getValue().toString();
                        else if (Integer.parseInt(item2.getKey()) % 10 == 4)
                            dapanc = item2.getValue().toString();
                        else if (Integer.parseInt(item2.getKey()) % 10 == 5)
                            dapand = item2.getValue().toString();
                        else if (Integer.parseInt(item2.getKey()) % 10 == 6)
                            dapandung = Integer.parseInt(item2.getValue().toString());
                    }
                    listcauhoi.add(new Question(id, noidung, dapana, dapanb, dapanc, dapand, 0, dapandung));
                }
                listView.setAdapter(new CauHoiAdapter(QuanLyCauHoiActivity.this,R.layout.giaovien_itemcauhoi,listcauhoi));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
