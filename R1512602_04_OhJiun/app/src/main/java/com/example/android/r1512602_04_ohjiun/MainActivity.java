package com.example.android.r1512602_04_ohjiun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    final int MAX = 10;
    TextView resultView;
    boolean doesSymbolExists = true;
    boolean isMinus = false;
    String initialString;
//    char operators[] = new char[MAX];
//    int operands[] = new int[MAX];
    char tokens[] = new char[MAX];
    Stack<Float> operands = new Stack<Float>();
    Stack<Character> operators = new Stack<Character>();
//    int intStack[] = new int[MAX];
//    int postStack[] = new int[MAX];
    int top=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultView = (TextView) findViewById(R.id.result);

        Button buttonEqual = (Button) findViewById(R.id.buttonEqual) ;
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                evaluate();
            }
        });

        Button buttonCancel = (Button) findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
    }

    public void setDigit (View view) {
        int viewId = view.getId();
        Button button = (Button) findViewById(viewId);
        String newDigit = button.getText().toString();
        String result = resultView.getText().toString();
//        operands[operandTop++] = Integer.parseInt(newDigit);
        resultView.setText(result+newDigit);
        doesSymbolExists = false;
    }

    public void setSymbol (View view) {
        int viewId = view.getId();
        Button button = (Button) findViewById(viewId);
        String newSymbol = button.getText().toString();
        if (!doesSymbolExists) {
            String result = resultView.getText().toString();
//            operators[operatorTop++] = newSymbol.charAt(0);
            resultView.setText(result + newSymbol);
            doesSymbolExists = true;
        }
        else {
            if (resultView.getText().toString().equals("") &&  newSymbol.equals("-")) {
                isMinus = true;
                resultView.setText(newSymbol);
            }
        }
    }

    public void evaluate() {
        Log.v("검사", "이벨 안으로 들어옴");
        initialString = resultView.getText().toString();
        if (initialString.equals(""))
            return;

        tokens = initialString.toCharArray();
        for (int i = 0; i < tokens.length; i++) {
            StringBuffer buffer = new StringBuffer();
            try {
                if (isMinus) {
                    buffer.append('-');
                    isMinus = false;
                    i++;
                }
                if (tokens[i] == '.') {
                    float temp = operands.pop();
                    buffer.append((int) temp);
                    buffer.append('.');
                    i++;
                    while (!isOperator(i))
                        buffer.append(tokens[i++]);
                    operands.push(Float.parseFloat(buffer.toString()));
//                    buffer.setLength(0);
                }
                if (Character.isDigit(tokens[i])) {
                    while (i < tokens.length && Character.isDigit(tokens[i])) {
                        buffer.append(tokens[i++]);
                    }
                    i--;
                    operands.push(Float.parseFloat(buffer.toString()));
                }
                else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                    while (!operators.empty() && isTerm(tokens[i], operators.peek())) {
                        operands.push(calculate(operators.pop(), operands.pop(), operands.pop()));
                    }
                    operators.push(tokens[i]);
                }
            }
            catch (ArithmeticException exception) {
                resultView.setText("0으로 나눌 수 없음");
                return;
            }
        }

        try {
            while (!operators.empty())
                operands.push(calculate(operators.pop(), operands.pop(), operands.pop()));
        }
        catch (ArithmeticException exception) {
            resultView.setText("0으로 나눌 수 없음");
            return;
        }

        float result = operands.pop();
        int intResult;
        if (result == Math.round(result)) {
            intResult = (int) result;
            resultView.setText(Integer.toString(intResult));
        }
        else
            resultView.setText(Float.toString(result));

        if (result<0)
            isMinus = true;
        initialString = resultView.getText().toString();
    }

    public boolean isOperator (int i) {
        if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/')
            return true;
        else
            return false;
    }

    public boolean isTerm(char operator1, char operator2) {
        if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-'))
            return false;
        else
            return true;
    }

    public float calculate(char operator, float b, float a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException();
                }
                return a / b;
        }
        return 0;
    }

    public void cancel() {
        initialString = "";
        resultView.setText(initialString);
        isMinus = false;
    }
}
