package com.techsun.famouswine.domain;

import java.util.List;

public class AdvertisementSort {

    private Integer type;

   List<Advertisement> advertisementList;

public Integer getType() {
	return type;
}

public void setType(Integer type) {
	this.type = type;
}

public List<Advertisement> getAdvertisementList() {
	return advertisementList;
}

public void setAdvertisementList(List<Advertisement> advertisementList) {
	this.advertisementList = advertisementList;
}

   
}