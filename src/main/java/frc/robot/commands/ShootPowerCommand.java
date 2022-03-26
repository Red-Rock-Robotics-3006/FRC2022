// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootPowerCommand extends CommandBase {
  private ShooterSubsystem shooter;
  private double power;

  /** Creates a new ShootPowerCommand. */
  public ShootPowerCommand(ShooterSubsystem shooter, double power) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);

    this.shooter = shooter;
    this.power = power;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.shooter.setPower(this.power);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
