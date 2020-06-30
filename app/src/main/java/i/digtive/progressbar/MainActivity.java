package i.digtive.progressbar;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView persentase;
    private int Value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress);
        persentase = findViewById(R.id.persentase);
        progressBar.setProgress(0); //Set Progress Dimulai Dari O
        progressBar.setMax(40000);
        final int terkumpul = 2000;
        persentase.setText("Terkumpul "+terkumpul+" dari "+progressBar.getMax());
        // Handler untuk Updating data pada latar belakang
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // Menampung semua data yang ingin diproses oleh thread
                persentase.setText(String.valueOf(Value)+"%");
                if(Value == progressBar.getMax()){
                    Toast.makeText(getApplicationContext(), "Progress Completed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Kambing.class));
                    finish();
                }
                Value++;
            }
        };

        // Thread untuk updating progress pada ProgressBar di Latar Belakang
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(terkumpul);
//                    for(int w=0; w<=progressBar.getMax(); w++){
//                        progressBar.setProgress(w); // Memasukan Value pada ProgressBar
//                        // Mengirim pesan dari handler, untuk diproses didalam thread
//                        handler.sendMessage(handler.obtainMessage());
//                        Thread.sleep(100); // Waktu Pending 100ms/0.1 detik
//                    }
            }
        });
        thread.start();
    }
}