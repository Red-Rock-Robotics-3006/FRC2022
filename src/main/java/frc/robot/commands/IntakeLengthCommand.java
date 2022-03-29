// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeLengthCommand extends CommandBase {
  private IntakeSubsystem intake;
  private double power;
  private int length;

  /** Creates a new IntakeLength. */
  public IntakeLengthCommand(IntakeSubsystem intake, double power, int length) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);

    this.intake = intake;
    this.power = power;
    this.length = length;

    this.intake.resetEncoders();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.intake.setPower(power);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.intake.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(this.intake.getEncoderDistance()) > Math.abs(this.length);
  }
}
