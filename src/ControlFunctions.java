/**
 * Created by FarzanZand on 16/09/16.
 */
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class ControlFunctions {

    /*
    Included methods:
    changeTrack
     */

    static Runtime runtime;
    static Random rand;
    static int iterations;
    static Scanner reader;

    public ControlFunctions(){
        reader = new Scanner(System.in);
        iterations = 0;
        runtime = Runtime.getRuntime();
        rand = new Random();
    }


    public static int getRandomValueInRange(int min, int max){
        return (rand.nextInt(max - min + 1) + min);
    }

    // Insert spotify URI as a parameter to change track
    public static void changeTrack(String trackURI) {
        String applescriptCommand =  "tell app \"Spotify\"\n" +
                "play track \""+trackURI+"\"\n" +
                "end tell";

        runApplescript(runtime, applescriptCommand);
    }

    // Skips to next track.
    public static void nextTrack(){
        String applescriptCommand =  "tell app \"Spotify\"\n" +
                "next track\n" +
                "end tell";

        runApplescript(runtime, applescriptCommand);
    }

    //Remake to be an Array instead for easy scaling.
    public static void loadRandomPlaylist(){
        switch(rand.nextInt(5) + 1){
            case 1:
                changeTrack("spotify:user:spotifybrazilian:playlist:6he8ymKaznMJV6AIeI3Z8u");
                break;
            case 2:
                changeTrack("spotify:user:spotifybrazilian:playlist:1ZRkRyUZCb3KxdNOdoH3aJ");
                break;
            case 3:
                changeTrack("user:spotify__sverige:playlist:0vhTwnTi73iMvEuaqmTJ7B");
                break;
            case 4:
                changeTrack("spotify:user:spotify:playlist:2JkjXscXs35c5wKE5ZeaYK");
                break;
            case 5:
                changeTrack("spotify:user:spotify:playlist:5O2ERf8kAYARVVdfCKZ9G7");
                break;
            default:
                System.out.println("Not between the rsdfsdfsange. Restart program.");
                wait(3000);
        }
    }

    // skips to next track.
    public static void previousTrack(){
        String applescriptCommand =  "tell app \"Spotify\"\n" +
                "previous track\n" +
                "end tell";

        runApplescript(runtime, applescriptCommand);
    }

    // Add a wait in the script in ms.
    public static void wait(int ms){
        try {
            Thread.sleep(ms);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    // Pauses current track
    public static void pause(){
        String applescriptCommand =  "tell app \"Spotify\"\n" +
                "pause\n" +
                "end tell";

        runApplescript(runtime, applescriptCommand);
    }

    // Plays track
    public static void play(){
        String applescriptCommand =  "tell app \"Spotify\"\n" +
                "play\n" +
                "end tell";

        runApplescript(runtime, applescriptCommand);
    }

    // Toggle pause/resume
    public static void playPause(){
        String applescriptCommand =  "tell app \"Spotify\"\n" +
                "playpause\n" +
                "end tell";

        runApplescript(runtime, applescriptCommand);
    }

    // Plays next track
    public static void setVolume(int volume){

        if (volume > 100)
            volume = 100;
        if (volume < 0)
            volume = 0;

        String applescriptCommand =  "tell app \"Spotify\"\n" +
                "set sound volume to (" + volume + ")\n" +
                "end tell";

        runApplescript(runtime, applescriptCommand);
    }

    // Increases volume
    public static void increaseVolume(){
        String applescriptCommand =  "tell app \"Spotify\"\n" +
                "set sound volume to (sound volume + 10)\n" +
                "end tell";

        runApplescript(runtime, applescriptCommand);
    }

    // Lowers volume
    public static void lowerVolume(){
        String applescriptCommand =  "tell app \"Spotify\"\n" +
                "set sound volume to (sound volume - 10)\n" +
                "end tell";

        runApplescript(runtime, applescriptCommand);
    }

    // Sends script to applescript
    public static void runApplescript(Runtime runtime, String applescriptCommand){
        String[] args = { "osascript", "-e", applescriptCommand };

        try
        {
            Process process = runtime.exec(args);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}

