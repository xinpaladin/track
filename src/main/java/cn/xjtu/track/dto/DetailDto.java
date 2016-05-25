package cn.xjtu.track.dto;

public class DetailDto {
	private Long id;

	private Double longitude;

	private Double latitude;

	private Long dataItemId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Long getDataItemId() {
		return dataItemId;
	}

	public void setDataItemId(Long dataItemId) {
		this.dataItemId = dataItemId;
	}

}
