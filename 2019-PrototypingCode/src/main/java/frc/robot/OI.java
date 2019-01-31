/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ExtendIntake;
import frc.robot.commands.HatchIntakeDown;
import frc.robot.commands.HatchIntakeUp;

/**
 * Add your docs here.
 */
public class OI {

    private Joystick pilotController;

    public OI() {
        pilotController = new Joystick(0);
    
        Button pilotButtonA = new JoystickButton(pilotController, RobotMap.joystickButtonA);
        pilotButtonA.whenPressed(new HatchIntakeDown());
        
        Button pilotButtonY = new JoystickButton(pilotController, RobotMap.joystickButtonY);
        pilotButtonY.whenPressed(new HatchIntakeUp());

        Button pilotButtonX = new JoystickButton(pilotController, RobotMap.joystickButtonA);
        pilotButtonX.whenPressed(new ExtendIntake(3));
        
        //Button pilotButtonB = new JoystickButton(pilotController, RobotMap.joystickButtonY);
        //pilotButtonB.whenPressed(new RetractHatchIntake(-3));
        
        

        
        
        pilotButtonA.close();
        pilotButtonY.close();
        pilotButtonX.close();
    }

    public Joystick getPilotController() {
        return pilotController;
    }
}
