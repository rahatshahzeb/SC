
package com.shahzeb.sc.model.Response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class AllowedUse implements Parcelable {

    public String how_can_i_use_it;
    public String release_info;
    public List<String> usage_restrictions = null;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.how_can_i_use_it);
        dest.writeString(this.release_info);
        dest.writeStringList(this.usage_restrictions);
    }

    public AllowedUse() {
    }

    protected AllowedUse(Parcel in) {
        this.how_can_i_use_it = in.readString();
        this.release_info = in.readString();
        this.usage_restrictions = in.createStringArrayList();
    }

    public static final Parcelable.Creator<AllowedUse> CREATOR = new Parcelable.Creator<AllowedUse>() {
        @Override
        public AllowedUse createFromParcel(Parcel source) {
            return new AllowedUse(source);
        }

        @Override
        public AllowedUse[] newArray(int size) {
            return new AllowedUse[size];
        }
    };
}
