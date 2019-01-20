/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.*;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  
  public WPI_TalonSRX intakeMotor = new WPI_TalonSRX(RobotMap.intakeMotor);


  @Override
  public void initDefaultCommand() {
   
  }

  public void intakeUp() {
    Robot.intake.intakeMotor.set(-.8);
  }

  public void intakeDown() {
    Robot.intake.intakeMotor.set(.8);
  }

  public void intakeStop() {
  Robot.intake.intakeMotor.set(0);
}

}
