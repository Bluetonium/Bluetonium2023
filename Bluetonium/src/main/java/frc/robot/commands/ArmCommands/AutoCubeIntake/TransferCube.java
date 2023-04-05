// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands.AutoCubeIntake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class TransferCube extends CommandBase {
  private int ticks;

  /** Creates a new TransferCube. */
  public TransferCube() {
    addRequirements(RobotContainer.m_arm);
  }

  @Override
  public void initialize() {
    ticks = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.m_arm.miniArmSpeed(-0.3);
    RobotContainer.m_arm.mainArmSpeed(0.2);
    ticks++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ticks = 0;
    RobotContainer.m_arm.miniArmSpeed(0);
    RobotContainer.m_arm.mainArmSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (ticks > 50);
  }
}
