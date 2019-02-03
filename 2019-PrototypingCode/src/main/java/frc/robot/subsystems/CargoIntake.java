/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class CargoIntake extends Subsystem {
  
  public WPI_TalonSRX cargoIntakeMotor = new WPI_TalonSRX(RobotMap.cargoIntakeMotor);
  public WPI_TalonSRX cargoExtensionMotor = new WPI_TalonSRX(RobotMap.cargoExtensionMotor);

  public CargoIntake(){
    configureTalons();
  }

  @Override
  public void initDefaultCommand() {
  }

  public void cargoIntakeStop() {
    cargoExtensionMotor.set(0);
    cargoIntakeMotor.set(0);
  }


  private void configureTalons() {
		cargoIntakeMotor.configClosedloopRamp(.1, 0);
		cargoExtensionMotor.configClosedloopRamp(.1, 0);
  }
}
