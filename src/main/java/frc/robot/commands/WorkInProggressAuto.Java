// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

//network tables import
//import edu.wpi.first.networktables.NetworkTable;
//import edu.wpi.first.networktables.NetworkTableEntry;
//import edu.wpi.first.networktables.NetworkTableInstance;

//public class Yay extends CommandBase {
  //NetworkTable xEntry;
  //NetowrkTable yEntry;

  //public void robotInit(){
    //NetworkTableInstance isnt = NetworkTableInstance.getDefault()

    //NetworkTable table = inst.getTable("datatable")

    //xEntry = table.getG
  //}
//}

/** An example command that uses an example subsystem. */
public class WorkInProggressAuto extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final TankDriveSubsystem driveTrain;
  private final IntakeSubsystem intake;
  private final ShooterSubsystem shooter;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  
  private boolean doneRunning = false; 

  public WorkInProggressAuto(TankDriveSubsystem driveTrain,IntakeSubsystem intake,ShooterSubsystem shooter) {
    
    this.driveTrain = driveTrain;
    this.intake = intake;
    this.shooter = shooter;
  }

  @Override
  public void initialize() {
    this.driveTrain.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
     
    TimedCommand(2,this.driveTrain.allMotorEquality(0.1))
    TimedCommand(5,this.intake.setPower(1),this.shooter.shoot)
    this.shooter.stop;
    this.doneRunning = true; 

    // while(this.driveTrain.getRightEncoderDistance() > 3)
    // {
    //   driveTrain.resetEncoders();
    //   System.out.println("Encoder Distance: " + driveTrain.getRightEncoderDistance());
    // }

    // while(driveTrain.getRightEncoderDistance() < 3)
    // {
    //   System.out.println("Here AGain!");
    //   driveTrain.mecDrive(0, 0, 0.0, -0.5);
    // }

    
  }

  @Override
  public boolean isFinished() {
    return doneRunning;
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.driveTrain.stop();
  }

}