package detai.android.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import detai.android.Object.message.Message;
import detai.android.Object.session.SessionManager;
import detai.android.activity.giaovien.MenuGiaoVienActivity;
import detai.android.activity.hocsinh.MenuHocSinhActivity;
import detai.android.thitracnghiem.R;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername;
    EditText editTextPassword;
    Button btnLogin;
    Button btnRegister;
    RadioButton rbGiaoVien;
    RadioButton rbHocSinh;
    ImageButton btnExit;
    String url = "https://firebasestorage.googleapis.com/v0/b/tracnghiem-data001.appspot.com/o/1.png?alt=media&token=8d8f8498-8f72-44e3-a09b-213e5df8901b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
        khoiTaoGiaTriBanDau();
    }

    private void addControls() {
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnExit = findViewById(R.id.btnExit);
        btnRegister = findViewById(R.id.btnRegister);
        rbGiaoVien = findViewById(R.id.rbGiaoVien);
        rbHocSinh = findViewById(R.id.rbHocSinh);
    }

    private void khoiTaoGiaTriBanDau() {

    }

    private void addEvents() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertbuild = new AlertDialog.Builder(MainActivity.this);
                alertbuild.setMessage("Bạn có thực sự muốn thoát chương trình?");
                alertbuild.setCancelable(false);
                alertbuild.setPositiveButton(
                        "CÓ",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                System.exit(1);
                            }
                        });
                alertbuild.setNegativeButton(
                        "KHÔNG",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = alertbuild.create();
                alert11.show();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = editTextUsername.getText().toString();
                final String password = editTextPassword.getText().toString();
                final String tucach;
                if (rbGiaoVien.isChecked())
                    tucach = "GiaoVien";
                else
                    tucach = "HocSinh";
                if (username.equals("")) {
                    Message.showDialog(MainActivity.this, "Tên đăng nhập chưa được nhập.");
                } else if (password.equals("")) {
                    Message.showDialog(MainActivity.this, "Mật khẩu chưa được nhập.");
                } else {
//                    Message.showDialog(MainActivity.this,username+";"+password);
                    Query account = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/").child("DanhSach" + tucach).child(username).child("Password");
                    account.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() == null) {
                                Message.showDialog(MainActivity.this, "Không tìm thấy username.");
                                return;
                            }

                            if (dataSnapshot.getValue().toString().equals(password)) {
                                //Neu nguoi dung chinh xac
                                SessionManager sessionManager = new SessionManager(MainActivity.this);
                                sessionManager.login(username);
                                if (tucach.equals("GiaoVien")) {
                                    Intent intent = new Intent(MainActivity.this, MenuGiaoVienActivity.class);
                                    startActivity(intent);
                                } else {
                                    Intent intent = new Intent(MainActivity.this, MenuHocSinhActivity.class);
                                    startActivity(intent);
                                }
                            } else {
                                Message.showDialog(MainActivity.this, "Mật khẩu chưa đúng. ");
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Message.showDialog(MainActivity.this, "Không thể lấy dữ liệu từ máy chủ, vui lòng kiểm tra lại mạng");
                        }
                    });

                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}
