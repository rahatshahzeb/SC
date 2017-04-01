
package com.shahzeb.sc.model.Response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SearchImageResponse implements Parcelable {

    public int result_count;
    public List<Image> images = null;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.result_count);
        dest.writeTypedList(this.images);
    }

    public SearchImageResponse() {
    }

    protected SearchImageResponse(Parcel in) {
        this.result_count = in.readInt();
        this.images = in.createTypedArrayList(Image.CREATOR);
    }

    public static final Parcelable.Creator<SearchImageResponse> CREATOR = new Parcelable.Creator<SearchImageResponse>() {
        @Override
        public SearchImageResponse createFromParcel(Parcel source) {
            return new SearchImageResponse(source);
        }

        @Override
        public SearchImageResponse[] newArray(int size) {
            return new SearchImageResponse[size];
        }
    };
}
