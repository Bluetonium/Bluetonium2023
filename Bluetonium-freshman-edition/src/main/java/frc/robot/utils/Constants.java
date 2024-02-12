package frc.robot.utils;


public final class Constants {

    public static class DriveTrainConstants {
        public static final int DRIVETRAIN_LEFT_FRONT = 2;
        public static final int DRIVETRAIN_LEFT_BACK = 3;
        public static final int DRIVETRAIN_RIGHT_FRONT = 1;
        public static final int DRIVETRAIN_RIGHT_BACK = 4;
    }


    public static class ControllerConstants {
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

        public static final int DRVIER_CONTROLLER2_YELLOW = 3;
        public static final int DRIVER_CONTROLLER2_PURPLE = 4;
        public static final int DRIVER_CONTROLLER2_NONE = 5;
    }

    public static class LedConstants {
        public static final int LED_PWM_PORT = 0;
        public static final int NUMBER_OF_LEDS = 93;// change later bc tbh i dont really know
        
    }
    public static enum ledColors {
            YELLOW,
            PURPLE,
            OFF
         }
}
