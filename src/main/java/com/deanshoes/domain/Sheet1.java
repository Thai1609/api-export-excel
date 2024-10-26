package com.deanshoes.domain;

import java.util.Objects;

public class Sheet1 {
	private String order;
	private String material;
	private String consolidated_Master_Order;

	public Sheet1(String order, String material, String consolidated_Master_Order) {
		super();
		this.order = order;
		this.material = material;
		this.consolidated_Master_Order = consolidated_Master_Order;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getConsolidated_Master_Order() {
		return consolidated_Master_Order;
	}

	public void setConsolidated_Master_Order(String consolidated_Master_Order) {
		this.consolidated_Master_Order = consolidated_Master_Order;
	}

	// Override equals and hashCode based on meaningful fields
//	@Override
//	public boolean equals(Object o) {
//		if (this == o)
//			return true;
//		if (o == null || getClass() != o.getClass())
//			return false;
//		Sheet1 sheet1 = (Sheet1) o;
//		return Objects.equals(order, sheet1.order) && Objects.equals(material, sheet1.material)
//				&& Objects.equals(consolidated_Master_Order, sheet1.consolidated_Master_Order);
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(order, material, consolidated_Master_Order);
//	}

	@Override
	public String toString() {
		return "Sheet1 [order=" + order + ", material=" + material + ", consolidated_Master_Order="
				+ consolidated_Master_Order + "]";
	}

}
