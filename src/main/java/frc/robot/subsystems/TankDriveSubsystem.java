// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TankDriveSubsystem extends SubsystemBase {
  public WPI_TalonFX leftMotorBack = new WPI_TalonFX(1);
  public WPI_TalonFX leftMotorFront = new WPI_TalonFX(4);
  public WPI_TalonFX rightMotorBack = new WPI_TalonFX(2);
  public WPI_TalonFX rightMotorFront = new WPI_TalonFX(3);

  public TankDriveSubsystem() {
    this.leftMotorBack.configFactoryDefault();
    this.leftMotorBack.setInverted(true);
    this.leftMotorBack.setNeutralMode(NeutralMode.Brake);

    this.leftMotorFront.configFactoryDefault();
    this.leftMotorFront.setInverted(true);
    this.leftMotorFront.setNeutralMode(NeutralMode.Brake);

    this.rightMotorBack.configFactoryDefault();
    //this.rightMotorBack.setInverted(true);
    this.rightMotorBack.setNeutralMode(NeutralMode.Brake);

    this.rightMotorFront.configFactoryDefault();
    //this.rightMotorFront.setInverted(true);
    this.rightMotorFront.setNeutralMode(NeutralMode.Brake);
  }

  public TankDriveSubsystem tank(double leftPower, double rightPower) {
    return this
      .setPower(WheelPair.LEFT, leftPower)
      .setPower(WheelPair.RIGHT, rightPower);
  } 

  public TankDriveSubsystem setPower(WheelPair p, double power) {
    if(p == WheelPair.LEFT) {
        this.leftMotorBack.set(ControlMode.PercentOutput, power);
        this.leftMotorFront.set(ControlMode.PercentOutput, power);
    }
    else if(p == WheelPair.RIGHT) {
        this.rightMotorBack.set(ControlMode.PercentOutput, power);
        this.rightMotorFront.set(ControlMode.PercentOutput, power);
    }

    return this;
  }

  public TankDriveSubsystem allMotorEquality(double power)
  {
    this.leftMotorBack.set(ControlMode.PercentOutput, power);
    this.leftMotorFront.set(ControlMode.PercentOutput, power);
    this.rightMotorBack.set(ControlMode.PercentOutput, power);
    this.rightMotorFront.set(ControlMode.PercentOutput, power);
    

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