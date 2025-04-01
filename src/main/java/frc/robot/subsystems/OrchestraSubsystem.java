// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OrchestraSubsystem extends SubsystemBase {


  private final Orchestra orchestra;
  
  private final int[] krakenIDs = {
    //Swerve
    5,
    6,
    7,
    8,
    9,
    10,
    11,
    12,
    13,
    14,
    15,
    16,
    // Elevator
    24,
    25
  };

  /** Creates a new DriveSubsystem. */
  public OrchestraSubsystem() {

    orchestra = new Orchestra();
    for (int i = 0; i < krakenIDs.length; i++) {
      TalonFX motor = new TalonFX(krakenIDs[i]);
      orchestra.addInstrument(motor);
    }

    orchestra.loadMusic("file.chrp");

  }

  @Override
  public void periodic() {
  }

  public void play() {
    orchestra.play();
  }
}
