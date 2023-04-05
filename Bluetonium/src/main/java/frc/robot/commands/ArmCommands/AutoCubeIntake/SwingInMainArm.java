// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands.AutoCubeIntake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

import com.revrobotics.RelativeEncoder;

public class SwingInMainArm extends CommandBase {
  public RelativeEncoder postion;

  /** Creates a new SwingInMainArm. */
  public SwingInMainArm() {
    addRequirements(RobotContainer.m_arm);
    postion = RobotContainer.m_arm.mainArmPostion;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.m_arm.mainArmSpeed(0.3);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_arm.mainArmSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(postion.getPosition()) < 1;
  }
}
