package testWeb.vo;

import java.io.InputStream;
import java.time.LocalTime;
import java.util.List;

public class Activity {
	private Long activityid;
	private String ts;
	private Integer cls_id;
	private String cls;
	private Float x1;
	private Float y1;
	private Float x2;
	private Float y2;
	private Float conf;
	private List<String> picList;

	public List<String> getPicList() {
		return picList;
	}

	public void setPicList(List<String> picList) {
		this.picList = picList;
	}

	public Long getActivityid() {
		return activityid;
	}

	public void setActivityid(Long activityid) {
		this.activityid = activityid;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public Integer getCls_id() {
		return cls_id;
	}

	public void setCls_id(Integer cls_id) {
		this.cls_id = cls_id;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public Float getX1() {
		return x1;
	}

	public void setX1(Float x1) {
		this.x1 = x1;
	}

	public Float getY1() {
		return y1;
	}

	public void setY1(Float y1) {
		this.y1 = y1;
	}

	public Float getX2() {
		return x2;
	}

	public void setX2(Float x2) {
		this.x2 = x2;
	}

	public Float getY2() {
		return y2;
	}

	public void setY2(Float y2) {
		this.y2 = y2;
	}

	public Float getConf() {
		return conf;
	}

	public void setConf(Float conf) {
		this.conf = conf;
	}
}
