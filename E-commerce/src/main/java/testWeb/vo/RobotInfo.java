package testWeb.vo;

public class RobotInfo {
	private Long robotinfoid;
	private String name;
    private String type;
    private Integer age;
    private Long userid;


	public Long getRobotinfoid() {
		return robotinfoid;
	}

	public void setRobotinfoid(Long robotinfoid) {
		this.robotinfoid = robotinfoid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "RobotInfo{" +
				"robotinfoid=" + robotinfoid +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				", age=" + age +
				", userid=" + userid +
				'}';
	}
}
