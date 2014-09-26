package jp.ac.it_college.nakasone.android.sequentialnumbers;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class ExamActivity extends Activity implements AdapterView.OnItemClickListener {
    private static final long timeInterval = 3000;
    private Handler mHandler;
    private boolean mIsEnded = true;
    private long startTime = 0;
    private int nextNumber = 1;
    private GridView mGrid;
    private String[] numbers = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"
    };
    private List<String> numberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        mHandler = new Handler();
        mGrid = (GridView) findViewById(R.id.numbers_grid);
        mGrid.setOnItemClickListener(this);

        startExam();
    }

    private void startExam() {
        findViewById(R.id.footer).setVisibility(View.GONE);
        setNumbers();
        final TextView elapsedTime = (TextView) findViewById(R.id.current_time);
        elapsedTime.setText("0.??");
        final Runnable time = new Runnable() {
            @Override
            public void run() {
                if (!mIsEnded) {
                    long time = System.currentTimeMillis() - startTime;
                    elapsedTime.setText(String.format("%d.??", time / 1000));
                    elapsedTime.invalidate();

                    mHandler.postDelayed(this, timeInterval);
                }
            }
        };

        mIsEnded = false;
        startTime = System.currentTimeMillis();
        mHandler.postDelayed(time, timeInterval);
    }

    private void endExam() {
        mIsEnded = true;
        final TextView elapsedTime = (TextView) findViewById(R.id.current_time);
        long time = System.currentTimeMillis() - startTime;
        elapsedTime.setText(String.format("%.2f", time / 1000f));

        findViewById(R.id.footer).setVisibility(View.VISIBLE);
    }

    public void retry(View v) {
        startExam();
    }

    public void exit(View v) {
        finish();
    }

    private void setNumbers() {
        numberList = Arrays.asList(numbers);
        Random random = new Random();
        for (int i = 0; i < numberList.size(); i++) {
            int target = random.nextInt(numberList.size());
            String tmp = numberList.get(target);
            numberList.set(target, numberList.get(i));
            numberList.set(i, tmp);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, numberList);

        mGrid.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (Integer.parseInt(numberList.get(i)) == nextNumber) {
            nextNumber++;
            ((TextView) view).setTextColor(Color.BLUE);
        }
        if (nextNumber > 25) {
            endExam();
        }
    }
}
