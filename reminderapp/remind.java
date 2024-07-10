package reminderapp;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * Make  joptionpanes
 * each of them will look different and have slightly different purposes.
 * 1 will be the one the user sees when they are setting their time.
 * and the last 1 will appear once the previous time the user set is up.
 */

// alarm icon does not apear when running from Vs code.

public class remind {
    private static int milleSecToMin = 60000;
    private static Timer duration;

    public static void main(String args[]) {
        duration = new Timer();
        int minutes = setTime();
        if (minutes == -1) {
            System.exit(-1);
        }
        System.out.printf("Setting timer for %d minutes...\n", minutes);
        TimerTask alarm = new TimerTask() {
            @Override
            public void run() {
                Object[] options = { "Ok" };
                ImageIcon icon = new ImageIcon("768px-Clock_11-50.svg.png");
                JOptionPane.showOptionDialog(null, "Time is Up!", "Timer", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, icon, options, options[0]);
                duration.cancel();
                this.cancel();
            }
        };
        duration.schedule(alarm, minutes * milleSecToMin);
    }

    public static int setTime() {
        while (true) {
            try {
                String time = JOptionPane.showInputDialog(null, "Set your time (in minutes):");
                if (time == null) {
                    break;
                }
                int minutes = Integer.parseInt(time);
                if (minutes > 0) {
                    return minutes;
                }
            } catch (java.lang.NumberFormatException ii) {
                JOptionPane.showConfirmDialog(null, "The value must be a positive integer!", "Error",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        return -1;
    }
}