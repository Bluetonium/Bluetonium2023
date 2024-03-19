package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

public class TeleopDrive extends Command {
    private DoubleSupplier forwardAxis;
    private DoubleSupplier rotationAxis;
    private DriveTrain driveTrain;

    public TeleopDrive(DoubleSupplier forwardAxis, DoubleSupplier rotationAxis, DriveTrain driveTrain) {
        addRequirements(driveTrain);
        this.forwardAxis = forwardAxis;
        this.rotationAxis = rotationAxis;
        this.driveTrain = driveTrain;
    }

    @Override
    public void execute() {
        driveTrain.setSpeed(forwardAxis.getAsDouble(), rotationAxis.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.setSpeed(0, 0);

    }
}
