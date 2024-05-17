// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private final CANSparkMax leftMotor = new CANSparkMax(2, MotorType.kBrushless);
  private final CANSparkMax rightMotor = new CANSparkMax(1, MotorType.kBrushless);
  private final XboxController control = new XboxController(0);

  private final RelativeEncoder leftEncoder = leftMotor.getEncoder();
  private final RelativeEncoder rightEncoder = rightMotor.getEncoder();

  private final static double speedCoefficent = 0.75;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    leftMotor.setInverted(false);
    rightMotor.setInverted(true);

    leftMotor.setIdleMode(IdleMode.kBrake);
    rightMotor.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    double leftPercentage = 0.0;
    double rightPercentage = 0.0;

    leftPercentage = -control.getLeftY();
    rightPercentage = - control.getRightY();

    leftMotor.setVoltage(leftPercentage * speedCoefficent * 12.0);
    rightMotor.setVoltage(rightPercentage * speedCoefficent * 12.0);

    
    SmartDashboard.putNumber("leftPercentage", leftPercentage);
    SmartDashboard.putNumber("rightPercentage", rightPercentage);

    SmartDashboard.putNumber("leftEncoderReportedVelocity", leftEncoder.getVelocity());
    SmartDashboard.putNumber("rightEncoderReportedVelocity", rightEncoder.getVelocity());
    
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
