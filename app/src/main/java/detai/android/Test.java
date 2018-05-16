package detai.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import static android.widget.Toast.LENGTH_LONG;
//import com.google.firebase.database.ValueEventListener;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//import detai.android.CommonUtils.Constants;
//import detai.android.CommonUtils.Question;

/**
 * Created by hoang on 30-Apr-18.
 */

public class Test {
    public Test(AppCompatActivity activity){
//        final DatabaseReference[] mFirebaseRef = {FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/")};
//        Query listQ = mFirebaseRef[0].child("DanhSachCauHoi");
//        final int[] size = new int[1];
//        listQ.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                size[0] = size[0] + 1;
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


//        try {
//            DatabaseReference myFirebaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tracnghiem-data001.firebaseio.com/");
//
//            InputStream fis = activity.getAssets().open(Constants.DATABASE_NAME_DATA);
//            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
//
//            String line;
//            int id = 0;
//            while ((line = br.readLine()) != null) {
//                line = line.trim();
//                if (line != null && !line.isEmpty()) {
//                    String[] list = line.split("\t");
//                    if (list != null && list.length >= 7) {
//                        myFirebaseRef.child("DanhSachCauHoi").child("Q"+id+"").child("id").setValue(id+"");
//                        myFirebaseRef.child("DanhSachCauHoi").child("Q"+id+"").child("question").setValue(list[0]);
//                        myFirebaseRef.child("DanhSachCauHoi").child("Q"+id+"").child("result1").setValue(list[1]);
//                        myFirebaseRef.child("DanhSachCauHoi").child("Q"+id+"").child("result2").setValue(list[2]);
//                        myFirebaseRef.child("DanhSachCauHoi").child("Q"+id+"").child("result3").setValue(list[3]);
//                        myFirebaseRef.child("DanhSachCauHoi").child("Q"+id+"").child("result4").setValue(list[4]);
//                        myFirebaseRef.child("DanhSachCauHoi").child("Q"+id+"").child("level").setValue(list[6]);
//                        myFirebaseRef.child("DanhSachCauHoi").child("Q"+id+"").child("result").setValue(list[5]);
//                        id++;
////                    db.Insert(new Question(
////                            idx++,
////                            list[0],
////                            list[1],
////                            list[2],
////                            list[3],
////                            list[4],
////                            Integer.parseInt(list[5]),
////                            Integer.parseInt(list[6])
////                    ));
//                    }
//                }
//            }
//            br.close();
//        }catch (IOException ioe){}




//        final FirebaseDatabase database = FirebaseDatabase.getInstance("https://gameapp-339b3.firebaseio.com/");
//        final DatabaseReference myRef = database.getReference().child("Rank_table");
//        Query query = myRef.orderByChild("score").limitToLast(2);
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
//                while (items.hasNext()) {
//                    DataSnapshot item = items.next();
//                    if(item == null){
//                        return;
//                    }
//                    Player p = new Player();
//                    p.setEmail(item.child("email").getValue().toString());
//                    p.setName(item.child("name").getValue().toString());
//                    p.setScore(Integer.parseInt(item.child("score").getValue().toString()));
//                    p.setTime_stamp(item.child("time_stamp").getValue().toString());
////                    arrayPlayer.add(p);
//                    result += " " + p.getName() + " " + p.getScore();
//                }
//
//                txt_view.setText(result);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w("TAG", "Failed to read value.", databaseError.toException());
//            }
//        });
    }
}
