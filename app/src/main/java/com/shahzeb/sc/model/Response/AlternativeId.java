
package com.shahzeb.sc.model.Response;


import android.os.Parcel;
import android.os.Parcelable;

public class AlternativeId implements Parcelable {

    public String key;
    public String value;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeString(this.value);
    }

    public AlternativeId() {
    }

    protected AlternativeId(Parcel in) {
        this.key = in.readString();
        this.value = in.readString();
    }

    public static final Parcelable.Creator<AlternativeId> CREATOR = new Parcelable.Creator<AlternativeId>() {
        @Override
        public AlternativeId createFromParcel(Parcel source) {
            return new AlternativeId(source);
        }

        @Override
        public AlternativeId[] newArray(int size) {
            return new AlternativeId[size];
        }
    };
}
