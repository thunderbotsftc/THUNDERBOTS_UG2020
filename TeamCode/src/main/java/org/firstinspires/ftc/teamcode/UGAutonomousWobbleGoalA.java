package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.bak.ThunderbotsSquareAutonomous;

@Autonomous(name="WobbleGoalA", group="Thunderbots")
public class UGAutonomousWobbleGoalA extends UGTowerGoalBaseAuto {
    UGHardwarePushbot robot = new UGHardwarePushbot();
    double powerMultiplier = 0.5;
    double shooterPowerMultiplier = 0.5;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);
        //initSkystoneCamera();
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Init done");    //
        telemetry.update();

        robot.leftDrive1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDrive1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.shooterMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDrive1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftDrive2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Send telemetry message to indicate successful Encoder reset
        //telemetry.addData("Path0", "Starting at %7d :%7d",
               // robot.leftDrive1.getCurrentPosition(),
               // robot.rightDrive1.getCurrentPosition());
      //  telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        dropWobbleGoalAGetIntoShootingPosition();
        shootAndPark();
       /* shootRingsIntoTowerGoal();
        moveforwardandpark(); */

    }

    public void dropWobbleGoalAGetIntoShootingPosition() {


        robot.leftDrive1.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.rightDrive1.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.leftDrive2.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.rightDrive2.setDirection(DcMotorSimple.Direction.REVERSE);

        encoderDrive(DRIVE_SPEED, 24, 24, 1.9);

        robot.wobbleArmMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        encoderDrive(DRIVE_SPEED, 5, 5, 0.3);

        double wobbleClawPosition = this.robot.wobbleClawServo.MAX_POSITION + 1.0;
        robot.wobbleClawServo.setPosition(wobbleClawPosition);

        robot.wobbleArmMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        encoderDrive(DRIVE_SPEED, 5, 5, 0.3);


        robot.leftDrive1.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.rightDrive1.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.leftDrive2.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.rightDrive2.setDirection(DcMotorSimple.Direction.FORWARD);

        encoderDrive(DRIVE_SPEED, 24, 24, 0.9);

        robot.leftDrive1.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.rightDrive1.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.leftDrive2.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.rightDrive2.setDirection(DcMotorSimple.Direction.FORWARD);

        encoderDrive(DRIVE_SPEED, 24, 24, 0.8);

    }

    public void shootAndPark() {
        robot.shooterMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.shooterMotor.setPower(shooterPowerMultiplier);

        //shoot 3 pre-loaded rings
        for (int a = 1; a <= 3; a++) {
            telemetry.addData("Status", "Trigger");
            double triggerposition = this.robot.triggerServo.MIN_POSITION+1.5;
            robot.triggerServo.setPosition(triggerposition);
            telemetry.addData("Status", "FoundationArmUp");
            triggerposition = this.robot.triggerServo.MAX_POSITION-1.5;
            robot.triggerServo.setPosition(triggerposition);

        }


        robot.shooterMotor.setPower(0);

        //get into parking location
        robot.leftDrive1.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.rightDrive1.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.leftDrive2.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.rightDrive2.setDirection(DcMotorSimple.Direction.REVERSE);

        encoderDrive(DRIVE_SPEED, 24, 24, 0.8);
    }
}


