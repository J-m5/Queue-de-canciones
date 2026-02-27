package umg.edu.gt.handler;

public class PlaylistApp {

    public static void main(String[] args) {
        PlaylistPlayer player = new PlaylistPlayer();

        player.addSong(new Song("Billie Jean", "Michael Jackson", 12, 1));
        player.addSong(new Song("Shape of You", "Ed Sheeran", 9, 2));
        player.addSong(new Song("Rolling in the Deep", "Adele", 15, 1));
        player.addSong(new Song("Uptown Funk", "Bruno Mars", 10, 2));
        player.addSong(new Song("Smells Like Teen Spirit", "Nirvana", 14, 2));
        player.addSong(new Song("Someone Like You", "Adele", 11, 1));
        player.addSong(new Song("Blinding Lights", "The Weeknd", 8, 2));

        player.playAll();
    }
}
