package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class intakeCommand extends Command {

    public intakeCommand() {
        addRequirements(RobotContainer.m_intake);
    }

    @Override
    public void execute() {
        if (RobotContainer.armController.getLeftTriggerAxis() > 0.2) {
            RobotContainer.m_intake.setMotor1Speed(0.5);
            RobotContainer.m_intake.setMotor2Speed(0.5);
        } else {
            if (RobotContainer.armController.getLeftBumper()) {
                RobotContainer.m_intake.setMotor1Speed(-1);
            } else {
                RobotContainer.m_intake.setMotor1Speed(0);
            }
            if (RobotContainer.armController.getAButton()) {
                RobotContainer.m_intake.setMotor2Speed(-1);
            } else {
                RobotContainer.m_intake.setMotor2Speed(0);
            }
        }
    }

    @Override
    public void end(boolean interupted) {
        RobotContainer.m_intake.setMotor1Speed(0);
        RobotContainer.m_intake.setMotor2Speed(0);
    }

}
