// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*
package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Autonomous extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final TankDriveSubsystem driveTrain;
  private final IntakeSubsystem intake;
  private final ShooterSubsystem shooter;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   /

  public Autonomous(TankDriveSubsystem driveTrain, IntakeSubsystem intake, ShooterSubsystem shooter) {
    this.driveTrain = driveTrain;
    this.intake = intake;
    this.shooter = shooter;

    addCommands(
      new ShootCommand(shooter, intake, driveTrain)
    );
  }

  @Override
  public void initialize() {
    this.driveTrain.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    /*new InstantCommand(() -> driveTrain.allMotorEquality(0.2));
    new InstantCommand(() -> intake.setPower(0.5));
    new InstantCommand(() -> shooter.shoot());*/

    /*while (this.driveTrain.getRightEncoderDistance() > 3) {
      driveTrain.resetEncoders();
      System.out.println("Encoder Distance: " + driveTrain.getRightEncoderDistance());
    }

    while (driveTrain.getRightEncoderDistance() < 3) {
      System.out.println("Here AGain!");
      driveTrain.mecDrive(0, 0, 0.0, -0.5);
    }

  }

  @Override
  public boolean isFinished() {
    return this.driveTrain.getRightEncoderDistance() > 10000;
  }

  //Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.driveTrain.allMotorEquality(0);
    this.shooter.stop();
    this.intake.setPower(0);
  }
}
*/