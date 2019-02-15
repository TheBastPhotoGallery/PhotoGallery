package lv.photogallery.businesslogic.builders.picture;

import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.builders.folder.FolderBuilder;

public class PictureBuilder {
    private Long id;
    private String pictureURL;

    private PictureBuilder() {}

    public static PictureBuilder createPicture() {
        return new PictureBuilder();
    }

    public Picture build() {
        Picture picture = new Picture();
        picture.setId(id);
        picture.setPictureURL(pictureURL);
        return picture;
    }


    public PictureBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PictureBuilder withURL(String url) {
        this.pictureURL = url;
        return this;
    }


    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

}
