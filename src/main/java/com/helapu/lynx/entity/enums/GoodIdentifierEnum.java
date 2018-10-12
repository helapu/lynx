package com.helapu.lynx.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GoodIdentifierEnum implements IEnum<String> {
	
	SMOKEDETECTION_V1("SMOKEDETECTION_V1", "第一代烟雾报警器", "AMOUNT"),
	AUTOMATIC_EXTINGUISHING_V1("AUTOMATIC_EXTINGUISHING_V1", "自动灭火系统", "AREA");

    private String value;
    private String name;
    private String unit;

    GoodIdentifierEnum(final String value, final String name, final String unit) {
        this.value = value;
        this.name = name;
        this.unit = unit;
    }

    @Override
    public String getValue() {
        return this.value;
    }
	public String getName() {
		return name;
	}
	public String getUnit() {
		return unit;
	}
    
}
