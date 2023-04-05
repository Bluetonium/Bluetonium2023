// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands.AutoCubeIntake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

import com.revrobotics.RelativeEncoder;

public class ChangeMiniArmPos extends CommandBase {
  private final double extendedPos = 22.0d;
  private final double retractedPos = 0.0d;

  private boolean retracting = false;
  private RelativeEncoder encoder;

  /** Creates a new FlipDownArm. */
  public ChangeMiniArmPos(boolean retracting) {
    this.retracting = retracting;
    addRequirements(RobotContainer.m_arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    encoder = RobotContainer.m_arm.miniArmPosition;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (retracting) {
      RobotContainer.m_arm.miniArmSpeed(-0.3);
    } else {
      RobotContainer.m_arm.miniArmSpeed(0.3);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_arm.miniArmSpeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double current = encoder.getPosition();
    if (retracting) {
      return Math.abs(current - retractedPos) < 1;
    } else {
      return Math.abs(current - extendedPos) < 1;
    }
  }
}
