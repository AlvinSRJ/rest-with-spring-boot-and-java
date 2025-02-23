package br.com.alvinsrj.integrationtests.vo.wrappers;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "WrapperPersonVO")
public class WrapperPersonVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("_embedded")
	private PersonEmbeddedVO enbedded;

	public WrapperPersonVO() {}

	public PersonEmbeddedVO getEnbedded() {
		return enbedded;
	}

	public void setEnbedded(PersonEmbeddedVO enbedded) {
		this.enbedded = enbedded;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enbedded == null) ? 0 : enbedded.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WrapperPersonVO other = (WrapperPersonVO) obj;
		if (enbedded == null) {
			if (other.enbedded != null)
				return false;
		} else if (!enbedded.equals(other.enbedded))
			return false;
		return true;
	}
	
	
	
}
