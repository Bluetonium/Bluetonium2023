package frc.robot.utils;

import edu.wpi.first.math.trajectory.TrapezoidProfile;

public final class Constants {
    public static class global {
        // nothing rn but might need later
    }

    public static class DriveTrainConstants {
        public static final int DRIVETRAIN_LEFT_FRONT = 2;
        public static final int DRIVETRAIN_LEFT_BACK = 3;
        public static final int DRIVETRAIN_RIGHT_FRONT = 1;
        public static final int DRIVETRAIN_RIGHT_BACK = 4;

    }

    public static class ArmConstants {
        public static final int ARM_MOTOR = 5;
        public static final int FEED_MOTOR = 6;

        public static final int MINI_ARM_MOTOR = 8;
        public static final int MINI_FEED_MOTOR = 7;

        public static final int STOP_SWITCH = 0;

        public static final double MAIN_MAX = 1;
        public static final double MAIN_MIN = -1;
        public static final double MAIN_PID_P = 0;
        public static final double MAIN_PID_I = 0;
        public static final double MAIN_PID_D = 0;

        public static final TrapezoidProfile MAIN_PROFILE = new TrapezoidProfile(// maxspeed of 5m/s, max accel of
                                                                                 // 10m/s, end state of 5, vel 0 start
                                                                                 // state of 0 pos 0 speed
                new TrapezoidProfile.Constraints(5, 10), new TrapezoidProfile.State(5, 0),
                new TrapezoidProfile.State(0, 0));

        public static final double MINI_MAX = 1;
        public static final double MINI_MIN = -1;
        public static final double MINI_PID_P = 0;
        public static final double MINI_PID_I = 0;
        public static final double MINI_PID_D = 0;

        public static final TrapezoidProfile MINI_PROFILE = new TrapezoidProfile(// maxspeed of 5m/s, max accel of
                                                                                 // 10m/s, end state of 5, vel 0 start
                                                                                 // state of 0 pos 0 speed
                new TrapezoidProfile.Constraints(5, 10), new TrapezoidProfile.State(5, 0),
                new TrapezoidProfile.State(0, 0));

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

        public static final double DRIVER_MINIMUM_SPEED = 0.1;

        public static final int DRVIER_CONTROLLER2_YELLOW = 3;
        public static final int DRIVER_CONTROLLER2_PURPLE = 4;
        public static final int DRIVER_CONTROLLER2_NONE = 5;
    }

    public static class AutoConstants {

        public static final double BALCINGKP = 0.4d;// we will need to change these later as its more of less just
                                                    // guessing
        public static final double BALCINGKI = 0.15d;
        public static final double BALCINGKD = 0.0d;
    }

    public static class MiscConstants {
        public static final int LED_PWM_PORT = 0;
        public static final int NUMBER_OF_LEDS = 93;// change later bc tbh i dont really know
    }
}
