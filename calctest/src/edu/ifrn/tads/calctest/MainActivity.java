package edu.ifrn.tads.calctest;

import expr.Expr;
import expr.Parser;
import expr.SyntaxException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private String mostrador;
	private boolean limpar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mostrador = "";
        limpar = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void buttonCalc_click(View view) {
    	Button btn = (Button) findViewById(view.getId());
    	EditText editText1 = (EditText) findViewById(R.id.editText1);
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
    			if(limpar) {
    				mostrador = "";
    			}
    			limpar = false;
    			mostrador += (String) btn.getText();
        		break;
    		case R.id.buttonPonto:
    			if(limpar) {
    				mostrador = "0";
    			}
    			limpar = false;
    			mostrador += (String) btn.getText();
    			break;
    		case R.id.buttonSoma:
    		case R.id.buttonSubtracao:
    		case R.id.buttonMultiplicacao:
    		case R.id.buttonDivisao:
    			if(mostrador.charAt(mostrador.length()-1) == '+' ||
    			mostrador.charAt(mostrador.length()-1) == '-' ||
    			mostrador.charAt(mostrador.length()-1) == '*' ||
    			mostrador.charAt(mostrador.length()-1) == '/') {
    				mostrador = mostrador.substring(0, mostrador.length()-1);
    			}
    			mostrador += (String) btn.getText();
    			limpar = false;
    			break;
    		case R.id.buttonIgual:
    			try {
    				Expr expr = Parser.parse(editText1.getText().toString());
    				String valor = String.valueOf(expr.value());
    				if(valor != null && valor.substring(valor.length()-2).equals(".0")) {
    					mostrador = valor.substring(0, valor.length()-2);
    				} else {
    					mostrador = valor;
    				}
    				limpar = true;
    			} catch (SyntaxException e) {
    			    System.err.println(e.explain());
    			    return;
    			}
    			break;
    	}
    	editText1.setText(mostrador);
    }
}
