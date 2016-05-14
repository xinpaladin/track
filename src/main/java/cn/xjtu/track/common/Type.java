package cn.xjtu.track.common;

public class Type {


	public enum LocusType{
		/** 直行*/
		Straight,
		/** 左转弯*/
		TurnLeft,
		/** 右转弯*/
		TurnRight,
		/** 左换道*/
		LeftLaneChange,
		/** 右换道*/
		RightLaneChange,
		/** 左掉头*/
		TurnAround,
	}
}
