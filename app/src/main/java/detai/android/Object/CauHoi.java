package detai.android.Object;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by admin on 9/24/2016.
 */
public class CauHoi implements Serializable {

    private String id;

    private String question;

    private String result1;

    private String result2;

    private String result3;

    private String result4;

    private String number;

    private int result;

    private int resultSelect;

    public CauHoi() {
    }

    public CauHoi(String id) {
        this.id = id;
    }

    public CauHoi(String id, String question, String result1, String result2, String result3,
                  String result4, String number, int result) {
        this.id = id;
        this.question = question;
        this.result1 = result1;
        this.result2 = result2;
        this.result3 = result3;
        this.result4 = result4;
        this.number = number;
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        CauHoi cauHoi = (CauHoi) o;

        return id.equals(cauHoi.id);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

    public String getResult3() {
        return result3;
    }

    public void setResult3(String result3) {
        this.result3 = result3;
    }

    public String getResult4() {
        return result4;
    }

    public void setResult4(String result4) {
        this.result4 = result4;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResultSelect() {
        return resultSelect;
    }

    public void setResultSelect(int resultSelect) {
        this.resultSelect = resultSelect;
    }

    public ContentValues ExportToContentValues() {
        ContentValues info = new ContentValues();

        info.put(Constants.COL_CAUHOI_NOIDUNG, question);
        info.put(Constants.COL_CAUHOI_DAPANA, result1);
        info.put(Constants.COL_CAUHOI_DAPANB, result2);
        info.put(Constants.COL_CAUHOI_DAPANC, result3);
        info.put(Constants.COL_CAUHOI_DAPAND, result4);
        info.put(Constants.COL_CAUHOI_DAPANDUNG, result);

        return info;
    }
}
