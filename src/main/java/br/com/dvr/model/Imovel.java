package br.com.dvr.model;

import java.math.BigInteger;

import javax.persistence.Entity;

@Entity
public class Imovel extends DomainEntity {

	private static final long serialVersionUID = -4762422136477300673L;
	
	private Integer x;
	private Integer y;
	private String title;
	private BigInteger price;
	private String description;
	private Integer beds;
	private Integer baths;
	private Integer squareMeters;
	
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigInteger getPrice() {
		return price;
	}
	public void setPrice(BigInteger price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getBeds() {
		return beds;
	}
	public void setBeds(Integer beds) {
		this.beds = beds;
	}
	public Integer getBaths() {
		return baths;
	}
	public void setBaths(Integer baths) {
		this.baths = baths;
	}
	public Integer getSquareMeters() {
		return squareMeters;
	}
	public void setSquareMeters(Integer squareMeters) {
		this.squareMeters = squareMeters;
	}
}
