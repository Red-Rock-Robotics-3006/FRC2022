// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TankDriveSubsystem extends SubsystemBase {
  public WPI_TalonFX leftMotor1 = new WPI_TalonFX(1);
  public WPI_TalonFX leftMotor2 = new WPI_TalonFX(1);
  public WPI_TalonFX rightMotor1 = new WPI_TalonFX(1);
  public WPI_TalonFX rightMotor2 = new WPI_TalonFX(1);

  public TankDriveSubsystem() {
    this.leftMotor1.configFactoryDefault();
    this.leftMotor1.setInverted(true);
    this.leftMotor1.setNeutralMode(NeutralMode.Brake);

    this.leftMotor2.configFactoryDefault();
    this.leftMotor2.setInverted(true);
    this.leftMotor2.setNeutralMode(NeutralMode.Brake);

    this.rightMotor1.configFactoryDefault();
    this.rightMotor1.setInverted(true);
    this.rightMotor1.setNeutralMode(NeutralMode.Brake);

    this.rightMotor2.configFactoryDefault();
    this.rightMotor2.setInverted(true);
    this.rightMotor2.setNeutralMode(NeutralMode.Brake);
  }

  public TankDriveSubsystem tank(double leftPower, double rightPower) {
    return this
      .setPower(WheelPair.LEFT, leftPower)
      .setPower(WheelPair.RIGHT, rightPower);
  } 

  public TankDriveSubsystem setPower(WheelPair p, double power) {
    if(p == WheelPair.LEFT) {
        this.leftMotor1.set(ControlMode.PercentOutput, power);
        this.leftMotor2.set(ControlMode.PercentOutput, power);
    }
    else if(p == WheelPair.RIGHT) {
        this.rightMotor1.set(ControlMode.PercentOutput, power);
        this.rightMotor2.set(ControlMode.PercentOutput, power);
    }

    return this;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public enum WheelPair {
      LEFT, RIGHT
  }
}