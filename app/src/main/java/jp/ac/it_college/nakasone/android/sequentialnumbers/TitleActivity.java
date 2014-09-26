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
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new TouchStartFragment())
                    .commit();
        }
    }

    private void startExamActivity() {
        Intent intent = new Intent(getApplicationContext(), ExamActivity.class);
        startActivity(intent);
    }

    public static class TouchStartFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_touch, container, false);
            view.findViewById(R.id.text_touch).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((TitleActivity)getActivity()).startExamActivity();
                }
            });
            return view;
        }
    }
}
