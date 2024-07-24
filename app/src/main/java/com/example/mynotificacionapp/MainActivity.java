package com.example.mynotificacionapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "channel_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNotification = findViewById(R.id.btn_notification);
        Button btnToastSimple = findViewById(R.id.btn_toast_simple);
        Button btnToastCustom = findViewById(R.id.btn_toast_custom);

        btnNotification.setOnClickListener(v -> showNotification());
        btnToastSimple.setOnClickListener(v -> showToastSimple());
        btnToastCustom.setOnClickListener(v -> showToastCustom());
    }

    private void showNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager);
        }

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(getString(R.string.notificacion_titulo))
                .setContentText(getString(R.string.notificacion_texto))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        notificationManager.notify(1, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(NotificationManager notificationManager) {
        CharSequence name = getString(R.string.channel_name);
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        notificationManager.createNotificationChannel(channel);
    }

    private void showToastSimple() {
        Toast.makeText(this, getString(R.string.toast_simple), Toast.LENGTH_SHORT).show();
    }

    private void showToastCustom() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container));

        TextView text = layout.findViewById(R.id.text);
        text.setText(getString(R.string.toast_personalizado));

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}

