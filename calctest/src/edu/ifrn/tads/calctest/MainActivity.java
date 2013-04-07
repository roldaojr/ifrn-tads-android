package edu.ifrn.tads.calctest;

import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Double calcNum1 = null;
	private Double calcNum2 = null;
	private String mostrador;
	private byte operacao = 0;
	private boolean limpar = true;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mostrador = "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void atualizarMostrador() {
    	EditText editText1 = (EditText) findViewById(R.id.editText1);
    	editText1.setText(mostrador);
    }
    
    private void resultado() {
    	switch(operacao) {
    		case 1:
    			calcNum1 += calcNum2;
    		case 2:
    			calcNum1 -= calcNum2;
    		case 3:
    			calcNum1 *= calcNum2;
    		case 4:
    			if(calcNum2 != 0) {
    				calcNum1 /= calcNum2;
    			} else {
    				calcNum1 = 0.0;
    			}
    	}
    	operacao = 0;
    	calcNum2 = calcNum1;
    }
    
    public void buttonCalc_click(View view) {
    	final int[] buttonNum = new int[]
    			{R.id.button1, R.id.button2, R.id.button3,
    	        R.id.button4, R.id.button5, R.id.button6,
    	        R.id.button7, R.id.button8, R.id.button9,
    	        R.id.button0};
    	if(Arrays.asList(buttonNum).contains(view.getId())) {
    		Button btn = (Button) findViewById(view.getId());
    		//calcNum2.toString()+
    		mostrador += btn.getText().toString()+".";
    	}
    	switch(view.getId()) {
    		case R.id.buttonSoma:
    			if(operacao != 0 && calcNum1 != null) resultado();
    			operacao = 1;
    			break;
    		case R.id.buttonSubtracao:
    			if(operacao != 0 && calcNum1 != null) resultado();
    			operacao = 2;
    			break;
    		case R.id.buttonMultiplicacao:
    			if(operacao != 0 && calcNum1 != null) resultado();
    			operacao = 3;
    			break;
    		case R.id.buttonDivisao:
    			if(operacao != 0 && calcNum1 != null) resultado();
    			operacao = 4;
    			break;
    		case R.id.buttonIgual:
    			if(operacao != 0 && calcNum1 != null) resultado();
    			operacao = 0;
    			calcNum1 = null;
    			break;
    		case R.id.buttonPonto:
    			calcNum2 = Double.parseDouble(calcNum2.toString()+".0");
    			break;
    	}
    	atualizarMostrador();
    }
}
