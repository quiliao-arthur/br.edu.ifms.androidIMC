package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class activity_main extends AppCompatActivity {

    Button btnCalc, btnClear, btnFem, btnMasc;
    EditText campPeso, campAlt;
    float result;

    RadioGroup grupoSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalc = findViewById(R.id.btnCalc);
        btnClear = findViewById(R.id.btnClear);
        btnFem = findViewById(R.id.btnFem);
        btnMasc = findViewById(R.id.btnMasc);
        campPeso = findViewById(R.id.campPeso);
        campAlt = findViewById(R.id.campAlt);
        grupoSexo = findViewById(R.id.grupoSexo);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validar
                if (validar()) {
                    AlertDialog.Builder janelaIMC = new AlertDialog.Builder(activity_main.this);
                    janelaIMC.setTitle("O seu IMC é:");

                    result = Float.parseFloat(campAlt.getText().toString()) /
                            Float.parseFloat(campPeso.getText().toString()) *
                            Float.parseFloat(campPeso.getText().toString());

                    String resultString = String.format("%.2f", result);

                    switch (grupoSexo.getCheckedRadioButtonId()){
                        case R.id.btnMasc:
                            if(result<20.7){
                                janelaIMC.setMessage(resultString+"- abaixo do peso");
                            }else{
                                if(result<27.8){
                                    janelaIMC.setMessage(resultString+"- no peso");
                                }else{
                                    janelaIMC.setMessage(resultString+"- acima do peso");
                                }
                            }
                            break;
                        case R.id.btnFem:
                            if(result<19.1){
                                janelaIMC.setMessage(resultString+"- abaixo do peso");
                            }else{
                                if(result<27.3){
                                    janelaIMC.setMessage(resultString+"- no peso");
                                }else{
                                    janelaIMC.setMessage(resultString+"- acima do peso");
                                }
                            }
                            break;
                    }
                    janelaIMC.show();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                limpar(campPeso);
                limpar(campAlt);
            }
        });
    }

    private boolean validar() {
        if (campAlt.getText().toString().trim().equals("")) {
            campAlt.setError("Informe valor");
            return false;
        }
        if (campPeso.getText().toString().trim().equals("")) {
            campPeso.setError("Informe valor");
            return false;
        }
        return true;
    }

    private void limpar(EditText editText){
        editText.setText("");
    }
}