package frc.robot.utils;


public final class Constants {

    public static class DriveTrainConstants {
        public static final int DRIVETRAIN_LEFT_FRONT = 2;
        public static final int DRIVETRAIN_LEFT_BACK = 3;
        public static final int DRIVETRAIN_RIGHT_FRONT = 1;
        public static final int DRIVETRAIN_RIGHT_BACK = 4;
    }

    public static class ArmConstants {
        public static final int ARM_MOTOR = 5;
        public static final int FEED_MOTOR = 6;

        public static final int MAIN_ARM_ENCODER_PORT = 0;
        public static final int MINI_ARM_ENCODER_PORT = 1;

        public static final int MINI_ARM_MOTOR = 8;
        public static final int MINI_FEED_MOTOR = 7;

        public static final int STOP_SWITCH = 2;

        public static final double MAIN_ARM_OUT_THRESHOLD = 0.4;
        
        public static final double MINI_ARM_OUT_THRESHOLD = 0.4;
        
        public static final double ARM_SPEED_DEADZONE = 0.1;
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
