package detai.android.activity.giaovien;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.UUID;

import detai.android.Object.session.SessionManager;
import detai.android.thitracnghiem.R;

/**
 * Created by hoang on 08-May-18.
 */

public class ChinhSuaCauHoiActivity extends AppCompatActivity {

    final int NOIDUNG = 1;
    final int DAPANA = 2;
    final int DAPANB = 3;
    final int DAPANC = 4;
    final int DAPAND = 5;

    String id = "", noidung = "", dapana = "", dapanb = "", dapanc = "", dapand = "";
    int dapandung = 0;
    String de = "", cauhoi = "";
    TextView tvTitle;
    ImageView imvNoiDung;
    ImageView imvDapAnA;
    ImageView imvDapAnB;
    ImageView imvDapAnC;
    ImageView imvDapAnD;
    RadioButton rbA;
    RadioButton rbB;
    RadioButton rbC;
    RadioButton rbD;
    Button btnBack;
    String delValue = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaovien_chinhsuacauhoi);
        addControls();
        addEvents();
        khoiTaoGiaTriBanDau();
    }

    private void addControls() {
        tvTitle = findViewById(R.id.tvTitle);
        imvNoiDung = findViewById(R.id.imvNoiDung);
        imvDapAnA = findViewById(R.id.imvDapAnA);
        imvDapAnB = findViewById(R.id.imvDapAnB);
        imvDapAnC = findViewById(R.id.imvDapAnC);
        imvDapAnD = findViewById(R.id.imvDapAnD);
        rbA = findViewById(R.id.rbA);
        rbB = findViewById(R.id.rbB);
        rbC = findViewById(R.id.rbC);
        rbD = findViewById(R.id.rbD);
        btnBack = findViewById(R.id.btnBack);
    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imvNoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, NOIDUNG);
            }
        });

        imvDapAnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, DAPANA);
            }
        });

        imvDapAnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, DAPANB);
            }
        });

        imvDapAnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, DAPANC);
            }
        });

        imvDapAnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, DAPAND);
            }
        });
        SessionManager sessionManager = new SessionManager(ChinhSuaCauHoiActivity.this);
        final DatabaseReference daDung = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                .child(sessionManager.getUsername())
                .child("DanhSachDe").child(de).child(cauhoi).child("6");

        rbA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daDung.setValue(1);
            }
        });
        rbB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daDung.setValue(2);
            }
        });
        rbC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daDung.setValue(3);
            }
        });
        rbD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daDung.setValue(4);
            }
        });
    }

    private void khoiTaoGiaTriBanDau() {
        Intent intent = getIntent();
        de = intent.getStringExtra("De");
        cauhoi = intent.getStringExtra("CauHoi");

        tvTitle.setText("Đề " + de + " - Câu " + cauhoi);

        final SessionManager sessionManager = new SessionManager(ChinhSuaCauHoiActivity.this);
        Query fbdb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/");
        fbdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> cauhoiitems = dataSnapshot.child("DanhSachGiaoVien").child(sessionManager.getUsername())
                        .child("DanhSachDe").child(de).child(cauhoi).getChildren().iterator();
                id = cauhoi;
                while (cauhoiitems.hasNext()) {
                    DataSnapshot item2 = cauhoiitems.next();
                    if (Integer.parseInt(item2.getKey()) == 1)
                        noidung = item2.getValue().toString();
                    else if (Integer.parseInt(item2.getKey()) == 2)
                        dapana = item2.getValue().toString();
                    else if (Integer.parseInt(item2.getKey()) == 3)
                        dapanb = item2.getValue().toString();
                    else if (Integer.parseInt(item2.getKey()) == 4)
                        dapanc = item2.getValue().toString();
                    else if (Integer.parseInt(item2.getKey()) == 5)
                        dapand = item2.getValue().toString();
                    else if (Integer.parseInt(item2.getKey()) == 6)
                        dapandung = Integer.parseInt(item2.getValue().toString());
                }
                Picasso.with(ChinhSuaCauHoiActivity.this).load(noidung).into(imvNoiDung);
                Picasso.with(ChinhSuaCauHoiActivity.this).load(dapana).into(imvDapAnA);
                Picasso.with(ChinhSuaCauHoiActivity.this).load(dapanb).into(imvDapAnB);
                Picasso.with(ChinhSuaCauHoiActivity.this).load(dapanc).into(imvDapAnC);
                Picasso.with(ChinhSuaCauHoiActivity.this).load(dapand).into(imvDapAnD);
                switch (dapandung) {
                    case 1:
                        rbA.setChecked(true);
                        break;
                    case 2:
                        rbB.setChecked(true);
                        break;
                    case 3:
                        rbC.setChecked(true);
                        break;
                    case 4:
                        rbD.setChecked(true);
                        break;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        final FirebaseStorage storage = FirebaseStorage.getInstance("gs://tracnghiem-data001.appspot.com");
        final StorageReference storageReference = storage.getReference();
        final SessionManager sessionManager = new SessionManager(ChinhSuaCauHoiActivity.this);
        ImageView imageView;
        switch (requestCode) {
            case NOIDUNG:
                if (resultCode == RESULT_OK && data != null) {
                    imageView = imvNoiDung;
                    delValue = noidung;
                }
                break;
            case DAPANA:
                if (resultCode == RESULT_OK && data != null) {
                    imageView = imvDapAnA;
                    delValue = dapana;
                }
                break;
            case DAPANB:
                if (resultCode == RESULT_OK && data != null) {
                    imageView = imvDapAnB;
                    delValue = dapanb;
                }
                break;
            case DAPANC:
                if (resultCode == RESULT_OK && data != null) {
                    imageView = imvDapAnC;
                    delValue = dapanc;
                }
                break;
            case DAPAND:
                if (resultCode == RESULT_OK && data != null) {
                    imageView = imvDapAnD;
                    delValue = dapand;
                }
                break;
        }

        imvNoiDung.setImageURI(data.getData()); //*

        final ProgressDialog progressDialog = new ProgressDialog(ChinhSuaCauHoiActivity.this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        StorageReference ref = storageReference.child("image" + UUID.randomUUID().toString());
        ref.putFile(data.getData())
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Uri uri = taskSnapshot.getDownloadUrl();
                        final String url = uri.getPath();
                        storage.getReferenceFromUrl(delValue).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                switch (requestCode) {
                                    case NOIDUNG:
                                        noidung = url;
                                        break;
                                    case DAPANA:
                                        dapana = url;
                                        break;
                                    case DAPANB:
                                        dapanb = url;
                                        break;
                                    case DAPANC:
                                        dapanc = url;
                                        break;
                                    case DAPAND:
                                        dapand = url;
                                        break;
                                }
                                FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                                        .child("DanhSachGiaoVien").child(sessionManager.getUsername())
                                        .child("DanhSachDe").child(de).child(cauhoi)
                                        .child(requestCode + "")
                                        .setValue("https://firebasestorage.googleapis.com" + url + "?alt=media");
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(ChinhSuaCauHoiActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                .getTotalByteCount());
                        progressDialog.setMessage("Uploaded " + (int) progress + "%");
                    }
                });
    }
}
