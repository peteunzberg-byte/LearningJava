public class C1problem {
    public static void main(String[] args){
        Playlist gym = new Playlist("Gym", 3);
        gym.addSong("Song A");
        gym.addSong("Song B");
        gym.addSong("Song C");
        gym.addSong("Song D");
        Playlist study = new Playlist("Study");
        study.addSong("Lofi 1");
        study.addSong("Lofi 2");
        Playlist gymCopy = new Playlist(gym);
        gymCopy.replaceSong("Song X", 2);
        gymCopy.replaceSong("Song X", 7);
        gym.display();
        study.display();
        gymCopy.display();
    }
}
/* Written question: if the copy constructor had done this.songs = other.songs; 
instead of making a new array, what would the Gym playlist show after step 3, and why?
Answer: That would make the this.songs reference point towards the original array (shallow copy),
instead of creating a new array and having this.songs point towards the new array(deep copy), which
would result in the Gym playlist's 2nd song being replaced with Song X instead of just the copy. 
*/


/*
QUESTIONS PROMPT
C1. Playlist (deep copy)
Build a playlist class whose copy constructor makes a true deep copy of an array field.
Fields
• private int id
• private String name
• private String[] songs (fixed-size array of song titles)
• private int songCount (how many slots are filled)
• private static int nextId starting at 1. Each new playlist takes the current value as its id, 
then nextId goes up by 1.
Constructors
1. Playlist(String name, int capacity): the main constructor. Creates the array and assigns the id.
If capacity is less than 1, use 5.
2. Playlist(String name): capacity 5. Must chain with this(...).
3. Playlist(Playlist other): a deep copy. The copy gets its own new array holding the same titles, 
the same songCount, a new id, and the name "Copy of " + other.name. Chaining is allowed but not required.
Methods
• void addSong(String title): adds to the next empty slot. If full, print Playlist full and add nothing.
• int getId() and int getSongCount()
• A method that replaces the song at a 1-based position (position 1 is the first song). You design its name,
return type and what happens for a bad position, such as 0, a negative number, or a position past songCount.
• void display(): prints the header, then one numbered line per song.
Main
1. Create "Gym" with capacity 3. Add Song A, Song B, Song C, Song D. The fourth prints Playlist full.
2. Create "Study" with constructor 2. Add Lofi 1 and Lofi 2.
3. Copy the Gym playlist. In the copy, replace position 2 with Song X. Then try replacing position 7 and show 
your bad-position behavior.
4. Display all three playlists.

Expected output (plus whatever your bad-position case prints):
Playlist full
Playlist #1: Gym (3/3)
1. Song A
2. Song B
3. Song C
Playlist #2: Study (2/5)
1. Lofi 1
2. Lofi 2
Playlist #3: Copy of Gym (3/3)
1. Song A
2. Song X
3. Song C
*/