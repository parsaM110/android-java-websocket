package com.example.websockettest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class MainActivity extends AppCompatActivity {


    private TextView output;
    private OkHttpClient client;
    private Switch switch1;
    private Switch switch2;

    private final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
//            webSocket.send("1");
//            webSocket.send("What's up ?");
//            webSocket.send(ByteString.decodeHex("deadbeef"));
//            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");webSocket.send("1");
////            webSocket.send("What's up ?");
        }
        @Override
        public void onMessage(WebSocket webSocket, String text) {
            output("Receiving : " + text);
        }
        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            output("Receiving bytes : " + bytes.hex());
        }
        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
            output("Closing : " + code + " / " + reason);
        }
        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            output("Error : " + t.getMessage());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView) findViewById(R.id.output);
        switch1 = (Switch) findViewById(R.id.switch1) ;
        switch2 = (Switch) findViewById(R.id.switch2) ;
        client = new OkHttpClient();
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(switch1.isChecked())
                {
                    Request request = new Request.Builder().url("http://spadwebsocket.runflare.run/ws/chat/lobby_room/").build();
                    EchoWebSocketListener listener = new EchoWebSocketListener();
                    WebSocket ws = client.newWebSocket(request, listener);
                    ws.send("1");
                }
                    else{
                        Request request = new Request.Builder().url("http://spadwebsocket.runflare.run/ws/chat/lobby_room/").build();
                    EchoWebSocketListener listener = new EchoWebSocketListener();
                    WebSocket ws = client.newWebSocket(request, listener);
                    ws.send("2");
                    }

            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(switch2.isChecked())
                {
                    Request request = new Request.Builder().url("http://spadwebsocket.runflare.run/ws/chat/lobby_room/").build();
                    EchoWebSocketListener listener = new EchoWebSocketListener();
                    WebSocket ws = client.newWebSocket(request, listener);
                    ws.send("3");
                }
                else{
                    Request request = new Request.Builder().url("http://spadwebsocket.runflare.run/ws/chat/lobby_room/").build();
                    EchoWebSocketListener listener = new EchoWebSocketListener();
                    WebSocket ws = client.newWebSocket(request, listener);
                    ws.send("4");
                }

            }
        });
    }



    private void start() {
        Request request = new Request.Builder().url("http://spadwebsocket.runflare.run/ws/chat/lobby_room/").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = client.newWebSocket(request, listener);
        ws.send("1");
//        client.dispatcher().executorService().shutdown();
    }
    private void off() {
        Request request = new Request.Builder().url("http://spadwebsocket.runflare.run/ws/chat/lobby_room/").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = client.newWebSocket(request, listener);
        ws.send("2");

//        client.dispatcher().executorService().shutdown();
    }
    private void start2() {
        Request request = new Request.Builder().url("http://spadwebsocket.runflare.run/ws/chat/lobby_room/").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = client.newWebSocket(request, listener);
        ws.send("3");
//        client.dispatcher().executorService().shutdown();
    }
    private void off2() {
        Request request = new Request.Builder().url("http://spadwebsocket.runflare.run/ws/chat/lobby_room/").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = client.newWebSocket(request, listener);
        ws.send("4");
//        client.dispatcher().executorService().shutdown();
    }
    private void output(final String txt) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                output.setText(output.getText().toString() + "\n\n" + txt);
            }
        });
    }
}

