package tk.bugnotwolf.sharejack.serverevents;

public final class StreamStatus {
    private boolean isPlaying;
    private int volume;
    private int currentTime;

    public StreamStatus(boolean isPlaying, int volume, int currentTime) {
        this.isPlaying = isPlaying;
        this.volume = volume;
        this.currentTime = currentTime;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public int getVolume() {
        return volume;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    @Override
    public String toString() {
        return "StreamStatus{" +
            "isPlaying=" + isPlaying +
            ", volume=" + volume +
            ", currentTime=" + currentTime +
            '}';
    }
}
