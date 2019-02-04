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
import frc.robot.RobotMap;
import frc.robot.commands.MoveCargo;

/**
 * Add your docs here.
 */
public class CargoIntake extends Subsystem {
  
  public WPI_TalonSRX cargoIntakeMotor = new WPI_TalonSRX(RobotMap.cargoIntakeMotor);
  public CANSparkMax cargoExtensionMotor = new CANSparkMax(RobotMap.cargoExtensionMotor, MotorType.kBrushless);

  public CargoIntake(){
    configureControllers();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveCargo());
  }

  public void cargoIntakeStop() {
    cargoIntakeMotor.set(0);
    cargoExtensionMotor.set(0);
  }


  private void configureControllers() {
    cargoIntakeMotor.configClosedloopRamp(.1, 0);
    cargoExtensionMotor.setRampRate(.1);
  }
}
