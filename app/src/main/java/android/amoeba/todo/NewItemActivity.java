package android.amoeba.todo;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Button;

import org.json.JSONObject;

import java.sql.Time;
import java.util.Date;

public class NewItemActivity extends Activity{
    protected Button saveItemButton;
    public static String title;
    public static String reminder;
    public static String date;
    public static String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newitem);
        saveItemButton = (Button)findViewById(R.id.saveItemButton);

//        Task task = new Task();

        saveItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TodoActivity.class);


                final EditText titleData = (EditText)findViewById(R.id.title);
                title = titleData.getText().toString();

                if (title == ""){
                    title = null;
                }

                final EditText reminderData = (EditText)findViewById(R.id.reminder);
                reminder = reminderData.getText().toString();

                if (reminder == ""){
                    reminder = null;
                }

                final Button timeData = (Button)findViewById(R.id.button);
                time = timeData.getText().toString();

                if (time == "Select Time"){
                    time = null;
                }

                final Button dateData = (Button)findViewById(R.id.button2);
                date = dateData.getText().toString();

                if (date == "Select Date"){
                    date = null;
                }
                Task task = new Task(title, reminder, date, time);
                JSONObject jsonTask = JsonUtil.toJSon(task);
                intent.putExtra("jsonTask", jsonTask.toString());
                System.out.println("Title="+title + " Reminder=" + reminder + " Time=" + time + " Date=" + date);
                startActivity(intent);
            }
        });
    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }
}
