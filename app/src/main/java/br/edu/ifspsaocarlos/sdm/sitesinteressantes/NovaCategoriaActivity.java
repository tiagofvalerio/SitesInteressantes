package br.edu.ifspsaocarlos.sdm.sitesinteressantes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class NovaCategoriaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_categoria);
    }

    public void onClick(View v) {
        EditText etCategoria = (EditText) findViewById(R.id.et_nova_categoria);
        int resultado = RESULT_CANCELED;
        Intent intentRetorno = new Intent();
        if ( v == findViewById(R.id.bt_ok_categoria) ) {
            if (!etCategoria.getText().toString().isEmpty()) {
                resultado = RESULT_OK;
                intentRetorno.putExtra("categoria", etCategoria.getText().toString());
            }
        }
        setResult(resultado, intentRetorno);
        finish();
    }
}
