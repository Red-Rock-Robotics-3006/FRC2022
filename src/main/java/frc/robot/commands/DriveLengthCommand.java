// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TankDriveSubsystem;

public class DriveLengthCommand extends CommandBase {
  private TankDriveSubsystem driver;
  private double power;
  private int length;

  /** Creates a new DriveLengthCommand. */
  public DriveLengthCommand(TankDriveSubsystem driver, double power, int length) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driver);

    this.driver = driver;
    this.power = power;
    this.length = length;

    this.driver.resetEncoders();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    this.driver.allMotorEquality(this.power);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.driver.allMotorEquality(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(this.driver.getRightEncoderDistance()) > Math.abs(this.length);
  }
}
