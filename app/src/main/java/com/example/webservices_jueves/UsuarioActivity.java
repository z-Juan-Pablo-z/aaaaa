package com.example.webservices_jueves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UsuarioActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    public static String nombre;
    EditText jetusr,jetnombre,jetcorreo,jetclave;
    Button jbtregistrar,jbtregresar,btnconsultar;
    RequestQueue rq;
    JsonRequest jrq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        getSupportActionBar().hide();
        jetusr = findViewById(R.id.etuser);
        jetnombre = findViewById(R.id.etnombre);
        jetcorreo = findViewById(R.id.etcorreo);
        jetclave = findViewById(R.id.etclave);
        jbtregistrar = findViewById(R.id.btregistrar);
        jbtregresar = findViewById(R.id.btregresar);
        btnconsultar=findViewById(R.id.btnconsultar);
        rq = Volley.newRequestQueue(this);//conexion a internet


        jbtregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar_usuario();
            }
        });

        jbtregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresar();
            }
        });
        btnconsultar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                consultar_usuario();
            }
        });
    }

    public void regresar() {
        Intent intmain=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intmain);
    }

    private void registrar_usuario() {
        String url = "http://172.16.58.226:81/usuarios/registrocorreo.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        limpiar();
                        jetusr.requestFocus();
                        Toast.makeText(getApplicationContext(), "Registro de usuario realizado correctamente!", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Registro de usuario incorrecto!", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("usr",jetusr.getText().toString().trim());
                params.put("nombre", jetnombre.getText().toString().trim());
                params.put("correo",jetcorreo.getText().toString().trim());
                params.put("clave",jetclave.getText().toString().trim());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(postRequest);
    }
    private void consultar_usuario(){
        if (jetusr.getText().toString().isEmpty()){
            Toast.makeText(this, "Es necesario el usuario", Toast.LENGTH_SHORT).show();
            jetusr.requestFocus();
        }else{
            String url = "http://172.16.58.226:81/usuarios/consulta.php?usr="+jetusr.getText().toString();
            jrq = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
            rq.add(jrq);

        }
    }

    private void limpiar(){
        jetclave.setText("");
        jetcorreo.setText("");
        jetnombre.setText("");
        jetusr.setText("");
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "No se encontro", Toast.LENGTH_LONG).show();
        jetusr.requestFocus();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(), "Busqueda encontrada", Toast.LENGTH_LONG).show();
        @Override
        protected  Map<String,String> getParams(){
            Map<String,String> params = new HashMap<String,String>();
            params.put("usr",jetusr.getText().toString().trim());
            jetnombre.setText(params.get("nombre"));
            jetcorreo.setText(params.get("correo"));
            jetclave.setText(params.get("clave"));
            return params;

    }
    //https://www.itsalif.info/content/android-volley-tutorial-http-get-post-put

}