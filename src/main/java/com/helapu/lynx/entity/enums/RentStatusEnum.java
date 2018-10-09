package com.helapu.lynx.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RentStatusEnum implements IEnum<String> {
	// 申请租赁 -> 接受订单 -> 发货 -> 确认订单 -> 申请退还押金 -> 退还盛誉物品 -> 公司清点物品
	// -> 与买家沟通确认 -> 退还押金(退还方式、金额、在线交易号) -> -> -> -> -> -> -> ->

	APPLY("apply", "申请订单"),
	APPLYBACK("applyback", "申请退还押金"),
	TALKWITH("talkwith", "沟通确认"),
	SENDBACK("sendback", "确认退还物品并退款");

	private String value;
    private String desc;

    RentStatusEnum(final String value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public String getDesc(){
        return this.desc;
    }
}
