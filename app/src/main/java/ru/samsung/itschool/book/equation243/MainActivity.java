package ru.samsung.itschool.book.equation243;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import static java.lang.Math.sqrt;


public class MainActivity extends Activity {
    // Вызывается при создании Активности
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Инициализирует Активность.
        setContentView(R.layout.activity_main);
    }

    /**
     * Вызывается при нажатии пользователем на кнопку Решить
     */
    public void solveEquation(View view) {
        double D = 0;
        double a = 0;
        double b = 0;
        double c = 0;
        // ax^ 2 + bx + c = 0
        a = Double.parseDouble(((EditText)
                findViewById(R.id.coefficient_a)).getText().toString());
        b = Double.parseDouble(((EditText)
                findViewById(R.id.coefficient_b)).getText().toString());
        c = Double.parseDouble(((EditText)
                findViewById(R.id.coefficient_c)).getText().toString());
        TextView result = (TextView) findViewById(R.id.result);
        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    result.setText("x - любое число");
                } else result.setText("x - не существует");

            } else if (c == 0) {
                result.setText("0");
            } else result.setText("" + String.valueOf((-c) / b));
        } else if (b == 0) {
            if (c == 0) {
                result.setText("0");
            } else if (c > 0 && a > 0 || c < 0 && a < 0 || c == 0 && a < 0) {
                result.setText("x - не существует");
            } else {
                double a1 = Math.sqrt((-c) / a);
                result.setText("" + String.valueOf(a1) + "; -" + String.valueOf(a1));
            }
        }else if (c == 0){
            result.setText("" + "0; " + String.valueOf((-b) / a));
        }else {
            D = b * b - 4 * a * c;
            if (D ==0){
                result.setText("" + String.valueOf((-b) / 2 * a));
            }else if (D < 0){
                result.setText("x - не существует");
            }else result.setText("" + String.valueOf(((-b - sqrt(D)) / 2 * a)) + "; " + String.valueOf(((-b + sqrt(D)) / 2 * a)));
        }
    }

}

 //result.setText("" + String.valueOf(((-b + sqrt(D)) / 2 * a)) + "; " + String.valueOf(((-b + sqrt(D)) / 2 * a)));
