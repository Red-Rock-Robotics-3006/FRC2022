// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.Autonomous;
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
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
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
  private final Autonomous basicAutoCommand = new Autonomous(drive,intake, shooter);
  //private final TankDriveSubsystem drive = new TankDriveSubsytem();
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    drive.setDefaultCommand(
      new RunCommand(
        () -> drive.tank(
          0.5*driveStick.getRawAxis(XboxController.Axis.kLeftY.value), 
          0.5*driveStick.getRawAxis(XboxController.Axis.kRightY.value)
        ),
        drive
      )
    );

    configureButtonBindings();
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
    new JoystickButton(mechStick, XboxController.Axis.kRightTrigger.value)//JoystickConstants.buttonX)
    .whenPressed(new InstantCommand(() -> shooter.shoot()))
    .whenReleased(new InstantCommand(() -> shooter.stop()));
    // new JoystickButton(mechStick, XboxController.Axis.kLeftTrigger.value)//JoystickConstants.buttonA)
    // .whenPressed(new InstantCommand(() -> intake.setPower(0.5)))
    // .whenReleased(new InstantCommand(() -> intake.setPower(0)));
    new JoystickButton(mechStick, XboxController.Button.kA.value)//JoystickConstants.buttonA)
    .whenPressed(new InstantCommand(() -> intake.setPower(0.5)))
    .whenReleased(new InstantCommand(() -> intake.setPower(0)));
    new JoystickButton(mechStick, XboxController.Button.kB.value)//JoystickConstants.buttonA)
    .whenPressed(new InstantCommand(() -> intake.setPower(-0.3)))
    .whenReleased(new InstantCommand(() -> intake.setPower(0)));

    new JoystickButton(mechStick, XboxController.Button.kRightBumper.value)
    .whenPressed(new InstantCommand(() -> climber.anglerSpeed(-0.3f)))
    .whenReleased(new InstantCommand(() -> climber.anglerSpeed(0)));
    new JoystickButton(mechStick, XboxController.Button.kLeftBumper.value)//if doesn't work change back to 2
    .whenPressed(new InstantCommand(() -> climber.anglerSpeed(0.3f)))
    .whenReleased(new InstantCommand(() -> climber.anglerSpeed(0)));

    //Climber
    new Button(() -> -1 * mechStick.getRawAxis(XboxController.Axis.kLeftY.value) > 0.3)
    .whenPressed(new InstantCommand(() -> climber.leftSpeed(0.3f)))
    .whenReleased(new InstantCommand(() -> climber.leftSpeed(0)));
    new Button(() -> -1 * mechStick.getRawAxis(XboxController.Axis.kLeftY.value) < -0.3)
    .whenPressed(new InstantCommand(() -> climber.leftSpeed(-0.3f)))
    .whenReleased(new InstantCommand(() -> climber.leftSpeed(0)));
    new Button(() -> -1 * mechStick.getRawAxis(XboxController.Axis.kRightY.value) > 0.3)
    .whenPressed(new InstantCommand(() -> climber.rightSpeed(0.3f)))
    .whenReleased(new InstantCommand(() -> climber.rightSpeed(0)));
    new Button(() -> -1 * mechStick.getRawAxis(XboxController.Axis.kRightY.value) < -0.3)
    .whenPressed(new InstantCommand(() -> climber.rightSpeed(-0.3f)))
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
    return new DriveLengthCommand(this.drive, -0.2, 100000)
      .beforeStarting(new ParallelDeadlineGroup(
        new IntakeLengthCommand(this.intake, 0.3, 500000), 
        new ShootPowerCommand(this.shooter, 0.7)
        )
      );//new ShootCommand(shooter, intake, drive);
  }
}
