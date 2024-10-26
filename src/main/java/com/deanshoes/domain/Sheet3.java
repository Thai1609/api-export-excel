package com.deanshoes.domain;

public class Sheet3 {
	private String date;
	private String internal_code;
	private String orde_type;
	private String style;
	private String model_name;
	private String destination;
	private String prs;
	private String eta;
	private String usage;
	private String order_qty;
	private String po;
	private String vendor_name;
	private String mtl_code;
	private String material_name;
	private String unit;
	private String price;
	private String etd;

	public Sheet3(String date, String internal_code, String orde_type, String style, String model_name,
			String destination, String prs, String eta, String usage, String order_qty, String po, String vendor_name,
			String mtl_code, String material_name, String unit, String price, String etd) {
		super();
		this.date = date;
		this.internal_code = internal_code;
		this.orde_type = orde_type;
		this.style = style;
		this.model_name = model_name;
		this.destination = destination;
		this.prs = prs;
		this.eta = eta;
		this.usage = usage;
		this.order_qty = order_qty;
		this.po = po;
		this.vendor_name = vendor_name;
		this.mtl_code = mtl_code;
		this.material_name = material_name;
		this.unit = unit;
		this.price = price;
		this.etd = etd;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getInternal_code() {
		return internal_code;
	}

	public void setInternal_code(String internal_code) {
		this.internal_code = internal_code;
	}

	public String getOrde_type() {
		return orde_type;
	}

	public void setOrde_type(String orde_type) {
		this.orde_type = orde_type;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getPrs() {
		return prs;
	}

	public void setPrs(String prs) {
		this.prs = prs;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getOrder_qty() {
		return order_qty;
	}

	public void setOrder_qty(String order_qty) {
		this.order_qty = order_qty;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public String getVendor_name() {
		return vendor_name;
	}

	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}

	public String getMtl_code() {
		return mtl_code;
	}

	public void setMtl_code(String mtl_code) {
		this.mtl_code = mtl_code;
	}

	public String getMaterial_name() {
		return material_name;
	}

	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getEtd() {
		return etd;
	}

	public void setEtd(String etd) {
		this.etd = etd;
	}

	@Override
	public String toString() {
		return "Sheet3 [date=" + date + ", internal_code=" + internal_code + ", orde_type=" + orde_type + ", style="
				+ style + ", model_name=" + model_name + ", destination=" + destination + ", prs=" + prs + ", eta="
				+ eta + ", usage=" + usage + ", order_qty=" + order_qty + ", po=" + po + ", vendor_name=" + vendor_name
				+ ", mtl_code=" + mtl_code + ", material_name=" + material_name + ", unit=" + unit + ", price=" + price
				+ ", etd=" + etd + "]";
	}

}
