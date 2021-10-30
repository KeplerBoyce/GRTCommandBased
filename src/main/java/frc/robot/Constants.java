// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //copied from config file
    public static final double ENCODER_TICKS_TO_METERS = 0.0005588;

    //calculated based on ENCODER_TICKS_TO_METERS and dt width from config file
    //i.e. (wheel turning circumference / 360deg)*(ticks per meter) = ticks per degree
    //---> (360 * 0.0005588) / (0.73025 * pi) = 0.08769 deg/tick
    public static final double ENCODER_TICKS_TO_DEGREES = 0.08769;
}
