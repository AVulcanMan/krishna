package org.example.rdf4j_getting_started;

import java.util.Scanner;
import java.time.LocalTime;
import javax.sound.sampled.*;
import java.io.File;

public class alarmClock {

    public static void run(Scanner sc) throws Exception {

        System.out.print("Set alarm hour (0-23): ");
        int alarmHour = sc.nextInt();

        System.out.print("Set alarm minute (0-59): ");
        int alarmMinute = sc.nextInt();

        boolean alarmTriggered = false;

        while (!alarmTriggered) {

            LocalTime now = LocalTime.now();

            if (now.getHour() == alarmHour &&
                now.getMinute() == alarmMinute) {

                System.out.println("ALARM!");

                File file = new File("alarm2.wav");

                AudioInputStream audioStream =
                        AudioSystem.getAudioInputStream(file);

                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);

                clip.start();

                while (!clip.isRunning()) {
                    Thread.sleep(10);
                }

                while (clip.isRunning()) {
                    Thread.sleep(100);
                }

                clip.close();
                audioStream.close();

                alarmTriggered = true;
            }

            Thread.sleep(500);
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        run(sc);
        sc.close();
    }
}