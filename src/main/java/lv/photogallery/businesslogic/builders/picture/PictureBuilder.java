package lv.photogallery.businesslogic.builders.picture;

public class PictureBuilder {
    private Long id;
    private String picturePath;
    private Long folderId;
    private int checkBox;

    private PictureBuilder() {
    }

    public static PictureBuilder createPicture() {
        return new PictureBuilder();
    }

    public Picture build() {
        Picture picture = new Picture();
        picture.setId(id);
        picture.setPicturePath(picturePath);
        picture.setFolderId(folderId);
        picture.setCheckBox(checkBox);
        return picture;
    }

    public PictureBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PictureBuilder withPath(String url) {
        this.picturePath = url;
        return this;
    }

    public PictureBuilder withFolderId(Long id) {
        this.folderId = id;
        return this;
    }

    public PictureBuilder withCheckBox(int checkBox) {
        this.checkBox = checkBox;
        return this;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

}
