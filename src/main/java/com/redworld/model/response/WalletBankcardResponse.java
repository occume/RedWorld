package com.redworld.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WalletBankcardResponse {
	
    private Integer id;

    private Long authId;

    @JsonProperty("card_type_id")
    private Integer cardTypeId;

    @JsonProperty("card_owner")
    private String cardOwner;

    @JsonProperty("card_number")
    private String cardNumber;
    
    @JsonProperty("card_name")
    private String cardName;
    
    @JsonProperty("bank_name")
    private String bankName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getAuthId() {
		return authId;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
	}

	public Integer getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(Integer cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner == null ? null : cardOwner.trim();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
}