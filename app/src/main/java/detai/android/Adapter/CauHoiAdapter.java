package detai.android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import detai.android.Object.Question;
import detai.android.activity.giaovien.ChinhSuaCauHoiActivity;
import detai.android.activity.giaovien.QuanLyCauHoiActivity;
import detai.android.thitracnghiem.R;

/**
 * Created by hoang on 08-May-18.
 */

public class CauHoiAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Question> listcauhoi;
    public CauHoiAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Question> listcauhoi) {
        super(context, resource, listcauhoi);
        this.context = context;
        this.resource = resource;
        this.listcauhoi = listcauhoi;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,parent,false);

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

        tvCauHoi.setText("Câu hỏi "+listcauhoi.get(position).getId());
        Picasso.with(context).load(listcauhoi.get(position).getQuestion()).into(imvNoiDung);
        Picasso.with(context).load(listcauhoi.get(position).getResult1()).into(imvDapAnA);
        Picasso.with(context).load(listcauhoi.get(position).getResult2()).into(imvDapAnB);
        Picasso.with(context).load(listcauhoi.get(position).getResult3()).into(imvDapAnC);
        Picasso.with(context).load(listcauhoi.get(position).getResult4()).into(imvDapAnD);

        switch (listcauhoi.get(position).getResult()){
            case 1: rbA.setChecked(true); break;
            case 2: rbB.setChecked(true); break;
            case 3: rbC.setChecked(true); break;
            case 4: rbD.setChecked(true); break;
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChinhSuaCauHoiActivity.class);
                intent.putExtra("De", ((QuanLyCauHoiActivity)context).de);
                intent.putExtra("CauHoi", listcauhoi.get(position).getId()+"");
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
