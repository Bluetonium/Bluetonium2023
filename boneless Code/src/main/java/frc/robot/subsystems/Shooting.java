package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;

import frc.robot.Constants;

public class Shooting extends SubsystemBase {
    private CANSparkMax topMotor = new CANSparkMax(Constants.shooting.TOP_MOTOR, MotorType.kBrushless);
    private CANSparkMax bottomMotor = new CANSparkMax(Constants.shooting.BOTTOM_MOTOR, MotorType.kBrushed);
}
