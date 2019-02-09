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
import frc.robot.commands.RetractIntake;


/**
 * Add your docs here.
 */
public class OI {

    private Joystick pilotController;
    private Joystick coPilotController;

    public OI() {
        pilotController = new Joystick(0);
        coPilotController = new Joystick(1);
    
        //  8==========D

        Button pilotButtonA = new JoystickButton(pilotController, RobotMap.joystickButtonA);
        pilotButtonA.whenPressed(new HatchIntakeDown());
        
        Button pilotButtonY = new JoystickButton(pilotController, RobotMap.joystickButtonY);
        pilotButtonY.whenPressed(new HatchIntakeUp());

        Button pilotButtonX = new JoystickButton(pilotController, RobotMap.joystickButtonX);
        pilotButtonX.whenPressed(new ExtendIntake(2.6));
        
        Button pilotButtonB = new JoystickButton(pilotController, RobotMap.joystickButtonB);
        pilotButtonB.whenPressed(new RetractIntake(1));

        
        
        pilotButtonA.close();
        pilotButtonY.close();
        pilotButtonX.close();
        pilotButtonB.close();
    }

    public Joystick getPilotController() {
        return pilotController;
    }
    public Joystick getcoPilotController() {
        return coPilotController;
    }
}
