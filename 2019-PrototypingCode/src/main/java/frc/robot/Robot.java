/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.ExtendIntake;
import frc.robot.commands.HatchIntakeUp;
import frc.robot.commands.SwapDriveDirection;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HatchIntake;
import frc.robot.subsystems.IntakeExtender;




/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static HatchIntake hatchIntake;
  public static Drivetrain drivetrain;
  public static IntakeExtender intakeExtender;
  public static CargoIntake cargoIntake;

  public static DriveWithJoysticks drive;
  public static SwapDriveDirection swap;
  public static HatchIntakeUp upCommand;
  
  public static ExtendIntake extend;

  public static Trigger.ButtonScheduler upButton;

  public static OI oi;

  public static UsbCamera frontCamera;
	public static UsbCamera backCamera;
	public static VideoSink cameraServer;

  
  public static AnalogInput actuatorPosition;
  public static AnalogInput distanceSensor;
  public static DigitalInput lowerHatchLimitSwitch;
  public static DigitalInput upperHatchLimitSwitch;
  public static DigitalInput lowerCargoLimitSwitch;
  public static DigitalInput upperCargoLimitSwitch;
  public static final int IMG_WIDTH = 320;
  public static final int IMG_HEIGHT = 240;
  public double centerX = 0; 
  public boolean prevTrigger = false;
  public static final int   MIN_DISTANCE = 30;
  public static final int MAX_CURRENT_NEO = 40;

  public final Object imgLock = new Object();


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */

  @Override
  public void robotInit(){


    hatchIntake = new HatchIntake();
    drivetrain = new Drivetrain();
    intakeExtender = new IntakeExtender();
    cargoIntake = new CargoIntake();

    drive = new DriveWithJoysticks();
    swap = new SwapDriveDirection();
    extend = new ExtendIntake(1.6);

    upButton = new Trigger.ButtonScheduler(){
    
      @Override
      public void execute() {
        upCommand.start();
      }
    };

    oi = new OI();

    actuatorPosition = new AnalogInput(0);
    distanceSensor = new AnalogInput(1);
    upperHatchLimitSwitch = new DigitalInput(0);
    lowerHatchLimitSwitch = new DigitalInput(4);
    lowerCargoLimitSwitch = new DigitalInput(1);
    upperCargoLimitSwitch = new DigitalInput(2);
  
    

    NetworkTableInstance ntinst = NetworkTableInstance.getDefault();

    SmartDashboard.putData(actuatorPosition);
    
    SmartDashboard.putData(distanceSensor);

    SmartDashboard.putData(upperHatchLimitSwitch);
    SmartDashboard.putData(upperCargoLimitSwitch);
    SmartDashboard.putData(lowerCargoLimitSwitch);
    

    frontCamera = CameraServer.getInstance().startAutomaticCapture(RobotMap.frontCamera);
		frontCamera.setResolution(IMG_WIDTH, IMG_HEIGHT);
    frontCamera.setExposureAuto();
    

    MjpegServer mj = new MjpegServer("Camera1", 7072);
    mj.setSource(frontCamera);
    
    backCamera = CameraServer.getInstance().startAutomaticCapture(RobotMap.backCamera);
		backCamera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		backCamera.setExposureAuto();
    
    MjpegServer c2 = new MjpegServer("Camera2", 7072);
    mj.setSource(backCamera);

  
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  @Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
  
    drivetrain.resetEncoders();
  
    drive.start();
    swap.start();
    extend.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }
  

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
