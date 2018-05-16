package detai.android.Adapter;

import android.content.Context;
<<<<<<< HEAD
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
=======
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

<<<<<<< HEAD
=======
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

<<<<<<< HEAD
import detai.android.Object.Question;
import detai.android.activity.giaovien.ChinhSuaCauHoiActivity;
import detai.android.activity.giaovien.QuanLyCauHoiActivity;
=======
import detai.android.Object.CauHoi;
import detai.android.Object.session.SessionManager;
import detai.android.activity.giaovien.ChinhSuaCauHoiActivity;
import detai.android.activity.giaovien.QuanLyCauHoiActivity;
import detai.android.activity.giaovien.TaoMoiCauHoiActivity;
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
import detai.android.thitracnghiem.R;

/**
 * Created by hoang on 08-May-18.
 */

public class CauHoiAdapter extends ArrayAdapter {
    Context context;
    int resource;
<<<<<<< HEAD
    ArrayList<Question> listcauhoi;
    public CauHoiAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Question> listcauhoi) {
=======
    ArrayList<CauHoi> listcauhoi;

    public CauHoiAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CauHoi> listcauhoi) {
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
        super(context, resource, listcauhoi);
        this.context = context;
        this.resource = resource;
        this.listcauhoi = listcauhoi;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
<<<<<<< HEAD
        convertView = LayoutInflater.from(context).inflate(resource,parent,false);
=======
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a

        TextView tvCauHoi = convertView.findViewById(R.id.tvTitle);
        ImageView imvNoiDung = convertView.findViewById(R.id.imvNoiDung);
        ImageView imvDapAnA = convertView.findViewById(R.id.imvDapAnA);
        ImageView imvDapAnB = convertView.findViewById(R.id.imvDapAnB);
        ImageView imvDapAnC = convertView.findViewById(R.id.imvDapAnC);
        ImageView imvDapAnD = convertView.findViewById(R.id.imvDapAnD);
        RadioButton rbA = convertView.findViewById(R.id.rbA);
        RadioButton rbB = convertView.findViewById(R.id.rbB);
        RadioButton rbC = convertView.findViewById(R.id.rbC);
        RadioButton rbD = convertView.findViewById(R.id.rbD);

<<<<<<< HEAD
        tvCauHoi.setText("Câu hỏi "+listcauhoi.get(position).getId());
=======
        tvCauHoi.setText("Câu hỏi " + listcauhoi.get(position).getId());
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
        Picasso.with(context).load(listcauhoi.get(position).getQuestion()).into(imvNoiDung);
        Picasso.with(context).load(listcauhoi.get(position).getResult1()).into(imvDapAnA);
        Picasso.with(context).load(listcauhoi.get(position).getResult2()).into(imvDapAnB);
        Picasso.with(context).load(listcauhoi.get(position).getResult3()).into(imvDapAnC);
        Picasso.with(context).load(listcauhoi.get(position).getResult4()).into(imvDapAnD);

<<<<<<< HEAD
        switch (listcauhoi.get(position).getResult()){
            case 1: rbA.setChecked(true); break;
            case 2: rbB.setChecked(true); break;
            case 3: rbC.setChecked(true); break;
            case 4: rbD.setChecked(true); break;
=======
        switch (listcauhoi.get(position).getResult()) {
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
            default:
                break;
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                Intent intent = new Intent(context, ChinhSuaCauHoiActivity.class);
                intent.putExtra("De", ((QuanLyCauHoiActivity)context).de);
                intent.putExtra("CauHoi", listcauhoi.get(position).getId()+"");
                context.startActivity(intent);
            }
        });
=======
                Intent intent;
                if (listcauhoi.get(position).getResult() == 0
                        || listcauhoi.get(position).getResult1().equals("0")
                        || listcauhoi.get(position).getResult2().equals("0")
                        || listcauhoi.get(position).getResult3().equals("0")
                        || listcauhoi.get(position).getResult4().equals("0")
                        || listcauhoi.get(position).getQuestion().equals("0")
                        ) {
                    intent = new Intent(context, TaoMoiCauHoiActivity.class);
                } else {
                    intent = new Intent(context, ChinhSuaCauHoiActivity.class);
                }
                intent.putExtra("De", ((QuanLyCauHoiActivity) context).de);
                intent.putExtra("CauHoi", listcauhoi.get(position).getId());
                context.startActivity(intent);
            }
        });
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alertbuild = new AlertDialog.Builder(context);
                alertbuild.setMessage("Bạn có thật sự muốn xóa câu hỏi này?");
                alertbuild.setCancelable(true);
                alertbuild.setPositiveButton(
                        "Có",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                FirebaseStorage storage = FirebaseStorage.getInstance("gs://tracnghiem-data001.appspot.com");
                                if(!listcauhoi.get(position).getQuestion().equals("0")&&!listcauhoi.get(position).getQuestion().equals(""))
                                storage.getReferenceFromUrl(listcauhoi.get(position).getQuestion()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                });
                                if(!listcauhoi.get(position).getResult1().equals("0")&&!listcauhoi.get(position).getResult1().equals("0"))
                                storage.getReferenceFromUrl(listcauhoi.get(position).getResult1()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                });
                                if(!listcauhoi.get(position).getResult2().equals("0")&&!listcauhoi.get(position).getResult2().equals("0"))
                                storage.getReferenceFromUrl(listcauhoi.get(position).getResult2()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                });
                                if(!listcauhoi.get(position).getResult3().equals("0")&&!listcauhoi.get(position).getResult3().equals("0"))
                                storage.getReferenceFromUrl(listcauhoi.get(position).getResult3()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                });
                                if(!listcauhoi.get(position).getResult4().equals("0")&&!listcauhoi.get(position).getResult4().equals("0"))
                                storage.getReferenceFromUrl(listcauhoi.get(position).getResult4()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                });
                                SessionManager sessionManager = new SessionManager(context);
                                FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                                        .child("DanhSachGiaoVien").child(sessionManager.getUsername())
                                        .child("DanhSachDe").child(((QuanLyCauHoiActivity)context).de).child(listcauhoi.get(position).getId()).removeValue();
                                listcauhoi.remove(position);
                                notifyDataSetChanged();
                                dialog.cancel();
                            }
                        });

                alertbuild.setNegativeButton(
                        "Không",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = alertbuild.create();
                alert11.show();
                return false;
            }
        });
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
        return convertView;
    }
}
