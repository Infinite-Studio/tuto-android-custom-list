package fr.infinitestudio.customlist.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Color implements Parcelable{

	private Integer id;
	private String color;
	private String label;
	private boolean checked;
	
	public Color(){
		
	}
	
	/**
	 * Constructeur qui lit les champs de l'objet Parcel dans l'ordre dans lequel ils ont été écrits dans la fonction writeToParcel()
	 * @param pc
	 */
	public Color(Parcel pc){
		id = pc.readInt();
		color = pc.readString();
		label = pc.readString();
		checked = (pc.readInt() == 1);
	}
	
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel paramParcel, int paramInt) {
		paramParcel.writeInt(id);
		paramParcel.writeString(color);
		paramParcel.writeString(label);
		paramParcel.writeInt(checked ? 1:0 );
	}
	
	/**
	 * Champ statique utilisé pour régénérer l'objet individuellement ou sous forme de tableau
	 */
	public static final Parcelable.Creator<Color> CREATOR = new Parcelable.Creator<Color>() {
		public Color createFromParcel(Parcel pc) {
			return new Color(pc);
		}
		public Color[] newArray(int size) {
			return new Color[size];
		}
	};
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Color other = (Color) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
