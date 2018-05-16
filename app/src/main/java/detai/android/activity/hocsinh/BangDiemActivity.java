package detai.android.activity.hocsinh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import detai.android.Adapter.UserInfoAdapter;
<<<<<<< HEAD
import detai.android.Object.UserInfo;
=======
import detai.android.Object.User;
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
import detai.android.Object.session.SessionManager;
import detai.android.thitracnghiem.R;

public class BangDiemActivity extends AppCompatActivity {

    ListView lvUserInfo;
    UserInfoAdapter userInfoAdapter;
<<<<<<< HEAD
    ArrayList<UserInfo> arrayUserInfo;
=======
    ArrayList<User> arrayUserInfo;
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
    Button btnBack;
    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hocsinh_diem);
        addControls();
        addEvents();
        InitDisplay();
    }

    private void InitDisplay() {

        arrayUserInfo = new ArrayList<>();

        SessionManager sessionManager = new SessionManager(BangDiemActivity.this);
        Query hocsinh = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                        .child("DanhSachHocSinh").child(sessionManager.getUsername()).child("DanhSachDiem");
        hocsinh.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                while (items.hasNext()){
                    DataSnapshot item = items.next();
<<<<<<< HEAD
                    arrayUserInfo.add(new UserInfo("Đề "+item.getKey()+":",item.getValue().toString()+" điểm"));
=======
                    arrayUserInfo.add(new User("Đề "+item.getKey()+":",item.getValue().toString()+" điểm"));
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        userInfoAdapter = new UserInfoAdapter(BangDiemActivity.this,
                R.layout.hocsinh_itemdiem,
                arrayUserInfo);
        lvUserInfo.setAdapter(userInfoAdapter);
    }

    private void addControls() {

        lvUserInfo = (ListView) findViewById(R.id.lvUserInfo);
        btnBack = (Button) findViewById(R.id.btnBack);

    }

    private void addEvents() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lvUserInfo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                currentPosition = i;

                return false;
            }
        });

        registerForContextMenu(lvUserInfo);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenucoregame, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.mnDelete) {
<<<<<<< HEAD
            UserInfo userInfo = arrayUserInfo.get(currentPosition);
=======
            User userInfo = arrayUserInfo.get(currentPosition);
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
            if (userInfo != null) {
                arrayUserInfo.remove(userInfo);
                userInfoAdapter.notifyDataSetChanged();
            }

        }

        return super.onContextItemSelected(item);
    }
}
