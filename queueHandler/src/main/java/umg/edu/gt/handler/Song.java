package umg.edu.gt.handler;

/**
 * Modelo obligatorio.
 * priority: 1 = alta, 2 = normal
 */
public class Song {
    private final String title;
    private final String artist;
    private final int durationSeconds; // 5 a 30
    private final int priority;        // 1 o 2

    public Song(String title, String artist, int durationSeconds, int priority) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("title is required");
        }
        if (artist == null || artist.trim().isEmpty()) {
            throw new IllegalArgumentException("artist is required");
        }
        if (durationSeconds < 5 || durationSeconds > 30) {
            throw new IllegalArgumentException("duration must be between 5 and 30 seconds");
        }
        if (priority != 1 && priority != 2) {
            throw new IllegalArgumentException("priority must be 1 (high) or 2 (normal)");
        }
        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
        this.priority = priority;
    }

    public String getTitle() { return title; }

    public String getArtist() { return artist; }

    public int getDurationSeconds() { return durationSeconds; }

    public int getPriority() { return priority; }

    public String uniqueKey() {
        return (title + "|" + artist).toLowerCase();
    }

    @Override
    public String toString() {
        return title + " - " + artist + " (" + durationSeconds + "s)";
    }
}
