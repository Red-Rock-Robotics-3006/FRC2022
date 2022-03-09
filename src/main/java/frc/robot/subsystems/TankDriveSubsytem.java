package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TankDriveSubsytem extends SubsystemBase{

    public WPI_TalonFX forwardLeft = new WPI_TalonFX(1);
    public WPI_TalonFX backLeft = new WPI_TalonFX(2);
    public WPI_TalonFX forwardRight = new WPI_TalonFX(3);
    public WPI_TalonFX backRight = new WPI_TalonFX(4);

    public TankDriveSubsytem() {
        this.forwardLeft.configFactoryDefault();
        this.forwardLeft.setInverted(true);
        this.forwardLeft.setNeutralMode(NeutralMode.Brake);
        
        this.backLeft.configFactoryDefault();
        this.backLeft.setInverted(true);
        this.backLeft.setNeutralMode(NeutralMode.Brake);

        this.forwardRight.configFactoryDefault();
        this.forwardRight.setInverted(true);
        this.forwardRight.setNeutralMode(NeutralMode.Brake);
        
        this.backRight.configFactoryDefault();
        this.backRight.setInverted(true);
        this.backRight.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void periodic() {}
  
    @Override
    public void simulationPeriodic() {}

    public void moveLeftForward(float targetSpeed){
        this.forwardLeft.set(ControlMode.PercentOutput,targetSpeed);
        this.backLeft.set(ControlMode.PercentOutput,targetSpeed);
    }
    public void moveRightForward(float targetSpeed){
        this.forwardRight.set(ControlMode.PercentOutput,targetSpeed);
        this.forwardLeft.set(ControlMode.PercentOutput,targetSpeed);
    }
    
}
