// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
// import com.revrobotics.CANSparkMAX;
// import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
//import com.ctre.phoenix.motorcontrol.can.WPI_rev;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase {

  public WPI_TalonFX leftMotor = new WPI_TalonFX(8);
  public WPI_TalonFX rightMotor = new WPI_TalonFX(11);
  public CANSparkMax angleMaker = new CANSparkMax(62, CANSparkMaxLowLevel.MotorType.kBrushed);
  public ClimberSubsystem() {
    this.leftMotor.configFactoryDefault();
    //this.leftMotor.setInverted(true);
    this.leftMotor.setNeutralMode(NeutralMode.Brake);

    this.rightMotor.configFactoryDefault();
    this.rightMotor.setInverted(false);
    this.rightMotor.setNeutralMode(NeutralMode.Brake);

    this.angleMaker.restoreFactoryDefaults();
    this.angleMaker.setInverted(true);
    this.angleMaker.setIdleMode(CANSparkMax.IdleMode.kBrake);
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
    this.angleMaker.set(targetSpeed);
  }

  // this.shootMotor.set(ControlMode.PercentOutput, 0.7);
}
