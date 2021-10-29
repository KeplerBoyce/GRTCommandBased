// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.guzzler.GuzzlerIntakeCommand;
import frc.robot.commands.tank.DriveTankCommand;
import frc.robot.subsystems.guzzler.GuzzlerSubsystem;
import frc.robot.subsystems.tank.TankSubsystem;

public class TextCommandGroup extends SequentialCommandGroup {

    public TextCommandGroup(TankSubsystem tank, GuzzlerSubsystem guzzler) {
        //handle possible file not found and io exceptions
        try {
            //reading lines from text file
            //path is the relative path to text file from GRTCommandBased folder
            BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\frc\\robot\\commands\\testcommands.txt"));
            Stream<String> lines = br.lines();

            //go through lines one by one and add commands accordingly
            lines.forEach(line -> {
                //split lines into parts (separated in text file by spaces)
                String[] parts = line.split(" ");
                //for turn commands: parts = ["turn", direction(left/right), degrees]
                //for drive commands: parts = ["drive", direction(forward/backward), meters/seconds]
                //for intake commands: parts = ["intake"]

                //check if this line is a turn or a drive command
                switch (parts[0]) {
                    case "turn":
                        //add turn command
                        if (parts[1].equals("left")) addCommands(new DriveTankCommand(tank, 0, -Float.parseFloat(parts[2])));
                        else if (parts[1].equals("right")) addCommands(new DriveTankCommand(tank, 0, Float.parseFloat(parts[2])));
                        break;
                    case "drive":
                        //check if driving by distance or by time
                        if (parts[1].equals("forward")) addCommands(new DriveTankCommand(tank, Float.parseFloat(parts[2]), 0));
                        else if (parts[1].equals("backward")) addCommands(new DriveTankCommand(tank, -Float.parseFloat(parts[2]), 0));
                        break;
                    case "intake":
                        addCommands(new GuzzlerIntakeCommand(guzzler))
                        break;
                }
            });
            //close buffered reader at the end
            br.close();

        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        } catch(IOException e) {
            System.out.println("Buffered reader is closed");
        }
    }
}
