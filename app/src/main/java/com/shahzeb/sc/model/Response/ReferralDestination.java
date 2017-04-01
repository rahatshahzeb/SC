
package com.shahzeb.sc.model.Response;


import android.os.Parcel;
import android.os.Parcelable;

public class ReferralDestination implements Parcelable {

    public String site_name;
    public String uri;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.site_name);
        dest.writeString(this.uri);
    }

    public ReferralDestination() {
    }

    protected ReferralDestination(Parcel in) {
        this.site_name = in.readString();
        this.uri = in.readString();
    }

    public static final Parcelable.Creator<ReferralDestination> CREATOR = new Parcelable.Creator<ReferralDestination>() {
        @Override
        public ReferralDestination createFromParcel(Parcel source) {
            return new ReferralDestination(source);
        }

        @Override
        public ReferralDestination[] newArray(int size) {
            return new ReferralDestination[size];
        }
    };
}
