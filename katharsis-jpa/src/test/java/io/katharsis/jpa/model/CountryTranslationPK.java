package io.katharsis.jpa.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CountryTranslationPK implements Serializable {

	private static final long serialVersionUID = -2786733635182897172L;

	@ManyToOne
	@JoinColumn(name = "country_cd", insertable = false, updatable = false)
	private CountryEntity country;

	@ManyToOne
	@JoinColumn(name = "lang_cd", insertable = false, updatable = false)
	private LangEntity lang;

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(CountryEntity country) {
		this.country = country;
	}

	public LangEntity getLang() {
		return lang;
	}

	public void setLang(LangEntity lang) {
		this.lang = lang;
	}
}
