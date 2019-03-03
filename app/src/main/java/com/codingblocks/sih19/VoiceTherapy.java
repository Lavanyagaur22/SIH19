package com.codingblocks.sih19;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.ricecode.similarity.JaroWinklerStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;

import java.util.ArrayList;
import java.util.Locale;

public class VoiceTherapy extends AppCompatActivity implements TextToSpeech.OnInitListener{


        TextView txtText;
        private TextToSpeech tts;

        ArrayList<String> texts;

        int count;
        Button startButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            txtText = findViewById(R.id.txtText);
            startButton = findViewById(R.id.startButton);
            texts = new ArrayList<>();

            texts.add("My mom drove me to school");
            texts.add("The mouse was so hungry he ran across the kitchen");
            texts.add("The tape got stuck on my lips so I couldn't talk anymore");
            texts.add("I found a gold coin on the playground after school today");
            texts.add("My dad is so funny that he told us jokes all night long");

            tts = new TextToSpeech(VoiceTherapy.this, this);


            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    speakOut("Repeat After Me");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    speakOut(texts.get(count));

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    getSpeechInput(startButton);

                }
            });





        }

        public void getSpeechInput(View view) {

            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

            startActivityForResult(intent, 10);

        }

        private void speakOut(String text) {

            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            switch (requestCode) {
                case 10:
                    if (resultCode == RESULT_OK && data != null) {
                        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                        SimilarityStrategy strategy = new JaroWinklerStrategy();
                        String target = result.get(0);

                        Log.e("Target", target + "");

                        String source = texts.get(count);

                        Log.e("Source", source + "");

                        StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
                        double score = service.score(source, target);

                        if (score > 0.90) {

                            speakOut("Very well done");
                            count++;
                        }
                        else if (score > 0.50) {
                            speakOut("You need to practice more");
                            count++;
                        }
                        else {
                            speakOut("Oops, you missed it. Please Try again");
                            count--;
                        }

                        Toast.makeText(this, score + "", Toast.LENGTH_SHORT).show();

                    }
                    break;
            }
        }

        @Override
        public void onInit(int status) {
            int result = tts.setLanguage(Locale.US);

        }

        @Override
        public void onDestroy() {
            // Don't forget to shutdown tts!
            if (tts != null) {
                tts.stop();
                tts.shutdown();
            }
            super.onDestroy();
        }
}
