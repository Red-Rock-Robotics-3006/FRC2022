// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.DriveLengthCommand;
//import frc.robot.commands.WorkInProggressAuto;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IntakeLengthCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.ShootPowerCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;
//import frc.robot.subsystems.TankDriveSubsytem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final XboxController driveStick = new XboxController(0);
  private final XboxController mechStick = new XboxController(1);
  private final ShooterSubsystem shooter = new ShooterSubsystem();
  private final ClimberSubsystem climber = new ClimberSubsystem();
  private final IntakeSubsystem intake = new IntakeSubsystem();
  private final TankDriveSubsystem drive = new TankDriveSubsystem();
  //private final Autonomous basicAutoCommand = new Autonomous(drive,intake, shooter);
  double speed = 0.55;
  //private final TankDriveSubsystem drive = new TankDriveSubsytem();
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    drive.setDefaultCommand(
      new RunCommand(
        () -> drive.tank(
          // Math.pow(speed * driveStick.getRawAxis(XboxController.Axis.kLeftY.value),3), 
          // Math.pow(speed * driveStick.getRawAxis(XboxController.Axis.kRightY.value),3)
          speed * driveStick.getRawAxis(XboxController.Axis.kLeftY.value),
          speed * driveStick.getRawAxis(XboxController.Axis.kRightY.value)
        ),
        drive
      )
    );

    configureButtonBindings();
  }

public void setSpeed(double speed) {
  this.speed = speed;
}

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Drive
    // Go to 46-58
    //Intake and Shooter
    // new JoystickButton(mechStick, XboxController.Axis.kRightTrigger.value)//JoystickConstants.buttonX)
    // .whenPressed(new InstantCommand(() -> shooter.shoot()))
    // .whenReleased(new InstantCommand(() -> shooter.stop()));
    // shooter.setPower(mechStick.getRawAxis(XboxController.Axis.kRightTrigger.value));
    // THIS IS THE ONE THAT WORKS
    new JoystickButton(mechStick, XboxController.Button.kX.value)//JoystickConstants.buttonX)
    .whenPressed(new InstantCommand(() -> shooter.shoot(0.45)))
    .whenReleased(new InstantCommand(() -> shooter.enterIdle()));

    new JoystickButton(driveStick, XboxController.Button.kLeftBumper.value)
    .whenPressed(new InstantCommand(() -> shooter.stop()));
    // shooter.setPower(mechStick.getRawAxis(XboxController.Axis.kRightTrigger.value));
    // System.out.println("SHOOTER POWER" + XboxController.Axis.kRightTrigger.value);
    // new JoystickButton(mechStick, XboxController.Axis.kRightTrigger.value)//JoystickConstants.buttonX)
    // .whenPressed(new InstantCommand(() -> shooter.setPower(mechStick.getRawAxis(XboxController.Axis.kRightTrigger.value))))
    // .whenReleased(new InstantCommand(() -> shooter.stop()));

    // new JoystickButton(mechStick, XboxController.Axis.kLeftTrigger.value)//JoystickConstants.buttonA)
    // .whenPressed(new InstantCommand(() -> intake.setPower(0.5)))
    // .whenReleased(new InstantCommand(() -> intake.setPower(0)));

    //Boost button
    new JoystickButton(mechStick, XboxController.Button.kY.value)
    .whenPressed(new InstantCommand(() -> this.setSpeed(0.715)))
    .whenReleased(new InstantCommand(() -> this.setSpeed(0.55))); 

    new JoystickButton(mechStick, XboxController.Button.kA.value)//JoystickConstants.buttonA)
    .whenPressed(new InstantCommand(() -> intake.setPower(0.6)))
    .whenReleased(new InstantCommand(() -> intake.setPower(0)));
    new JoystickButton(mechStick, XboxController.Button.kB.value)//JoystickConstants.buttonA)
    .whenPressed(new InstantCommand(() -> intake.setPower(-0.6)))
    .whenReleased(new InstantCommand(() -> intake.setPower(0)));

    new JoystickButton(mechStick, XboxController.Button.kRightBumper.value)
    .whenPressed(new InstantCommand(() -> climber.anglerSpeed(-0.8f)))
    .whenReleased(new InstantCommand(() -> climber.anglerSpeed(0)));
    new JoystickButton(mechStick, XboxController.Button.kLeftBumper.value)//if doesn't work change back to 2
    .whenPressed(new InstantCommand(() -> climber.anglerSpeed(0.8f)))
    .whenReleased(new InstantCommand(() -> climber.anglerSpeed(0)));

    //Climber
    new Button(() -> -1 * mechStick.getRawAxis(XboxController.Axis.kLeftY.value) > 0.3)
    .whenPressed(new InstantCommand(() -> climber.leftSpeed(-0.8f)))
    .whenReleased(new InstantCommand(() -> climber.leftSpeed(0)));
    new Button(() -> -1 * mechStick.getRawAxis(XboxController.Axis.kLeftY.value) < -0.3)
    .whenPressed(new InstantCommand(() -> climber.leftSpeed(0.8f)))
    .whenReleased(new InstantCommand(() -> climber.leftSpeed(0)));
    new Button(() -> -1 * mechStick.getRawAxis(XboxController.Axis.kRightY.value) > 0.3)
    .whenPressed(new InstantCommand(() -> climber.rightSpeed(0.8f)))
    .whenReleased(new InstantCommand(() -> climber.rightSpeed(0)));
    new Button(() -> -1 * mechStick.getRawAxis(XboxController.Axis.kRightY.value) < -0.3)
    .whenPressed(new InstantCommand(() -> climber.rightSpeed(-0.6f)))
    .whenReleased(new InstantCommand(() -> climber.rightSpeed(0)));
    // new Button(() -> -1 * mechStick.getRawAxis(XboxController.Button.kA.value) < -0.3)
    // .whenPressed(new InstantCommand(() -> climber.anglerSpeed(-0.3f)))
    // .whenReleased(new InstantCommand(() -> climber.anglerSpeed(0)));
    /*new JoystickButton(mechStick, JoystickConstants.buttonB)
    .whenPressed(new InstantCommand(() -> climber.rightSpeed(0.3f)))
    .whenReleased(new InstantCommand(() -> climber.rightSpeed(0)));
    new JoystickButton(mechStick, JoystickConstants.buttonY)
    .whenPressed(new InstantCommand(() -> climber.leftSpeed(0.3f)))
    .whenReleased(new InstantCommand(() -> climber.leftSpeed(0)));
    new Button(() -> -1 * mechStick.getRawAxis(JoystickConstants.leftYAxis) > 0.3)
    .whenPressed(new InstantCommand(() -> climber.setSynchronousSpeed(0.5f)))
    .whenReleased(new InstantCommand(() -> climber.setSynchronousSpeed(0)));
    new Button(() -> -1 * mechStick.getRawAxis(JoystickConstants.leftYAxis) < -0.3)
    .whenPressed(new InstantCommand(() -> climber.setSynchronousSpeed(-0.5f)))
    .whenReleased(new InstantCommand(() -> climber.setSynchronousSpeed(0)));
    */

    // new JoystickButton(mechJoystick, 1).whenPressed(new InstantCommand(() -> climber.setSynchronousSpeed(0.7f))).whenReleased(new InstantCommand(() -> climber.setSynchronousSpeed(0)));

    /*new JoystickButton(mechStick, JoystickConstants.buttonA)
    .whenPressed(new InstantCommand(() -> intake.setPower(0.2)))
    .whenReleased(new InstantCommand(() -> intake.setPower(0)));*/
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //return new new ShootCommand(shooter, intake, drive);
    /*return new DriveLengthCommand(this.drive, -0.2, 100000)
      .beforeStarting(new ParallelDeadlineGroup(
        new IntakeLengthCommand(this.intake, 0.3, 500000), 
        new ShootPowerCommand(this.shooter, 0.7)
        )
      );*/
    // The following section should:
    // Back into the ball on the field
    // Pick up the ball on the field
    // Move back to the robots orginal placement
    // Start spinning up the shooter
    // Start the intake 2 seconds later
    // Stop both the intake and shooter after the intake has spun for 500,000 encoder ticks
    // Drive forwards for 100,000 encoder ticks

  



    //return new DriveLengthCommand(this.drive, -0.5, 25000) //(Drive subsys; Drive power; Drive encoder ticks)
    //   .beforeStarting(
    //     //new ParallelDeadlineGroup(
    //     new IntakeLengthCommand(this.intake, 0.5, 5000) //(Intake subsys; Intake power; Intake encoder ticks)
    //         .beforeStarting(new WaitCommand(2.0)) //(Wait seconds) - This should be long enough to allow the shooter to speed up before ball feeding
    //       //new ShootPowerCommand(this.shooter, 0.7
    //    // ) //(Shooter subsys; Shooter power)
    //         .beforeStarting(  
             
    //          new DriveLengthCommand(this.drive,0.5,55000)
    //         //     .beforeStarting(
    //         //       new IntakeLengthCommand(this.intake, -0.5, 50000)
                
    //         //       //new ShootPowerCommand(this.shooter, 0.3)
    //         //     //)  
    //         //     //Moving back to shoot it
    //         //     .beforeStarting(
    //         //       new ParallelDeadlineGroup(
    //         //         new DriveLengthCommand(this.drive, -0.5, 55000),
    //         //         new IntakeLengthCommand(this.intake, 0.5, 850000)
    //         //           //.beforeStarting(new WaitCommand(1.0))//We cant overdo the intake! Otherwise we will pull the ball to close to the shooter to shoot, we need timing to be exact.
    //         //         //new ShootPowerCommand(this.shooter,0.55)
    //         //       )
    //         //     )
            
    //       )
    // );
    /*return new DriveLengthCommand(this.drive, 0.2, 100000).beforeStarting(
    new DriveLengthCommand(this.drive, 0.2, 100000) //(Drive subsys; Drive power; Drive encoder ticks)
      .beforeStarting(new ParallelDeadlineGroup(
        new IntakeLengthCommand(this.intake, 0.3, 500000)
        .beforeStarting(new DriveLengthCommand(this.drive, 0.2, 75000).beforeStarting(new DriveLengthCommand(this.drive, -0.2, 75000))) //(Intake subsys; Intake power; Intake encoder ticks)
          .beforeStarting(new WaitCommand(2.0)), //(Wait seconds) - This should be long enough to allow the shooter to speed up before ball feeding
        new ShootPowerCommand(this.shooter, 0.7) //(Shooter subsys; Shooter power)
      )
      )
    );*/
    /*return new SequentialCommandGroup(
      new ParallelDeadlineGroup(
        new DriveLengthCommand(this.drive, -0.2, 45000),
        new IntakeLengthCommand(this.intake, 0.3, 1000000)
      ),
      new DriveLengthCommand(this.drive, 0.2, 45000),
      new ParallelDeadlineGroup(
        new WaitCommand(3),
        new ShootPowerCommand(this.shooter, 0.55),
        new IntakeLengthCommand(this.intake, 0.3, 1000000)
      ),
      new DriveLengthCommand(this.drive, -0.2, 2*20000)
    );*/
    //.beforeStarting(new ParallelCommandGroup(new DriveLengthCommand(this.drive, -0.2, 75000), new IntakeLengthCommand(this.intake, 0.2, 75000)));
  //}
//}

    

return new DriveLengthCommand(this.drive, -0.3, 75000) //(Drive subsys; Drive power; Drive encoder ticks)
      .beforeStarting(
        new ParallelDeadlineGroup(
          new IntakeLengthCommand(this.intake, 1, 1000000) //(Intake subsys; Intake power; Intake encoder ticks)
            .beforeStarting(new WaitCommand(4)), //(Wait seconds) -   aaaaaaaaaaaaaaaaaaaaaaaa xhis should be long enough to allow the shooter to speed up before ball feeding
          new ShootPowerCommand(this.shooter, 0.45)
        )); //(Shooter subsys; Shooter power)
  }    
}          
/*

    return new DriveLengthCommand(this.drive, -0.5, 75000) //(Drive subsys; Drive power; Drive encoder ticks)
      .beforeStarting(
        new ParallelDeadlineGroup(
          new IntakeLengthCommand(this.intake, 0.5, 500000) //(Intake subsys; Intake power; Intake encoder ticks)
            .beforeStarting(new WaitCommand(2.0)), //(Wait seconds) - This should be long enough to allow the shooter to speed up before ball feeding
          new ShootPowerCommand(this.shooter, 0.7
        ) //(Shooter subsys; Shooter power)
      ).beforeStarting(  
        new ParallelDeadlineGroup(
          new DriveLengthCommand(this.drive,0.5,75000),
          new ShootPowerCommand(this.shooter, 0.3)
        )  
         //Moving back to shoot it
        .beforeStarting(
          new ParallelDeadlineGroup(
            new DriveLengthCommand(this.drive, -0.5, 75000),
            new IntakeLengthCommand(this.intake, 0.5, 25000)
              .beforeStarting(new WaitCommand(1.0)),//We cant overdo the intake! Otherwise we will pull the ball to close to the shooter to shoot, we need timing to be exact.
            new ShootPowerCommand(this.shooter,0.55)
          )
        )
      )
    );
  }
}
    */