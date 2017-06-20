package com.example.omar_salem.note.utilities;




        import android.app.AlarmManager;
        import android.app.Notification;
        import android.app.NotificationManager;
        import android.app.PendingIntent;

        import android.content.Context;
        import android.content.Intent;
        import android.os.SystemClock;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import com.example.omar_salem.note.R;
        import com.example.omar_salem.note.activity.NoteActivity;
        import com.example.omar_salem.note.reciver.NotificationBroadcast;

public class Notifcation extends NoteActivity {
    Button B1,B2,B3;
    //private boolean notiactive=false;
    // private int noti_ID=33;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifcation);
        B1=(Button)findViewById(R.id.b1_id);
        B2=(Button)findViewById(R.id.b2_id);
        B3=(Button)findViewById(R.id.b3_id);
    }
    @Override
    protected void onPause() {

        super.onPause();
    }
    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, NotificationBroadcast.class);
        notificationIntent.putExtra(NotificationBroadcast.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationBroadcast.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        //builder.setSmallIcon(R.drawable.ic_launcher);
        return builder.build();
    }


    public void Noti1(View view){
        scheduleNotification(getNotification("1 day Your Note "),5000);//this for one day
    }
    public void Noti2(View view){
        scheduleNotification(getNotification("2 day Your Note "),10000);//this for 2 days
    }
    public void Noti3(View view){
        scheduleNotification(getNotification("3 day Your Note "),30000);//this for 3 days
    }
}
