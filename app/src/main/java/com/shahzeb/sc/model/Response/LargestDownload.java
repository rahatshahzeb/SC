
package com.shahzeb.sc.model.Response;


import android.os.Parcel;
import android.os.Parcelable;

public class LargestDownload implements Parcelable {

    public String product_id;
    public String product_type;
    public String uri;
    public String agreement_name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.product_id);
        dest.writeString(this.product_type);
        dest.writeString(this.uri);
        dest.writeString(this.agreement_name);
    }

    public LargestDownload() {
    }

    protected LargestDownload(Parcel in) {
        this.product_id = in.readString();
        this.product_type = in.readString();
        this.uri = in.readString();
        this.agreement_name = in.readString();
    }

    public static final Parcelable.Creator<LargestDownload> CREATOR = new Parcelable.Creator<LargestDownload>() {
        @Override
        public LargestDownload createFromParcel(Parcel source) {
            return new LargestDownload(source);
        }

        @Override
        public LargestDownload[] newArray(int size) {
            return new LargestDownload[size];
        }
    };
}
