package net.james.dao;

public class LolHeros {
    private Integer id;

    private String nameCn;

    private String nameEn;

    private String nickname;

    private String type;

    private String story;

    private String headpicUrl;

    private String soundsUrl;

    private byte[] headpic;

    private byte[] sounds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn == null ? null : nameCn.trim();
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story == null ? null : story.trim();
    }

    public String getHeadpicUrl() {
        return headpicUrl;
    }

    public void setHeadpicUrl(String headpicUrl) {
        this.headpicUrl = headpicUrl == null ? null : headpicUrl.trim();
    }

    public String getSoundsUrl() {
        return soundsUrl;
    }

    public void setSoundsUrl(String soundsUrl) {
        this.soundsUrl = soundsUrl == null ? null : soundsUrl.trim();
    }

    public byte[] getHeadpic() {
        return headpic;
    }

    public void setHeadpic(byte[] headpic) {
        this.headpic = headpic;
    }

    public byte[] getSounds() {
        return sounds;
    }

    public void setSounds(byte[] sounds) {
        this.sounds = sounds;
    }
}