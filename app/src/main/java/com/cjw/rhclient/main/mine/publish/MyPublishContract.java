package com.cjw.rhclient.main.mine.publish;

import com.cjw.rhclient.been.Rent;

import java.util.List;

public interface MyPublishContract {
	interface MyPublishView {
		void showMyPublishList(List<Rent> result);
	}

	interface MyPublishPresenter {
		void getMyPublish();

		void deleteRent(int rentId);

		void offShelfRent(int rentId);

		void onShelfRent(int rentId);
	}
}
