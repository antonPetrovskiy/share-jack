package tk.bugnotwolf.sharejack.serverevents;

public interface StreamListener {
    void connect();
    void disconnect();

    @FunctionalInterface
    interface StatusUpdateListener {
        void onStatusUpdate(StreamStatus state);
    }
}
