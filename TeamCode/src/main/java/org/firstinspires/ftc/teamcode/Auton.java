package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Auton")
public class Auton extends LinearOpMode {
    // Tune these
    final double DRIVE_POWER = 0.5;
    //final double SHOOTER_POWER=1;
    final double FORWARD_TIME = 1.0;  // seconds
    final double PAUSE_TIME = 1.0;    // seconds stopped
    final double BACK_TIME = 1.0;     // seconds

    // Same signs you use in Movement (order: FL, BL, FR, BR)
    final int[] dir = {-1, 1, 1, 1};

    DcMotor frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive;
    //DcMotor middleMotor1, middleMotor2, shooter;

    @Override
    public void runOpMode() {
        frontLeftDrive = hardwareMap.get(DcMotor.class, "leftFront");
        backLeftDrive = hardwareMap.get(DcMotor.class, "leftBack");
        frontRightDrive = hardwareMap.get(DcMotor.class, "rightFront");
        backRightDrive = hardwareMap.get(DcMotor.class, "rightBack");
        //middleMotor1=hardwareMap.get(DcMotor.class, "middle1");
        //middleMotor2=hardwareMap.get(DcMotor.class, "middle2");
        //shooter=hardwareMap.get(DcMotor.class, "shooter");
/*
        for (DcMotor m : new DcMotor[]{frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive}) {
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
*/
        telemetry.addLine("Ready");
        telemetry.update();
        waitForStart();

        drive(DRIVE_POWER, FORWARD_TIME, "Forward");
        turnRight(1, 0.5);
        //shoot(1, 15);
        drive(0, PAUSE_TIME, "Stopped");
        turnRight(1, 0.5);
        drive(DRIVE_POWER, FORWARD_TIME, "Backward");
        drive(0, 0, "Done");
    }

    // y > 0 = forward, y < 0 = backward
    private void drive(double y, double seconds, String label) {
        frontLeftDrive.setPower(dir[0] * y);
        backLeftDrive.setPower(dir[1] * y);
        frontRightDrive.setPower(dir[2] * y);
        backRightDrive.setPower(dir[3] * y);

        telemetry.addData("Step", label);
        telemetry.update();

        long end = System.currentTimeMillis() + (long) (seconds * 1000);
        while (opModeIsActive() && System.currentTimeMillis() < end) {
            idle();
        }
    }
    /*private void shoot(double shooterPower, double seconds){
        shooter.setPower(shooterPower);

        long end = System.currentTimeMillis() + (long) (seconds * 1000);
        while (opModeIsActive() && System.currentTimeMillis() < end) {
            idle();
        }
        shooter.setPower(0);
    }*/

    private void turnRight(double power, double seconds){
        double yaw=power;
        double frontLeftPower=yaw;
        double frontRightPower=-yaw;
        double backLeftPower=yaw;
        double backRightPower=-yaw;
        frontLeftDrive.setPower(frontLeftPower);
        frontRightDrive.setPower(frontRightPower);
        backLeftDrive.setPower(backLeftPower);
        backRightDrive.setPower(backRightPower);
        long end = System.currentTimeMillis() + (long) (seconds * 1000);
        while (opModeIsActive() && System.currentTimeMillis() < end) {
            idle();
        }
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }
}