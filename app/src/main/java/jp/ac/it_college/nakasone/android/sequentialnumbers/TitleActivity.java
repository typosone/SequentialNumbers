package jp.ac.it_college.nakasone.android.sequentialnumbers;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TitleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
    }

    public void startExamActivity(View v) {
        Intent intent = new Intent(getApplicationContext(), ExamActivity.class);
        startActivity(intent);
    }

}
