package com.example.zalegoadmin.projecthbroadcast;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver receiver= null;

    TextView textView,textview;
    Button sending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.msgTV);

        textview=(TextView)findViewById(R.id.msggTV);

        sending=(Button)findViewById(R.id.sendmsg);

        sending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = textView.getText().toString();

                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                PendingIntent pi= PendingIntent.getActivity(getApplicationContext(),0,intent,0);
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage("+2547...",null,msg,pi,null);

                Toast.makeText(getApplicationContext(),"Message sent successfully",Toast.LENGTH_LONG).show();
            }
        });

        IntentFilter filter= new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                getSmsReceived(context,intent);
            }
        };
        registerReceiver(receiver, filter);

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(receiver);
    }

public void getSmsReceived(Context c,Intent intent){
    Bundle bundle = intent.getExtras();
    Object[] obj =(Object[]) bundle.get("pdus");

    String sms ="";
    for(int i=0; i<obj.length;i++){
        SmsMessage msg= SmsMessage.createFromPdu((byte[]) obj[i]);


    String msgBody  =msg.getMessageBody();
        String sender = msg.getDisplayOriginatingAddress();
        if(sender.equals("+2547.....")){
            finish();
        }else{

        }

        sms +=msgBody+"-Sent By: "+sender;
        textView.setText(sms);
    }
}

}
