
package com.shahzeb.sc.model.Response;


import android.os.Parcel;
import android.os.Parcelable;

public class IstockLicense implements Parcelable {

    public String license_type;
    public int credits;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.license_type);
        dest.writeInt(this.credits);
    }

    public IstockLicense() {
    }

    protected IstockLicense(Parcel in) {
        this.license_type = in.readString();
        this.credits = in.readInt();
    }

    public static final Parcelable.Creator<IstockLicense> CREATOR = new Parcelable.Creator<IstockLicense>() {
        @Override
        public IstockLicense createFromParcel(Parcel source) {
            return new IstockLicense(source);
        }

        @Override
        public IstockLicense[] newArray(int size) {
            return new IstockLicense[size];
        }
    };
}
