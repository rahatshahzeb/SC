
package com.shahzeb.sc.model.Response;


import android.os.Parcel;
import android.os.Parcelable;

public class DisplaySize implements Parcelable {

    public boolean is_watermarked;
    public String name;
    public String uri;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.is_watermarked ? (byte) 1 : (byte) 0);
        dest.writeString(this.name);
        dest.writeString(this.uri);
    }

    public DisplaySize() {
    }

    protected DisplaySize(Parcel in) {
        this.is_watermarked = in.readByte() != 0;
        this.name = in.readString();
        this.uri = in.readString();
    }

    public static final Parcelable.Creator<DisplaySize> CREATOR = new Parcelable.Creator<DisplaySize>() {
        @Override
        public DisplaySize createFromParcel(Parcel source) {
            return new DisplaySize(source);
        }

        @Override
        public DisplaySize[] newArray(int size) {
            return new DisplaySize[size];
        }
    };
}
