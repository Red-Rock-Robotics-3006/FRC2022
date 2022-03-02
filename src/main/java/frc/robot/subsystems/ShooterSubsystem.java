// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  
  private WPI_TalonFX shootMotor = new WPI_TalonFX(9);
  

  public ShooterSubsystem() {
    this.shootMotor.configFactoryDefault();
    this.shootMotor.setInverted(true);
    this.shootMotor.setNeutralMode(NeutralMode.Brake);

  }

  @Override
  public void periodic() {
    
  }

  @Override
  public void simulationPeriodic() {
    
  }

  public void shoot()
  {
    this.shootMotor.set(ControlMode.PercentOutput, 0.7);
  }

  public void stop()
  {
    this.shootMotor.set(ControlMode.PercentOutput, 0);
  }
}
