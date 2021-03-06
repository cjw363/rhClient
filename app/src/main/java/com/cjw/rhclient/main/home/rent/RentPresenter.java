package com.cjw.rhclient.main.home.rent;

import android.content.Context;

import com.cjw.rhclient.been.Rent;
import com.cjw.rhclient.been.Session;
import com.cjw.rhclient.http.HttpResult;
import com.cjw.rhclient.http.HttpResultSubscriber;
import com.cjw.rhclient.http.RxDoOnSubscribe;
import com.cjw.rhclient.http.RxSchedulers;
import com.cjw.rhclient.http.RxTrHttpMethod;
import com.cjw.rhclient.service.RentService;
import com.cjw.rhclient.utils.ListUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;

class RentPresenter implements RentContract.RentPresenter {

	private final RentContract.RentView mRentView;
	private Context mContext;

	@Inject
	RentPresenter(Context context, RentContract.RentView mRentView) {
		this.mContext = context;
		this.mRentView = mRentView;
	}

	@Override
	public void getRentList(int type, String sortType) {
		getRentList(type,sortType,Session.location.getLongitude(),Session.location.getLatitude());
	}

	@Override
	public void getRentList(int type, String sortType, double lng, double lat) {
		Map<String, String> map = new HashMap<>();
		map.put("token", Session.user.getToken());
		map.put("type", type + "");
		map.put("sort_type", sortType);
		map.put("longitude", lng+ "");
		map.put("latitude", lat + "");
		RxTrHttpMethod.getInstance().createService(RentService.class).getCampusList(map).compose(RxSchedulers.<HttpResult<List<Rent>>>defaultSchedulers()).doOnSubscribe(new RxDoOnSubscribe(mContext)).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new HttpResultSubscriber<List<Rent>>(mContext) {
			@Override
			public void _onSuccess(List<Rent> result) {
				if (!ListUtil.isEmpty(result)) mRentView.showRentList(result);
				else mRentView.showNoData();
			}
		});
	}
}
