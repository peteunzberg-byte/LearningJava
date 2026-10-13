public class Playlist{
    private int id;
    private String name;
    private String[] songs;
    private int songCount;
    private static int nextId = 1;
    Playlist(String name, int capacity){
        this.name = name;
        if (capacity < 1) {
            capacity = 5;
        }
        this.songs = new String[capacity];
        this.id = nextId;
        nextId++;
    }
    Playlist(String name){
        this(name, 5);
    }
    Playlist(Playlist playlist){
        this("Copy of " + playlist.name, playlist.songs.length);
        for(int i = 0; i < playlist.songCount; i++){
            this.songs[i] = playlist.songs[i];
        }
        this.songCount = playlist.songCount;
    }
    public void addSong(String title){
        if(this.songCount == this.songs.length){
            System.out.println("Playlist full");
        }
        else{
            this.songs[this.songCount] = title;
            this.songCount++;
        }
    }
    public int getId(){
        return this.id;
    }
    public int getSongCount(){
        return this.songCount;
    }
    public void replaceSong(String newTitle, int position){
        if((position >= 1) && (position <= this.songCount)){
            this.songs[position - 1] = newTitle;
        }
        else{
            System.out.println("Replace position not found!");
        }
    }
    public void display(){
        System.out.println("Playlist #" + this.id + ": " + this.name 
        + " (" + this.songCount + "/" + this.songs.length + ")");
        for(int i = 1; i <= this.songCount; i++){
            System.out.println(i + ". " + this.songs[i-1]);
        }
    }
}