package developer.code.kpchandora.calcdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.button_seven)
    Button buttonSeven;
    @BindView(R.id.button_eight)
    Button buttonEight;
    @BindView(R.id.button_nine)
    Button buttonNine;
    @BindView(R.id.button_divide)
    Button buttonDivide;
    @BindView(R.id.button_four)
    Button buttonFour;
    @BindView(R.id.button_five)
    Button buttonFive;
    @BindView(R.id.button_six)
    Button buttonSix;
    @BindView(R.id.button_multiply)
    Button buttonMultiply;
    @BindView(R.id.button_one)
    Button buttonOne;
    @BindView(R.id.button_two)
    Button buttonTwo;
    @BindView(R.id.button_three)
    Button buttonThree;
    @BindView(R.id.button_subtract)
    Button buttonSubtract;
    @BindView(R.id.button_dot)
    Button buttonDot;
    @BindView(R.id.button_zero)
    Button buttonZero;
    @BindView(R.id.button_equals)
    Button buttonEquals;
    @BindView(R.id.button_add)
    Button buttonAdd;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.button_clear)
    Button buttonClear;
    @BindView(R.id.resultTextView)
    TextView resultTextView;

    private double result;
    private StringBuilder builder;
    private StringBuilder operationBuilder;

    private static final int OPERATION_ADD = 0;
    private static final int OPERATION_SUB = 1;
    private static final int OPERATION_MUL = 2;
    private static final int OPERATION_DIV = 3;
    private boolean containsDot;
    private ArrayList<Integer> operationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        result = 0;
        containsDot = false;
        builder = new StringBuilder();
        operationBuilder = new StringBuilder();
        operationList = new ArrayList<>();
    }

    public void onClick(View view) {

        String text = "";
        int builderCount = builder.length();
        String splitText = "";

        switch (view.getId()) {
            case R.id.button_one:
                text = "1";
                break;
            case R.id.button_two:
                text = "2";
                break;
            case R.id.button_three:
                text = "3";
                break;
            case R.id.button_four:
                text = "4";
                break;
            case R.id.button_five:
                text = "5";
                break;
            case R.id.button_six:
                text = "6";
                break;
            case R.id.button_seven:
                text = "7";
                break;
            case R.id.button_eight:
                text = "8";
                break;
            case R.id.button_nine:
                text = "9";
                break;
            case R.id.button_zero:
                text = "0";
                break;
            case R.id.button_add:
                if (builderCount != 0) {
                    if (checkOperationChar(OPERATION_ADD)) {
                        text = "+";
                        removeLastChar("+");
                    } else {
                        text = "+";
                        splitText = ":";
                        operationList.add(OPERATION_ADD);
                    }
                    containsDot = false;
                }
                break;
            case R.id.button_subtract:
                if (builderCount != 0) {
                    if (checkOperationChar(OPERATION_SUB)) {
                        text = "-";
                        removeLastChar("-");
                    } else {
                        text = "-";
                        splitText = ":";
                        operationList.add(OPERATION_SUB);
                    }
                    containsDot = false;
                }
                break;
            case R.id.button_multiply:
                if (builderCount != 0) {
                    if (checkOperationChar(OPERATION_MUL)) {
                        text = "×";
                        removeLastChar("×");
                    } else {
                        text = "×";
                        splitText = ":";
                        operationList.add(OPERATION_MUL);
                    }
                    containsDot = false;
                }
                break;
            case R.id.button_divide:
                if (builderCount != 0) {
                    if (checkOperationChar(OPERATION_DIV)) {
                        text = "÷";
                        removeLastChar("÷");
                    } else {
                        text = "÷";
                        splitText = ":";
                        operationList.add(OPERATION_DIV);
                    }
                    containsDot = false;
                }
                break;
            case R.id.button_dot:
                if (!containsDot) {
                    text = ".";
                    containsDot = true;
                }
                break;
            case R.id.button_equals:
                double result = doCalculation();
                resultTextView.setText(result + "");
                return;
            case R.id.button_clear:
                builder.setLength(0);
                operationList.clear();
                operationBuilder.setLength(0);
                text = "clear";
                containsDot = false;
                break;
//            case R.id.button_delete:
//                if (builderCount > 0) {
//                    builder.deleteCharAt(builder.length() - 1);
//                }
//                if (builder.length() == 0) {
//                    text = "clear";
//                }
//                break;
        }

        if (builderCount < 10) {
            if (TextUtils.isDigitsOnly(text) || text.equals(".") || splitText.equals(":")) {
                if (splitText.equals(":")) {
                    operationBuilder.append(splitText);
                } else {
                    operationBuilder.append(text);
                }
            }
        }

        Log.i(TAG, "onClick: " + operationBuilder);
        Log.i(TAG, "onClick: " + operationList);

        if (/*text.equals("") ||*/ text.equals("clear")) {
            resultTextView.setText("0");
        } else {
            int count = builder.length();
            if (count < 10) {
                resultTextView.setText(builder.append(text));
            }
            if (count == 0 && text.equals("")) {
                resultTextView.setText("0");
            }

        }
    }

    private void removeLastChar(String s) {
        int count = builder.length();
        if (count > 0) {
//            builder.replace(count - 1, count, s);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    //Checks if last char is an operation or not
    private boolean checkOperationChar(int tag) {
        String lastChar = String.valueOf(builder.toString().charAt(builder.length() - 1));

        if (lastChar.equals("+") || lastChar.equals("-") ||
                lastChar.equals("×") || lastChar.equals("÷")) {
            if (operationList.size() > 0) {
                operationList.remove(operationList.size() - 1);
                operationList.add(tag);
            }
            return true;
        }
        return false;
    }

    private double doCalculation() {

        String[] numberStrings = operationBuilder.toString().split(":");

        double result = 0;
        int tag = 0;
        for (int i = 0; i < numberStrings.length; i++) {

            double num = Double.parseDouble(numberStrings[i]);

            if (operationList.size() > 0) {
                tag = operationList.get(0);
                operationList.remove(0);
            }

            switch (tag) {
                case OPERATION_ADD:
                    result += num;
                    break;
                case OPERATION_SUB:
                    result = num - result;
                    break;
                case OPERATION_MUL:

                    break;
                case OPERATION_DIV:

                    break;
            }

        }
        Log.i(TAG, "doCalculation: " + result);
        return result;
    }

}
