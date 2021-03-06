package com.cjw.rhclient.main.home.rent;

import com.cjw.rhclient.been.Rent;

import java.util.List;

public interface RentContract {
	interface RentView {
		void showRentList(List<Rent> result);

		void showNoData();
	}

	interface RentPresenter {
		void getRentList(int type, String sortType);
		void getRentList(int type, String sortType,double lng,double lat);
	}
}
