// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;


/** An example command that uses an example subsystem. */
public class BasicAuto extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final TankDriveSubsystem driveTrain;
  private final IntakeSubsystem intake;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  
  private boolean doneRunning = false; 

  public BasicAuto(TankDriveSubsystem driveTrain,IntakeSubsystem intake) {
    
    this.driveTrain = driveTrain;
    this.intake = intake;
  
  }

  @Override
  public void initialize() {
    this.driveTrain.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
     
    TimedCommand(2,this.driveTrain.allMotorEquality(0.1))
    TimedCommand(5,this.intake.setPower(1))
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