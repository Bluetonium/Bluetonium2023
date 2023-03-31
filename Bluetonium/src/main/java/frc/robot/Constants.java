package frc.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

public final class Constants {
    /* Motors */

    // drive train
    public static final int DRIVETRAIN_LEFT_FRONT = 2;
    public static final int DRIVETRAIN_LEFT_BACK = 3;
    public static final int DRIVETRAIN_RIGHT_FRONT = 1;
    public static final int DRIVETRAIN_RIGHT_BACK = 4;

    public static final double DRIVETRAIN_RAMP_UP_SPEED = 0.03d;

    // arm/feed
    public static final int ARM_MOTOR = 5;
    public static final int FEED_MOTOR = 6;

    public static final int MINI_ARM_MOTOR = 8;
    public static final int MINI_FEED_MOTOR = 7;

    /* controller constants */

    // the controllers
    public static int DRIVER_CONTROLLER1 = 0;
    public static int DRIVER_CONTROLLER2 = 1;

    // controller 0 inputs
    public static final int DRIVER_CONTROLLER1_MOVE_AXIS = 2;
    public static final int DRIVER_CONTROLLER1_ROTATE_AXIS = 1;

    // controller 1 inputs
    public static final int DRIVER_CONTROLLER2_ARMMOTOR = 1;
    public static final int DRIVER_CONTROLLER2_FEEDIN = 1;
    public static final int DRIVER_CONTROLLER2_FEEDOUT = 2;

    public static final double DRIVER_MINIMUM_SPEED = 0.1;

    public static final int DRVIER_CONTROLLER2_YELLOW = 3;
    public static final int DRIVER_CONTROLLER2_PURPLE = 4;
    public static final int DRIVER_CONTROLLER2_NONE = 5;

    /* auto */
    public static final double BALCINGKP = 0.4d;// we will need to change these later as its more of less just guessing
    public static final double BALCINGKI = 0.15d;
    public static final double BALCINGKD = 0.0d;

    // Misic
    public static final Port ARDUINO_PORT = SerialPort.Port.kUSB2;
    public static final int LED_PWM_PORT = 0;
    public static final int NUMBER_OF_LEDS = 93;// change later bc tbh i dont really know

}
