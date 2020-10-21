package com.ramasolutions.petagramfragments.activities;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ramasolutions.petagramfragments.R;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactoActivity extends AppCompatActivity {

    Button enviar;
    Session sesion;
    String nombre;
    String correo;
    String contrasena;
    String comentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Get a support ActionBar corresponding to this toolbar

        getSupportActionBar().setIcon(R.drawable.ic_action_huella);

        enviar = (Button) findViewById(R.id.btEnviar);
        final EditText etNombre = (EditText) findViewById(R.id.etNombre);
        final EditText etCorreo = (EditText) findViewById(R.id.etEmail);
        final EditText etContrasena = (EditText) findViewById(R.id.etPassword);
        final EditText etComentario = (EditText) findViewById(R.id.etMensaje);


        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                nombre = etNombre.getText().toString();
                correo = etCorreo.getText().toString();
                contrasena = etContrasena.getText().toString();
                comentario = etComentario.getText().toString();
                Properties properties = new Properties();
                properties.put("mail.smtp.host","smtp.googlemail.com");
                properties.put("mail.smtp.socketFactory.port", "465");
                properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.port", "465");

                try {
                    sesion = Session.getDefaultInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correo, contrasena);
                        }
                    });

                    if(sesion!=null){
                        Message message = new MimeMessage(sesion);
                        message.setFrom(new InternetAddress(correo));
                        message.setSubject("Comentario de: "+nombre);
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ramoncarrion@gmail.com"));
                        message.setContent(comentario, "text/html; charset=utf-8");
                        Transport.send(message);
                        Toast.makeText(getBaseContext(), "Mensaje enviado", Toast.LENGTH_LONG).show();
                        etNombre.setText("");
                        etCorreo.setText("");
                        etContrasena.setText("");
                        etComentario.setText("");
                    }
                }catch(Exception e){
                    Toast.makeText(getBaseContext(), "Mensaje no enviado", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }
}