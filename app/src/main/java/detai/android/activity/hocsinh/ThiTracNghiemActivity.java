package detai.android.activity.hocsinh;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;

<<<<<<< HEAD
import detai.android.Object.Constants;
import detai.android.Object.Question;
=======
import detai.android.Object.CauHoi;
import detai.android.Object.Constants;
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
import detai.android.Object.session.SessionManager;
import detai.android.thitracnghiem.R;

public class ThiTracNghiemActivity extends AppCompatActivity {

    PowerManager.WakeLock mWakeLock;
    boolean nopbai = false;

    Button btnTruoc;
    Button btnSau;
    Button btnKetThuc;

    TextView tvThoiGian;
    TextView tvSoCau;
    ImageView tvCauHoi;
    ImageView tvDapAnA;
    ImageView tvDapAnB;
    ImageView tvDapAnC;
    ImageView tvDapAnD;
    LinearLayout layoutA;
    LinearLayout layoutB;
    LinearLayout layoutC;
    LinearLayout layoutD;

    int hh;
    int mm;
    int ss;
    int time_interval;
    int bg_dapan;
    int bg_dapan_chon;
    int soCauToiDa;
    int soCau;
<<<<<<< HEAD
    ArrayList<Question> dsCauHoi;
    //    ThiTracNghiemDbContext db;
    Question cauHoi;
=======
    ArrayList<CauHoi> dsCauHoi;
    //    ThiTracNghiemDbContext db;
    CauHoi cauHoi;
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a

    View.OnClickListener chonDapAn;
    View.OnClickListener xuLyCauHoi;
    View.OnClickListener ketThucPhanThi;

    CountDownTimer thoiGian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hocsinh_thi_trac_nghiem);

        addControls();
        addEvents();
        khoiTaoGiaTriBanDau();

        //Giữ màn hình luôn bật
        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        mWakeLock.acquire();
    }

    @Override
    protected void onPause() {
        mWakeLock.release();
        super.onPause();
        if(!nopbai) {
            //Gui ket qua ve server
            final SessionManager sessionManager = new SessionManager(ThiTracNghiemActivity.this);
            Query fbdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/");
            fbdb.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Iterator<DataSnapshot> hocsinh = dataSnapshot.child("DanhSachHocSinh").child(sessionManager.getUsername()).getChildren().iterator();
                    String lop = "", giaovien = "";
                    while (hocsinh.hasNext()) {
                        DataSnapshot hs = hocsinh.next();
                        if (hs.getKey().equals("Lop"))
                            lop = hs.getValue().toString();
                        if (hs.getKey().equals("GiaoVien"))
                            giaovien = hs.getValue().toString();
                    }
                    final String de = dataSnapshot.child("DanhSachGiaoVien").child(giaovien).child("DanhSachLop").child(lop).child("De").getValue().toString();
                    int soCauDung = 0;
                    double diemSo = 0;

<<<<<<< HEAD
                    for (Question cauHoi : dsCauHoi) {
=======
                    for (CauHoi cauHoi : dsCauHoi) {
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a

                        if (cauHoi.getResultSelect() == cauHoi.getResult()) {
                            soCauDung++;
                        }
                    }

                    diemSo = (double) ((soCauDung * 100) / soCauToiDa) / 10;
                    FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                            .child("DanhSachHocSinh").child(sessionManager.getUsername()).child("DanhSachDiem").child(de).setValue(diemSo + "");
                    finish();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private void formatThoiGian() {

        ss--;
        if (ss < 0) {
            ss = 59;
            mm--;
        }

        if (mm < 0) {
            hh--;
            mm = 59;
        }

        tvThoiGian.setText(
                (hh < 10 ? "0" + hh : hh) + ":" +
                        (mm < 10 ? "0" + mm : mm) + ":" +
                        (ss < 10 ? "0" + ss : ss));
    }

    private void addEvents() {

        hh = 00;
        mm = 30;
        ss = 01;
        time_interval = ((hh * 60) + mm) * 60 * 1000 + 2000;
        thoiGian = new CountDownTimer(time_interval, 1000) {
            @Override
            public void onTick(long l) {

                formatThoiGian();
            }

            @Override
            public void onFinish() {
                tinhKetQuaPhanThi();
            }
        };

        thoiGian.start();

        chonDapAn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int daDaChon = 0;

                if (view.getId() == R.id.tvDapAnA) {
                    daDaChon = 1;

                } else if (view.getId() == R.id.tvDapAnB) {
                    daDaChon = 2;

                } else if (view.getId() == R.id.tvDapAnC) {
                    daDaChon = 3;

                } else if (view.getId() == R.id.tvDapAnD) {
                    daDaChon = 4;
                }

                cauHoi.setResultSelect(daDaChon);
                layBackground(daDaChon);
            }
        };

        xuLyCauHoi = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view.getId() == R.id.btnTruoc) {
                    cauHoiTruocDo();

                } else if (view.getId() == R.id.btnSau) {
                    cauHoiTiepTheo();
                }
            }
        };

        ketThucPhanThi = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view.getId() == R.id.btnKetThuc) {
                    KetThucPhanThi();
                }
            }
        };

        tvDapAnA.setOnClickListener(chonDapAn);
        tvDapAnB.setOnClickListener(chonDapAn);
        tvDapAnC.setOnClickListener(chonDapAn);
        tvDapAnD.setOnClickListener(chonDapAn);

        btnTruoc.setOnClickListener(xuLyCauHoi);
        btnSau.setOnClickListener(xuLyCauHoi);

        btnKetThuc.setOnClickListener(ketThucPhanThi);
    }

    private void addControls() {

        btnTruoc = (Button) findViewById(R.id.btnTruoc);
        btnSau = (Button) findViewById(R.id.btnSau);
        btnKetThuc = (Button) findViewById(R.id.btnKetThuc);

        tvThoiGian = (TextView) findViewById(R.id.tvThoiGian);
        tvSoCau = (TextView) findViewById(R.id.tvSoCau);
        tvCauHoi = (ImageView) findViewById(R.id.tvTitle);
        tvDapAnA = (ImageView) findViewById(R.id.tvDapAnA);
        tvDapAnB = (ImageView) findViewById(R.id.tvDapAnB);
        tvDapAnC = (ImageView) findViewById(R.id.tvDapAnC);
        tvDapAnD = (ImageView) findViewById(R.id.tvDapAnD);

        layoutA = (LinearLayout) findViewById(R.id.layoutA);
        layoutB = (LinearLayout) findViewById(R.id.layoutB);
        layoutC = (LinearLayout) findViewById(R.id.layoutC);
        layoutD = (LinearLayout) findViewById(R.id.layoutD);

    }

    private void khoiTaoGiaTriBanDau() {
        dsCauHoi = new ArrayList<>();
        final SessionManager sessionManager = new SessionManager(ThiTracNghiemActivity.this);
        Query fbdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/");
        fbdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String lop = "";
                String giaovien = "";
                Iterator<DataSnapshot> hocsinhitems = dataSnapshot.child("DanhSachHocSinh").child(sessionManager.getUsername()).getChildren().iterator();
                while (hocsinhitems.hasNext()) {
                    DataSnapshot item = hocsinhitems.next();
                    if (item.getKey().equals("Lop"))
                        lop = item.getValue().toString();
                    else if (item.getKey().equals("GiaoVien"))
                        giaovien = item.getValue().toString();
                }
                Iterator<DataSnapshot> lopitems = dataSnapshot.child("DanhSachGiaoVien").child(giaovien).child("DanhSachLop").child(lop).getChildren().iterator();
                String de = "";
                while (lopitems.hasNext()) {
                    DataSnapshot item = lopitems.next();
                    if (item.getKey().equals("De")) {
                        de = item.getValue().toString();
                    }
                }
                Iterator<DataSnapshot> deitems = dataSnapshot.child("DanhSachGiaoVien").child(giaovien).child("DanhSachDe").child(de).getChildren().iterator();
                while (deitems.hasNext()) {
                    String noidung = "", dapana = "", dapanb = "", dapanc = "", dapand = "";
<<<<<<< HEAD
                    int id = 0, dapandung = 0;

                    DataSnapshot item = deitems.next();
                    id = Integer.parseInt(item.getKey());
=======
                    String id = "0";
                    int dapandung = 0;

                    DataSnapshot item = deitems.next();
                    id = item.getKey();
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a

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
<<<<<<< HEAD
                    dsCauHoi.add(new Question(id, noidung, dapana, dapanb, dapanc, dapand, 0, dapandung));
=======
                    dsCauHoi.add(new CauHoi(id, noidung, dapana, dapanb, dapanc, dapand, 0+"", dapandung));
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
                }
                soCauToiDa = dsCauHoi.size();
                soCau = 0;
                bg_dapan = R.drawable.bgdapan;
                bg_dapan_chon = R.drawable.bgdapanchon;

                cauHoiTiepTheo();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        Message.showDialog(ThiTracNghiemActivity.this,dsCauHoi.size()+"");
    }


    private void layCauHoi() {

        // Nếu kiểm tra số câu hỏi là không hợp lệ thì không xử lý
        if (!kiemTraHopLeCauHoi()) {
            return;
        }

        tvSoCau.setText(dinhDangSoCau());

        cauHoi = dsCauHoi.get(soCau - 1);
        if (cauHoi == null) {
            return;
        }

//        tvCauHoi.setText("Câu hỏi: " + cauHoi.getQuestion());
//        tvDapAnA.setText("(A). " + cauHoi.getResult1());
//        tvDapAnB.setText("(B). " + cauHoi.getResult2());
//        tvDapAnC.setText("(C). " + cauHoi.getResult3());
//        tvDapAnD.setText("(D). " + cauHoi.getResult4());
        Picasso.with(this).load(cauHoi.getQuestion()).into(tvCauHoi);
        Picasso.with(this).load(cauHoi.getResult1()).into(tvDapAnA);
        Picasso.with(this).load(cauHoi.getResult2()).into(tvDapAnB);
        Picasso.with(this).load(cauHoi.getResult3()).into(tvDapAnC);
        Picasso.with(this).load(cauHoi.getResult4()).into(tvDapAnD);

        // lay background dap an chon truoc do
        layBackground(cauHoi.getResultSelect());
    }

    private void layBackground(int dapAn) {

        layoutA.setBackgroundResource(bg_dapan);
        layoutB.setBackgroundResource(bg_dapan);
        layoutC.setBackgroundResource(bg_dapan);
        layoutD.setBackgroundResource(bg_dapan);

        if (dapAn == 1) {
            layoutA.setBackgroundResource(bg_dapan_chon);
        } else if (dapAn == 2) {
            layoutB.setBackgroundResource(bg_dapan_chon);
        } else if (dapAn == 3) {
            layoutC.setBackgroundResource(bg_dapan_chon);
        } else if (dapAn == 4) {
            layoutD.setBackgroundResource(bg_dapan_chon);
        }
    }

    private String dinhDangSoCau() {
        return soCau + "/" + soCauToiDa;
    }

    private boolean kiemTraHopLeCauHoi() {

        if (soCau < 1) {
            soCau = 1;
            return false;
        }

        if (soCau > soCauToiDa) {
            soCau = soCauToiDa;
            return false;
        }

        return true;
    }

    private boolean kiemTraHopLeKhiKetThuc() {

<<<<<<< HEAD
        for (Question cauHoi : dsCauHoi) {
=======
        for (CauHoi cauHoi : dsCauHoi) {
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
            if (cauHoi.getResultSelect() <= 0) {
                return false;
            }
        }

        return true;
    }

    private void cauHoiTiepTheo() {

        soCau++;

        layCauHoi();
    }

    private void cauHoiTruocDo() {

        soCau--;

        layCauHoi();
    }

    private void KetThucPhanThi() {

        boolean hopLe = kiemTraHopLeKhiKetThuc();

        String contentMsg = hopLe ? "Bạn có chắc chắn muốn kết thúc phần thi của mình không?" :
                "Vẫn còn một số câu chưa chọn. \nBạn có chắc chắn muốn kết thúc phần thi của mình không?";

        AlertDialog.Builder message = new AlertDialog.Builder(this)
                .setTitle("Xác nhận")
                .setMessage(contentMsg)
                .setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tinhKetQuaPhanThi();
                    }
                })
                .setPositiveButton("Thi tiếp", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        message.show();

    }

    private void tinhKetQuaPhanThi() {

        int soCauDung = 0;
        double diemSo = 0;

<<<<<<< HEAD
        for (Question cauHoi : dsCauHoi) {
=======
        for (CauHoi cauHoi : dsCauHoi) {
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a

            if (cauHoi.getResultSelect() == cauHoi.getResult()) {
                soCauDung++;
            }
        }

        diemSo = (double) ((soCauDung * 100) / soCauToiDa)/10;

        nopbai=true;
        Intent intent = new Intent(ThiTracNghiemActivity.this, KetQuaThiActivity.class);
        intent.putExtra(Constants.SO_CAU_DUNG, (soCauDung + "/" + soCauToiDa));
        intent.putExtra(Constants.SO_DIEM_DAT_DUOC, diemSo);

        startActivity(intent);

        finish();
    }

}
