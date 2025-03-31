package com.autoclicker;

import java.util.Scanner;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class Clicker {
    public static final String RESET = "\u001B[0m";
    public static final String ROSSO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String GIALLO = "\u001B[33m";
    public static final String BLU = "\u001B[34m";

    public static void main(String[] args) {
        if (args.length == 0 || !args[0].equals(".")) {
            System.out.println(ROSSO + "Error: Invalid or corrupt jarfile" + RESET);
            System.exit(1);
        }

        System.out.println(BLU + "                      /  |                              /  |/  |          /  |                 " + RESET);
        System.out.println(BLU + " ______   __    __  _$$ |_     ______          _______ $$ |$$/   _______ $$ |   __   ______  " + RESET);
        System.out.println(BLU + "/      \\ /  |  /  |/ $$   |   /      \\        /       |$$ |/  | /       |$$ |  /  | /      \\" + RESET);
        System.out.println(BLU + "$$$$$$  |$$ |  $$ |$$$$$$/   /$$$$$$  |      /$$$$$$$/ $$ |$$ |/$$$$$$$/ $$ |_/$$/ /$$$$$$  |/$$$$$$  |" + RESET);
        System.out.println(BLU + "$$ |  $$ |$$ |  $$ |  $$ | __$$ |  $$ |      $$ |      $$ |$$ |$$ |      $$ | $$ | $$ | $$ | $$ | $$ |" + RESET);
        System.out.println(BLU + "$$ |__$$ |$$ |  $$ |  $$ |/  |$$ |__$$ |      $$ |_____ $$ |$$ |$$ \\_____ $$   $$<  $$    $$ |$$ |  $$/ " + RESET);
        System.out.println(BLU + "$$    $$ |$$ \\__$$ |  $$ |$$ |$$    $$/       $$       |$$ |$$ |$$       |$$$$$$  \\ $$$$$$$$/ $$ |      " + RESET);
        System.out.println(BLU + "$$$$$$$$/ $$    $$/   $$  $$/ $$$$$$$/        $$$$$$$$/ $$/ $$/ $$$$$$$$/ $$ | $$  |$$       |$$ |    " + RESET);
        System.out.println(BLU + "         $$$$$$/     $$$$/                              $$/   $$/  $$$$$$$/ $$/   $$/  $$$$$$$/ $$/   " + RESET);
        System.out.println(ROSSO + "Autoclicker by _s0sa_." + RESET);

        System.out.println(ROSSO + "[1] Start autoclicker" + RESET);
        System.out.println(ROSSO + "[2] Stop autoclicker" + RESET);
        System.out.println(ROSSO + "[3] Exit" + RESET);

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println(VERDE + "Autoclicker started" + RESET);
                System.out.println(GIALLO + "Enter clicks for second [CPS]: " + RESET);
                try {
                    int clicksPerSecond = Integer.parseInt(scanner.nextLine());
                    System.out.println(GIALLO + "Select mouse button (left/right): " + RESET);
                    String button = scanner.nextLine().toLowerCase();
                    System.out.println(GIALLO + "Enter delay between clicks [ms]: " + RESET);
                    int delay = Integer.parseInt(scanner.nextLine());
                    System.out.println(GIALLO + "Enter your preferred key to use the Clicker: " + RESET);
                    String key = scanner.nextLine().toLowerCase();

                    System.out.println(BLU + "Autoclicker will start in 3 seconds, press " + key + " to start. Press CTRL+C to stop." + RESET);
                    Thread.sleep(3000);

                    Robot robot = new Robot();
                    int mouseButton = button.equals("right") ? InputEvent.BUTTON3_DOWN_MASK : InputEvent.BUTTON1_DOWN_MASK;

                    while (true) {
                        robot.mousePress(mouseButton);
                        robot.mouseRelease(mouseButton);
                        Thread.sleep(1000 / clicksPerSecond);
                    }
                } catch (Exception e) {
                    System.out.println(ROSSO + "Error: " + e.getMessage() + RESET);
                }
                break;
            case "2":
                System.out.println(VERDE + "Autoclicker stopped" + RESET);
                break;
            case "3":
                System.out.println(VERDE + "Exiting..." + RESET);
                System.out.println(ROSSO + "Bye! [Ricorda di non cheattare sempre altrimenti verrai sgamato e trattato come un cagnolino]" + RESET);
                System.exit(0);
                break;
            default:
                System.out.println(ROSSO + "Invalid choice" + RESET);
                break;
        }
        scanner.close();
    }
}
