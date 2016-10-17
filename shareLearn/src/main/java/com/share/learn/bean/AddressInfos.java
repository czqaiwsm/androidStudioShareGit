package com.share.learn.bean;

import com.volley.req.parser.JsonParserBase;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by czq on 16/10/15.
 */
public class AddressInfos  implements Serializable{

    public ArrayList<AddressInfo> getAddressList() {
        return addressList;
    }

    public void setAddressList(ArrayList<AddressInfo> addressList) {
        this.addressList = addressList;
    }

    ArrayList<AddressInfo> addressList ;



    public class AddressInfo implements Serializable{
        private String addressId;
        private String areaAddress;
        private String detailAddress;

        public String getAddressId() {
            return addressId;
        }

        public void setAddressId(String addressId) {
            this.addressId = addressId;
        }

        public String getAreaAddress() {
            return areaAddress;
        }

        public void setAreaAddress(String areaAddress) {
            this.areaAddress = areaAddress;
        }

        public String getDetailAddress() {
            return detailAddress;
        }

        public void setDetailAddress(String detailAddress) {
            this.detailAddress = detailAddress;
        }


    }
   }
