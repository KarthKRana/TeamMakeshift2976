// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public enum ShooterState {
  //Ben 10 speeds :D
  SPEED_01(0.1),
  SPEED_02(0.2),
  SPEED_03(0.3),
  SPEED_04(0.4),
  SPEED_05(0.5),
  SPEED_06(0.6),
  SPEED_07(0.7),
  SPEED_08(0.8),
  SPEED_09(0.9),
  SPEED_10(1.0);

  private final double speed;

  ShooterState(double speed) {
    this.speed = speed;
  }

  public double getSpeed() {
    return speed;
  }

  public ShooterState increase() {
    ShooterState[] values = values();
    int currentIndex = ordinal();
    if (currentIndex < values.length - 1) {
      return values[currentIndex + 1]; // + .1 speed
    }
    return this; //If already at min/max will return the min/max speed
  }

  public ShooterState decrease() {
    ShooterState[] values = values();
    int currentIndex = ordinal();
    if (currentIndex > 0) {
      return values[currentIndex - 1]; // -.1 speed
    }
    return this;
  }
}

