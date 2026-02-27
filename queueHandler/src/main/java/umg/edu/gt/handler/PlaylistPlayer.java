package umg.edu.gt.handler;

import umg.edu.gt.datastructure.queue.Queue;

import java.util.HashSet;
import java.util.Set;

public class PlaylistPlayer {

   
    private final Queue<Song> highPriority = new Queue<>();
    private final Queue<Song> normalPriority = new Queue<>();

    
    private final SongStack history = new SongStack();
    private int totalSongsPlayed = 0;
    private int totalSecondsPlayed = 0;
    private final Set<String> dedup = new HashSet<>();

    public void addSong(Song song) {
        if (song == null) throw new IllegalArgumentException("song is null");

        
        String key = song.uniqueKey();
        if (dedup.contains(key)) {
            log("Duplicate ignored: " + song.getTitle() + " - " + song.getArtist());
            return;
        }
        dedup.add(key);

        if (song.getPriority() == 1) {
            highPriority.enqueue(song);
            log("Added (HIGH): " + song);
        } else {
            normalPriority.enqueue(song);
            log("Added (NORMAL): " + song);
        }
    }

    public void playAll() {
        log("Starting playlist...");

        while (!highPriority.isEmpty() || !normalPriority.isEmpty()) {
            Song next = !highPriority.isEmpty() ? highPriority.dequeue() : normalPriority.dequeue();
            playSong(next);
        }

        log("Playlist finished.");
        log("Summary: songsPlayed=" + totalSongsPlayed + ", totalTime=" + totalSecondsPlayed + "s, historySize=" + history.size());
    }

    private void playSong(Song song) {
        log("Now playing: " + song.getTitle() + " - " + song.getArtist() + " (" + song.getDurationSeconds() + "s)");

        int duration = song.getDurationSeconds();
        for (int second = 1; second <= duration; second++) {
            sleepOneSecond();
            String bar = progressBar(second, duration, 10);
            log("Playing: " + song.getTitle() + " | " + bar + " " + second + "s / " + duration + "s");
        }

        log("Finished: " + song.getTitle());

        history.push(song);
        totalSongsPlayed++;
        totalSecondsPlayed += duration;
    }

    private static void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static String progressBar(int current, int total, int width) {
        int filled = (int) Math.round(((double) current / (double) total) * width);
        if (filled < 0) filled = 0;
        if (filled > width) filled = width;

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < width; i++) {
            sb.append(i < filled ? '#' : '-');
        }
        sb.append(']');
        return sb.toString();
    }

    private static void log(String msg) {
        System.out.println("[LOG] " + msg);
    }
}
