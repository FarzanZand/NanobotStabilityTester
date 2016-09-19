import java.util.Random;

/**
 * Created by FarzanZand
 */

public class StabilityTests extends ControlFunctions{
/*
    Functions in parentClass:

    wait(ms)                Wait time in ms before next command
    nextTrack();            Plays next track in playlist
    previousTrack();        Previous function pressed
    play();                 Toggles play
    pause();                Toggles pause
    playPause();            Toggles between play and pause
    setVolume(volume);      Sets volume (0-100)
    increaseVolume();       Increases volume by 10
    lowerVolume();          lowers volume by 10
    loadRandomPlaylist();   Loads a random playlist
 */

    static boolean trackChanged = false;
    static int waitTime = 1000;
    static int minWaitTime = 1000;
    static int maxWaitTime = 2000;
    static int iter = 10;
    static int choice;
    static boolean restart = false;
    static boolean chooseAgain = true;

    public static void main(String[] args){
        StabilityTests tester = new StabilityTests();
        Random rand = new Random();
        System.out.println("Welcome the the Nanobot Stability Tester! If you have any questions or have any feature requests, please contact Farzan Zand. \n");
        wait(500);

        // Main testing loop
        do {
            iterations = 0;
            System.out.println("Instructions: Have the Spotify client running in the background with a playlist selected and repeat enabled. Mac Only. \n" +
                    "Select your test by typing an integer within the defined interval. \n");
            wait(500);
            System.out.println("" +
                    "1. Stress the next button.\n" +
                    "2. Stress the previous even more.\n" +
                    "3. Toggle pause and resume like a maniac.\n" +
                    "4. Set random volume ranges like you just don't care.\n" +
                    "5. Increment and decrement volume systematically like you care a little.\n" +
                    "6. Release the Kraken. Run a mix of control commands (volume, skip, previous, pause, resume, change playlist). \n");


            // Accept input from user and make sure it is valid.
            do {
                try {
                    choice = reader.nextInt();
                    if(choice < 1 || choice > 7){
                        chooseAgain = true;
                        System.out.println("Please only choose a numeric value between 1 - 7. ");
                    }
                    else {
                        chooseAgain = false;
                    }
                } catch (Exception e) {
                    System.out.println("Please only choose a numeric value between 1 - 7. ");
                    reader.next();
                }
            }while (chooseAgain);

            System.out.println("You have chosen wisely");
            wait(500);

            //if (choice != 2)
             //   changeTrack("spotify:album:64F64GjwOHjy3YfOOvBC05");

            switch(choice){
                case 1: tester.nextTest();
                    break;
                case 2: tester.previousTest();
                    break;
                case 3: tester.pauseResumeTest();
                    break;
                case 4: tester.randomVolumeTest();
                    break;
                case 5: tester.volumeIncrementTest();
                    break;
                case 6: tester.randomCommandTest();
                    break;
                default:
                    System.out.println("Not between the range. Restart program.");
                    wait(3000);
            }

            System.out.println("Test has ended. " + iterations + " iterations were run.");
            wait (400);
            System.out.println("Good job Nanobot!");

            System.out.println("Would you like to go again? \n" +
                    "1. Yes\n" +
                    "2. No");
            choice = reader.nextInt();
            if(choice == 1)
                restart = true;
            else
                restart = false;
        } while(restart);
        // Main testing loop ends


        System.out.println("Nanobot shutting down.");
    }

    // Get how many iterations and time per iteration
    public static void getIterationValuesFromUserDepricated(){
        System.out.println("Time between iterations? (ms)");
        waitTime = reader.nextInt();
        wait(500);
        System.out.println("How many iterations do you want to run?");
        iter = reader.nextInt();
    }

    // Get how many iterations and time per iteration
    public static void getIterationValuesFromUser(){
        System.out.println("Please define the minimum and maximum values. Time between commands sent is a random value between defined interval.");
        System.out.println("Minimum waitTime between iterations? (ms)");
        minWaitTime = reader.nextInt();
        wait(500);
        System.out.println("Maximum waitTime between iterations? (ms)");
        maxWaitTime = reader.nextInt();
        wait(500);
        System.out.println("Time between actions will be random chosen between " + minWaitTime + " and " + maxWaitTime);
        wait(500);
        System.out.println("How many iterations do you want to run?");
        iter = reader.nextInt();
    }

    // Skip to next track test
    public void nextTest(){
        getIterationValuesFromUser();
        for(int i=0; i<iter; i++){
            iterations += 1;
            nextTrack();
            waitTime = getRandomValueInRange(minWaitTime, maxWaitTime);
            wait(waitTime);
            System.out.println("Test iterations: " + iterations + "      waitTime: " + waitTime);
        }
    }

    // Previous track test
    public void previousTest(){
        getIterationValuesFromUser();
        for(int i=0; i<iter; i++){
            iterations += 1;
            previousTrack();
            waitTime = getRandomValueInRange(minWaitTime, maxWaitTime);
            wait(waitTime);
            System.out.println("Test iterations: " + iterations + "      waitTime: " + waitTime);
        }
    }

    // toggle pauseResume test
    public void pauseResumeTest() {
        getIterationValuesFromUser();
        for(int i=0; i<iter; i++) {
            iterations += 1;
            playPause();
            waitTime = getRandomValueInRange(minWaitTime, maxWaitTime);
            wait(waitTime);
            System.out.println("Test iterations: " + iterations + "      waitTime: " + waitTime);
        }
    }

    // Random volume test between 10 - 40 %
    public void randomVolumeTest(){
        getIterationValuesFromUser();
        for(int i=0; i<iter; i++){
            iterations += 1;
            setVolume(rand.nextInt(30)+10);
            waitTime = getRandomValueInRange(minWaitTime, maxWaitTime);
            wait(waitTime);
            System.out.println("Test iterations: " + iterations + "      waitTime: " + waitTime);
        }
    }

    // Volume increment test
    public void volumeIncrementTest(){
        getIterationValuesFromUser();
        setVolume(0);
        for(int i=0; i<iter; i++){

            iterations += 1;

            for (int n = 0; n<10; n++){
                increaseVolume();
                waitTime = getRandomValueInRange(minWaitTime, maxWaitTime);
                wait(waitTime);
                System.out.println("Test iterations: " + iterations + "      waitTime: " + waitTime);
            }
            for (int n = 0; n<10; n++){
                lowerVolume();
                waitTime = getRandomValueInRange(minWaitTime, maxWaitTime);
                wait(waitTime);
                System.out.println("Test iterations: " + iterations + "      waitTime: " + waitTime);
            }
        }
    }

    // Test everything randomly.
    public void randomCommandTest(){
        String typeOfCommand = "";

        getIterationValuesFromUser();

        for(int i=0; i<iter; i++){

            waitTime = getRandomValueInRange(minWaitTime, maxWaitTime);
            iterations += 1;
            switch(rand.nextInt(8) + 1){
                case 1: case 2: case 3:
                    nextTrack();
                    typeOfCommand = "Next";
                    break;
                case 4: case 5:
                    if(rand.nextInt(5) > 2)
                    previousTrack();
                    typeOfCommand = "Previous";
                    break;
                case 6:
                    playPause();
                    typeOfCommand = "PlayPause";
                    break;
                case 7:
                    loadRandomPlaylist(); //Doesn't always change playlist. Debug:
                    typeOfCommand = "randomPlaylist";
                    break;
                case 8:
                    setVolume(rand.nextInt(30)+10); 
                    typeOfCommand = "randomVolume";
                    break;
                default:
                    System.out.println("Not between the range. Restart program. ");
                    wait(3000);
            }
            System.out.println("Test iterations: " + iterations + "      waitTime: " + waitTime + "      Type of Command: " + typeOfCommand);
            wait(waitTime);
        }
    }
}