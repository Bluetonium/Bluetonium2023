package frc.robot;

public final class Constants {
// Motor constants
    //drive train
    public static final int DRIVETRAIN_LEFT_FRONT = 2;
    public static final int DRIVETRAIN_LEFT_BACK = 3;

    public static final int  DRIVETRAIN_RIGHT_FRONT = 1;
    public static final int DRIVETRAIN_RIGHT_BACK = 4;
    //arm/feed
    public static final int ARM_MOTOR = 5;
    public static final int FEED_MOTOR = 6;


// controller constants
    // controller 0 inputs
    public static final int DRIVER_CONTROLLER1_MOVE_AXIS = 2;//change this (if needed)
    public static final int DRIVER_CONTROLLER1_ROTATE_AXIS = 1;//change this (if needed)

    // controller 1 inputs
    public static final int DRIVER_CONTROLLER2_ARMMOTOR = 1;
    public static final int DRIVER_CONTROLLER2_FEEDIN = 1;
    public static final int DRIVER_CONTROLLER2_FEEDOUT = 2;
    

    public static int DRIVER_CONTROLLER1 = 0;
    public static int DRIVER_CONTROLLER2 = 1;


//auto stuff
    public static final double BALCINGKP = 0.4d;
    public static final double BALCINGKI = 0.15d;
    public static final double BALCINGKD = 0.0d;

}
