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
	private int operacao = 0;
	private boolean limpar = false;
	
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

    private void atualizarMostrador(String texto) {
    	if(limpar) {
    		mostrador = texto;
    	} else {
    		mostrador += texto;
    	}
    	limpar = false;
    	EditText editText1 = (EditText) findViewById(R.id.editText1);
    	editText1.setText(mostrador);
    }
    
    private void operacao(int tipo, Double num) {
    	if(calcNum1 != null) {
        	switch(operacao) {
	    		case 1:
	    			calcNum1 += num;
	    			break;
	    		case 2:
	    			calcNum1 -= num;
	    			break;
	    		case 3:
	    			calcNum1 *= num;
	    			break;
	    		case 4:
	    			if(calcNum2 != 0) {
	    				calcNum1 /= num;
	    			} else {
	    				calcNum1 = 0.0;
	    			}
	    			break;
        	}
        	limpar = true;
        	String s = calcNum1.toString();
        	if(s.endsWith(".0")) {
        		s = s.substring(0, s.length()-2);
        	}
        	atualizarMostrador(s);
    	} else {
			calcNum1 = num;
			operacao = tipo;
    	}
    }
    
    public void buttonCalc_click(View view) {
    	Button btn = (Button) findViewById(view.getId());
    	switch(view.getId()) {
    		case R.id.button1:
    		case R.id.button2:
    		case R.id.button3:
    		case R.id.button4:
    		case R.id.button5:
    		case R.id.button6:
    		case R.id.button7:
    		case R.id.button8:
    		case R.id.button9:
    		case R.id.button0:
    			atualizarMostrador((String) btn.getTag());
        		break;
    		case R.id.buttonPonto:
    			//atualizarMostrador("1");
    			break;
    		case R.id.buttonSoma:
    			operacao(1, Double.valueOf(mostrador));
    			limpar = true;
    			break;
    		case R.id.buttonSubtracao:
    			operacao(2, Double.valueOf(mostrador));
    			break;
    		case R.id.buttonMultiplicacao:
    			operacao(3, Double.valueOf(mostrador));
    			break;
    		case R.id.buttonDivisao:
    			operacao(4, Double.valueOf(mostrador));
    			break;
    		case R.id.buttonIgual:
    			operacao(0, null);
    			break;
    	}
    }
}
