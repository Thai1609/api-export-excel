package com.deanshoes.domain;

public class Sheet2 {
	private String MRP;
	private String PO;

	public Sheet2(String mRP, String pO) {
		super();
		MRP = mRP;
		PO = pO;
	}

	public String getMRP() {
		return MRP;
	}

	public void setMRP(String mRP) {
		MRP = mRP;
	}

	public String getPO() {
		return PO;
	}

	public void setPO(String pO) {
		PO = pO;
	}

	// Override equals and hashCode based on meaningful fields
//	@Override
//	public boolean equals(Object o) {
//		if (this == o)
//			return true;
//		if (o == null || getClass() != o.getClass())
//			return false;
//		Sheet2 sheet2 = (Sheet2) o;
//		return Objects.equals(MRP, sheet2.MRP) && Objects.equals(PO, sheet2.PO);
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(MRP, PO);
//	}

	@Override
	public String toString() {
		return "Sheet2 [MRP=" + MRP + ", PO=" + PO + "]";
	}

}