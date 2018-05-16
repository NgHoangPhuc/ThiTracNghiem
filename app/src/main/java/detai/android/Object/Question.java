package detai.android.Object;

import android.content.ContentValues;
import android.database.Cursor;

import java.io.Serializable;

/**
 * Created by admin on 9/24/2016.
 */
public class Question implements Serializable {

    private int id;

    private String question;

    private String result1;

    private String result2;

    private String result3;

    private String result4;

    private int level;

    private int result;

    private int resultSelect;

    public Question() {
    }

    public Question(int id, String question, String result1, String result2, String result3,
                    String result4, int level, int result) {
        this.id = id;
        this.question = question;
        this.result1 = result1;
        this.result2 = result2;
        this.result3 = result3;
        this.result4 = result4;
        this.level = level;
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question1 = (Question) o;

        if (id != question1.id) return false;
        if (result != question1.result) return false;
        if (question != null ? !question.equals(question1.question) : question1.question != null)
            return false;
        if (result1 != null ? !result1.equals(question1.result1) : question1.result1 != null)
            return false;
        if (result2 != null ? !result2.equals(question1.result2) : question1.result2 != null)
            return false;
        if (result3 != null ? !result3.equals(question1.result3) : question1.result3 != null)
            return false;
        return result4 != null ? result4.equals(question1.result4) : question1.result4 == null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public void ImportQuestion(Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndexOrThrow(
                Constants.COL_CAUHOI_ID)));

        this.setQuestion(cursor.getString(cursor.getColumnIndexOrThrow(
                Constants.COL_CAUHOI_NOIDUNG)));

        this.setResult(cursor.getInt(cursor.getColumnIndexOrThrow(
                Constants.COL_CAUHOI_DAPANDUNG)));

        this.setResult1(cursor.getString(cursor.getColumnIndexOrThrow(
                Constants.COL_CAUHOI_DAPANA)));

        this.setResult2(cursor.getString(cursor.getColumnIndexOrThrow(
                Constants.COL_CAUHOI_DAPANB)));

        this.setResult3(cursor.getString(cursor.getColumnIndexOrThrow(
                Constants.COL_CAUHOI_DAPANC)));

        this.setResult4(cursor.getString(cursor.getColumnIndexOrThrow(
                Constants.COL_CAUHOI_DAPAND)));
    }
}
