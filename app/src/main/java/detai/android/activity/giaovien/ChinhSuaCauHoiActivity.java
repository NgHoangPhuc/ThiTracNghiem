package detai.android.activity.giaovien;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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

import detai.android.Object.Question;
import detai.android.Object.message.Message;
import detai.android.Object.session.SessionManager;
import detai.android.thitracnghiem.R;

/**
 * Created by hoang on 08-May-18.
 */

public class ChinhSuaCauHoiActivity extends AppCompatActivity {

    final int NOIDUNG = 0;
    final int DAPANA = 1;
    final int DAPANB = 2;
    final int DAPANC = 3;
    final int DAPAND = 4;
    Uri noidung_changed;
    String a_changed = "";
    String b_changed = "";
    String c_changed = "";
    String d_changed = "";
    Question q_new = new Question();

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
    Button btnLuu, btnHuy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaovien_chinhsuade);
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
        btnLuu = findViewById(R.id.btnLuu);
        btnHuy = findViewById(R.id.btnHuy);
    }

    private void addEvents() {
        btnHuy.setOnClickListener(new View.OnClickListener() {
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
                String noidung = "", dapana = "", dapanb = "", dapanc = "", dapand = "";
                int id = 0, dapandung = 0;
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
                q_new = new Question(Integer.parseInt(cauhoi),
                        noidung,
                        dapana,
                        dapanb,
                        dapanc,
                        dapand,
                        0,
                        dapandung
                );
                rbA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        q_new.setResult(1);
                    }
                });
                rbB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        q_new.setResult(2);
                    }
                });
                rbC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        q_new.setResult(3);
                    }
                });
                rbD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        q_new.setResult(4);
                    }
                });

                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Message.showDialog(ChinhSuaCauHoiActivity.this, de + ";" + cauhoi);
                        final FirebaseStorage storage = FirebaseStorage.getInstance("gs://tracnghiem-data001.appspot.com");
                        final StorageReference storageReference = storage.getReference();
                        if (noidung_changed != null) {
                            final ProgressDialog progressDialog = new ProgressDialog(ChinhSuaCauHoiActivity.this);
                            progressDialog.setTitle("Uploading...");
                            progressDialog.show();

                            StorageReference ref = storageReference.child("image" + UUID.randomUUID().toString());
                            ref.putFile(noidung_changed)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            progressDialog.dismiss();
                                            Uri uri = taskSnapshot.getDownloadUrl();
                                            final String url = uri.getPath();
                                            storage.getReferenceFromUrl(q_new.getQuestion()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                                                            .child("DanhSachGiaoVien").child(sessionManager.getUsername())
                                                            .child("DanhSachDe").child(de).child(cauhoi)
                                                            .child("1").setValue("https://firebasestorage.googleapis.com"+url+"?alt=media");
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
//                        finish();
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case NOIDUNG:
                if (resultCode == RESULT_OK && data != null) {
                    imvNoiDung.setImageURI(data.getData());
                    noidung_changed = data.getData();
                }
                break;
            case DAPANA:
                if (resultCode == RESULT_OK && data != null) {
                    imvDapAnA.setImageURI(data.getData());
                    a_changed = data.getData().getPath();
                }
                break;
            case DAPANB:
                if (resultCode == RESULT_OK && data != null) {
                    imvDapAnB.setImageURI(data.getData());
                    b_changed = data.getData().getPath();
                }
                break;
            case DAPANC:
                if (resultCode == RESULT_OK && data != null) {
                    imvDapAnC.setImageURI(data.getData());
                    c_changed = data.getData().getPath();
                }
                break;
            case DAPAND:
                if (resultCode == RESULT_OK && data != null) {
                    imvDapAnD.setImageURI(data.getData());
                    d_changed = data.getData().getPath();
                }
                break;
        }

    }
}
