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

            //go through line by line and add commands accordingly
            lines.forEach(line -> {
                //split line into parts (separated in text file by spaces)
                String[] parts = line.split(" ");
                //parts[0] is the type of command
                //parts[1] is the direction (if command has a direction)
                //parts[2] is the value (degrees, seconds, or meters, if command has a value)
                //parts[3] is either "s" or "m"; only for drive commands

                //degrees, seconds, or meters, depending on drive mode
                float value = 0;
                //check what kind of command this line says to add
                switch (parts[0]) {
                    case "turn":
                        //set degrees positive or negative according to direction
                        if (parts[1].equals("right")) value = Float.parseFloat(parts[2]);
                        else if (parts[1].equals("left")) value = -Float.parseFloat(parts[2]);
                        //add turn command
                        addCommands(new DriveTankCommand(tank, 0, value));
                        break;
                    case "drive":
                        //set seconds/meters positive or negative according to direction
                        if (parts[1].equals("forward")) value = Float.parseFloat(parts[2]);
                        else if (parts[1].equals("backward")) value = -Float.parseFloat(parts[2]);
                        //add drive command (either time mode or distance mode)
                        if (parts[3].equals("s")) addCommands(new DriveTankCommand(tank, 1, value));
                        else if (parts[3].equals("m")) addCommands(new DriveTankCommand(tank, 2, value));
                        break;
                    case "intake":
                        //add guzzler intake command
                        addCommands(new GuzzlerIntakeCommand(guzzler));
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
