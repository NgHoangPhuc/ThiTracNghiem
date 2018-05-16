package detai.android.activity;

<<<<<<< HEAD
import android.content.Context;
=======
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import detai.android.Object.message.Message;
import detai.android.thitracnghiem.R;

/**
 * Created by hoang on 06-May-18.
 */

public class RegisterActivity extends AppCompatActivity {
    EditText editTextUsername;
    EditText editTextPassword;
    EditText editTextRePassword;
    EditText editTextName;
    EditText editTextSearch;

    Button btnRegister;
    Button btnSearch;

    RadioButton rbGiaoVien;
    RadioButton rbHocSinh;

    Spinner spinner;

    LinearLayout layoutHocsinh;

    ArrayList<String> danhsachlop = new ArrayList<>();

    boolean haditem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addControls();
        addEvents();
        khoiTaoGiaTriBanDau();

    }

    private void khoiTaoGiaTriBanDau() {

    }

    private void addEvents() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = editTextUsername.getText().toString();
                final String password = editTextPassword.getText().toString();
                String repassword = editTextRePassword.getText().toString();
                final String idgiaovien = editTextSearch.getText().toString();
                final String lop;
                final String ten = editTextName.getText().toString();
<<<<<<< HEAD
                if(spinner.getCount() > 0)
=======
                haditem = true;
                if (spinner.getCount() > 0)
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
                    lop = spinner.getSelectedItem().toString();
                else
                    lop = "";
                String tucach = "";
<<<<<<< HEAD
                if(rbGiaoVien.isChecked())
                    tucach = "GiaoVien";
                else
                    tucach = "HocSinh";
                if(username.equals(""))
                    Message.showDialog(RegisterActivity.this,"Username chưa được nhập");
                else if(password.equals(""))
                    Message.showDialog(RegisterActivity.this,"Mật khẩu chưa được nhập");
                else if(!password.equals(repassword))
                    Message.showDialog(RegisterActivity.this,"Mật khẩu 1 và mật khẩu 2 chưa giống nhau");
                else if(ten.equals(""))
                    Message.showDialog(RegisterActivity.this,"Tên chưa được nhập");
                else if(idgiaovien.equals(""))
                    Message.showDialog(RegisterActivity.this,"Id giáo viên chưa được nhập");
                else if(lop.equals(""))
                    Message.showDialog(RegisterActivity.this,"Giáo viên này hiện chưa mở lớp nào.");
                else {
                    final DatabaseReference user = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/").child("DanhSach" + tucach).child(username);
                    Query account = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/").child("DanhSach"+tucach).child(username);
=======
                if (rbGiaoVien.isChecked())
                    tucach = "GiaoVien";
                else
                    tucach = "HocSinh";
                if (username.equals(""))
                    Message.showDialog(RegisterActivity.this, "Username chưa được nhập");
                else if (password.equals(""))
                    Message.showDialog(RegisterActivity.this, "Mật khẩu chưa được nhập");
                else if (!password.equals(repassword))
                    Message.showDialog(RegisterActivity.this, "Mật khẩu 1 và mật khẩu 2 chưa giống nhau");
                else if (ten.equals(""))
                    Message.showDialog(RegisterActivity.this, "Tên chưa được nhập");
                else if (idgiaovien.equals("") && tucach.equals("HocSinh"))
                    Message.showDialog(RegisterActivity.this, "Id giáo viên chưa được nhập");
                else if (lop.equals("") && tucach.equals("HocSinh"))
                    Message.showDialog(RegisterActivity.this, "Giáo viên này hiện chưa mở lớp nào.");
                else {
                    Query account = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/").child("DanhSach" + tucach).child(username);
                    final DatabaseReference user = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/").child("DanhSach" + tucach).child(username);
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
                    final String finalTucach = tucach;
                    account.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
<<<<<<< HEAD
                            Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                            haditem = false;
                            if (!items.hasNext()) {
                                if (finalTucach.equals("HocSinh")) {
                                    user.child("GiaoVien").setValue(idgiaovien);
                                    user.child("Lop").setValue(lop);
                                    user.child("Ten").setValue(ten);
                                    FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                                            .child("DanhSachGiaoVien").child(idgiaovien).child(lop).child(username).setValue("-1");
                                }
                                showDialogYN(RegisterActivity.this, "Đăng ký thành công, bạn có muốn trở về trang đăng nhập?");
                            }
                            else
                                haditem = true;
=======
                            if (dataSnapshot.getValue() == null) {
                                AlertDialog.Builder alertbuild = new AlertDialog.Builder(RegisterActivity.this);
                                alertbuild.setMessage("Đăng ký thành công.");
                                alertbuild.setCancelable(true);
                                alertbuild.setPositiveButton(
                                        "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                if (finalTucach.equals("HocSinh")) {
                                                    user.child("GiaoVien").setValue(idgiaovien);
                                                    user.child("Lop").setValue(lop);
                                                    FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                                                            .child("DanhSachGiaoVien").child(idgiaovien).child(lop).child(username).setValue("-1");
                                                }
                                                user.child("Ten").setValue(ten);
                                                user.child("Password").setValue(password);
                                                finish();
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog alert11 = alertbuild.create();
                                alert11.show();
                            } else {
                                AlertDialog.Builder alertbuild = new AlertDialog.Builder(RegisterActivity.this);
                                alertbuild.setMessage("Username này đã được sử dụng.");
                                alertbuild.setCancelable(true);
                                alertbuild.setPositiveButton(
                                        "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog alert11 = alertbuild.create();
                                alert11.show();
                            }
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Message.showDialog(RegisterActivity.this, "Không thể lấy dữ liệu từ máy chủ, vui lòng kiểm tra lại mạng");
                        }
                    });
<<<<<<< HEAD
                    if (haditem == true)
                        Message.showDialog(RegisterActivity.this, "Đã tồn tại id này.");
=======
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
                }
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                danhsachlop.removeAll(danhsachlop);
<<<<<<< HEAD
                spinner.setAdapter(new ArrayAdapter<String>(RegisterActivity.this,R.layout.support_simple_spinner_dropdown_item,danhsachlop));
                String idgiaovien = editTextSearch.getText().toString();
                if(idgiaovien.equals("")){
                    Message.showDialog(RegisterActivity.this,"ID giáo viên chưa được nhập.");
                }
                else {
=======
                spinner.setAdapter(new ArrayAdapter<String>(RegisterActivity.this, R.layout.support_simple_spinner_dropdown_item, danhsachlop));
                String idgiaovien = editTextSearch.getText().toString();
                if (idgiaovien.equals("")) {
                    Message.showDialog(RegisterActivity.this, "ID giáo viên chưa được nhập.");
                } else {
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
                    Query account = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/").child("DanhSachGiaoVien").child(idgiaovien).child("DanhSachLop");
                    account.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                            if (!items.hasNext()) {
                                Message.showDialog(RegisterActivity.this, "Không tìm thấy giáo viên.");
                                return;
                            }
                            while (items.hasNext()) {
                                DataSnapshot item = items.next();
                                if (item == null) {
                                    return;
                                }
                                danhsachlop.add(item.getKey());
                            }
<<<<<<< HEAD
                            ArrayAdapter arrayAdapter = new ArrayAdapter(RegisterActivity.this,R.layout.support_simple_spinner_dropdown_item,danhsachlop);
=======
                            ArrayAdapter arrayAdapter = new ArrayAdapter(RegisterActivity.this, R.layout.support_simple_spinner_dropdown_item, danhsachlop);
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
                            spinner.setAdapter(arrayAdapter);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Message.showDialog(RegisterActivity.this, "Không thể lấy dữ liệu từ máy chủ, vui lòng kiểm tra lại mạng");
                        }
                    });
                }
            }
        });
        editTextSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                danhsachlop.removeAll(danhsachlop);
<<<<<<< HEAD
                spinner.setAdapter(new ArrayAdapter<String>(RegisterActivity.this,R.layout.support_simple_spinner_dropdown_item,danhsachlop));
=======
                spinner.setAdapter(new ArrayAdapter<String>(RegisterActivity.this, R.layout.support_simple_spinner_dropdown_item, danhsachlop));
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
            }
        });
        rbGiaoVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutHocsinh.setVisibility(View.INVISIBLE);
            }
        });
        rbHocSinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutHocsinh.setVisibility(View.VISIBLE);
            }
        });
    }

    private void addControls() {
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextRePassword = findViewById(R.id.editTextRePassword);
        editTextSearch = findViewById(R.id.editTextSearch);
        editTextName = findViewById(R.id.editTextName);
        btnRegister = findViewById(R.id.btnRegister);
        btnSearch = findViewById(R.id.btnSearch);
        rbGiaoVien = findViewById(R.id.rbGiaoVien);
        rbHocSinh = findViewById(R.id.rbHocSinh);
        spinner = findViewById(R.id.spinner);
        layoutHocsinh = findViewById(R.id.layoutHocsinh);
    }

<<<<<<< HEAD
    void showDialogYN(Context context, String message){
        AlertDialog.Builder alertbuild = new AlertDialog.Builder(context);
        alertbuild.setMessage(message);
        alertbuild.setCancelable(true);
        alertbuild.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        dialog.cancel();
                    }
                });

        alertbuild.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        editTextUsername.setText("");
                        editTextPassword.setText("");
                        editTextRePassword.setText("");
                        editTextSearch.setText("");
                        editTextSearch.callOnClick();
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = alertbuild.create();
        alert11.show();
    }
=======
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
}
