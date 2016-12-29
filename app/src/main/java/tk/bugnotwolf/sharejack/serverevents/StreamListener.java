package tk.bugnotwolf.sharejack.serverevents;

public interface StreamListener {
    void connect(String s);
    void disconnect();
    void play();
    void onStatusUpdate(StreamStatus state);
}
