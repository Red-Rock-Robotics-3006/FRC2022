// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  // public static final class DriveConstants
  // {
  //   public static final int kEncoderCPR = 2048;
  //   public static final double kWheelDiameterMeters = 0.15875;
  //   public static final double kEncoderDistancePerPulse = (kWheelDiameterMeters * Math.PI) / ((double) kEncoderCPR * 10.71);
  // }
  public static final class auto{
    public static final double wheelRotations = 4096;
    public static final double wheelDiameter = 0.15875;
    public static final double wheelCircumference = wheelDiameter * Math.PI;
  }
    public static final class JoystickConstants {
        //Gamepad values
        public static final int buttonY = 4;
        public static final int buttonX = 3;
        public static final int buttonB = 2;
        public static final int buttonA = 1;
        public static final int buttonLeftBumper = 5;
        public static final int buttonRightBumper = 6; 
        public static final int leftXAxis = 0;
        public static final int leftYAxis = 1; 
        public static final int rightXAxis = 4;
        public static final int rightYAxis = 5; 
        public static final int leftTrigger = 2; 
        public static final int rightTrigger = 3;
        public static final double triggerDeadZone = 0.05;
      }
}
