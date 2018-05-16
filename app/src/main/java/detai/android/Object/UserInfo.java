package detai.android.Object;

import android.content.ContentValues;
import android.database.Cursor;

import java.io.Serializable;

/**
 * Created by admin on 9/24/2016.
 */

public class UserInfo implements Serializable {

    private Integer id;

    private String tenThiSinh;

    private String soCauDung;

    private String soDiemDatDuoc;

    public UserInfo() {

    }

    public UserInfo(String tenThiSinh, String soDiemDatDuoc) {
        this.tenThiSinh = tenThiSinh;
        this.soDiemDatDuoc = soDiemDatDuoc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenThiSinh() {
        return tenThiSinh;
    }

    public void setTenThiSinh(String name) {
        this.tenThiSinh = name;
    }

    public String getSoCauDung() {
        return soCauDung;
    }

    public void setSoCauDung(String soCauDung) {
        this.soCauDung = soCauDung;
    }

    public String getSoDiemDatDuoc() {
        return soDiemDatDuoc;
    }

    public void setSoDiemDatDuoc(String money) {
        this.soDiemDatDuoc = money;
    }

    public ContentValues ExportToContentValues() {
        ContentValues info = new ContentValues();

        info.put(Constants.COL_THISINH_ID, getId());
        info.put(Constants.COL_THISINH_TENTHISINH, getTenThiSinh());
        info.put(Constants.COL_THISINH_SOCAUDUNG, getSoCauDung());
        info.put(Constants.COL_THISINH_SODIEM, getSoDiemDatDuoc());
        return info;
    }

    public void ImportUserInfo(Cursor cursor) {

        this.setId(cursor.getInt(cursor.getColumnIndexOrThrow(
                Constants.COL_THISINH_ID)));

        this.setTenThiSinh(cursor.getString(cursor.getColumnIndexOrThrow(
                Constants.COL_THISINH_TENTHISINH)));

        this.setSoCauDung(cursor.getString(cursor.getColumnIndexOrThrow(
                Constants.COL_THISINH_SOCAUDUNG)));

        this.setSoDiemDatDuoc(cursor.getString(cursor.getColumnIndexOrThrow(
                Constants.COL_THISINH_SODIEM)));
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "Tên thí sinh: '" + tenThiSinh + '\'' +
                ", Số câu đúng: " + soCauDung +
                ", Số điểm đạt được: '" + soDiemDatDuoc + '\'' +
                '}';
    }
}
