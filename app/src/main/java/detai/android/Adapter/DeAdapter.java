package detai.android.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import detai.android.Object.session.SessionManager;
import detai.android.activity.giaovien.QuanLyCauHoiActivity;
import detai.android.thitracnghiem.R;

/**
 * Created by hoang on 06-May-18.
 */

public class DeAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<String> listde;
    int dem;

    public DeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> listde) {
        super(context, resource, listde);
        this.context = context;
        this.resource = resource;
        this.listde = listde;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,parent,false);
        final TextView tvDe = convertView.findViewById(R.id.tvDe);
        final TextView tvSoCau = convertView.findViewById(R.id.tvSoCau);

        tvDe.setText(listde.get(position));

        SessionManager sessionManager = new SessionManager(context);
        Query de = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                .child("DanhSachGiaoVien").child(sessionManager.getUsername()).child("DanhSachDe").child(listde.get(position));
        dem = 0;
        tvSoCau.setText(dem+"");
        de.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                dem++;
                tvSoCau.setText(dem+"");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QuanLyCauHoiActivity.class);
                intent.putExtra("De", tvDe.getText());

                context.startActivity(intent);
            }
        });
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alertbuild = new AlertDialog.Builder(context);
                alertbuild.setMessage("Bạn có muốn xóa đề này?");
                alertbuild.setCancelable(true);
                alertbuild.setPositiveButton(
                        "Có",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                listde.remove(position);
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
        return convertView;
    }
}
