package events.tgh2020.androidlab.astrology;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 正しい日付データをユーザーに確実に入力してもらうために、
        // DatePickerというビューを表示して強制的に選ばせる方法をとっています。
        // 以下は、DatePickerDialogという簡易的な日付選択機能を使った実装例です。
        final EditText etBirthday = findViewById(R.id.etBirthday);
        final Calendar calendar = Calendar.getInstance(); // 今日の日付
        // DatePickerDialogの日付とテキストボックスの値との変更を連動させています。
        final DatePickerDialog.OnDateSetListener dialog =
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
                        etBirthday.setText(fmt.format(calendar.getTime()));
                    }
                };

        // 誕生日のテキストボックスがクリックされたときに走る処理。
        // DatePickerDialogを表示させるようにします（初期値は本日日付）。
        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(
                        MainActivity.this,
                        dialog,
                        calendar.get(Calendar.YEAR ) ,
                        calendar.get(Calendar.MONTH) ,
                        calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        Button b = findViewById(R.id.theButton);
        // theButtonという名前のボタンが押下されたときに走る処理
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // テキストボックス（「おなまえ」と「お誕生日」）の値を文字列として取り出します。
                EditText etName = findViewById(R.id.etName);
                String name = etName.getText().toString();
                EditText etBirthday = findViewById(R.id.etBirthday);
                String birthday = etBirthday.getText().toString();

                // 名前と誕生日のどちらか／両方が未入力の場合、エラーメッセージを表示して戻ります。
                if (name.isEmpty() ){
                    etName.setError("名前を入力してください");
                    return;
                }
                if (birthday.isEmpty()){
                    etBirthday.setError("誕生日を選んでください");
                    return;
                }

                // 遷移先のアクティビティを指定
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("NAME", name);
                intent.putExtra("BIRTHDAY", birthday);

                // 名前と誕生日を添えて次画面(ResultActivity)に制御を渡します。
                startActivity(intent);
            }
        });

    }
}
