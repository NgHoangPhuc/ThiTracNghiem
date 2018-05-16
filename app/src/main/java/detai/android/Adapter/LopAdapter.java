package detai.android.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import detai.android.Object.Lop;
import detai.android.Object.session.SessionManager;
import detai.android.thitracnghiem.R;

/**
 * Created by hoang on 06-May-18.
 */

public class LopAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Lop> list = new ArrayList<>();

    public LopAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Lop> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,parent,false);

        TextView tvTenLop = convertView.findViewById(R.id.tvTenLop);
        TextView tvSiSo = convertView.findViewById(R.id.tvSiSo);
        final Spinner spinner = convertView.findViewById(R.id.spinner);

        tvTenLop.setText(list.get(position).getName());
        tvSiSo.setText(list.get(position).getSiso()+"");

        final ArrayList<String> listde = new ArrayList<>();
        listde.add("Trống");

        final SessionManager sessionManager = new SessionManager(context);
        Query danhsachde = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                .child("DanhSachGiaoVien").child(sessionManager.getUsername()).child("DanhSachDe");

        danhsachde.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                int posiSpinner = 0;
                while (items.hasNext()){
                    DataSnapshot item = items.next();
                    listde.add(item.getKey());
//                    Message.showDialog(context,item.getKey().toString()+";"+list.get(position).getDe()+";"+item.getKey().toString().equals(list.get(position).getDe())+";");
                    if(item.getKey().toString().equals(list.get(position).getDe())) {
                        posiSpinner = listde.size() - 1;
                    }
                }
                spinner.setAdapter(new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,listde));
                spinner.setSelection(posiSpinner);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posi, long id) {
                String de = spinner.getItemAtPosition(posi).toString();
                if(de.equals("Trống"))
                    de = "-1";

                FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                        .child("DanhSachGiaoVien").child(sessionManager.getUsername())
                        .child("DanhSachLop").child(list.get(position).getName()).child("De").setValue(de);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
            AlertDialog.Builder alertbuild = new AlertDialog.Builder(context);
            alertbuild.setMessage("Bạn có muốn xóa lớp này?");
            alertbuild.setCancelable(true);
            alertbuild.setPositiveButton(
                    "Có",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")
                                    .child("DanhSachGiaoVien").child(sessionManager.getUsername()).child("DanhSachLop").child(list.get(position).getName()).removeValue();
                            list.remove(position);
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
