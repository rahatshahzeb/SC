
package com.shahzeb.sc.model.Response;


import android.os.Parcel;
import android.os.Parcelable;

public class MaxDimensions implements Parcelable {

    public int height;
    public int width;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.height);
        dest.writeInt(this.width);
    }

    public MaxDimensions() {
    }

    protected MaxDimensions(Parcel in) {
        this.height = in.readInt();
        this.width = in.readInt();
    }

    public static final Parcelable.Creator<MaxDimensions> CREATOR = new Parcelable.Creator<MaxDimensions>() {
        @Override
        public MaxDimensions createFromParcel(Parcel source) {
            return new MaxDimensions(source);
        }

        @Override
        public MaxDimensions[] newArray(int size) {
            return new MaxDimensions[size];
        }
    };
}
