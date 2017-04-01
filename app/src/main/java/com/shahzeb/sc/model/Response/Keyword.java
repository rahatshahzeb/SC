
package com.shahzeb.sc.model.Response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Keyword implements Parcelable {

    public String keyword_id;
    public String text;
    public String type;
    public int relevance;
    public List<String> entity_uris = null;
    public List<String> entity_types = null;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.keyword_id);
        dest.writeString(this.text);
        dest.writeString(this.type);
        dest.writeInt(this.relevance);
        dest.writeStringList(this.entity_uris);
        dest.writeStringList(this.entity_types);
    }

    public Keyword() {
    }

    protected Keyword(Parcel in) {
        this.keyword_id = in.readString();
        this.text = in.readString();
        this.type = in.readString();
        this.relevance = in.readInt();
        this.entity_uris = in.createStringArrayList();
        this.entity_types = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Keyword> CREATOR = new Parcelable.Creator<Keyword>() {
        @Override
        public Keyword createFromParcel(Parcel source) {
            return new Keyword(source);
        }

        @Override
        public Keyword[] newArray(int size) {
            return new Keyword[size];
        }
    };
}
