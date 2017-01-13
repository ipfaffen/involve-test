package com.ipfaffen.involve.model.entity;

import com.ipfaffen.involve.model.annotation.Column;
import com.ipfaffen.involve.model.annotation.Table;

/**
 * @author Isaias Pfaffenseller
 */
@Table("cidades")
public class City extends ModelEntity<City> {

	@Column("ibge_id")
	private Long id;
	
	@Column
	private String uf;

	@Column
	private String name;
	
	@Column
	private String capital;
	
	@Column("lon")
	private String longitude;
	
	@Column("lat")
	private String latitude;
	
	@Column("no_accents")
	private String noAccentName;
	
	@Column("alternative_names")
	private String alternativeName;
	
	@Column
	private String microregion;
	
	@Column
	private String mesoregion;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * @param uf
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return capital
	 */
	public String getCapital() {
		return capital;
	}

	/**
	 * @param capital
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}

	/**
	 * @return longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return noAccentName
	 */
	public String getNoAccentName() {
		return noAccentName;
	}

	/**
	 * @param noAccentName
	 */
	public void setNoAccentName(String noAccentName) {
		this.noAccentName = noAccentName;
	}

	/**
	 * @return alternativeName
	 */
	public String getAlternativeName() {
		return alternativeName;
	}

	/**
	 * @param alternativeName
	 */
	public void setAlternativeName(String alternativeName) {
		this.alternativeName = alternativeName;
	}

	/**
	 * @return microregion
	 */
	public String getMicroregion() {
		return microregion;
	}

	/**
	 * @param microregion
	 */
	public void setMicroregion(String microregion) {
		this.microregion = microregion;
	}

	/**
	 * @return mesoregion
	 */
	public String getMesoregion() {
		return mesoregion;
	}

	/**
	 * @param mesoregion
	 */
	public void setMesoregion(String mesoregion) {
		this.mesoregion = mesoregion;
	}
}