package tk.bugnotwolf.sharejack.serverevents;


import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketExtension;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;

public abstract class WebSocketListener implements StreamListener {
    private static final int TIMEOUT = 8000;
    private static final WebSocketFactory wsFactory = new WebSocketFactory().setConnectionTimeout(TIMEOUT);
    private static final String TAG = WebSocketListener.class.toString();

    private WebSocket ws;

    /**
     * Method blocks until the handshake is performed.
     */
    @Override
    public void connect(String server) {



        try {
            ws = wsFactory.createSocket(server)
                .addListener(new WebSocketAdapter() {
                    @Override
                    public void onTextMessage(WebSocket websocket, String text) throws Exception {
                        Log.v(TAG, text);
                        if (text.equals("status")) {
                        }
                    }
                })
                .addExtension(WebSocketExtension.PERMESSAGE_DEFLATE)
                .connectAsynchronously();
        } catch (IOException e) {
            throw new StreamException(e);
        }
    }

    @Override
    public void disconnect() {
        ws.disconnect();
    }

    @Override
    public void play() {
        ensureWebSocket().sendText("play");
    }

    private WebSocket ensureWebSocket() {
        if (ws != null) {
            return ws;
        } else {
            throw new IllegalStateException("Call connect() first");
        }
    }
}
