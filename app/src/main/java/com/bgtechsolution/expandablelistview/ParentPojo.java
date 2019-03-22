package com.bgtechsolution.expandablelistview;

import java.io.Serializable;

public class ParentPojo implements Serializable, Comparable {

    private String mImageUrl;
    private String mCreator;
    private String mid;

    public ParentPojo(String creator, String mImageUrl,String mid) {
        this.mCreator = creator;
        this.mImageUrl = mImageUrl;
        this.mid = mid;

    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getCreator() {
        return mCreator;
    }

    public String getId() {
        return mid;
    }

    @Override
    public int compareTo(Object another) {
//        if(((DepartInfo)another).getCreator() > mCreator){ // ya > sign int ko case ma matra use hunx n ahile string gareko xa
//            return 1;
//        }
        if(((ParentPojo)another).getCreator() == mCreator){
            return 0;
        }else{
            return -1;
        }
//        return 0;
    }
}
