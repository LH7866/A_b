package com.lenovo.manufacture.b.fragment;

public class OrderBean {
    private String carId;//车型
    private String time;
    private String num;
    private String enagine;//发动机排量
    private String speed;//变速箱
    private String wheel;//轮胎
    private String control;//中控
    private String brake;//刹车
    private String hang;//悬挂

    @Override
    public String toString() {
        return "OrderBean{" +
                "carId='" + carId + '\'' +
                ", time='" + time + '\'' +
                ", num='" + num + '\'' +
                ", enagine='" + enagine + '\'' +
                ", speed='" + speed + '\'' +
                ", wheel='" + wheel + '\'' +
                ", control='" + control + '\'' +
                ", brake='" + brake + '\'' +
                ", hang='" + hang + '\'' +
                '}';
    }

    public String getCarId() {
//        if(carId.equals("1")){
//            carId="轿车";
//        }if(carId.equals("2")){
//            carId="MPV";
//        }if(carId.equals("3")){
//            carId="SUV";
//        }
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getTime() {
//        StringBuffer s = new StringBuffer(time);
//        s.insert(4,"/");
//        s.insert(6,"/");
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getEnagine() {
        return enagine;
    }

    public void setEnagine(String enagine) {
        this.enagine = enagine;
    }

    public String getSpeed() {
//        if (speed.equals("0")){
//            speed = "自动";
//        }if (speed.equals("1")){
//            speed = "手动";
//        }
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getWheel() {
//        if (wheel.equals("0")){
//            wheel = "烤漆";
//        }if (wheel.equals("1")){
//            wheel = "电镀";
//        }
        return wheel;
    }

    public void setWheel(String wheel) {
        this.wheel = wheel;
    }

    public String getControl() {
//        if (control.equals("0")){
//            control = "低配";
//        }if (control.equals("1")){
//            control = "高配";
//        }
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getBrake() {
//        if(brake.equals("0")){
//            brake ="鼓式制动器";
//        }if(brake.equals("1")){
//            brake ="盘式制动器";
//        }
        return brake;
    }

    public void setBrake(String brake) {
        this.brake = brake;
    }

    public String getHang() {
//        if(hang.equals("1")){
//            hang ="独立悬挂系统";
//        }if(hang.equals("2")){
//            hang ="主动悬挂系统";
//        }if(hang.equals("3")){
//            hang ="横臂式悬挂系统";
//        }if(hang.equals("4")){
//            hang ="纵臂式悬挂系统";
//        }if(hang.equals("5")){
//            hang ="烛式悬挂系统";
//        }if(hang.equals("6")){
//            hang ="多连杆式悬挂系统";
//        }if(hang.equals("7")){
//            hang ="麦弗逊式悬挂系统";
//        }
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }
}
