package detai.android.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

<<<<<<< HEAD
import detai.android.Object.UserInfo;
=======
import detai.android.Object.User;
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
import detai.android.thitracnghiem.R;

/**
 * Created by admin on 9/24/2016.
 */
<<<<<<< HEAD
public class UserInfoAdapter extends ArrayAdapter<UserInfo> {

    Activity context;
    int resource;
    List<UserInfo> objects;

    public UserInfoAdapter(Activity context, int resource, List<UserInfo> objects) {
=======
public class UserInfoAdapter extends ArrayAdapter<User> {

    Activity context;
    int resource;
    List<User> objects;

    public UserInfoAdapter(Activity context, int resource, List<User> objects) {
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        TextView tvName = (TextView) row.findViewById(R.id.tvName);
        TextView tvMoney = (TextView) row.findViewById(R.id.tvMoney);

<<<<<<< HEAD
        UserInfo info = this.objects.get(position);
=======
        User info = this.objects.get(position);
>>>>>>> 48fff061116cfa44973b356cb565bd6ee789f05a

        tvName.setText(info.getTenThiSinh());
        tvMoney.setText(info.getSoDiemDatDuoc());

        return row;
    }
}
