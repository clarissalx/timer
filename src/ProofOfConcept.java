import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * The program is to ask the user to input their desired timer times and the
 * program will output a timer and if desired the user is able to add more time
 * or end the timer entirely.
 *
 *
 * Design justification:
 *
 * This program is built to provide a basic structure for helping users manage
 * their work and rest sessions. The program is simple enough by using console
 * input/output (SimpleReader and SimpleWriter) which makes it easy use for
 * everyone.
 *
 * * The reason why I chose this design is to help aid people specifically
 * students (me lol) organize their time wisely for work and break.
 *  I have always liked the idea of a pomodoro timer and essentially how it works
 * so by implementing this it helps me learn on how exactly a pomodoro timer works.
 * Real world factors: - User flexibility: The timer can be used as an actual
 * timer where it counts down from a given time that the user has input.
 *
 * Simplicity: The use of basic loops and input/output keeps the code
 * accessible.
 *
 * Control: Methods such as the pauseTimer, stopTime, and restTime allow the
 * user to adjust their session which reflects real world use such as breaks and
 * pauses.
 *
 * Limitation: As this timer is as simple as it can get (only counting down from
 * the given number and the break) was chosen to keep the implementation a
 * design where a user is able to pause their time will hopefully be added one
 * day.
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
     * Sets the timer.
     *
     * @param minutes
     */
    public void setTimer(int minutes) {
        final int sixty = 60;
        this.remainingTime = minutes * sixty;
    }

    /**
     * Starts the countdown for the timer. Modified method for add(NN n),
     * multiplyBy10(NN n), divideBy10(NN n) method.
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
            //thread.sleep() try and catch gives one sec delay for timer
            try {
                final int thousand = 1000;
                Thread.sleep(thousand);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.remainingTime--;
        }
        if (this.remainingTime == 0) {
            out.println("Time is up. Let's take a break!");
        }
    }

    /**
     * Pauses the timer. Modified divide(NN n) method.
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
     * Stops the timer and resets it. Modified isZero(NN n) method.
     *
     * @param out
     */
    public void stopTime(SimpleWriter out) {
        this.isRunning = false;
        this.remainingTime = 0;
        out.println("Timer has stopped.");
    }

    /**
     * Outputs the rest time.
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

        out.println("Timer is finished. Great work! Hope to see you soon!");
        in.close();
        out.close();
    }
}
