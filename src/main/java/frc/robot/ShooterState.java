// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//Enum for the shooter states
public enum ShooterState {
  FIRE(1.0),
  REVERSE(-0.3),
  STOP(0.0);

  private final double speed;

  ShooterState(double speed) {
    this.speed = speed;
  }

  public double getSpeed() {
    return speed;
  }
}

