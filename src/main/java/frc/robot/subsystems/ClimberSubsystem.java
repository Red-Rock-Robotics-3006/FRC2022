// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase {

  public WPI_TalonFX leftMotor = new WPI_TalonFX(8);
  public WPI_TalonFX rightMotor = new WPI_TalonFX(11);
  public WPI_TalonSRX angleMaker = new WPI_TalonSRX(12);

  public ClimberSubsystem() {
    this.leftMotor.configFactoryDefault();
    //this.leftMotor.setInverted(true);
    this.leftMotor.setNeutralMode(NeutralMode.Brake);

    this.rightMotor.configFactoryDefault();
    this.rightMotor.setInverted(true);
    this.rightMotor.setNeutralMode(NeutralMode.Brake);

    this.angleMaker.configFactoryDefault();
    this.angleMaker.setInverted(true);
    this.angleMaker.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    
  }

  @Override
  public void simulationPeriodic() {
    
  }

  public void setSynchronousSpeed(float targetSpeed)
  {
    this.rightMotor.set(ControlMode.PercentOutput, targetSpeed);
    this.leftMotor.set(ControlMode.PercentOutput, targetSpeed);
  }
  public void rightSpeed(float targetSpeed)
  {
    this.rightMotor.set(ControlMode.PercentOutput,targetSpeed);
  }
  public void leftSpeed(float targetSpeed){
    this.leftMotor.set(ControlMode.PercentOutput,targetSpeed);
  }
  public void anglerSpeed(float targetSpeed){
    this.angleMaker.set(ControlMode.PercentOutput,targetSpeed);
  }

  // this.shootMotor.set(ControlMode.PercentOutput, 0.7);
}
