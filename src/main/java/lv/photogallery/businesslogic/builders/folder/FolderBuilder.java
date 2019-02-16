package lv.photogallery.businesslogic.builders.folder;


public class FolderBuilder {
    private Long id;
    private String files;
    private String folderName;
    private Integer usrId;

    private FolderBuilder() {}

    public static FolderBuilder createFolder() {
        return new FolderBuilder();
    }

    public Folder build() {
        Folder folder = new Folder();
        folder.setId(id);
        folder.setFolderPicture(files);
        folder.setFolderName(folderName);
        folder.setUsrId(usrId);
        return folder;
    }

    public FolderBuilder withFolderName(String folderName) {
        this.folderName = folderName;
        return this;
    }

    public FolderBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public FolderBuilder withFiles(String file) {
        this.files = file;
        return this;
    }
    public FolderBuilder withUsrId(Integer id) {
        this.usrId = id;
        return this;
    }

}
