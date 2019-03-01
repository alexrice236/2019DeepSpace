/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.MoveCargo;

/**
 * Add your docs here.
 */
public class CargoIntake extends Subsystem {
  
  public WPI_TalonSRX cargoIntakeMotor = new WPI_TalonSRX(RobotMap.cargoIntakeMotor);
  public CANSparkMax cargoExtensionMotor = new CANSparkMax(RobotMap.cargoExtensionMotor, MotorType.kBrushless);
  private boolean armDirection;
  private boolean intakeDirection;

  public CargoIntake(){
    configureControllers();
    cargoExtensionMotor.setSmartCurrentLimit(Robot.MAX_CURRENT_NEO);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveCargo());
  }

  public void cargoExtensionStop() {
    cargoExtensionMotor.set(0);
  }

  public void cargoIntakeStop(){
    cargoIntakeMotor.set(0);
  }


  private void configureControllers() {
    cargoIntakeMotor.configClosedloopRamp(.1, 0);
    cargoExtensionMotor.setRampRate(.1);
  }

  public void intakeCargo(){
    intakeDirection = Robot.oi.getcoPilotController().getRawAxis(RobotMap.rightJoystickYAxis) > 0;
    if(intakeDirection){
      cargoIntakeMotor.set(Robot.oi.getcoPilotController().getRawAxis(RobotMap.rightJoystickYAxis) * 0.7);
    }
    else{
      cargoIntakeMotor.set(Robot.oi.getcoPilotController().getRawAxis(RobotMap.rightJoystickYAxis));
    }

  }

  public boolean getCargoLowerLimit(){
    return !Robot.lowerCargoLimitSwitch.get();
  }

  public boolean getCargoUpperLimit(){
    return Robot.upperCargoLimitSwitch.get();
  }

  public void checkCargoLimits(){
    armDirection = Robot.oi.getcoPilotController().getRawAxis(RobotMap.leftJoystickYAxis) < 0;
    if(armDirection && getCargoUpperLimit() || !armDirection && getCargoLowerLimit()){
      cargoExtensionStop();
      return;
    }
    moveCargoArm();
  }
  

  public void moveCargoArm(){
    if(armDirection){
      cargoExtensionMotor.set(Robot.oi.getcoPilotController().getRawAxis(RobotMap.leftJoystickYAxis) * -.4);
    }else{
      cargoExtensionMotor.set(Robot.oi.getcoPilotController().getRawAxis(RobotMap.leftJoystickYAxis) * -.1);
    }
  }
}
