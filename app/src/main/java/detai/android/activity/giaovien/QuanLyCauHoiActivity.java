package detai.android.activity.giaovien;

<<<<<<< HEAD
=======
import android.content.Intent;
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

<<<<<<< HEAD
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
=======
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a

import java.util.ArrayList;
import java.util.Iterator;

import detai.android.Adapter.CauHoiAdapter;
<<<<<<< HEAD
import detai.android.Object.Question;
=======
import detai.android.Object.CauHoi;
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
import detai.android.Object.session.SessionManager;
import detai.android.thitracnghiem.R;

/**
 * Created by hoang on 08-May-18.
 */

<<<<<<< HEAD
public class QuanLyCauHoiActivity extends AppCompatActivity{
    public String de;
    TextView tvDe;
    ListView listView;
    Button btnXong;
=======
public class QuanLyCauHoiActivity extends AppCompatActivity {
    public String de;
    TextView tvDe;
    ListView listView;
    Button btnQuayLai;
    Button btnThemCauHoi;
    CauHoiAdapter cauHoiAdapter;
    ArrayList<CauHoi> listcauhoi;

    @Override
    protected void onRestart() {
        super.onRestart();
        khoiTaoGiaTriBanDau();
    }
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a

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
<<<<<<< HEAD
        btnXong = findViewById(R.id.btnXong);
    }

    private void addEvents() {
        btnXong.setOnClickListener(new View.OnClickListener() {
=======
        btnQuayLai = findViewById(R.id.btnBack);
        btnThemCauHoi = findViewById(R.id.btnThemCauHoi);
    }

    private void addEvents() {
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
            @Override
            public void onClick(View v) {
                finish();
            }
        });
<<<<<<< HEAD
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
=======
        btnThemCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i;
                for(i = 1; i <= listcauhoi.size(); i++){
                    if(!listcauhoi.contains(new CauHoi(i+"")))
                        break;
                }
                final SessionManager sessionManager = new SessionManager(QuanLyCauHoiActivity.this);
                DatabaseReference db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                        .child("DanhSachGiaoVien").child(sessionManager.getUsername())
                        .child("DanhSachDe").child(de).child(i+"");
                db.child("1").setValue("0");
                db.child("2").setValue("0");
                db.child("3").setValue("0");
                db.child("4").setValue("0");
                db.child("5").setValue("0");
                db.child("6").setValue(0);
                listcauhoi.add(new CauHoi(i+"","0","0","0","0","0","0",0));
                cauHoiAdapter.notifyDataSetChanged();
            }
        });
    }

    private void khoiTaoGiaTriBanDau() {
        listcauhoi = new ArrayList<>();
        cauHoiAdapter = new CauHoiAdapter(QuanLyCauHoiActivity.this, R.layout.giaovien_itemcauhoi, listcauhoi);
        listView.setAdapter(cauHoiAdapter);
        de = getIntent().getStringExtra("De");
        tvDe.setText("Đề " + de);
        final SessionManager sessionManager = new SessionManager(QuanLyCauHoiActivity.this);
        Query fbdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                .child("DanhSachGiaoVien").child(sessionManager.getUsername())
                .child("DanhSachDe").child(de);
        fbdb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                if (dataSnapshot != null) {
                    String arr[] = new String[7];
                    for (int i = 0; i <= 6; i++) {
                        if (i == 0)
                            arr[i] = dataSnapshot.getKey();
                        else {
                            if(!items.hasNext())
                                return;
                            DataSnapshot item = items.next();
                            arr[i] = item.getValue().toString();
                        }
                    }
                    listcauhoi.add(new CauHoi(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], de, Integer.parseInt(arr[6])));
                }
                cauHoiAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
