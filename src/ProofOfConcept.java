import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * The program is to ask the user to input their desired timer times and the
 * program will output a timer and if desired the user is able to add more time
 * or end the timer entirely.
 */
public final class ProofOfConcept {
    /**
     * No argument constructor--private to prevent instantiation.
     */

    private boolean isRunning;

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private int remainingTime;

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ProofOfConcept() {

        //sets inital run time as false
        this.isRunning = false;
        this.remainingTime = 0;
    }

    /**
     * This method sets the timer.
     *
     * @param minutes
     */
    public void setTimer(int minutes) {
        final int sixty = 60;
        this.remainingTime = minutes * sixty;
    }

    /**
     * This method starts the countdown for the timer. Modified method for
     * add(NN n), multiplyBy10(NN n), divideBy10(NN n) method.
     *
     * @param out
     *
     */
    public void startTimer(SimpleWriter out) {
        this.isRunning = true;
        final int sixty = 60;
        while (this.remainingTime > 0 && this.isRunning) {
            int remainMin = (this.remainingTime / sixty);
            int remainSec = (this.remainingTime % sixty);
            out.println(
                    "Time left: " + remainMin + " min " + remainSec + " sec");
            this.remainingTime--;
        }
        if (this.remainingTime == 0) {
            out.println("Time is up. Let's take a break!");
        }
    }

    /**
     * This method pauses the timer. Modified divide(NN n) method.
     *
     * @param out
     */
    public void pauseTimer(SimpleWriter out) {
        this.isRunning = false;
        final int sixty = 60;
        int pausedMin = (this.remainingTime / sixty);
        int remainMin = (this.remainingTime % sixty);
        out.println("Timer paused: " + pausedMin + " min " + remainMin
                + " seconds left.");
    }

    /**
     * This method stops the timer and resets it. Modified isZero(NN n) method.
     *
     * @param out
     */
    public void stopTime(SimpleWriter out) {
        this.isRunning = false;
        this.remainingTime = 0;
        out.println("Timer has stopped.");
    }

    /**
     * This method outputs the rest time.
     *
     * @param minutes
     * @param out
     */
    public void restTime(int minutes, SimpleWriter out) {
        out.println("Let's take a rest for " + minutes + " minutes.");
        final int sixty = 60;
        this.remainingTime = minutes * sixty;
        this.startTimer(out);
    }

    /**
     * This program implements an application that helps a user time themselves.
     *
     * @param args
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        ProofOfConcept timer = new ProofOfConcept();

        out.println("How many minutes of work time?: ");
        int workMin = in.nextInteger();

        out.println("How many minutes of rest time?: ");
        int restMin = in.nextInteger();

        timer.setTimer(workMin);
        timer.startTimer(out);

        out.print("Would you like to take a break? (y/n): ");
        String answer = in.nextLine();
        if (answer.equals("y")) {
            timer.setTimer(restMin);
            timer.startTimer(out);
        }

        out.println("Timer is finished. Hope to see you soon!");
        in.close();
        out.close();
    }
}

//Is there any way to make the timer work as a real-life timer instead of a
//quick output coutdown?
