// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;
import java.util.HashMap;
import java.util.function.Consumer;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {

  private final RobotContainer robotContainer;
  private static final String defaultSong = "allstar";
  private final SendableChooser<String> songChooser = new SendableChooser<>();

  private static final HashMap<String, String> fancyNames = new HashMap<>() {{
    put("africa", "Africa");
    put("allstar", "All Star");
    put("bigshot", "BIG SHOT");
    put("bonetrousle", "Bonetrousle");
    put("deathbyglamour", "Death By Glamour");
    put("determination", "Determination");
    put("downunder", "Down Under");
    put("drunkensailor", "Drunken Sailor");
    put("icecream", "Ice Cream Song");
    put("megalovania", "Megalovania");
    put("miichannel", "Mii Channel");
    put("nevergonnagiveyouup", "Never Gonna Give You Up");
    put("piratesofthecaribbean", "Pirates of the Caribbean");
    put("rudebuster", "Rude Buster");
    put("spearofjustice", "Spear of Justice");
  }};
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public Robot() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    robotContainer = new RobotContainer();

    songChooser.setDefaultOption(fancyNames.get(defaultSong), defaultSong);

    File[] chirpFiles = robotContainer.getOrchestraSubsystem().getChirpFiles();
    for (int i = 0; i < chirpFiles.length; i++) {
      String fileName = chirpFiles[i].getName().replace(".chrp", "");
      String fancyName = fancyNames.getOrDefault(fileName, fileName);
      if (fileName != defaultSong) songChooser.addOption(fancyName, fileName);
    }

    SmartDashboard.putData("Songs", songChooser);

    songChooser.onChange(new Consumer<String>() {
      @Override
      public void accept(String file) {
        System.out.println("Loading Chirp file: " + file);
        robotContainer.getOrchestraSubsystem().loadChirpFile(file);
      }
      
    });
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}


  @Override
  public void autonomousInit() {
    robotContainer.getOrchestraSubsystem().play();
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
