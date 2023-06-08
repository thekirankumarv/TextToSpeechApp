package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private EditText text;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.Text);
    }

    public void convertPressed(View view) {
        textToSpeech = new TextToSpeech(this,
                status -> {
                    if (status == TextToSpeech.SUCCESS) {
                        int result = textToSpeech.setLanguage(Locale.ENGLISH);
                        if (result == TextToSpeech.LANG_MISSING_DATA ||
                                result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Toast.makeText(this, "Error : This Language is not supported", Toast.LENGTH_LONG);
                        } else {
                            String text = this.text.getText().toString();
                            if ((text == null || "".equals(text))) {
                                text = " Content not available";
                            }
                            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    } else {
                        Toast.makeText(this, "Error : Initialisation Failed", Toast.LENGTH_LONG).show();
                    }
                });
    }
}

