/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class RetractIntake extends PIDCommand {
  
  private double currentIntakeRotations;
  
  public RetractIntake(double intakeRotations) {
    super(1,0,0);
    requires(Robot.intakeExtender);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    getPIDController().setAbsoluteTolerance(0.1);
    getPIDController().setSetpoint(intakeRotations);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    super.initialize();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    getPIDController().enable();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return getPIDController().onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.intakeExtender.stopExtension();
  }
  
  @Override
  protected double returnPIDInput() {
    currentIntakeRotations = Robot.intakeExtender.getExtenderEncoderPosition() / 4096;
    return currentIntakeRotations;
  }

  @Override
  protected void usePIDOutput(double output) {
    Robot.intakeExtender.hatchIntakeExtensionMotor.set(output);
  }



  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
