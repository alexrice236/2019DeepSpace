/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class IntakeExtender extends Subsystem {

  public WPI_TalonSRX hatchIntakeExtensionMotor = new WPI_TalonSRX(RobotMap.hatchIntakeExtensionMotor);

  public IntakeExtender() {
   configureTalon();
  }

  private void configureTalon() {
    hatchIntakeExtensionMotor.configClosedloopRamp(.1, 0);
    hatchIntakeExtensionMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    hatchIntakeExtensionMotor.setSelectedSensorPosition(0, 0, 0);
  }
  @Override
  public void initDefaultCommand() {
  }

  public double getExtenderEncoderPosition() {
		return hatchIntakeExtensionMotor.getSelectedSensorPosition(0);
		
  }
  
  public void stopExtension(){
    Robot.intakeExtender.hatchIntakeExtensionMotor.set(0);
  }
}
