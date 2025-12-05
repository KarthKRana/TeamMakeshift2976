// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public enum IntakeState {
  INPUT(0.5),
  REVERSE(-0.5),
  STOP(0.0);

  private final double speed;

  IntakeState(double speed) {
    this.speed = speed;
  }

  public double getSpeed() {
    return speed;
  }
}

