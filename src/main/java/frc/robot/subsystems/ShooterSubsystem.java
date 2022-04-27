// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  
  private WPI_TalonFX shootMotor = new WPI_TalonFX(9);
  
  public CANSparkMax shootMotor2 = new CANSparkMax(17, CANSparkMaxLowLevel.MotorType.kBrushed);

  public ShooterSubsystem() {
    this.shootMotor.configFactoryDefault();
    this.shootMotor.setInverted(false);
    this.shootMotor.setNeutralMode(NeutralMode.Coast);

    this.shootMotor2.restoreFactoryDefaults();
    this.shootMotor2.setInverted(false);
    this.shootMotor2.setIdleMode(CANSparkMax.IdleMode.kCoast);
  }

  public void resetEncoders()
  {
    this.shootMotor.getSensorCollection().setIntegratedSensorPosition(0, 0);
  }
  public double getEncoderDistance()
  {
    return this.shootMotor.getSensorCollection().getIntegratedSensorPosition();
  }

  @Override
  public void periodic() {
    
  }

  @Override
  public void simulationPeriodic() {
    
  }

  public void setPower(double power) {
    double ratio =1.5;
    //this.shootMotor.set(ControlMode.PercentOutput, power);
    //this.shootMotor2.set(power); // this looks like an arbritary number, but it is not, assuming the gear ratios are even on each (which is not likely) this is the percent we need to make them the same speed
    //this.shootMotor.set(ControlMode.PercentOutput,(power / (2/3/4))*ratio);
    //this.shootMotor2.set(power/ratio);
    this.shootMotor.set(ControlMode.PercentOutput,(power * 1));
    this.shootMotor2.set(power * 1);
  }
  public void shoot(double power)
  {
    this.setPower(power);
  }

  public void enterIdle() {
    this.setPower(0.2);
  }

  public void stop()
  {
    this.setPower(0);
  }
}
