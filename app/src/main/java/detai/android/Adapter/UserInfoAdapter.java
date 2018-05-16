package detai.android.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import detai.android.Object.UserInfo;
import detai.android.thitracnghiem.R;

/**
 * Created by admin on 9/24/2016.
 */
public class UserInfoAdapter extends ArrayAdapter<UserInfo> {

    Activity context;
    int resource;
    List<UserInfo> objects;

    public UserInfoAdapter(Activity context, int resource, List<UserInfo> objects) {
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

        UserInfo info = this.objects.get(position);

        tvName.setText(info.getTenThiSinh());
        tvMoney.setText(info.getSoDiemDatDuoc());

        return row;
    }
}
