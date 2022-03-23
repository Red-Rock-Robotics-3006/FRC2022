// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ShootCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final TankDriveSubsystem driveTrain;
  private final ShooterSubsystem shooter;
  private final IntakeSubsystem intake;

  public ShootCommand(ShooterSubsystem subsystem, IntakeSubsystem intake, TankDriveSubsystem driveTrain) {
    shooter = subsystem;
    this.intake = intake;
    this.driveTrain = driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.shooter, this.intake, this.driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.shooter.resetEncoders();
    this.intake.resetEncoders();
    this.driveTrain.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.intake.setPower(0.2);
    while(Math.abs(this.shooter.getEncoderDistance()) < Math.abs(50000))
      this.shooter.shoot();
    this.driveTrain.allMotorEquality(-0.2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //System.out.println("Shooter ended");
    this.shooter.stop();
    this.intake.setPower(0);
    this.driveTrain.allMotorEquality(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(this.driveTrain.getRightEncoderDistance()) > Math.abs(50000);
  }
}
