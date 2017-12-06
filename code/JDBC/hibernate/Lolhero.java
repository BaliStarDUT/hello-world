package hibernate;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the lolheros database table.
 *
 */
@Entity
@Table(name="lolheros")
public class Lolhero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Lob
	private byte[] headpic;

	@Column(name="headpic_url")
	private String headpicUrl;

	@Column(name="name_cn")
	private String nameCn;

	@Column(name="name_en")
	private String nameEn;

	private String nickname;

	@Lob
	private byte[] sounds;

	@Column(name="sounds_url")
	private String soundsUrl;

	private String story;

	private String type;

	public Lolhero() {
	}

	public Lolhero(String nameCn, String nameEn, String nickname, String story, String type) {
		super();
		this.nameCn = nameCn;
		this.nameEn = nameEn;
		this.nickname = nickname;
		this.story = story;
		this.type = type;
	}

	public Lolhero(String nameCn, String nameEn, String nickname,  String story,
				   String type,String headpicUrl, String soundsUrl) {
		super();
		this.headpicUrl = headpicUrl;
		this.nameCn = nameCn;
		this.nameEn = nameEn;
		this.nickname = nickname;
		this.soundsUrl = soundsUrl;
		this.story = story;
		this.type = type;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getHeadpic() {
		return this.headpic;
	}

	public void setHeadpic(byte[] headpic) {
		this.headpic = headpic;
	}

	public String getHeadpicUrl() {
		return this.headpicUrl;
	}

	public void setHeadpicUrl(String headpicUrl) {
		this.headpicUrl = headpicUrl;
	}

	public String getNameCn() {
		return this.nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public byte[] getSounds() {
		return this.sounds;
	}

	public void setSounds(byte[] sounds) {
		this.sounds = sounds;
	}

	public String getSoundsUrl() {
		return this.soundsUrl;
	}

	public void setSoundsUrl(String soundsUrl) {
		this.soundsUrl = soundsUrl;
	}

	public String getStory() {
		return this.story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString(){
		return "Lolhero{"+
				nameCn+"}";
	}

}
