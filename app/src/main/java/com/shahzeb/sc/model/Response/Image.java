
package com.shahzeb.sc.model.Response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Image implements Parcelable {

    public AllowedUse allowed_use;
    public List<AlternativeId> alternative_ids = null;
    public String artist;
    public String asset_family;
    public boolean call_for_image;
    public String caption;
    public String collection_code;
    public int collection_id;
    public String collection_name;
    public String color_type;
    public String copyright;
    public String date_camera_shot;
    public String date_created;
    public List<DisplaySize> display_sizes = null;
    public List<String> editorial_segments = null;
    public List<Integer> event_ids = null;
    public String graphical_style;
    public String id;
    public List<Keyword> keywords = null;
    public List<LargestDownload> largest_downloads = null;
    public String license_model;
    public MaxDimensions max_dimensions;
    public String orientation;
    public List<String> people = null;
    public boolean prestige;
    public List<String> product_types = null;
    public int quality_rank;
    public List<ReferralDestination> referral_destinations = null;
    public String title;
    public String uri_oembed;
    public List<IstockLicense> istock_licenses = null;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.allowed_use, flags);
        dest.writeList(this.alternative_ids);
        dest.writeString(this.artist);
        dest.writeString(this.asset_family);
        dest.writeByte(this.call_for_image ? (byte) 1 : (byte) 0);
        dest.writeString(this.caption);
        dest.writeString(this.collection_code);
        dest.writeInt(this.collection_id);
        dest.writeString(this.collection_name);
        dest.writeString(this.color_type);
        dest.writeString(this.copyright);
        dest.writeString(this.date_camera_shot);
        dest.writeString(this.date_created);
        dest.writeList(this.display_sizes);
        dest.writeStringList(this.editorial_segments);
        dest.writeList(this.event_ids);
        dest.writeString(this.graphical_style);
        dest.writeString(this.id);
        dest.writeList(this.keywords);
        dest.writeList(this.largest_downloads);
        dest.writeString(this.license_model);
        dest.writeParcelable((Parcelable) this.max_dimensions, flags);
        dest.writeString(this.orientation);
        dest.writeStringList(this.people);
        dest.writeByte(this.prestige ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.product_types);
        dest.writeInt(this.quality_rank);
        dest.writeList(this.referral_destinations);
        dest.writeString(this.title);
        dest.writeString(this.uri_oembed);
        dest.writeList(this.istock_licenses);
    }

    public Image() {
    }

    protected Image(Parcel in) {
        this.allowed_use = in.readParcelable(AllowedUse.class.getClassLoader());
        this.alternative_ids = new ArrayList<AlternativeId>();
        in.readList(this.alternative_ids, AlternativeId.class.getClassLoader());
        this.artist = in.readString();
        this.asset_family = in.readString();
        this.call_for_image = in.readByte() != 0;
        this.caption = in.readString();
        this.collection_code = in.readString();
        this.collection_id = in.readInt();
        this.collection_name = in.readString();
        this.color_type = in.readString();
        this.copyright = in.readString();
        this.date_camera_shot = in.readString();
        this.date_created = in.readString();
        this.display_sizes = new ArrayList<DisplaySize>();
        in.readList(this.display_sizes, DisplaySize.class.getClassLoader());
        this.editorial_segments = in.createStringArrayList();
        this.event_ids = new ArrayList<Integer>();
        in.readList(this.event_ids, Integer.class.getClassLoader());
        this.graphical_style = in.readString();
        this.id = in.readString();
        this.keywords = new ArrayList<Keyword>();
        in.readList(this.keywords, Keyword.class.getClassLoader());
        this.largest_downloads = new ArrayList<LargestDownload>();
        in.readList(this.largest_downloads, LargestDownload.class.getClassLoader());
        this.license_model = in.readString();
        this.max_dimensions = in.readParcelable(MaxDimensions.class.getClassLoader());
        this.orientation = in.readString();
        this.people = in.createStringArrayList();
        this.prestige = in.readByte() != 0;
        this.product_types = in.createStringArrayList();
        this.quality_rank = in.readInt();
        this.referral_destinations = new ArrayList<ReferralDestination>();
        in.readList(this.referral_destinations, ReferralDestination.class.getClassLoader());
        this.title = in.readString();
        this.uri_oembed = in.readString();
        this.istock_licenses = new ArrayList<IstockLicense>();
        in.readList(this.istock_licenses, IstockLicense.class.getClassLoader());
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
