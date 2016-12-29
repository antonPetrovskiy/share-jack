package tk.bugnotwolf.sharejack.serverevents;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class WebSocketListener implements StreamListener {
    private static final String TAG = WebSocketListener.class.getSimpleName();

    private Socket socket;

    public WebSocketListener(String uri, StatusUpdateListener updateListener) {
        try {
            socket = IO.socket(uri);
        } catch (URISyntaxException e) {
            throw new StreamException();
        }
        socket.on("status", a -> {
            JSONObject json = (JSONObject) a[0];
            try {
                boolean isPlaying = json.getBoolean("isPlaying");
                int volume = json.getInt("volume");
                int currentTime = json.getInt("currentTime");
                StreamStatus status = new StreamStatus(isPlaying, volume, currentTime);
                Log.d(TAG, status.toString());
                updateListener.onStatusUpdate(status);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void connect() {
        socket.connect();
    }

    @Override
    public void disconnect() {
        socket.disconnect();
    }
}
