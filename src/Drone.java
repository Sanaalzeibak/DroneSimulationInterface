import org.json.JSONObject;

class Drone {
    private int id;
    private String dronetype;
    private String created;
    private String serialnumber;
    private int carriage_weight;
    private String carriage_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDronetype() {
        return dronetype;
    }

    public void setDronetype(String dronetype) {
        this.dronetype = dronetype;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public int getCarriageWeight() {
        return carriage_weight;
    }

    public void setCarriageWeight(int carriage_weight) {
        this.carriage_weight = carriage_weight;
    }

    public String getCarriageType() {
        return carriage_type;
    }

    public void setCarriageType(String carriage_type) {
        this.carriage_type = carriage_type;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", dronetype='" + dronetype + '\'' +
                ", created='" + created + '\'' +
                ", serialnumber='" + serialnumber + '\'' +
                ", carriage_weight=" + carriage_weight +
                ", carriage_type='" + carriage_type + '\'' +
                '}';
    }

    public static Drone fromJson(JSONObject droneJson) {
        Drone drone = new Drone();
        drone.setId(droneJson.getInt("id"));
        drone.setDronetype(droneJson.getString("dronetype"));
        drone.setCreated(droneJson.getString("created"));
        drone.setSerialnumber(droneJson.getString("serialnumber"));
        drone.setCarriageWeight(droneJson.getInt("carriage_weight"));
        drone.setCarriageType(droneJson.getString("carriage_type"));
        return drone;
    }
}