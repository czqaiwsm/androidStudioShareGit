package com.share.learn.service;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.share.learn.utils.BaseApplication;

import java.util.ArrayList;

/**
 * @desc Baiedu定位
 * @creator caozhiqing
 * @data 2016/3/21
 */
public class LocationUitl {


    private LocationService locationService;

    private static ArrayList<LocationListener> listeners = new ArrayList<LocationListener>();

    public static void registListener(LocationListener listener){
        listeners.add(listener);

    }

    public  static void removeListener(LocationListener listener){
        listeners.remove(listener);
    }

    public void startLocation(){
        locationService = (BaseApplication.getInstance()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();// 定位SDK
    }


    public void stopLocation(){
        if (locationService != null){
            locationService.unregisterListener(mListener); //注销掉监听
            locationService.stop(); //停止定位服务
        }

    }

    /*****
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
//                StringBuffer sb = new StringBuffer(256);
//                sb.append("time : ");
//                /**
//                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
//                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
//                 */
//                sb.append(location.getTime());
//                sb.append("\nerror code : ");
//                sb.append(location.getLocType());
//                sb.append("\nlatitude : ");
//                sb.append(location.getLatitude());
//                sb.append("\nlontitude : ");
//                sb.append(location.getLongitude());
//                sb.append("\nradius : ");
//                sb.append(location.getRadius());
//                sb.append("\nCountryCode : ");
//                sb.append(location.getCountryCode());
//                sb.append("\nCountry : ");
//                sb.append(location.getCountry());
//                sb.append("\ncitycode : ");
//                sb.append(location.getCityCode());
//                sb.append("\ncity : ");
//                sb.append(location.getCity());
//                sb.append("\nDistrict : ");
//                sb.append(location.getDistrict());
//                sb.append("\nStreet : ");
//                sb.append(location.getStreet());
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());
//                sb.append("\nDescribe: ");
//                sb.append(location.getLocationDescribe());
//                sb.append("\nDirection(not all devices have value): ");
//                sb.append(location.getDirection());
//                sb.append("\nPoi: ");
//                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
//                    for (int i = 0; i < location.getPoiList().size(); i++) {
//                        Poi poi = (Poi) location.getPoiList().get(i);
//                        sb.append(poi.getName() + ";");
//                    }
//                }
//                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
//                    sb.append("\nspeed : ");
//                    sb.append(location.getSpeed());// 单位：km/h
//                    sb.append("\nsatellite : ");
//                    sb.append(location.getSatelliteNumber());
//                    sb.append("\nheight : ");
//                    sb.append(location.getAltitude());// 单位：米
//                    sb.append("\ndescribe : ");
//                    sb.append("gps定位成功");
//                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
//                    // 运营商信息
//                    sb.append("\noperationers : ");
//                    sb.append(location.getOperators());
//                    sb.append("\ndescribe : ");
//                    sb.append("网络定位成功");
//                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
//                    sb.append("\ndescribe : ");
//                    sb.append("离线定位成功，离线定位结果也是有效的");
//                } else if (location.getLocType() == BDLocation.TypeServerError) {
//                    sb.append("\ndescribe : ");
//                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
//                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//                    sb.append("\ndescribe : ");
//                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
//                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//                    sb.append("\ndescribe : ");
//                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
//                }
//                AppLog.Logi(sb.toString());

                if(listeners.size()>0){
                    for (int i=0 ;i<listeners.size();i++){
                        LocationListener locationListener = listeners.get(i);
                        if (locationListener != null){
                            locationListener.locatinNotify(location);
                        }
                    }
                }

                BaseApplication.getInstance().location[0] = location.getCity();
                BaseApplication.getInstance().location[1] = location.getCityCode();
                BaseApplication.getInstance().mapLocation = location;

            }
        }

    };

    public interface LocationListener{

        void locatinNotify(BDLocation location);

    }

}
